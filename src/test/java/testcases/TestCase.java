package testcases;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SearchResultsPage;
import properties.Settings;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestCase extends Settings {
    private EventFiringWebDriver driver;

    @Test
    public void trialTestCase() {
        driver.get(getFrontendUrl());
        HomePage homePage = new HomePage(driver);
        homePage.changeCurrencyType("eur");
        assertTrue(homePage.checkCurrency("eur"), "The currency type doesn't match" +
                " the selected EUR currency in header");
        System.out.println("Product's price is displayed in EUR currency. Verified.");
        homePage.changeCurrencyType("usd");
        assertTrue(homePage.checkCurrency("usd"), "The currency type doesn't match" +
                " the selected USD currency in header");
        System.out.println("Product's price is displayed in USD currency. Verified");
        homePage.changeCurrencyType("uah");
        assertTrue(homePage.checkCurrency("uah"), "The currency type doesn't match" +
                " the selected UAH currency in header");
        System.out.println("Product's price is displayed in UAH currency. Verified.");
        homePage.changeCurrencyType("usd");
    }

    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser) {
        driver = getConfiguredDriver(browser);
    }

    @AfterTest
    @Parameters("browser")
    public void tearDown(String browser) {
        if (driver != null) {
            driver.close();
        }
        writeLogs(browser);
    }
}
