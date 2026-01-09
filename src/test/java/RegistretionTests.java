import POM.HomePage;
import POM.LoginPage;
import POM.RegistrationPage;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static POM.EnvConfig.BASE_URL;
import static POM.EnvConfig.URL_LOGIN;

import users.Creds;
import users.Users;
import users.UsersClient;

public class RegistretionTests {

    private WebDriver driver;
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private String accessToken;
    private UsersClient usersClient;

    @RegisterExtension
    static DriverExtension extension = new DriverExtension();

    @BeforeEach
    public void starUp() {
        driver = extension.getDriver();
        driver.get(BASE_URL);
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        usersClient = new UsersClient();
    }

    @AfterEach
    public void dropUser() {
        if (accessToken != null) {
            usersClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegistration() {
        homePage.clickLogin();
        loginPage.clickRegisterButton();
        Users user = Users.randomUser();
        registrationPage.register(user);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("https://stellarburgers.education-services.ru/login"));

        Assertions.assertTrue(driver.getCurrentUrl().contains(URL_LOGIN));

        //для удаления
        var creds = Creds.getCreds(user);
        ValidatableResponse loginResponse = usersClient.loginCourier(creds);
        accessToken = usersClient.checkLogin(loginResponse, user);
    }

    @Test
    @DisplayName("Ошибка регистрации с коротким паролем")
    public void registrationWithShortPassword() {
        homePage.clickLogin();
        loginPage.clickRegisterButton();
        registrationPage.registerWithShortPassword();

        Assertions.assertEquals("Минимальный пароль — шесть символов", registrationPage.getErrorMessage());
    }
}

