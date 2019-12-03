package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

public class DriverFactory {
    private static ThreadLocal<WebDriverThread> driverThread;

    @BeforeSuite
    public static void instantiateDriverObject() {
        driverThread = new ThreadLocal<WebDriverThread>() {
            @Override
            protected WebDriverThread initialValue(){
                return new WebDriverThread();
            }
        };
    }

    public static WebDriver getDriver() throws Exception {
        return driverThread.get().getWebDriver();
    }

    @AfterMethod
    public static void quitDriver() {
        driverThread.get().quitDriver();
    }
}
