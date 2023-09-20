package Pages;

import Methods.Methods;
import org.openqa.selenium.WebDriver;

public class ProductDetail extends Methods {

    public final String allDiv="dealsAccordionRow";
    public final String dealOfDay="a-autoid-10-announce";
    public final String normalPrice="//span[contains(.,' Normal Fiyat ')]";
    public final String addToCardButton="add-to-cart-button";
    public final String quantity="quantity";
    public final String complateShoppingButton="//input[@name='proceedToRetailCheckout']";
    public final String buyNowButton="buy-now-button";
    public final String TwoButtonDiv="a-box-inner";
    public final String priceInfo="div#corePrice_feature_div > div > span > span:nth-of-type(2)";//classname
    public final String subTotal="sw-subtotal";//id
    public final String navCart="nav-cart";//id
    public final String deleteItem="a-color-link";//classname
    public final String productDiv=".sg-col-4-of-24.sg-col-4-of-12.s-result-item.s-asin.sg-col-4-of-16.sg-col.s-widget-spacing-small.sg-col-4-of-20";
    public ProductDetail(WebDriver driver) {
        super(driver);
    }
}
