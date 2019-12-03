package tests.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import java.util.Collections;

public enum DriverType implements DriverSetup {

    FIREFOX {
        @Override
        public DesiredCapabilities getDesiredCapabilities() {
            return DesiredCapabilities.firefox();
        }

        @Override
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            return new FirefoxDriver(firefoxOptions.merge(capabilities));
        }
    },
    CHROME {
        @Override
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Collections.singletonList("--no-default-browser-check"));
            return capabilities;
        }

        @Override
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver_v78\\chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setHeadless(true);
            chromeOptions.addArguments("start-maximized");
            return new ChromeDriver(chromeOptions.merge(capabilities));
        }
    },
    EDGE {
        @Override
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new EdgeDriver(new EdgeOptions().merge(capabilities));
        }

        @Override
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.edge();
            capabilities.setAcceptInsecureCerts(true);
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
            capabilities.setCapability("requireWindowFocus", true);
            return capabilities;
        }
    },
    SAFARI {
        @Override
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new SafariDriver(new SafariOptions().merge(capabilities));
        }

        @Override
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            capabilities.setCapability("safari.cleanSession", true);
            return capabilities;
        }
    }
}
