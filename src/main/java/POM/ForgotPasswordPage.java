package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginButton = By.linkText("Войти");

    @Step("Нажать Войти на странице восставноление пароля")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}
