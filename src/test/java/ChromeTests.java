import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import utils.BaseTest;



public class ChromeTests extends BaseTest {

    @Test
    public void duckduckgoSearchingTest(){

        //Открыть Chrome в headless режиме
        startHeadlessMode();

        //Перейти на https://duckduckgo.com/
        driver.get("https://duckduckgo.com");
        logger.info("переход на duckduckgo выполнен");

        //В поисковую строку ввести ОТУС
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("ОТУС");
        driver.findElement(By.id("search_button_homepage")).click();
        String firstResult = driver.findElement(By.xpath("//*[@id='r1-0']/div[2]/h2/a/span")).getText();
        logger.info("ввод данных в поисковую строку выполнен, поиск выполнен");

        //Проверить что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...", firstResult);
        logger.info("Отус в поисковой выдаче является первым");
    }

    @Test
    public void chromeKioskModeTest() {
        // Открыть Chrome в режиме киоска
        startKioskMode();
        logger.info("хром запушен в режиме киоска");

        // Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        logger.info("переход на https://demo.w3layouts.com/... выполнен");

        // Нажать на любую картинку
        driver.findElement(By.cssSelector(".content-overlay")).click();
        logger.info("нажатие на картинку выполненно");

        // Проверить что картинка открылась в модальном окне
        Assertions.assertTrue(driver.findElement(By.cssSelector(".pp_overlay")).isDisplayed());
        logger.info("картинка открывается в модальном окне");

    }

    @Test
    public void authTest(){

        final String userName = System.getProperty("login");
        final String userPassword = System.getProperty("password");
        final String buttonEnterLocator = "button.header2__auth.js-open-modal";
        //Открыть Chrome в режиме полного экрана
        startDefaultMode();
        driver.manage().window().maximize();
        logger.info("хром открыт в режиме полного экрана");

        //Перейти на https://otus.ru
        driver.get("https://otus.ru");
        logger.info("переход на https://otus.ru выполнен");

        //Авторизоваться под каким-нибудь тестовым пользователем(можно создать нового)
        driver.findElement(By.cssSelector(buttonEnterLocator)).click();
        waitClickVisible(By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)"));
        driver.findElement(By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)")).sendKeys(userName);
        driver.findElement(By.cssSelector(".js-psw-input")).sendKeys(userPassword);
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).click();
        logger.info("Авторизация прошла успешно");
        logger.info("Все куки: \n" + driver.manage().getCookies());
    }

}
