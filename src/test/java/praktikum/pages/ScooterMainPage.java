package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.config.EnvConfig;
import praktikum.support.RobustElementClicker;

// Класс главной страницы
public class ScooterMainPage {

    private final WebDriver driver;

    // нижняя кнопка Заказать
    // <button class="Button_Button__ra12g Button_Middle__1CSJM">Заказать</button>
    private final By orderButtonMiddle = By.xpath(".//div[contains(@class, 'Home_FinishButton_')]//button");

    // верхняя кнопка Заказать
    // NOTE: с этой кнопкой сложнее - если искать по классу Button_Button_*, то находится несколько кнопок, поэтому
    // я пишу xpath, в который добавляю уточняющий div с классом Header_Nav_*, чтобы однозначно найти нужную кнопку
    /*
    <div class="Header_Nav__AGCXC">
        <button class="Button_Button__ra12g">Заказать</button>
        <button class="Header_Link__1TAG7">Статус заказа</button>
    </div>
    */
    private final By orderButtonTop = By.xpath(".//div[contains(@class, 'Header_Nav_')]//button[contains(@class,'Button_Button_')]");

    public ScooterMainPage(WebDriver driver) {
        this.driver = driver;
    }

    // метод открытия страницы
    public void open()
    {
        // открываем страницу
        driver.get(EnvConfig.BASE_URL);
    }

    // метод для нажатия на верхнюю кнопку Заказать
    public ScooterOrderCustomerPage clickOrderButtonTop()
    {
        driver.findElement(orderButtonTop).click();
        return new ScooterOrderCustomerPage(driver);
    }

    // метод для нажатия на нижнюю кнопку Заказать
    public ScooterOrderCustomerPage clickOrderButtonMiddle()
    {
        RobustElementClicker.Click(driver, driver.findElement(orderButtonMiddle));
        return new ScooterOrderCustomerPage(driver);
    }

    // клик по эл-ту FAQ
    public void clickFAQItem(int id)
    {
        RobustElementClicker.Click(driver, driver.findElement(By.id("accordion__heading-" + id)));
    }

    private WebElement getFAQAnswerElement(int id)
    {
        return driver.findElement(By.id("accordion__panel-" + id));
    }

    // получение текста ответа FAQ
    public String getFAQAnswerText(int id)
    {
        return getFAQAnswerElement(id).getText();
    }

    // получение признака видимости ответа FAQ
    public boolean isFAQAnswerDisplayed(int id)
    {
        WebElement element = getFAQAnswerElement(id);
        new WebDriverWait(driver, EnvConfig.DEFAULT_TIMEOUT_SEC).until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }
}
