package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.config.EnvConfig;

public class ScooterOrderConfirmPage
{
    private final WebDriver driver;


    // локатор кнопки подтверждения заказа
    private final By confirmButton = By.xpath(".//div[contains(@class,'Order_Modal_')]//button[2]");

    public ScooterOrderConfirmPage(WebDriver driver) {
        this.driver = driver;
    }

    // дождаться загрузки формы
    public void waitFor()
    {
        new WebDriverWait(driver, EnvConfig.DEFAULT_TIMEOUT_SEC).until(ExpectedConditions.visibilityOfElementLocated(confirmButton));
    }

    // нажать на кнопку подтверждения заказа
    public ScooterOrderDonePage clickConfirmButton()
    {
        driver.findElement(confirmButton).click();
        return new ScooterOrderDonePage(driver);
    }
}
