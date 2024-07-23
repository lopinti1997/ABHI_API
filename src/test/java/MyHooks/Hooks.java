package MyHooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import readData.ReadData;

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
           
           Collection<String> tags=scenario.getSourceTagNames();    
           String testCaseTag= String.valueOf(tags.toArray()[0]);
           testCaseTag= testCaseTag.substring(1);
           
           ExtentReportUtil.logInfo("Data For ::: "+testCaseTag);
           
           if (testCaseTag.contains("TC_")) 
           {
               List<String[]> data = ReadData.forExtentReports("TestData");
               StringBuilder examples = new StringBuilder();
             
              List<String> rowdata= ReadData.fullRowData("TestData", testCaseTag);
             String ignoreResponceValue= ReadData.getCellData("TestData", testCaseTag, "Responce");
             String ignoreStatuscodeValue= ReadData.getCellData("TestData", testCaseTag, "Status Code");
              
               for(int i=0; i<data.size(); i++)
               {
               	for(int j=0; j<data.getFirst().length; j++)
               	{
               		String cell="";
               		if(i==0)
               		{
               	    cell=data.get(i)[j];
               	    if(!(cell.equalsIgnoreCase("Status Code")||cell.equalsIgnoreCase("Responce")))
               	    {
               		examples.append(cell).append("|");
               	    }
               		}
               	}
               	
               }
               examples.append("\n");
               
               for (String str : rowdata) {  
               	 if(!(str.equalsIgnoreCase(ignoreResponceValue)||str.equalsIgnoreCase(ignoreStatuscodeValue)))
            	    {
           		examples.append(str).append("|");
            	    }
   			}
                 
       	   ExtentReportUtil.logInfo(examples.toString());
           extent.flush();
           ExtentTestManager.unloadTest();   
           }
    }
}


