package selenium_framework;

import enums.Browsers;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static selenium_framework.Constants.CHROMEDRIVER_PATH;

public class WebDriverFactory {
    public static WebDriver getDriver(Browsers browser, Properties properties) {
        WebDriver driver = null;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            File f = new File(CHROMEDRIVER_PATH);
            System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
        }

        //support for more browsers can easily be added by adding the browser name in the enum
        switch (browser) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.setAcceptInsecureCerts(true);
                options.addArguments("--disable-translate");
                options.setPageLoadStrategy(PageLoadStrategy.NONE);
                options.setExperimentalOption("w3c", true);
                driver = new ChromeDriver(options);
                break;

            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true);
                driver = new FirefoxDriver(firefoxOptions);
                break;
        }
        setTimeout(driver, properties);
        return driver;
    }

    private static void setTimeout(WebDriver driver, Properties properties) {
        try {
            driver.manage().timeouts().implicitlyWait(Long.parseLong(
                    properties.getProperty("webdriver.implicit.wait")), TimeUnit.SECONDS);
        } catch (Exception e) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        try {
            driver.manage().timeouts().setScriptTimeout(Long.parseLong(
                    properties.getProperty("webdriver.default.wait")), TimeUnit.SECONDS);
        } catch (Exception e) {
            driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        }
        try {
            driver.manage().timeouts().pageLoadTimeout(Long.parseLong(
                    properties.getProperty("webdriver.pageload.wait")), TimeUnit.SECONDS);
        } catch (Exception e) {
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        }

    }
}
