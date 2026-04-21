package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    // Initializes the WebDriver, opens the browser, and navigates to the URL
    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        // Initialize the Page Object
        loginPage = new LoginPage(driver);
    }

    // Closes the browser and releases resources
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Verifies that entering valid credentials redirects the user to the inventory page
    @Test
    void testSuccessfulLogin() throws InterruptedException {
        // Perform login action
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(2000); // Visual pause

        // Verifies that the URL contains "inventory" to confirm successful redirection
        String urlActual = driver.getCurrentUrl();
        assertTrue(urlActual.contains("inventory"), "The URL is not the expected one after login");
    }

    // Verifies that entering invalid credentials displays the expected error message
    @Test
    void testFailedLogin() throws InterruptedException {
        // Attempt login with incorrect password
        loginPage.login("standard_user", "pass_incorrecto");
        Thread.sleep(2000); // Visual pause

        // Retrieve the error message and verifies its content
        String message = loginPage.getErrorMessage();
        assertTrue(message.contains("Epic sadface"), "The error message is not the expected one");
    }



}
