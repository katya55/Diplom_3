package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import users.Creds;

import java.time.Duration;


//Страница https://stellarburgers.education-services.ru/login
public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By registerButton = By.xpath("//a[normalize-space()='Зарегистрироваться']");
    private final By emailField = By.name("name");
    private final By passwordField = By.name("Пароль");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By forgotPassword = By.linkText("Восстановить пароль");


    @Step("Нажать Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Логин пользователя")
    public void logIn(Creds creds) {
        driver.findElement(emailField).sendKeys(creds.getEmail());
        driver.findElement(passwordField).sendKeys(creds.getPassword());
        driver.findElement(loginButton).click();
    }

    @Step("Нажать Забыли пароль")
    public void clickForgotPassword() {
        driver.findElement(forgotPassword).click();
    }

    @Step("Кнопка Войти отображается")
    public boolean isLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return true;
    }

}
