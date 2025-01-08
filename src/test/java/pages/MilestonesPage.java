package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import wrappers.JavaScriptExecutorWrapper;

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
            SAVE = "#accept";
    public static String START_DATE = "#start_on",
            END_DATE = "#due_on";

    public MilestonesPage openPage(String projectNumber) {
        log.info("Opening the Milestones page");
        open("milestones/overview/" + projectNumber);
        return this;
    }

    public MilestonesPage addMilestone() {
        log.info("Adding a milestone");
        if ($(ADD_MILESTONES_BUTTON).isDisplayed()) {
            $(ADD_MILESTONES_BUTTON).click();
        } else if ($(ADD_MILESTONES_SIDEBAR).isDisplayed()) {
            $(ADD_MILESTONES_SIDEBAR).click();
        } else {
            log.warn("Neither button for adding a milestone is displayed.");
            throw new NoSuchElementException("No button available to add a milestone.");
        }
        return this;
    }

    public MilestonesPage setMilestoneDetails(String name, String references, String parentValue, String descriptionText) {
        log.info("Setting milestone details with name: {}, references: {}, parent value: {}, description text: {}",
                name, references, parentValue, descriptionText);
        $(NAME).setValue(name);
        $(REFERENCES).setValue(references);
        JavaScriptExecutorWrapper.dispatchMouseEvent($(PARENT));
        $(PARENT_VALUE).setValue(parentValue).pressEnter();
        $(DESCRIPTION).shouldBe(visible).click();
        $(DESCRIPTION).setValue(descriptionText);
        return this;
    }

    public MilestonesPage selectImage() {
        $(ADD_IMAGE).click();
        $(IMAGE_SELECT, 1).shouldBe(visible).click();
        $(ATTACH_BUTTON).click();
        return this;
    }

    public MilestonesPage selectDate(String dateSelector, String date) {
        SelenideElement dateElement = $(dateSelector);
        actions().moveToElement(dateElement).click(dateElement).perform();
        $x(String.format(DATE, date)).pressEnter();
        return this;
    }

    public void completeMilestone() {
        $(COMPLETED).click();
        $(SAVE).click();
    }
}
