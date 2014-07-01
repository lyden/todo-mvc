package org.davidpadbury.modernweb.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.davidpadbury.modernweb.cucumber.config.CucumberConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        format = {
                "pretty",
                "html:target/cucumber",
                "json:target/cucumber.json"
        }
)
public class RunCukesIT {}