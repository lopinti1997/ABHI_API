package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

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
public class TestNgRunner extends AbstractTestNGCucumberTests {

	
}
