package POM;

import io.qameta.allure.Step;
import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import users.Users;

import java.util.concurrent.ThreadLocalRandom;


//Страница https://stellarburgers.education-services.ru/register
public class RegistrationPage {

    private WebDriver driver;
    private static final Faker faker = new Faker();
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By nameField = By.xpath("//input[@name='name'][1]");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.name("Пароль");

    private final By errorMessage = By.cssSelector(".input__error");
    private final By registerButtonOnRegister = By.xpath("//button[normalize-space()='Зарегистрироваться']");

    private final By ButtonEnter = By.linkText("Войти");

    @Step("Регистрация пользователя")
    public void register(Users users) {
        driver.findElement(nameField).sendKeys(users.getName());
        driver.findElement(emailField).sendKeys(users.getEmail());
        driver.findElement(passwordField).sendKeys(users.getPassword());
        driver.findElement(registerButtonOnRegister).click();
    }

    @Step
    public void registerWithShortPassword() {
        driver.findElement(nameField).sendKeys(faker.name().firstName());
        driver.findElement(emailField).sendKeys(faker.internet().emailAddress());
        driver.findElement(passwordField).sendKeys("123");
        driver.findElement(registerButtonOnRegister).click();
    }
    @Step("Проверка ошибки при некорректном пароле")
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    @Step("Нажать 'Вход' на странице Регистрации")
    public void clickButtonEnter() {
        driver.findElement(ButtonEnter).click();
    }

}
