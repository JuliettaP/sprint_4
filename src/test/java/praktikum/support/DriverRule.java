package praktikum.support;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Objects;

public class DriverRule extends ExternalResource
{
    private WebDriver driver;

    @Override
    protected void before() throws Throwable
    {
        initDriver();
    }

    @Override
    protected void after()
    {
        if (driver != null)
            driver.quit();
    }

    private void initFirefox()
    {
        // драйвер для браузера Firefox
        FirefoxOptions options = new FirefoxOptions();

        var prop = System.getProperty("driver");

        if (prop != null && !prop.isEmpty())
            options.setBinary(System.getProperty("driver"));

        driver = new FirefoxDriver(options);
    }

    private void initChrome()
    {
        // драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();

        //options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

        var prop = System.getProperty("driver");

        if (prop != null && !prop.isEmpty())
            options.setBinary(System.getProperty("driver"));

        driver = new ChromeDriver(options);
    }

    public void initDriver()
    {
        if (Objects.equals(System.getProperty("browser"), "firefox"))
            initFirefox();
        else
            initChrome();
    }

    public WebDriver getDriver()
    {
        return driver;
    }
}
