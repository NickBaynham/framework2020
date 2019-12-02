package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BasicTest {

    @Test
    public void googleCheeseExample() {
        googleExampleThatSearchesFor("Cheese!");
    }

    @Test
    public void googleMilkExample() {
        googleExampleThatSearchesFor("Milk!");
    }

    private void googleExampleThatSearchesFor(final String search) {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver_v78\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        WebElement fieldSearch = driver.findElement(By.name("q"));
        fieldSearch.clear();
        fieldSearch.sendKeys(search);
        System.out.println("The Page Title is: " + driver.getTitle());
        fieldSearch.submit();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driverObject) {
                return driverObject.getTitle().toLowerCase().startsWith(search.toLowerCase());
            }
        });

        System.out.println("Page title is: " + driver.getTitle());
        driver.quit();
    }
}
