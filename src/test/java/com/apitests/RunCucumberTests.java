package com.apitests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "src/test/java/com/apitests/scenarios" },
        tags = "@ApiTests",
        plugin = { "pretty", "html:target/cucumber-report.html" },
        monochrome = true
)
public class RunCucumberTests {

}
