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

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testSuccessfulLogin() throws InterruptedException {
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(2000);

        String urlActual = driver.getCurrentUrl();
        assertTrue(urlActual.contains("inventory"), "The URL no es la esperada tras el login");
    }

    @Test
    void testFailedLogin() throws InterruptedException {
        loginPage.login("standard_user", "pass_incorrecto");
        Thread.sleep(2000);

        String mensaje = loginPage.getErrorMessage();
        assertTrue(mensaje.contains("Epic sadface"), "El mensaje de error no es el esperado");
    }



}
