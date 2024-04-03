package praktikum.support;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.config.EnvConfig;

public class RobustElementClicker {
    // NOTE: иногда при попытке клика на элементе драйвер падает, т.к. элемент находится вне зоны видимости
    // (т.е. к нему нужно проскролить), или же он какое-то время не кликабелен, т.к. требуется дождаться завершения
    // анимации или загрузки данных и т.п., поэтому метод клика дополнен необходиыми действиями и вынесен в отедльный
    // хелпер-класс, чтобы все работало надежно, единообразно и не было дублирования кода
    public static void Click(WebDriver driver, WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        new WebDriverWait(driver, EnvConfig.DEFAULT_TIMEOUT_SEC).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}
