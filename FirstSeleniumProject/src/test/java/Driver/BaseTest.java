package Driver;

import Methods.Methods;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    public static WebDriver driver;


    @BeforeAll
    public static void beforeAll(){
        logger.info("========== Before All ============");

    }
    @BeforeEach
    public void beforeEach()
    {
        ChromeOptions chromeOptions= new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--screenshot=C:/ScreenShots/screenshot.png");
        driver= new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com.tr");
    }
    @AfterEach
    public void afterEach()
    {

    }
}

