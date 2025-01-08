package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import wrappers.TableWrapper;

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

    public ProjectPage openPage(String projectNumber) {
        log.info("Opening the Milestones page");
        open("projects/overview/" + projectNumber);//каждый новый проект следующая цифра
        return this;
    }

    private static final TableWrapper TABLE_WRAPPER = new TableWrapper(
            ADD_TABLE_ADD_COLUMN, ADD_TABLE_ADD_ROW, REMOVE_ROW_XPATH, CHILD_FORM, FORM_CONTROL);

    public ProjectPage createProject(String projectName, String value) {
        log.info("Creating a new project with name: {}", projectName);
        $(CREATE_NEW_PROJECT_BUTTON).click();
        $(PROJECT_NAME_CREATE).setValue(projectName);
        $(ADD_TABLE).shouldBe(Condition.visible).click();
        createTable();
        $(SAVE_TABLE).shouldBe(Condition.visible).click();
        $(ANNOUNCEMENT).click();
        selectRadioButton(value);
        $(SAVE_PROJECT).shouldBe(Condition.visible).click();

        if ($(byText("Congratulations! You have created your first project")).isDisplayed() ||
                $(byText("Successfully added the new project.")).isDisplayed()) {
            log.info("Project created successfully");
        } else {
            log.warn("Project creation message not found");
            throw new NoSuchElementException("Project creation message not found");
        }
        return this;
    }

    private void createTable() {
        TABLE_WRAPPER.selectOptionInColumn(1, "Center");
        TABLE_WRAPPER.selectOptionInColumn(2, "Left");
        TABLE_WRAPPER.selectOptionInColumn(3, "Right");
        TABLE_WRAPPER.addColumn();
        TABLE_WRAPPER.selectOptionInColumn(5, "Center");
        TABLE_WRAPPER.addRow();
        TABLE_WRAPPER.removeRow();
    }

    private void selectRadioButton(String value) {
        SelenideElement radioButton = $(RADIO_BUTTON + value + "']");
        radioButton.shouldBe(Condition.visible).click();
    }


    public void clickEditButton() {
        log.info("Clicking the edit button");
        $(EDIT_BUTTON).click();
    }
}
