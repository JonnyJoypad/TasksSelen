package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DecimalFormat;
import java.util.List;

public class SearchResultsPage extends Page {
    private By productTile = By.cssSelector(".h3.product-title");
    private By foundProductsLabel = By.cssSelector(".col-md-6.hidden-sm-down.total-products");
    private By productPrice = By.className("price");
    private By productOnlyRegularPrice = By.xpath("//div[@class='product-price-and-shipping']/span[1]");
    private By productsWithDiscount = By.className("discount");
    private By labelOfDiscount = By.className("discount-percentage");
    private By actualPriceOfDiscountProducts = By.xpath("//span[@class='discount-percentage']/following-sibling::span");
    private By regularPriceOfDiscountProducts = By.xpath("//span[@class='discount-percentage']/preceding-sibling::span");
    private By sortingMethodsField = By.className("select-title");


    SearchResultsPage(WebDriver driver) {
        super(driver);
    }
}
