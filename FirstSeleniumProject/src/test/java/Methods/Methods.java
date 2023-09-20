package Methods;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;



public class Methods {
    WebDriver driver;
    public Methods(WebDriver driver)
    {
        this.driver=driver;
    }
    public void clickButtonClassName(String buttonId)
    {
        driver.findElement(By.xpath(buttonId)).click();
    }
    public void WaitUntilLoad(WebDriver driver,String locatorType,String LocatorValue)
    {
        WebElement element = null;
        switch (locatorType.toLowerCase()) {
            case "id":
                element = driver.findElement(By.id(LocatorValue));
                break;
            case "css":
                element = driver.findElement(By.cssSelector(LocatorValue));
                break;
            case "classname":
                element = driver.findElement(By.className(LocatorValue));
                break;
            case "xpath":
                element = driver.findElement(By.xpath(LocatorValue));
                break;
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15L));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public  void writeField(String elementId,String text)
    {
        driver.findElement(By.id(elementId)).sendKeys(text);
    }
    public void chooseRandomProduct(String cssElement)
    {
        List<WebElement> products = driver.findElements(By.cssSelector(cssElement));
        int randomIndex = new Random().nextInt(products.size());
        // Seçilen ürünü tıklayın
        WebElement randomProduct = products.get(randomIndex);
        //WaitUntilLoadCSS(cssElement);
        WaitUntilLoad(driver,"css",cssElement);
        randomProduct.click();
    }
    public void scrollToElement(String locatorType,String LocatorValue){
    WebElement element = null;
    switch (locatorType.toLowerCase()) {
        case "id":
            element = driver.findElement(By.id(LocatorValue));
            break;
        case "css":
            element = driver.findElement(By.cssSelector(LocatorValue));
            break;
        case "xpath":
            element = driver.findElement(By.xpath(LocatorValue));
            break;
        default:
            throw new IllegalArgumentException("Invalid locator type: " + locatorType);
    }
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
}
    public void AddToCardNormal(String addToCardButtonElement,String productPriceElementP,String cartItemPriceElementP,String  quantityElement)
    {
        WaitUntilLoad(driver,"id",quantityElement);
        WebElement quantitySelect = driver.findElement(By.id(quantityElement)); //
        // Bir Select nesnesi oluşturun
        Select select = new Select(quantitySelect);
        // İstediğiniz miktarı seçin (örneğin, 2 adet)
        select.selectByVisibleText("2"); // Bu, seçeneği görünür metinle seçer
        // Ürün sayfasındaki fiyatı alın
        WebElement productPriceElement = driver.findElement(By.cssSelector(productPriceElementP));
        String productPriceText = productPriceElement.getText();
        double productPrice = Double.parseDouble(productPriceText.replaceAll("[^0-9.]", ""));
        double resultproductPrice=productPrice*2;
        WaitUntilLoad(driver,"css",productPriceElementP);
        //Ekleme butonu
        WebElement addButton= driver.findElement(By.id(addToCardButtonElement));
        WaitUntilLoad(driver,"id",addToCardButtonElement);
        addButton.click();
        // Sepet sayfasındaki tüm ürün fiyatlarını alın
        List<WebElement> cartItemPriceElements = driver.findElements(By.id(cartItemPriceElementP));
        // Her bir ürün fiyatını sepet fiyatlarıyla karşılaştırın
        for (WebElement cartItemPriceElement : cartItemPriceElements) {
            String cartItemPriceText = cartItemPriceElement.getText();
            double cartItemPrice = Double.parseDouble(cartItemPriceText.replaceAll("[^0-9.]", ""));
            WaitUntilLoad(driver,"id",cartItemPriceElementP);
            // Fiyatları karşılaştırın ve eşitlik kontrolü yapın
            Assertions.assertEquals(resultproductPrice, cartItemPrice, 0.01); //0.01 tölerans değeri
        }
    }

    //GÜNÜN FIRSATI SEKMESİ GELDİĞİNDE KULLANILACAK KOD BLOĞU
    public void AddToCardWithCondition(String butunDiv,String addToCardElement,String normalPriceElement,String twoButtonDivElement)
    {
        // "Günün Fırsatı" div'ini bulma
       // WebElement dealOfDay = driver.findElement(By.className(dealOfDayElement));
        WebElement allDiv = driver.findElement(By.id(butunDiv));
        WebElement twoButtonDiv = driver.findElement(By.className(twoButtonDivElement));
        WebElement addToCard = driver.findElement(By.id(addToCardElement));
        if(twoButtonDiv.isDisplayed()) {
            // "Günün Fırsatı" div'i yoksa "Sepete Ekle" butonuna tıkla
           System.out.println("deneme");
        }
        // "Günün Fırsatı" div'i varsa "Normal Fiyat" sekmesine tıkla
        if (allDiv.isDisplayed()) {
       //= driver.findElement(By.className(normalPriceElement));
            WebElement normalPriceTab =driver.findElement(By.xpath(normalPriceElement));
            normalPriceTab.click();
          //  WaitUntilLoad(addToCardElement);
            addToCard.click();
        }

        }
    public void verifyElementIsDisplayed(WebDriver driver, By locator, String errorMessage) {

        WebElement element = driver.findElement(locator);
        Assert.assertTrue(element.isDisplayed(), errorMessage);
    }

    public void takeScreenShot()
    {
        // Ekran görüntüsü alma işlemi
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("C:/ScreenShots/screenshot.png");
        try {
            FileUtils.copyFile(sourceFile, destinationFile);
            System.out.println("Ekran görüntüsü kaydedildi: " + destinationFile.getAbsolutePath());
        } catch (Exception e) {
          //  e.printStackTrace();
            System.out.println("Ekran görüntüsü alma işlemi başarısız.");
        }
    }
    public void clickButton(String locatorType, String LocatorValue) {
        WebElement element = null;
        switch (locatorType.toLowerCase()) {
            case "id":
                element = driver.findElement(By.id(LocatorValue));
                break;
            case "css":
                element = driver.findElement(By.cssSelector(LocatorValue));
                break;
            case "classname":
                element = driver.findElement(By.className(LocatorValue));
                break;
            case "xpath":
                element = driver.findElement(By.xpath(LocatorValue));
                break;
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
        element.click();


    }

}
