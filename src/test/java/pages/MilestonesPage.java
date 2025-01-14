package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import wrappers.JavaScriptExecutorWrapper;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class MilestonesPage {

    private static final String ADD_MILESTONES_BUTTON = "#navigation-overview-addmilestones",
            ADD_MILESTONES_SIDEBAR = "#navigation-milestones-add",
            NAME = "#name",
            REFERENCES = "#reference",
            PARENT = "#parent_id_chzn",
            PARENT_VALUE = "#parent_id_chzn input",
            DESCRIPTION = "#description_display",
            ADD_IMAGE = ".icon-markdown-image",
            IMAGE_SELECT = ".attachment-selection",
            ATTACH_BUTTON = "#attachmentNewSubmit",
            DATE = "//a[contains(.,'%s')]",
            COMPLETED = "#is_completed",
            SAVE = "#accept",
            MESSAGE_SAVE_MILESTONE = "[data-testid='messageSuccessDivBox']",
            START_DATE = "#start_on",
            END_DATE = "#due_on",
            ATTACH_IMAGE_SORT = "#attachments-library-sortby",
            IMAGE_SORT_BY = "//a[contains(.,'Name')]",
            IMAGE_INPUT = "(//input[@type='file'])[1]",
            IMAGE_NAME_RESOURCES = "images/test-image.jpg";

    @Step("Opening the Milestones page for project number: {projectNumber}.")
    public MilestonesPage openPage(String projectNumber) {
        log.info("Opening the Milestones page for project number: {}", projectNumber);
        open("milestones/overview/" + projectNumber);
        return this;
    }

    @Step("Adding a milestone.")
    public MilestonesPage addMilestone() {
        log.info("Attempting to add a milestone.");
        if ($(ADD_MILESTONES_BUTTON).isDisplayed()) {
            $(ADD_MILESTONES_BUTTON).click();
            log.info("Clicked on the 'Add Milestones' button.");
        } else if ($(ADD_MILESTONES_SIDEBAR).isDisplayed()) {
            $(ADD_MILESTONES_SIDEBAR).click();
            log.info("Clicked on the 'Add Milestones' sidebar.");
        } else {
            log.warn("Neither button for adding a milestone is displayed.");
            throw new NoSuchElementException("No button available to add a milestone.");
        }
        return this;
    }

    @Step("Setting milestone details with name: {name}, references: {references}, parent value: {parentValue}.")
    public MilestonesPage setMilestoneDetails(String name, String references, String parentValue) {
        log.info("Setting milestone details with name: {}, references: {}, parent value: {}",
                name, references, parentValue);
        $(NAME).setValue(name);
        $(REFERENCES).setValue(references);
        JavaScriptExecutorWrapper.dispatchMouseEvent($(PARENT));
        $(PARENT_VALUE).setValue(parentValue).pressEnter();
        log.info("Milestone details set successfully.");
        return this;
    }

    @Step("Adding description: {descriptionText}.")
    public MilestonesPage addDescription(String descriptionText) {
        log.info("Adding description: {}", descriptionText);
        $(DESCRIPTION).shouldBe(visible).click();
        $(DESCRIPTION).setValue(descriptionText);
        log.info("Description added successfully.");
        return this;
    }

    @Step("Selecting an image for the milestone.")
    public MilestonesPage selectImage() {
        log.info("Selecting an image for the milestone.");
        $(ADD_IMAGE).click();

        File file = new File(getClass().getClassLoader().getResource(IMAGE_NAME_RESOURCES).getFile());
        SelenideElement fileInput = $x(IMAGE_INPUT);
        fileInput.sendKeys(file.getAbsolutePath());
        sleep(2000);

        $(ATTACH_IMAGE_SORT).click();
        $x(IMAGE_SORT_BY).click();

        $(IMAGE_SELECT, 1).shouldBe(visible).click();
        $(ATTACH_BUTTON).click();
        log.info("Image selected and attached successfully.");
        return this;
    }

    @Step("Selecting start date: {startDate} and end date: {endDate} for the milestone.")
    public MilestonesPage selectDates(String startDate, String endDate) {
        log.info("Selecting start date: {} and end date: {}.", startDate, endDate);

        SelenideElement startDateElement = $(START_DATE);
        actions().moveToElement(startDateElement).click(startDateElement).perform();
        $x(String.format(DATE, startDate)).pressEnter();

        SelenideElement endDateElement = $(END_DATE);
        actions().moveToElement(endDateElement).click(endDateElement).perform();
        $x(String.format(DATE, endDate)).pressEnter();

        log.info("Start and end dates selected successfully.");
        return this;
    }

    @Step("Complete the milestone.")
    public MilestonesPage completeMilestone() {
        log.info("Completing the milestone.");
        $(COMPLETED).click();
        log.info("Milestone completed successfully.");
        return this;
    }

    @Step("Saving the milestone.")
    public MilestonesPage saveMilestone() {
        log.info("Saving the milestone.");
        $(SAVE).click();
        log.info("Milestone saved successfully.");
        return this;
    }

    @Step("Getting the message that the milestone was added.")
    public String getMilestoneAdded() {
        $(MESSAGE_SAVE_MILESTONE).shouldBe(visible);
        String addedNewMilestone = $(MESSAGE_SAVE_MILESTONE).getText();
        log.info("Retrieved update message: {}", addedNewMilestone);
        return addedNewMilestone;
    }
}
