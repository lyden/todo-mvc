package org.davidpadbury.modernweb.cucumber;

import org.davidpadbury.modernweb.cucumber.config.CucumberConfig;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = { CucumberConfig.class })
public class SpringDependentParticipant {}
