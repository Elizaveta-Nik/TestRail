package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class TestRunsAndResultsPage {

    private static final String ADD_TEST_RUN_NAVIGATION_BUTTON = "#navigation-runs-add",
            ADD_FIRST_TEST_RUN_BUTTON = "[data-testid='runAddButton']",
            NAME = "#name";

    public TestRunsAndResultsPage openPage(String projectNumber) {
        log.info("Opening the Test Runs & Results page");
        open("runs/overview/" + projectNumber);
        return this;
    }

    public TestRunsAndResultsPage addTestRun() {
        log.info("Adding a Test Run");
        if ($(ADD_FIRST_TEST_RUN_BUTTON).isDisplayed()) {
            $(ADD_FIRST_TEST_RUN_BUTTON).click();
        } else if ($(ADD_TEST_RUN_NAVIGATION_BUTTON).isDisplayed()) {
            $(ADD_TEST_RUN_NAVIGATION_BUTTON).click();
        } else {
            log.warn("Neither button for adding a test run is displayed.");
            throw new NoSuchElementException("No button available to add a test run.");
        }
        $("[data-testid='runSuiteOkButton']").shouldBe(visible).click();
        return this;
    }
}
