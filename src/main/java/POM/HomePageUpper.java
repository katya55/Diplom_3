package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageUpper {

    private WebDriver driver;

    public HomePageUpper(WebDriver driver) {
        this.driver = driver;
    }

    private final By personalCabinetButton = By.xpath("//p[normalize-space()='Личный Кабинет']");
    private final By buttonConstructor = By.xpath("//p[text()='Конструктор']");
    private final By logo = By.xpath("//*[name()='svg']");

    @Step("Нажать на Конструктор")
    public void clickButtonConstructor() {
        driver.findElement(buttonConstructor).click();
    }

    @Step("Нажать на Личный кабинет")
    public void clickPersonalCabinet() {
        driver.findElement(personalCabinetButton).click();
    }

    @Step("Нажать на лого")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

}
