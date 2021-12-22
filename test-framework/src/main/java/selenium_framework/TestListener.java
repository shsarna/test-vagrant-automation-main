package selenium_framework;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AllureUtils;

@Slf4j
public class TestListener extends SeleniumBaseFramework implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (checkTestType(result)) {
            Object testClass = result.getInstance();
            WebDriver driver = ((SeleniumBaseFramework) testClass).driver;
            AllureUtils.saveScreenshot(driver);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (checkTestType(result)) {
            Object testClass = result.getInstance();
            WebDriver driver = ((SeleniumBaseFramework) testClass).driver;
            AllureUtils.saveScreenshot(driver);
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }


    private boolean checkTestType(ITestResult result) {
        String[] nameArray = result.getTestClass().getName().split("\\.");
        className = nameArray[nameArray.length - 1];
        if (className.contains("BackendTest")) return false;
        else return true;
    }
}
