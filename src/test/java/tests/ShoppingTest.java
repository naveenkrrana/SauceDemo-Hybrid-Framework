package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader; // Import our new utility

public class ShoppingTest extends BaseTest {

    @Test
    public void testAddBackpackToCart() {
        // 1. Set up the initial state using the ConfigReader!
        getDriver().get(ConfigReader.getProperty("url"));

        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());

        // 2. Execute the user journey using the ConfigReader!
        loginPage.loginToApplication(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        inventoryPage.addBackpackToCart();

        // 3. Extract the data
        String itemsInCart = inventoryPage.getCartItemCount();
        System.out.println("Items currently in cart: " + itemsInCart);

        // We are expecting 2 items, but the automation will only add 1. This will cause a crash.
        Assert.assertEquals(itemsInCart, "1", "Intentional Failure: The cart did not update correctly!");
    }
}