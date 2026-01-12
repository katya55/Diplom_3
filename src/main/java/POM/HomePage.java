package POM;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By loginButton = By.xpath("//button[normalize-space()='Войти в аккаунт']");

    @Step("Нажать Войти в аккаунт")
    public void clickLogin() {
      wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

    }
}


