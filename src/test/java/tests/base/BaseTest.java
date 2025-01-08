package tests.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Log4j2
public class BaseTest {

    protected String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected String password = System.getProperty("password", PropertyReader.getProperty("password"));
    protected String apiKey = System.getProperty("apiKey", PropertyReader.getProperty("apiKey"));;

    public LoginPage loginPage;
    public ProjectPage projectPage;
    public DashboardPage dashboardPage;
    public EditPage editPage;
    public MilestonesPage milestonesPage;
    public ReportsPage reportsPage;
    public TestRunsAndResultsPage testRunsAndResultsPage;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Configuration.browserCapabilities = options;

        Configuration.browser = "chrome";//выбор браузера по умолчанию
        Configuration.headless = false;//или true - для гитхаб экшен!!
        Configuration.timeout = 20000;//ожидание любых условий
        Configuration.clickViaJs = true;//клики с помощью JS
        Configuration.baseUrl = "https://meyenem698.testrail.io/index.php?/";

        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        projectPage = new ProjectPage();
        editPage = new EditPage();
        milestonesPage = new MilestonesPage();
        reportsPage = new ReportsPage();
        testRunsAndResultsPage = new TestRunsAndResultsPage();

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
