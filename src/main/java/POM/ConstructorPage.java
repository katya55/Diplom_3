package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {

    private WebDriver driver;

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By title = By.xpath("//h1[normalize-space()='Соберите бургер']");
    private final By titleBuns = By.xpath("//h2[normalize-space()='Булки']");
    private final By titleSauces = By.xpath("//h2[normalize-space()='Соусы']");
    private final By titleFillings = By.xpath("//h2[normalize-space()='Начинки']");

    private final By bunsTab = By.xpath("//div[contains(@class,'tab_tab__1SPyG')]/span[text()='Булки']");
    private final By saucesTab = By.xpath("//div[contains(@class,'tab_tab__1SPyG')]/span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//div[contains(@class,'tab_tab__1SPyG')]/span[text()='Начинки']");


    @Step("Переход на страницу Конструктор")
    public boolean isConstructorOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(title));
        return true;
    }

    @Step("Заголовок Булки отображается")
    public boolean isTitleBunsOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(titleBuns));
        return true;
    }

    @Step("Заголовок Соусы отображается")
    public boolean isTitleSaucesOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(titleSauces));
        return true;
    }

    @Step("Заголовок Начинки отображается")
    public boolean isTitleFillingsOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(titleFillings));
        return true;
    }

    @Step("Клик на Булки")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Клик на Соусы")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Клик на начинки")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }
}

