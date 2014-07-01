package org.davidpadbury.modernweb.cucumber.hooks;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.davidpadbury.modernweb.cucumber.SpringDependentParticipant;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;

public class WebDriverHooks extends SpringDependentParticipant {
    private static final String BROWSER_LOG = "browser";

    @Autowired
    private WebDriver webDriver;

    @After
    public void afterScenario(Scenario scenario) {
        takeScreenshot(scenario);
        recordBrowserLog(scenario);

        webDriver.quit();
    }

    private void recordBrowserLog(final Scenario scenario) {
        final LogEntries browserLogs = webDriver.manage().logs().get(BROWSER_LOG);
        final Iterable<String> textEntries = Iterables.transform(browserLogs.getAll(), ToStringSelector.INSTANCE);
        final String browserLog = Joiner.on(System.lineSeparator()).join(textEntries);
        final String output = String.format("Browser:%n%s", browserLog);

        scenario.write(output);
    }

    private void takeScreenshot(final Scenario scenario) {
        if (webDriver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) webDriver;

            final byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, MimeTypeUtils.IMAGE_PNG_VALUE);
        }
    }

    private final static class ToStringSelector implements Function<Object, String> {
        public static final ToStringSelector INSTANCE = new ToStringSelector();

        @Override
        public String apply(final Object input) {
            return input != null ? input.toString() : "";
        }
    }
}
