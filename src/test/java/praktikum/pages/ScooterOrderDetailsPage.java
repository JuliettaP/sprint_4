package praktikum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.config.EnvConfig;
import praktikum.support.RobustElementClicker;

public class ScooterOrderDetailsPage {

    private final WebDriver driver;

    // локатор поля даты доставки
    private final By date = By.xpath(".//div[contains(@class, 'Order_MixedDatePicker_')]//input");

    // локатор дропдауна срока аренды
    private final By days = By.xpath(".//div[contains(@class, 'Order_Form_')]//div[@class='Dropdown-control']");

    // локатор коментария к заказу
    private final By comment = By.xpath(".//div[contains(@class,'Input_InputContainer_')]//input[contains(@class,'Input_Responsible_')]");

    // локатор кнопки Заказать
    private final By submitButton = By.xpath(".//div[contains(@class, 'Order_Buttons_')]//button[2]");

    public ScooterOrderDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    // дождаться загрузки формы
    public void waitFor()
    {
        // дожидаемся появления формы ввода
        new WebDriverWait(driver, EnvConfig.DEFAULT_TIMEOUT_SEC).until(ExpectedConditions.visibilityOfElementLocated(submitButton));
    }

    // установить текст в поле даты доставки
    public void setDateText(String text)
    {
        WebElement field = driver.findElement(date);
        field.clear();
        field.sendKeys(text);
        // скрыть дропдаун
        driver.findElement(By.xpath(".//div[@class='react-datepicker-popper']")).click();
    }

    // выбрать пункт в дропдауне срока аренда
    public void selectDaysDropDown(int numDays)
    {
        Assert.assertTrue(numDays > 0 && numDays <= 7);
        // открыть дропдаун
        driver.findElement(days).click();
        // кликнуть на элемент, соотв. кол-ву дней
        RobustElementClicker.Click(driver, driver.findElement(By.xpath(".//div[@class='Dropdown-option'][" + numDays + "]")));
    }

    // идентификаторы цветов
    public static class ColorId
    {
        public static final String BLACK = "black";
        public static final String GREY = "grey";
    }

    // клик по чекбоксу с цветом
    public void clickColorCheckBox(String id)
    {
        driver.findElement(By.xpath(".//div[contains(@class, 'Order_Form_')]//input[@id='" + id +"']")).click();
    }

    // установить текст комментария к заказу
    public void setCommentText(String text)
    {
        WebElement field = driver.findElement(comment);
        field.clear();
        field.sendKeys(text);
    }

    // клик по кнопке Заказать
    public ScooterOrderConfirmPage clickSubmitButton()
    {
        driver.findElement(submitButton).click();
        return new ScooterOrderConfirmPage(driver);
    }
}
