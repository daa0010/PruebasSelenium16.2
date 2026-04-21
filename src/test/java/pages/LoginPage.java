package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By userField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUser(String user) {
        driver.findElement(userField).clear();
        driver.findElement(userField).sendKeys(user);
    }

    public void setPass(String pass) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void login(String user, String pass) {
        setUser(user);
        setPass(pass);
        clickLogin();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

}
