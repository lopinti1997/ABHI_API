package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
		 features="src/test/java/features",
		 glue= {"stepDefinations","MyHooks"},
			
		 plugin = { "pretty", "html:target/cucumberReports/cucumber-reports.html", "json:target/jsonReports/cucumber-report.json",
				 	"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		 	}, 
		 monochrome = true 
//		,dryRun=true
		 ,tags= "@ALL"
//		,tags= "@TC_008"
		)
public class JunitRunner {

	
}
