package Pages;

import Methods.Methods;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Methods {
    public final String mailTextBox ="ap_email";
    public final String continueButton ="continue";
    public final String passwordField ="ap_password";
    public final String SignInButton ="signInSubmit";

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
