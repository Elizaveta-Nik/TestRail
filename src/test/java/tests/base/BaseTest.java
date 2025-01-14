package tests.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.*;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Log4j2
public class BaseTest {

    public LoginPage loginPage;
    public ProjectPage projectPage;
    public DashboardPage dashboardPage;
    public EditPage editPage;
    public MilestonesPage milestonesPage;
    public ReportsPage reportsPage;
    public TestRunsAndResultsPage testRunsAndResultsPage;
    protected String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected String password = System.getProperty("password", PropertyReader.getProperty("password"));
    protected String apiKey = System.getProperty("apiKey", PropertyReader.getProperty("apiKey"));
    protected String baseURL = System.getProperty("baseURL", PropertyReader.getProperty("baseURL"));
    protected String baseUrlAPI = System.getProperty("baseUrlAPI", PropertyReader.getProperty("baseUrlAPI"));

    @Parameters("browser")
    @BeforeMethod
    @Description("Open browser")
    public void setup(@Optional("chrome") String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            Configuration.browser = "chrome";
            Configuration.browserSize = "1920x1080";
            Configuration.headless = false;// или true - для гитхаб экшен!!
            Configuration.timeout = 20000;
            Configuration.clickViaJs = true;
            Configuration.browserCapabilities = options;
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            Configuration.browserCapabilities = options;
            Configuration.browser = "firefox";
            Configuration.browserSize = "1920x1080";
            Configuration.headless = false;// или true - для гитхаб экшен!!
            Configuration.timeout = 20000;
            Configuration.clickViaJs = true;
        }

        Configuration.baseUrl = baseURL;
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
    @Description("Close browser")
    public void close() {
        closeWebDriver();
    }
}
