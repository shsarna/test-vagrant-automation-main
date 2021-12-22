package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureUtils {
    public static void updateTestCaseDetails(String testCaseName, String testCaseDescription) {
        AllureLifecycle lifecycle = Allure.getLifecycle();

        lifecycle.updateTestCase(testResult -> {
            testResult.setName(testCaseName);
            testResult.setDescription(testCaseDescription);
        });
    }

    @Step("Status for above assertion: {1} {2}")
    public static void stepScreenshot(WebDriver driver, String status, String description) {
        saveScreenshot(driver);
    }

    @Attachment(value = "Click here for screenshot : ", type = "image/png")
    public static byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
