package utils;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WindowsUtils {
    public static void switchToWindow(WebDriver driver, int windowNumber) {
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> windowList = new ArrayList<>(windowHandles);
        driver.switchTo().window(windowList.get(windowNumber - 1));
    }

    public static void switchToLatestWindow(WebDriver driver) {
        switchToWindow(driver, driver.getWindowHandles().size());
    }

    public static void closeAllWindows(WebDriver driver) {
        int totalWindowsOnScreen = getWindowCount(driver);
        while (totalWindowsOnScreen != 1) {
            switchToWindow(driver, totalWindowsOnScreen);
            driver.close();
            totalWindowsOnScreen--;
        }
        switchToWindow(driver,1);
    }

    private static int getWindowCount(WebDriver driver) {
        Set<String> windowHandles = driver.getWindowHandles();
        return windowHandles.size();
    }
}
