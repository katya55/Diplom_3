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

class ConstructorTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private PersonalAccountPage personalAccountPage;
    private HomePageUpper homePageUpper;
    private ConstructorPage constructor;
    private UsersClient usersClient;
    private String accessToken;

    @RegisterExtension
    static DriverExtension extension = new DriverExtension();

    @BeforeEach
    public void starUp() {
        var driver = extension.getDriver();
        driver.get(BASE_URL);

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        homePageUpper = new HomePageUpper(driver);
        constructor = new ConstructorPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
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
    @DisplayName("Переход к разделу Булки")
    public void goToBuns() {
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

        constructor.clickSaucesTab();
        constructor.clickBunsTab();
        assertTrue(constructor.isTitleBunsOpened(), "Раздел Булки открыт");
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void goToSauces() {
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

        constructor.clickSaucesTab();
        assertTrue(constructor.isTitleSaucesOpened(), "Раздел Соусы открыт");
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void goToFillings() {
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

        constructor.clickFillingsTab();
        assertTrue(constructor.isTitleFillingsOpened(), "Раздел Начинки открыт");
    }
}