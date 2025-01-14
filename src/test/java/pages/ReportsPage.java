package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class ReportsPage {

    private static final String CREATE_REPORT = "//*[@id='sidebar']//a[contains(text(), '%s')]",
            DESCRIPTION = "#description",
            GROUP_BY = "#custom_cases_groupby",
            TIME_FRAME = "#custom_changes_daterange",
            CHECK_CHANGES = "#custom_cases_include_new",
            TEST_SUITES = "//a[@class='tab2 ']",
            INCLUDE_ALL = "#custom_sections_include_all",
            TEST_CASES = "//a[@class='tab3']",
            FILTER_CHANGE = "#custom_cases_filter_change",
            SET_FILTER = "//a[contains(.,'%s')]",
            DROPDOWN_FILTER = "#filter-cases\\3A assigned_to_id .form-control",
            FILTER_CASES_BUTTON_APPLY = "#filterCasesApply",
            ADD_COLUMN = "#custom_cases_add",
            ADD_COLUMNS_ITEMS = "#addColumnItems",
            ADD_COLUMNS_BUTTON = "#addColumnSubmit",
            CUSTOM_CASES = "#custom_cases_limit",
            CASES_LIMIT = "#custom_cases_limit",
            CHECKBOX_LINK = "#custom_content_hide_links",
            REPORT_ACCESS = "input[name='access'][value='",
            SCHEDULE_INTERVAL = "#schedule_interval",
            SCHEDULE_WEEKDAY = "#schedule_weekday",
            SCHEDULE_HOUR = "#schedule_hour",
            NOTIFY_USER = "#notify_user",
            SUBMIT = "#submit",
            MASSAGE_SAVE_REPORT = "[data-testid='messageSuccessDivBox']";

    @Step("Opening the Reports page for project number: {projectNumber}.")
    public ReportsPage openPage(String projectNumber) {
        log.info("Opening the Reports page for project number: {}", projectNumber);
        open("reports/overview/" + projectNumber);
        return this;
    }

    @Step("Creating a new report with name: {reportName}.")
    public ReportsPage createNewReport(String reportName) {
        log.info("Creating a new report with name: {}", reportName);
        $x(String.format(CREATE_REPORT, reportName)).click();
        return this;
    }

    @Step("Adding description: {description}.")
    public ReportsPage addDescription(String description) {
        log.info("Adding description: {}", description);
        $(DESCRIPTION).click();
        $(DESCRIPTION).setValue(description);
        return this;
    }

    @Step("Selecting group by: {selectGroupBy}.")
    public ReportsPage selectGroupTheChangesBy(String selectGroupBy) {
        log.info("Selecting group by: {}", selectGroupBy);
        $(GROUP_BY).selectOption(selectGroupBy);
        return this;
    }

    @Step("Selecting time frame: {selectTimeFrame}.")
    public ReportsPage selectTheFollowingTimeFrame(String selectTimeFrame) {
        log.info("Selecting time frame: {}", selectTimeFrame);
        $(TIME_FRAME).selectOption(selectTimeFrame);
        return this;
    }

    @Step("Including the following changes.")
    public ReportsPage includeTheFollowingChanges() {
        log.info("Including the following changes.");
        $(CHECK_CHANGES).click();
        return this;
    }

    @Step("Selecting test suites with value: {value}.")
    public ReportsPage reportSection() {
        log.info("Selecting test suites with value");
        $x(TEST_SUITES).click();
        $(INCLUDE_ALL).click();
        return this;
    }

    @Step("Testing cases with filter: {filterForTheTestCases}, dropdown option: {dropdownOption}, column: {columnChoose}," +
            " cases to display: {casesToDisplay}, access value: {value}, schedule option: {scheduleOption}," +
            " day option: {dayOption}, hour option: {hourOption}.")
    public ReportsPage testCases(String filterForTheTestCases, String dropdownOption, String columnChoose,
                                 String casesToDisplay, String value, String scheduleOption, String dayOption, String hourOption) {
        log.info("Testing cases with filter: {}, dropdown option: {}, column: {}, cases to display: {}," +
                        " access value: {}, schedule option: {}, day option: {}, hour option: {}",
                filterForTheTestCases, dropdownOption, columnChoose, casesToDisplay, value, scheduleOption, dayOption, hourOption);
        clickTestCases();
        applyFilter(filterForTheTestCases, dropdownOption);
        addColumn(columnChoose);
        setCasesLimit(casesToDisplay);
        clickCheckBoxLinkInReport();
        reportAccess(value);
        scheduleInterval(scheduleOption, dayOption, hourOption);
        notifyUser();
        addReport();
        return this;
    }

    @Step("Clicking on Test Cases tab.")
    private void clickTestCases() {
        log.info("Clicking on Test Cases tab.");
        $x(TEST_CASES).click();
    }

    @Step("Applying filter: {filterForTheTestCases} with dropdown option: {dropdownOption}.")
    private void applyFilter(String filterForTheTestCases, String dropdownOption) {
        log.info("Applying filter: {}, dropdown option: {}", filterForTheTestCases, dropdownOption);
        $(FILTER_CHANGE).click();
        $x(String.format(SET_FILTER, filterForTheTestCases)).click();
        $(DROPDOWN_FILTER).selectOption(dropdownOption);
        $(FILTER_CASES_BUTTON_APPLY).click();
    }

    @Step("Adding column: {columnChoose}.")
    private void addColumn(String columnChoose) {
        log.info("Adding column: {}", columnChoose);
        $(ADD_COLUMN).click();
        $(ADD_COLUMNS_ITEMS).selectOption(columnChoose);
        $(ADD_COLUMNS_BUTTON).click();
    }

    @Step("Setting cases limit to: {casesToDisplay}.")
    private void setCasesLimit(String casesToDisplay) {
        log.info("Setting cases limit to: {}", casesToDisplay);
        $(CASES_LIMIT).click();
        $(CUSTOM_CASES).selectOption(casesToDisplay);
    }

    @Step("Clicking checkbox link in report.")
    private void clickCheckBoxLinkInReport() {
        log.info("Clicking checkbox link in report.");
        $(CHECKBOX_LINK).click();
    }

    @Step("Setting report access with value: {value}.")
    private void reportAccess(String value) {
        log.info("Setting report access with value: {}", value);
        $(REPORT_ACCESS + value + "']").click();
    }

    @Step("Scheduling interval with option: {scheduleOption}, day: {dayOption}, hour: {hourOption}.")
    private void scheduleInterval(String scheduleOption, String dayOption, String hourOption) {
        log.info("Scheduling interval with option: {}, day: {}, hour: {}", scheduleOption, dayOption, hourOption);
        $(SCHEDULE_INTERVAL).selectOption(scheduleOption);
        $(SCHEDULE_WEEKDAY).selectOption(dayOption);
        $(SCHEDULE_HOUR).selectOption(hourOption);
    }

    @Step("Notifying user.")
    private void notifyUser() {
        log.info("Notifying user.");
        $(NOTIFY_USER).click();
    }

    @Step("Submitting the report.")
    private void addReport() {
        log.info("Submitting the report.");
        $(SUBMIT).click();
    }

    @Step("Getting the message that the report was added")
    public String getReportAdded() {
        $(MASSAGE_SAVE_REPORT).shouldBe(visible);
        String addedNewReport = $(MASSAGE_SAVE_REPORT).getText();
        log.info("Retrieved update message: {}", addedNewReport);
        return addedNewReport;
    }
}