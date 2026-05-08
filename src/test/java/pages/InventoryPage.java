package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions; // Cleanly imported here!

public class InventoryPage extends BasePage {

    // 1. LOCATORS
    private final By backpackAddToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");

    // 2. CONSTRUCTOR
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    // 3. ACTIONS
    public void addBackpackToCart() {
        clickElement(backpackAddToCartButton);
    }

    public void clickCartIcon() {
        clickElement(cartIcon);
    }

    public String getCartItemCount() {
        // Look how much cleaner this line is now!
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
    }
}