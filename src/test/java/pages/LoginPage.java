package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Notice the 'extends BasePage'
public class LoginPage extends BasePage {

    // 1. LOCATORS
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");

    // 2. CONSTRUCTOR
    public LoginPage(WebDriver driver) {
        super(driver); // This passes the driver up to the BasePage constructor
    }

    // 3. ACTIONS
    public void loginToApplication(String username, String password) {
        // Look how clean this is! We use our explicit wait methods from the BasePage.
        typeText(usernameField, username);
        typeText(passwordField, password);
        clickElement(loginButton);
    }
}