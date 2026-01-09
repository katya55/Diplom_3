import POM.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
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
    UsersClient usersClient = new UsersClient();


    @RegisterExtension
    static DriverExtension extension = new DriverExtension();

    @BeforeEach
    public void starUp() {
        var driver = extension.getDriver();
        driver.get(BASE_URL);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    void enterButtonLoginButton() {
        Users user = Users.randomUser();
        usersClient.createUser(user);

        var creds = Creds.getCreds(user);
        homePage.clickLogin();
        loginPage.logIn(creds);

        Assertions.assertTrue(personalAccountPage.isOrderButtonVisible(), "Кнопка не отобразилась");
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void enterButtonPersonalAccountPage() {
        Users user = Users.randomUser();
        usersClient.createUser(user);

        var creds = Creds.getCreds(user);
        homePage.clickPersonalCabinet();
        loginPage.logIn(creds);

        Assertions.assertTrue(personalAccountPage.isOrderButtonVisible(), "Кнопка не отобразилась");
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void enterRegistrationPage() {
        Users user = Users.randomUser();
        usersClient.createUser(user);

        var creds = Creds.getCreds(user);
        homePage.clickLogin();
        loginPage.clickRegisterButton();
        registrationPage.clickButtonEnter();
        loginPage.logIn(creds);

        Assertions.assertTrue(personalAccountPage.isOrderButtonVisible(), "Кнопка не отобразилась");
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void enterForgotPasswordPPage() {
        Users user = Users.randomUser();
        usersClient.createUser(user);

        var creds = Creds.getCreds(user);
        homePage.clickLogin();
        loginPage.clickForgotPassword();
        forgotPasswordPage.clickLoginButton();
        loginPage.logIn(creds);

        Assertions.assertTrue(personalAccountPage.isOrderButtonVisible(), "Кнопка не отобразилась");
    }


}
