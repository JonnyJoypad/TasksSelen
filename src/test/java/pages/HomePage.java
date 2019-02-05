package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends Page {
    private By headerCurrencyButton = By.cssSelector(".expand-more._gray-darker.hidden-sm-down");
    private By eurCurrencyType = By.xpath("//a[.='EUR €']");
    private By uahCurrencyType = By.xpath("//a[.='UAH ₴']");
    private By usdCurrencyType = By.xpath("//a[.='USD $']");
    private By productPrice = By.cssSelector(".price");

    public HomePage(WebDriver driver) {
        super(driver);

        if (!"prestashop-automation".equals(driver.getTitle())) {
            throw new IllegalStateException("Wrong site");
        }
    }

    public void changeCurrencyType(String currency) {
        waitUntilElementIsClickable(this.headerCurrencyButton);
        WebElement currencyBar = driver.findElement(headerCurrencyButton);
        currencyBar.click();
        WebElement eurCurrencyType = driver.findElement(this.eurCurrencyType);
        WebElement uahCurrencyType = driver.findElement(this.uahCurrencyType);
        WebElement usdCurrencyType = driver.findElement(this.usdCurrencyType);
        switch (currency) {
            case "eur":
                waitUntilElementIsClickable(this.eurCurrencyType);
                eurCurrencyType.click();
                break;
            case "uah":
                waitUntilElementIsClickable(this.uahCurrencyType);
                uahCurrencyType.click();
                break;
            case "usd":
                waitUntilElementIsClickable(this.usdCurrencyType);
                usdCurrencyType.click();
                break;
        }
    }

    public boolean checkCurrency(String currency) {
        waitUntilElementIsClickable(this.productPrice);
        WebElement productPrice = driver.findElement(this.productPrice);
        String symbol = productPrice.getText();
        switch (currency) {
            case "eur":
                return symbol.contains("€") || symbol.contains("EUR");
            case "uah":
                return symbol.contains("₴");
            case "usd":
                return symbol.contains("$") || symbol.contains("USD");
            default:
                throw new IllegalArgumentException("Error with picking up a currency");
        }
    }
}

