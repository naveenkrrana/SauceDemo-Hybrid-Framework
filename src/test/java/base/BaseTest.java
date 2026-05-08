package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.chrome.ChromeOptions; // Add this import
import utils.ConfigReader;

public class BaseTest {

    // 1. We wrap the driver in ThreadLocal. It is now completely Thread-Safe!
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);

        // 1. Initialize the Chrome configuration object
        ChromeOptions options = new ChromeOptions();

        // 2. Read the properties file. If headless=true, inject the argument
        if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
            options.addArguments("--headless=new"); // "new" triggers Chrome's modern headless engine
            options.addArguments("--window-size=1920,1080"); // Explicitly set DOM viewport size since there is no physical monitor
        }

        // 3. Pass the configuration into the ChromeDriver constructor
        driver.set(new ChromeDriver(options));

        getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            // 4. CRITICAL: Clear the thread memory so we don't cause memory leaks!
            driver.remove();
        }
    }

    // 5. This getter is now the only way our tests can access the browser
    public WebDriver getDriver() {
        return driver.get();
    }
}