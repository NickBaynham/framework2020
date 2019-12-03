package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.config.DriverType;
import java.net.MalformedURLException;

public class WebDriverThread {
    private WebDriver webDriver;
    private DriverType driverType;

    private final DriverType defaultDriverType = DriverType.CHROME;
    private final String browser = "CHROME";
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");

    public WebDriver getWebDriver() throws Exception {
        if (webDriver == null) {
            driverType = determineEffectiveDriverType();
            DesiredCapabilities capabilities = driverType.getDesiredCapabilities();
            instantiateWebDriver(capabilities);
        }
        return webDriver;
    }

    public void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    private DriverType determineEffectiveDriverType() {
        DriverType driverType = defaultDriverType;
        try {
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException e) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException e) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        return driverType;
    }

    private void instantiateWebDriver(DesiredCapabilities capabilities) throws MalformedURLException {

        System.out.println(" ");
        System.out.println("Current Operating System: " + operatingSystem);
        System.out.println("Current Architecture: " + systemArchitecture);
        System.out.println("Current Browser Selection: " + driverType);
        System.out.println(" ");
        webDriver = driverType.getWebDriverObject(capabilities);
    }
}
