package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import wrappers.TableWrapper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class ProjectPage {

    private static final String CREATE_NEW_PROJECT_BUTTON = "#sidebar-projects-add",
            PROJECT_NAME_CREATE = "#name",
            ANNOUNCEMENT = "#announcement_display",
            ADD_TABLE = ".icon-markdown-table",
            ADD_TABLE_ADD_COLUMN = "#addTableAddColumn",
            ADD_TABLE_ADD_ROW = "#addTableAddRow",
            REMOVE_ROW_XPATH = "//tr[@class='row nohover']/child::td",
            CHILD_FORM = "th:nth-child(",
            FORM_CONTROL = ") .form-control",
            SAVE_TABLE = "#addTableSubmit",
            RADIO_BUTTON = "input[type='radio'][value='",
            SAVE_PROJECT = "#accept",
            EDIT_BUTTON = "[data-testid='editProjectButton']";

    private static final TableWrapper TABLE_WRAPPER = new TableWrapper(
            ADD_TABLE_ADD_COLUMN, ADD_TABLE_ADD_ROW, REMOVE_ROW_XPATH, CHILD_FORM, FORM_CONTROL);

    @Step("Opening the Project page for project number: {projectNumber}.")
    public ProjectPage openPage(String projectNumber) {
        log.info("Opening the Project page for project number: {}", projectNumber);
        open("projects/overview/" + projectNumber);
        return this;
    }

    @Step("Creating a new project with name: {projectName}.")
    public ProjectPage createProject(String projectName, String value) {
        log.info("Creating a new project with name: {}", projectName);
        $(CREATE_NEW_PROJECT_BUTTON).click();
        $(PROJECT_NAME_CREATE).setValue(projectName);
        $(ADD_TABLE).shouldBe(visible).click();
        createTable();
        $(SAVE_TABLE).shouldBe(visible).click();
        $(ANNOUNCEMENT).click();
        selectRadioButton(value);
        $(SAVE_PROJECT).shouldBe(visible).click();
        return this;
    }

    @Step("Creating the project table.")
    private void createTable() {
        log.info("Creating the project table.");
        TABLE_WRAPPER.selectOptionInColumn(1, "Center");
        TABLE_WRAPPER.selectOptionInColumn(2, "Left");
        TABLE_WRAPPER.selectOptionInColumn(3, "Right");
        TABLE_WRAPPER.addColumn();
        TABLE_WRAPPER.selectOptionInColumn(5, "Center");
        TABLE_WRAPPER.addRow();
        TABLE_WRAPPER.removeRow();
        log.info("Project table created successfully.");
    }

    @Step("Selecting radio button with value: {value}.")
    private void selectRadioButton(String value) {
        log.info("Selecting radio button with value: {}", value);
        SelenideElement radioButton = $(RADIO_BUTTON + value + "']");
        radioButton.shouldBe(visible).click();
    }

    @Step("Clicking the edit button.")
    public void clickEditButton() {
        log.info("Clicking the edit button.");
        $(EDIT_BUTTON).click();
    }

    @Step("Getting the message that the project was created")
    public String getCreationMessage() {
        if ($(byText("Congratulations! You have created your first project")).isDisplayed()) {
            log.info("Project created successfully.");
            return "Congratulations! You have created your first project";
        } else if ($(byText("Successfully added the new project.")).isDisplayed()) {
            log.info("Project created successfully.");
            return "Successfully added the new project.";
        } else {
            log.warn("Project creation message not found.");
            throw new NoSuchElementException("Project creation message not found.");
        }
    }
}