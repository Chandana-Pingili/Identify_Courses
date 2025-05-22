package Utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExtentReportListener implements ITestListener {

    private Map<String, ExtentReports> extentReportsMap = new HashMap<>();
    private ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
    

    private ExtentReports getExtentInstance(ITestContext context) {
        String testName = context.getCurrentXmlTest().getName();
        if (!extentReportsMap.containsKey(testName)) {
            String reportFilename = "Report_" + testName + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + reportFilename);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle(testName + " Test Report");
            sparkReporter.config().setReportName(testName + " Test Results");
            ExtentReports extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extentReportsMap.put(testName, extent);
        }
        return extentReportsMap.get(testName);
    }

    private String captureScreenshot(String testName) {
        if (DriverSetup.driver == null) {
            System.err.println("WebDriver instance is null, cannot capture screenshot.");
            return null;
        }
        TakesScreenshot ts = (TakesScreenshot) DriverSetup.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
            return destination;
        } catch (IOException e) {
            System.err.println("Error while capturing screenshot: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void onStart(ITestContext context) {
        ExtentReports extent = getExtentInstance(context);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReports extent = getExtentInstance(result.getTestContext());
        ExtentTest test = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        extentTestThreadLocal.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTestThreadLocal.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentTestThreadLocal.get();
        test.log(Status.FAIL, result.getName());
        test.log(Status.FAIL, result.getThrowable());
        String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
        if (screenshotPath != null) {
            test.addScreenCaptureFromPath(screenshotPath);
        } else {
            test.log(Status.INFO, "Failed to capture screenshot.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTestThreadLocal.get().log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReports extent = getExtentInstance(context);
        if (extent != null) {
            extent.flush();
        }
    }
}
