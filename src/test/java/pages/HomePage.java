package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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
        switch (currency) {
            case "eur":
                List<String> priceEUR = new ArrayList<String>();
                List<WebElement> allEUR = driver.findElements(this.productPrice);
                for (WebElement webElement : allEUR) {
                    priceEUR.add(webElement.getText());
                }
                for (int i = 0; i < priceEUR.size(); i++) {
                    if(priceEUR.get(i).contains("€")) {
                        return true;
                    }else
                        return false;

                }

            case "uah":

                List<String> priceUAH = new ArrayList<>();
                List<WebElement> allText = driver.findElements(this.productPrice);
                for (WebElement webElement : allText) {
                    priceUAH.add(webElement.getText());
                }

                for (int i = 0; i < priceUAH.size(); i++) {
                    if( priceUAH.get(i).contains("₴") == true) {
                        return true;
                    }else
                        return false;

                }

            case "usd":
                List<String> priceUSD = new ArrayList<>();
                List<WebElement> allUSD = driver.findElements(this.productPrice);
                for (WebElement webElement : allUSD) {
                    priceUSD.add(webElement.getText());
                }
                if (priceUSD != null) {
                    for (int i = 0; i < priceUSD.size(); i++) {
                        if (priceUSD.get(i).contains("$")) {
                            return true;
                        } else {
                            return false;

                        }
                    }
                }

            default:
                throw new IllegalArgumentException("Error with picking up a currency");
        }
    }
}

