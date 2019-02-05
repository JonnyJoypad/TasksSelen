package properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public abstract class Settings {
    private static final String FRONTEND_URL = "http://prestashop-automation.qatestlab.com.ua/ru/";

    protected String getFrontendUrl() {
        return FRONTEND_URL;
    }

    private static WebDriver getDriver(String browserName) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                + "//resources//chromedriver.exe");
        return new ChromeDriver();
        }

    protected static EventFiringWebDriver getConfiguredDriver(String browserName) {
        WebDriver driver = getDriver(browserName);
        driver.manage().window().maximize();
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.register(new EventHandler());
        eventFiringWebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return eventFiringWebDriver;
    }

    protected void writeLogs(String browserName) {
        File logsFile = new File(browserName + "Logs.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logsFile))) {
            bw.write(EventHandler.sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        EventHandler.sb = new StringBuilder();
    }
}
