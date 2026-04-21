package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // Locators for login elements
    private By userField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    // WebDriver constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to clear and set the username field
    public void setUser(String user) {
        driver.findElement(userField).clear();
        driver.findElement(userField).sendKeys(user);
    }

    // Method to clear and set the password field
    public void setPass(String pass) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(pass);
    }

    // Method to click the login button
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // Method to do a full login
    public void login(String user, String pass) {
        setUser(user);
        setPass(pass);
        clickLogin();
    }

    // Method to retrieve the text from the error message
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

}
