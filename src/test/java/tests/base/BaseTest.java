package tests.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import tests.TestListener;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Log4j2
@Listeners(TestListener.class)
public abstract class BaseTest {

    String user = System.getProperty("user", PropertyReader.getProperty("user"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Configuration.browserCapabilities = options;

        Configuration.browser = "chrome";//выбор браузера по умолчанию
        Configuration.headless = false;//или true
        Configuration.timeout = 20000;//ожидание любых условий
        Configuration.clickViaJs = true;//клики с помощью JS
        Configuration.baseUrl = "https://meyenem698.testrail.io/index.php?/auth/login/";


//        Configuration.assertionMode = AssertionMode.valueOf("SOFT");//для софт ассертов
        //getWebDriver();// есть еще setWebDriver()

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        closeWebDriver();
    }
}
