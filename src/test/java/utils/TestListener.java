package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import base.BaseTest;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

// 'implements ITestListener' is a built-in TestNG feature that lets us spy on the tests!
public class TestListener implements ITestListener {

    private final ExtentReports extent = ReportManager.getInstance();
    // ThreadLocal ensures that if we run tests in parallel later, the logs don't get mixed up
    private final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        // When a test starts, create a new row in the report
        // result.getParameters() will automatically grab "standard_user", "problem_user", etc!
        String testName = result.getMethod().getMethodName();
        if (result.getParameters().length > 0) {
            testName = testName + " [" + result.getParameters()[0] + "]";
        }

        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Executed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed");
        test.get().log(Status.FAIL, result.getThrowable());

        try {
            // Check if the class that failed actually extends our UI BaseTest engine
            Object testClass = result.getInstance();
            if (testClass instanceof BaseTest) {
                BaseTest failedTestClass = (BaseTest) testClass;
                WebDriver driver = failedTestClass.getDriver();

                TakesScreenshot ts = (TakesScreenshot) driver;
                String base64Screenshot = ts.getScreenshotAs(OutputType.BASE64);
                test.get().addScreenCaptureFromBase64String(base64Screenshot);
            } else {
                // If it was an API test (or the Runner), just log a message instead of taking a picture
                test.get().info("API Execution Failed - No UI screenshot available.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        // Crucial: This pushes all the data we collected into the physical HTML file
        extent.flush();
    }
}