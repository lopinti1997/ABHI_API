package extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	    private static ExtentReports extent;
	    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();


		public static synchronized ExtentReports getInstance() {
	        if (extent == null) {
	        	  extent = new ExtentReports();
	              com.aventstack.extentreports.reporter.ExtentSparkReporter spark = new com.aventstack.extentreports.reporter.ExtentSparkReporter("test-output/ExtentReports/extent.html");
	              extent.attachReporter(spark);
	        }
	        return extent;
	    }
		
	    public static synchronized ExtentTest getTest() {
	        return extentTest.get();
	    }

	    public static synchronized void setTest(ExtentTest test) {
	        extentTest.set(test);
	    }

	    public static synchronized void endTest() {
	        extent.flush();
	    }
	

}
