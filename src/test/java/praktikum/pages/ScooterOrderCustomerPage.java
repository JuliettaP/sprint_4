package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.config.EnvConfig;
import praktikum.support.RobustElementClicker;

import java.util.List;

public class ScooterOrderCustomerPage {

    private final WebDriver driver;

    // этот локатор находит все поля ввода на форме Order_Form_*
    private final By inputs = By.xpath(".//div[contains(@class, 'Order_Form_')]//input");

    // локатор кнопки Далее
    private final By nextButton = By.xpath(".//div[contains(@class, 'Order_NextButton_')]//button");

    // здесь перечисленны идентификаторы, соответствующие найденным локатором полям
    // NOTE: если раскладка страницы изменится (добавятся/удалятся поля ввода), то этот список потребуется актуализировать
    public static class InputId {
        public static final int NAME = 0;
        public static final int SURNAME = 1;
        public static final int ADDRESS = 2;
        public static final int STATION = 3;
        public static final int PHONE = 4;
    }

    public ScooterOrderCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    // дождаться загрузки формы
    public void waitFor()
    {
        new WebDriverWait(driver, EnvConfig.DEFAULT_TIMEOUT_SEC).until(ExpectedConditions.visibilityOfElementLocated(nextButton));
    }

    // устанавливает текст в заданное поле ввода (для идентификации поля ввода нужно использовть константы из InputId)
    public void setInputText(int id, String text)
    {
        List<WebElement> fields = driver.findElements(inputs);

        WebElement field = fields.get(id);

        if (id == InputId.STATION)
        {
            // дропдаун - открываем выпадающий список, находим требуемый элемент и кликаем по нему
            field.click();
            RobustElementClicker.Click(driver, driver.findElement(By.xpath(".//div[text()='" + text + "']")));
        }
        else
        {
            // обычное поле ввода - очищаем и вставляем текст
            field.clear();
            field.sendKeys(text);
        }
    }

    // нажатие кнопки Далее
    public ScooterOrderDetailsPage clickNextButton()
    {
        driver.findElement(nextButton).click();
        return new ScooterOrderDetailsPage(driver);
    }
}
