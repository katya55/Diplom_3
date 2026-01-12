import POM.*;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import users.Creds;
import users.Users;
import users.UsersClient;

import static POM.EnvConfig.BASE_URL;

public class EnterTests {


    private WebDriver driver;
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private PersonalAccountPage personalAccountPage;
    private ForgotPasswordPage forgotPasswordPage;
    private UsersClient usersClient;
    HomePageUpper homePageUpper;
    ConstructorPage constructorPage;
    private String accessToken;
    private Creds creds;

    @RegisterExtension
    static DriverExtension extension = new DriverExtension();

    @BeforeEach
    public void starUp() {
        var driver = extension.getDriver();
        driver.get(BASE_URL);

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        homePageUpper = new HomePageUpper(driver);
        constructorPage = new ConstructorPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        usersClient = new UsersClient();

        Users user = Users.randomUser();
        ValidatableResponse response = usersClient.createUser(user);
        accessToken = usersClient.checkCreated(response);
        creds = Creds.getCreds(user);
    }

    @AfterEach
    public void dropUser() {
        if (accessToken != null) {
            usersClient.deleteUser(accessToken);
            System.out.println("Пользователь удален");
        }
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    void enterButtonLoginButton() {
        homePage.clickLogin();
        loginPage.logIn(creds);
        Assertions.assertTrue(personalAccountPage.isOrderButtonVisible(), "Логин не выполнен");
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void enterButtonPersonalAccountPage() {
        homePageUpper.clickPersonalCabinet();
        loginPage.logIn(creds);

        Assertions.assertTrue(personalAccountPage.isOrderButtonVisible(), "Логин не выполнен");
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void enterRegistrationPage() {
        homePage.clickLogin();
        loginPage.clickRegisterButton();
        registrationPage.clickButtonEnter();
        loginPage.logIn(creds);

        Assertions.assertTrue(personalAccountPage.isOrderButtonVisible(), "Логин не выполнен");
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void enterForgotPasswordPPage() {
        homePage.clickLogin();
        loginPage.clickForgotPassword();
        forgotPasswordPage.clickLoginButton();
        loginPage.logIn(creds);

        Assertions.assertTrue(personalAccountPage.isOrderButtonVisible(), "Логин не выполнен");
    }
}
