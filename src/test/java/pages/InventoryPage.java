package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    // Attributes
    private WebDriver driver;

    private By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private By addBikeLightButton = By.id("add-to-cart-sauce-labs-bike-light");
    private By cartNumber = By.className("shopping_cart_badge");
    private By removeBackpack = By.id("remove-sauce-labs-backpack");
    private By removeBikeLight = By.id("remove-sauce-labs-bike-light");

    // WebDriver constructor
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to add products to cart
    public void addCart(By productLoc) {
        driver.findElement(productLoc).click();
    }

    // Method to access backpack locator from InventoryTest class
    public void addBackpack() {
        addCart(addBackpackButton);
    }

    // Method to access bike light locator from InventoryTest class
    public void addBikeLight() {
        addCart(addBikeLightButton);
    }

    // Method to get products number from the chart
    public String getCartNumber() {
        return driver.findElement(cartNumber).getText();
    }

    // Method to check if the remove button for the backpack
    public boolean isRemoveBackpackDisplayed() {
        return driver.findElement(removeBackpack).isDisplayed();
    }
}
