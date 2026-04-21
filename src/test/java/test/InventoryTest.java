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

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

        loginPage.login("standard_user", "secret_sauce");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testAddOneProductToCart() throws InterruptedException {
        inventoryPage.addBackpack();
        Thread.sleep(2000); // Visual pause

        String cartCount = inventoryPage.getCartNumber();
        assertEquals("1", cartCount, "The cart badge should display '1' after adding one item.");
    }

    @Test
    void testAddTwoProductsToCart() throws InterruptedException {
        inventoryPage.addBackpack();
        inventoryPage.addBikeLight();
        Thread.sleep(2000); // Visual pause

        String cartCount = inventoryPage.getCartNumber();
        assertEquals("2", cartCount, "The cart badge should display '2' after adding two items.");
    }

    @Test
    void testButtonChangesAfterAddingProduct() throws InterruptedException {
        // Initially, the 'Remove' button should not be displayed or we haven't clicked yet
        inventoryPage.addBackpack();
        Thread.sleep(2000); // Visual pause

        boolean isRemoveVisible = inventoryPage.isRemoveBackpackDisplayed();
        assertTrue(isRemoveVisible, "The 'Remove' button should be visible after adding the product.");
    }
}
