package org.davidpadbury.modernweb.cucumber.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:properties/cukes.properties")
public class CucumberConfig {

    private static final String PHANTOM_JS_PATH = "node_modules/phantomjs/bin/phantomjs";

    @Autowired
    private Environment environment;

    @Bean(name = "webapp.url")
    public String getWebAppUrl() {
        return environment.getProperty("webapp.url");
    }

    @Bean
    public WebDriver getWebDriver() {
        System.setProperty(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOM_JS_PATH);

        final WebDriver webDriver = new PhantomJSDriver();

        return webDriver;
    }
}
