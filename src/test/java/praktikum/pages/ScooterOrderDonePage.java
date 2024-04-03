package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.config.EnvConfig;

public class ScooterOrderDonePage {

    private final WebDriver driver;


    // локатор кнопки подтверждения заказа
    private final By orderText = By.xpath(".//div[contains(@class,'Order_Modal_')]//div[contains(@class,'Order_Text_')]");

    public ScooterOrderDonePage(WebDriver driver) {
        this.driver = driver;
    }

    // дождаться загрузки формы
    public void waitFor()
    {
        new WebDriverWait(driver, EnvConfig.DEFAULT_TIMEOUT_SEC).until(ExpectedConditions.visibilityOfElementLocated(orderText));
    }

    public String getOrderText()
    {
        return driver.findElement(orderText).getText();
    }
}
