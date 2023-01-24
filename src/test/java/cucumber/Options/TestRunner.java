package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features",
		glue = "stepDefinations",
		plugin = "json:target/jsonReports/cucumber-report.json" // json reporting will be present in the cucumber-report.json file
		//tags = "@DeletePlace") // only scenarios tagged as AddPlace will run
		)
public class TestRunner {

}
