package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.InventoryPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryTest {
    // Attributes
    private WebDriver driver;
    private InventoryPage inventoryPage;
    private LoginPage loginPage;

    // Initializes the driver and performs a mandatory login as a pre-condition
    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // Initialize Page Objects
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

        // Pre-condition: User must be logged in to access the products page
        loginPage.login("standard_user", "secret_sauce");
    }

    // Closes the browser and releases resources
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Validates that clicking 'Add to cart' updates the badge to '1'
    @Test
    void testAddOneProductToCart() throws InterruptedException {
        inventoryPage.addBackpack();
        Thread.sleep(2000); // Visual pause

        // Get the badge text and assert it matches '1'
        String cartCount = inventoryPage.getCartNumber();
        assertEquals("1", cartCount, "The cart badge should display '1' after adding one item.");
    }

    // Validates that adding multiple items correctly increments the cart counter
    @Test
    void testAddTwoProductsToCart() throws InterruptedException {
        // Calls the Page Object methods to add the products to the cart
        inventoryPage.addBackpack();
        inventoryPage.addBikeLight();
        Thread.sleep(2000); // Visual pause

        // Verifies the badge reflects the two added products
        String cartCount = inventoryPage.getCartNumber();
        assertEquals("2", cartCount, "The cart badge should display '2' after adding two items.");
    }


    // Validates that the 'Add to cart' button toggles to 'Remove' after being clicked
    @Test
    void testButtonChangesAfterAddingProduct() throws InterruptedException {
        // Add item to trigger button change
        inventoryPage.addBackpack();
        Thread.sleep(2000); // Visual pause

        // Assert that the 'Remove' button is now visible
        boolean isRemoveVisible = inventoryPage.isRemoveBackpackDisplayed();
        assertTrue(isRemoveVisible, "The 'Remove' button should be visible after adding the product.");
    }
}
