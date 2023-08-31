package utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected org.apache.logging.log4j.Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeAll
    public static void init() {
        WebDriverManager.chromedriver().setup();
        System.out.println("initialized");
    }


    @AfterEach
    public void close(){
        if (driver!=null)
            driver.quit();
    }

    public void waitClickVisible(By element){
        WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).click();
    }

    public void startDefaultMode() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        logger.info("Драйвер поднят");
    }

    public void startHeadlessMode() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        logger.info("Драйвер поднят в режиме headless");

    }

    public void startKioskMode() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("kiosk");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        logger.info("Драйвер поднят  в режиме kiosk");


    }
}
