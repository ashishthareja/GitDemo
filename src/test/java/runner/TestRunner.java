package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
		".\\src\\test\\resources\\Features",
		}, glue = {
				"stepDefinitions" }, monochrome = true, dryRun = false, plugin = { "pretty",
						"json:target/cucumber-reports/reports.json", "junit:target/cucumber-reports/Cucumber.xml",
						"html:target/cucumber-reports/reports.html", "html:test-output/index.html",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
		publish = true)
public class TestRunner {

}