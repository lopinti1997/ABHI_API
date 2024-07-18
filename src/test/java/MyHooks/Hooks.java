package MyHooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.Collection;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import extentReports.ExtentManager;
import extentReports.ExtentReportUtil;
import extentReports.ExtentTestManager;

public class Hooks {

    public ExtentReports extent = ExtentManager.getInstance(); // Assuming ExtentManager is initialized similarly
    public ExtentTest extentTest=null;
    public ExtentTest extentTest1=null;

    public Scenario scenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
        extentTest = ExtentReportUtil.createTest(scenario.getName());
        ExtentTestManager.setTest(extentTest);
        ExtentTestManager.getTest().info("Scenario::: " + scenario.getName() + "---> Execution started.");
               
    }

    @After
    public void afterScenario() {
    	   String scenarioName = scenario.getName();
           ExtentTestManager.getTest().info("Scenario::: " + scenarioName + "---> Successfully Execution completed.");
           extent.flush();
           ExtentTestManager.unloadTest();   
    }
}


