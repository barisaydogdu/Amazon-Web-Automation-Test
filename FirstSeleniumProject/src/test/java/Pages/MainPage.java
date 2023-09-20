package Pages;

import Methods.Methods;
import org.openqa.selenium.WebDriver;

public class MainPage extends Methods {
public final String accountList ="nav-link-accountList-nav-line-1";
public final String logo ="nav-logo-sprites";
public final String searchTextBox="twotabsearchtextbox";
public final String searchButton="nav-search-submit-button";
public final String girisYapH1="a-spacing-small";

//günün fırsatı

    public MainPage(WebDriver driver) {
        super(driver);
    }
}
