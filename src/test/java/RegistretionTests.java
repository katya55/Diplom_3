import POM.HomePage;
import POM.LoginPage;
import POM.RegistrationPage;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static POM.EnvConfig.BASE_URL;
import static POM.EnvConfig.URL_LOGIN;

public class RegistretionTests {

    private WebDriver driver;
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;

    @RegisterExtension
    static DriverExtension extension = new DriverExtension();

    @BeforeEach
    public void starUp() {
        driver = extension.getDriver();
        driver.get(BASE_URL);
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegistration() {
        homePage.clickLogin();
        loginPage.clickRegisterButton();
        registrationPage.register(
                "Иван",
                "ivan" + System.currentTimeMillis() + "@mail.com",
                "password123"
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("https://stellarburgers.education-services.ru/login"));

        Assertions.assertTrue(driver.getCurrentUrl().contains(URL_LOGIN));
    }

    @Test
    @DisplayName("Ошибка регистрации с коротким паролем")
    public void registrationWithShortPassword() {
        homePage.clickLogin();
        loginPage.clickRegisterButton();
        registrationPage.register("Иван", "ivan" + System.currentTimeMillis() + "@mail.com", "123");

        Assertions.assertEquals("Минимальный пароль — шесть символов", registrationPage.getErrorMessage());
    }


}

