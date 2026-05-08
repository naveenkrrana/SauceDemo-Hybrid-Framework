package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // 1. Tell it where to save the file
            String reportPath = System.getProperty("user.dir") + "/target/ExtentReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            // 2. Configure the look of the report
            spark.config().setDocumentTitle("SauceDemo Automation Report");
            spark.config().setReportName("UI E2E Test Results");

            // 3. Attach the configuration to the main engine
            extent = new ExtentReports();
            extent.attachReporter(spark);

            // 4. Add some metadata for the dashboard
            extent.setSystemInfo("Environment", "QA Sandbox");
            extent.setSystemInfo("Tester", "Naveen Kumar Rana");
        }
        return extent;
    }
}