package Tests;

import Driver.BaseTest;
import Pages.Cookies;
import Pages.LoginPage;
import Pages.MainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Pages.ProductDetail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AmazonTest extends BaseTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private Cookies cookies;
    private ProductDetail productDetail;
    private static final Logger logger = LogManager.getLogger(AmazonTest.class);




    @Test
    void AmazonLoginTest()
    {
        mainPage= new MainPage(driver);
        loginPage= new LoginPage(driver);
        cookies= new Cookies(driver);
        productDetail= new ProductDetail(driver);
        //Logo yüklenene kadar bekle
        mainPage.WaitUntilLoad(driver,"id",mainPage.logo);
        mainPage.verifyElementIsDisplayed(driver,By.id(mainPage.logo),"Logo Görünür Değil!");
        logger.error("Logo görünür hale geldi.");

        //Sayfa yüklenene kadar bekle
        cookies.WaitUntilLoad(driver,"id",cookies.accept);
        cookies.clickButton("id",cookies.accept);
        logger.error("Çerezler kabul edildi.");
        mainPage.takeScreenShot();

        logger.error("Sayfa oluşturuldu");
        //Giriş yapma sayfasına gidiş
        //driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();
        mainPage.clickButton("id",mainPage.accountList);
        logger.error("Giriş yapma sayfasına gidildi.");

        //Sayfa yüklenene kadar bekle
        loginPage.WaitUntilLoad(driver,"id",loginPage.mailTextBox);
        //TextBox'ın varlığı kontrol edilir.
        mainPage.verifyElementIsDisplayed(driver, By.id(loginPage.mailTextBox),"MailTextBox Görünür Değil!");
        logger.error("MailTextBox görünür hale geldi.");
        loginPage.writeField(loginPage.mailTextBox,"amazontest829@gmail.com");

        //Sayfa yüklenene kadar bekle
        loginPage.WaitUntilLoad(driver,"id",loginPage.continueButton);
        //Continiue buttonun varlığı kontrol edilir.
        loginPage.verifyElementIsDisplayed(driver,By.id(loginPage.continueButton),"Button Görünür Değil!");
        loginPage.clickButton("id",loginPage.continueButton);
        //Sayfa yüklenene kadar bekle
        loginPage.WaitUntilLoad(driver,"id",loginPage.passwordField);
        //Password Fiedl varlığı kontrol edilir.
        loginPage.verifyElementIsDisplayed(driver,By.id(loginPage.passwordField),"PasswordField Görünür Değil!");
        loginPage.writeField(loginPage.passwordField,"Ankara1903!");
        logger.error("Şifre dolduruldu.");

        //Sayfa yüklenene kadar bekle
        loginPage.WaitUntilLoad(driver,"id",loginPage.SignInButton);
        logger.error("Giriş yap butonuna tıklandı.");

        //SignIn buttonun varlığı kontrol edilir.
        loginPage.verifyElementIsDisplayed(driver,By.id(loginPage.SignInButton),"MailTextBox Görünür Değil!");
        loginPage.clickButton("id",loginPage.SignInButton);
    }
    @Test
    void SearchProductTest() throws InterruptedException {
        AmazonLoginTest();
        mainPage= new MainPage(driver);
        loginPage= new LoginPage(driver);
        cookies= new Cookies(driver);
        productDetail= new ProductDetail(driver);

        // cookies.clickButton(cookies.accept);

        //SEARCHTEXTBOX.
        mainPage.WaitUntilLoad(driver,"id",mainPage.searchTextBox);
       //SearchTextBox varlığı kontrol edilir
        mainPage.verifyElementIsDisplayed(driver,By.id(mainPage.searchTextBox),"SearchBox Görünür Değil!");
        mainPage.writeField(mainPage.searchTextBox, "bilgisayar");

        //SEARCHBUTTON
        mainPage.WaitUntilLoad(driver,"id",mainPage.searchButton);
        //SearchButton varlığı kontrol edilir.
        mainPage.verifyElementIsDisplayed(driver,By.id(mainPage.searchButton),"SearchBox Görünür Değil!");
        mainPage.clickButton("id",mainPage.searchButton);
        logger.error("Arama butonuna tıklandı.");

        //Random bir ürün seçilir.
        productDetail.chooseRandomProduct(productDetail.productDiv);
        logger.info("Rastgele bir ürün seçildi");

        //Ekleme butonuna kadar sayfa kaydırılır.
        productDetail.scrollToElement("id", productDetail.addToCardButton);
        //Miktarı arttır,sepete ekle fiyat kontrolü yap
        productDetail.AddToCardNormal(productDetail.addToCardButton,productDetail.priceInfo,productDetail.subTotal,productDetail.quantity);
        logger.error("Rastgele bir ürün seçildi");

        //Alışverişi tamamla butonu yüklenene kadar bekle
        productDetail.WaitUntilLoad(driver,"xpath",productDetail.complateShoppingButton);
        //Alışveriş tamamla butonuna tıklanır.
        productDetail.clickButton("xpath",productDetail.complateShoppingButton);
        logger.error("Alışveriş tamamlandı butonuna tıklandı.");

        //Sayfa geri gelir
        driver.navigate().back();
        //ÜSt menüde bulunan sepet'e tıklanır
        productDetail.clickButton("id",productDetail.navCart);
        logger.error("Sepete tıklandı.");
        //Sepette bulunan sil butonu yüklenene kadar beklenir
     //   productDetail.WaitUntilMultiple(driver,"classname",productDetail.deleteItem);
       //sepetteki ürün silinir.
        productDetail.clickButton("classname",productDetail.deleteItem);
        logger.error("İtem silindi.");


    }

    @AfterEach
    void tearDown()
    {
        //  driver.close();
    }
}






