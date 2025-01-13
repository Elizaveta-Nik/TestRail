package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class DashboardPage {

    private static final String CREATE_NEW_PROJECT = "#sidebar-projects-add";

    @Step("Opening the dashboard page.")
    public DashboardPage openPage() {
        log.info("Opening the dashboard page.");
        open("dashboard");
        return this;
    }

    @Step("Waiting for the Dashboard page to be fully opened.")
    public DashboardPage waitTillOpened() {
        log.info("Waiting for the Dashboard page to be fully opened. Checking visibility of the 'Create New Project' button.");
        $(CREATE_NEW_PROJECT).shouldBe(visible);
        log.info("Dashboard page is fully opened.");
        return this;
    }
}
