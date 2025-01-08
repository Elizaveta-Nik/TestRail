package pages;

import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class DashboardPage {

    private static final String CREATE_NEW_PROJECT = "#sidebar-projects-add",
            PROJECT_NAME_BUTTON = "//a[contains(text(), '%s')]";

    public DashboardPage openPage() {
        log.info("Opening the dashboard page");
        open("dashboard");
        return this;
    }

    public DashboardPage waitTillOpened() {
        log.info("Waiting for the Dashboard page to be fully opened");
        $(CREATE_NEW_PROJECT).shouldBe(visible);
        return this;
    }

    public void clickProjectNameButton(String projectName) {
        $x(String.format(PROJECT_NAME_BUTTON, projectName)).click();
    }
}
