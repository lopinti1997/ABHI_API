package extentReports;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class ExtentTestListener implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        ExtentReportUtil.logPass("Test Passed: " + context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        ExtentReportUtil.logFail("Test Failed: " + context.getDisplayName());
        ExtentReportUtil.logFail("Failure details: " + cause.getMessage());
    }

    public void testSkipped(ExtensionContext context, Throwable cause) {
        ExtentReportUtil.logSkip("Test Skipped: " + context.getDisplayName());
    }

    public void afterAll(ExtensionContext context) {
        ExtentReportUtil.flushReports();
    }
}


