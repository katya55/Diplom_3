import POM.*;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

import users.Creds;
import users.Users;
import users.UsersClient;

import static POM.EnvConfig.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PersonalAccountPageTest {

    private WebDriver driver;
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private PersonalAccountPage personalAccountPage;
    private ForgotPasswordPage forgotPasswordPage;
    private HomePageUpper homePageUpper;
    private ConstructorPage constructor;
    private UsersClient usersClient = new UsersClient();
    private String accessToken;

    @RegisterExtension
    static DriverExtension extension = new DriverExtension();

    @BeforeEach
    public void starUp() {
        driver = extension.getDriver();
        driver.get(BASE_URL);

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        homePageUpper = new HomePageUpper(driver);
        constructor = new ConstructorPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        usersClient = new UsersClient();
    }

    @AfterEach
    public void dropUser() {
        if (accessToken != null) {
            usersClient.deleteUser(accessToken);
            System.out.println("Пользователь удален");
        }
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void goToPersonalAccount() {
        Users user = Users.randomUser();
        ValidatableResponse response = usersClient.createUser(user);
        accessToken = usersClient.checkCreated(response);
        var creds = Creds.getCreds(user);

        homePage.clickLogin();
        loginPage.logIn(creds);
        String expectedUserName = user.getName();
        Assertions.assertTrue(personalAccountPage.isOrderButtonVisible(), "Логин не выполнен");

        homePageUpper.clickPersonalCabinet();
        String actualName = personalAccountPage.getUsrName();
        Assertions.assertEquals(expectedUserName, actualName,
                "Личный кабинет не открыт");
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор» ")
    public void goToConstructorWithButtonConstructor() {
        Users user = Users.randomUser();
        ValidatableResponse response = usersClient.createUser(user);
        accessToken = usersClient.checkCreated(response);
        var creds = Creds.getCreds(user);
        homePage.clickLogin();
        loginPage.logIn(creds);
        String expectedUserName = user.getName();
        Assertions.assertTrue(personalAccountPage.isOrderButtonVisible(), "Логин не выполнен");

        homePageUpper.clickPersonalCabinet();
        String actualName = personalAccountPage.getUsrName();
        Assertions.assertEquals(expectedUserName, actualName,
                "Личный кабинет не открыт");

        homePageUpper.clickButtonConstructor();
        assertTrue(constructor.isConstructorOpened(), "Конструктор не открыт");
    }

    @Test
    @DisplayName("Переход по клику на лого Stellar Burgers")
    public void goToConstructorWithLogo() {
        Users user = Users.randomUser();
        ValidatableResponse response = usersClient.createUser(user);
        accessToken = usersClient.checkCreated(response);
        var creds = Creds.getCreds(user);
        homePage.clickLogin();
        loginPage.logIn(creds);
        String expectedUserName = user.getName();
        Assertions.assertTrue(personalAccountPage.isOrderButtonVisible(), "Логин не выполнен");

        homePageUpper.clickPersonalCabinet();
        String actualName = personalAccountPage.getUsrName();
        Assertions.assertEquals(expectedUserName, actualName,
                "Личный кабинет не открыт");

        homePageUpper.clickLogo();
        assertTrue(constructor.isConstructorOpened(), "Конструктор не открыт");

    }

    @Test
    @DisplayName("Выход в личном кабинете")
    public void logOut() {
        Users user = Users.randomUser();
        ValidatableResponse response = usersClient.createUser(user);
        accessToken = usersClient.checkCreated(response);
        var creds = Creds.getCreds(user);

        homePage.clickLogin();
        loginPage.logIn(creds);

        String expectedUserName = user.getName();
        Assertions.assertTrue(personalAccountPage.isOrderButtonVisible(), "Логин не выполнен");

        homePageUpper.clickPersonalCabinet();
        String actualName = personalAccountPage.getUsrName();
        Assertions.assertEquals(expectedUserName, actualName,
                "Личный кабинет не открыт");

        personalAccountPage.clickButtonLogOut();
        assertTrue(loginPage.isLoginButton(), "Выход из аккаунта не выполнен");

    }

}