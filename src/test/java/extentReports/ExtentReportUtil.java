package extentReports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentReportUtil {

			private static ExtentReports extent=ExtentManager.getInstance();
	        private static ExtentTest test=null;

	    /**
	     * Initializes ExtentReports with configuration.
	     * @param reportFilePath Path where the Extent report will be saved.
	     */
	    public static void initializeExtentReports(String reportFilePath) {
	        extent = new ExtentReports();
//            com.aventstack.extentreports.reporter.ExtentSparkReporter spark = new com.aventstack.extentreports.reporter.ExtentSparkReporter("test-output/ExtentReports/extent.html");
              com.aventstack.extentreports.reporter.ExtentSparkReporter spark = new com.aventstack.extentreports.reporter.ExtentSparkReporter("test-output/SparkReport/Index.html");

	        extent.attachReporter(spark);
	    }

	    /**
	     * Creates a new test in the ExtentReports.
	     * @param testName Name of the test.
	     * @return 
	     */
	    public static ExtentTest createTest(String testName) {
	        test = extent.createTest(testName);
			return test;
	    }

	    /**
	     * Logs a pass status with details to the current test.
	     * @param details Details of the pass status.
	     */
	    public static void logPass(String details) {
	        test.log(Status.PASS, details);
	    }

	    /**
	     * Logs a fail status with details to the current test.
	     * @param details Details of the fail status.
	     */
	    public static void logFail(String details) {
	        test.log(Status.FAIL, details);
	    }

	    /**
	     * Logs an info status with details to the current test.
	     * @param details Details of the info status.
	     */
	    public static void logInfo(String details) {
	        test.log(Status.INFO, details);
	    }

	    /**
	     * Logs a skip status with details to the current test.
	     * @param details Details of the skip status.
	     */
	    public static void logSkip(String details) {
	        test.log(Status.SKIP, details);
	    }

	    /**
	     * Adds a screenshot to the current test.
	     * @param screenshotPath Path of the screenshot file.
	     */
	    public static void addScreenshot(String screenshotPath) {
	        test.addScreenCaptureFromPath(screenshotPath);
	    }

	    /**
	     * Flushes the ExtentReports instance to write all logs to the report.
	     */
	    public static void flushReports() {
	        extent.flush();
	    }
	}

