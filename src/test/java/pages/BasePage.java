package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait; // The Explicit Wait Engine

    // CONSTRUCTOR
    public BasePage(WebDriver driver) {
        this.driver = driver;
        // We initialize the explicit wait to 10 seconds.
        // It will NOT wait 10 seconds every time. It just means "wait UP TO 10 seconds, but proceed the millisecond the element is ready."
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // REUSABLE ACTION: Type Text
    protected void typeText(By locator, String text) {
        // Explicitly wait until the element is physically visible on the screen
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    // REUSABLE ACTION: Click
    protected void clickElement(By locator) {
        // Explicitly wait until the element is physically clickable (not covered by a popup)
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
}