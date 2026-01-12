package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {

    private WebDriver driver;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By buttonMakeOrder = By.xpath("//button[text()='Оформить заказ']");

    private final By nameInput = By.xpath("//input[@name='Name']");
    private final By buttonLogOut = By.xpath("//button[normalize-space()='Выход']");

    @Step("Кнопка Оформить заказ отображается")
    public boolean isOrderButtonVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement orderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(buttonMakeOrder));
        return orderButton.isDisplayed();
    }

    @Step("Получить имя пользователя")
    public String getUsrName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        return input.getAttribute("value");
    }

    @Step("Выход из аккаунта")
    public void clickButtonLogOut() {
        driver.findElement(buttonLogOut).click();
    }
}

