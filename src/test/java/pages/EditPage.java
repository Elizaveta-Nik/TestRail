package pages;

import dto.WebhookData;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import wrappers.CheckboxWrapper;
import wrappers.JavaScriptExecutorWrapper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class EditPage {

    private static final String
            ACCESS = "#projects-tabs-access",
            ACCESS_OPTION = "#access",
            USER_ACCESS_DROPDOWN = ".dropdown-icon-black",
            DEFECTS = "#projects-tabs-defects",
            DEFECT_VIEW_URL = "#defect_id_url",
            DEFECT_ADD_URL = "#defect_add_url",
            DEFECT_PLUGIN = "#defect_plugin_chzn",
            DEFECT_PLUGIN_VALUE = "#defect_plugin_chzn input",
            REFERENCES = "#projects-tabs-references",
            REFERENCES_VIEW_URL = "#reference_id_url",
            REFERENCES_ADD_URL = "#reference_add_url",
            REFERENCES_PLUGIN = "#reference_plugin_chzn",
            REFERENCES_PLUGIN_VALUE = "#reference_plugin_chzn input",
            USER_VARIABLES = "#users-fields-fields",
            LABEL_FIELD = "#userFieldLabel",
            DESCRIPTION_FIELD = "#userFieldDesc",
            SYSTEM_NAME_FIELD = "#userFieldName",
            TYPE_FIELD = "#userFieldType",
            FALLBACK_FIELD = "#userFieldFallback",
            OK_BUTTON = "#userFieldSubmit",
            WEBHOOKS = "#users-webhooks",
            WEBHOOK_NAME = "[data-testid='webhooksTabName']",
            PAYLOAD_URL = "payload_url",
            METHOD = "method",
            CONTENT_TYPE = "content_type",
            HEADERS = "#request_headers",
            PAYLOAD = "#request_payload",
            SECRET = "secret",
            SAVE = "#accept",
            WARNING = "#warning_content",
            CONFIRM = "[data-testid='confirmProjectsAllowChanges']",
            DELETE_WEBHOOK = "#admin-integration-form-delete",
            ERROR_MESSAGE = "#webhookErrors",
            MESSAGE_SUCCESS_DIV_BOX = "[data-testid='messageSuccessDivBox']",
            ENTER_TEMPLATE = "Enter a template",
            ACCEPT = "#accept",
            WEBHOOK_DELETE_ICON = "[data-testid='webhooksTabDeleteIcon']",
            DELETE_WEBHOOK_CONFIRM = "//a[@class='button-black delete-btn-border']",
            WEBHOOK_NOT_FOUND = "Webhooks not found";

    @Step("Setting access with default option: {defaultOption} and user access: {userAccessDropdown}.")
    public EditPage access(String defaultOption, String userAccessDropdown) {
        log.info("Setting access with default option: {} and user access: {}", defaultOption, userAccessDropdown);
        $(ACCESS).click();
        $(ACCESS_OPTION).selectOption(defaultOption);
        $(USER_ACCESS_DROPDOWN).click();
        $(byLinkText(userAccessDropdown)).click();
        log.info("Access settings updated successfully.");
        return this;
    }

    @Step("Setting defect with URL: {defectURL}, Add URL: {defectAddURL}, Plugin Value: {defectPluginValue}.")
    public EditPage defect(String defectURL, String defectAddURL, String defectPluginValue) {
        log.info("Setting defect with URL: {}, Add URL: {}, Plugin Value: {}", defectURL, defectAddURL, defectPluginValue);
        $(DEFECTS).click();
        $(DEFECT_VIEW_URL).setValue(defectURL);
        $(DEFECT_ADD_URL).setValue(defectAddURL);
        JavaScriptExecutorWrapper.dispatchMouseEvent($(DEFECT_PLUGIN));
        $(DEFECT_PLUGIN_VALUE).setValue(defectPluginValue).pressEnter();
        log.info("Defect settings updated successfully.");
        return this;
    }

    @Step("Setting references with URL: {referencesURL}, Add URL: {referencesAddURL}, Plugin Value: {referencesPluginValue}.")
    public EditPage references(String referencesURL, String referencesAddURL, String referencesPluginValue) {
        log.info("Setting references with URL: {}, Add URL: {}, Plugin Value: {}", referencesURL, referencesAddURL,
                referencesPluginValue);
        $(REFERENCES).click();
        $(REFERENCES_VIEW_URL).shouldBe(visible).shouldBe(clickable).setValue(referencesURL);
        $(REFERENCES_ADD_URL).setValue(referencesAddURL);
        JavaScriptExecutorWrapper.dispatchMouseEvent($(REFERENCES_PLUGIN));
        $(REFERENCES_PLUGIN_VALUE).setValue(referencesPluginValue).pressEnter();
        log.info("References settings updated successfully.");
        return this;
    }

    @Step("Adding user variable with label: {label}, description: {description}, system name: {systemName}, type: {type}," +
            " fallback: {fallback}.")
    public EditPage userVariables(String label, String description, String systemName, String type, String fallback) {
        log.info("Adding user variable with label: {}, description: {}, system name: {}, type: {}, fallback: {}",
                label, description, systemName, type, fallback);
        $(USER_VARIABLES).click();
        $(byText("Add User Variable")).click();
        JavaScriptExecutorWrapper.setValueUsingJavaScript($(LABEL_FIELD), label);
        $(DESCRIPTION_FIELD).setValue(description);
        $(SYSTEM_NAME_FIELD).setValue(systemName);
        $(TYPE_FIELD).selectOption(type);
        $(FALLBACK_FIELD).setValue(fallback);
        $(OK_BUTTON).click();
        log.info("User variable added successfully.");
        return this;
    }

    @Step("Click webhook")
    public EditPage clickWebhookButton() {
        $(WEBHOOKS).click();
        $(byText("Add Webhook")).click();
        return this;
    }

    @Step("Delete webhook")
    public EditPage deleteWebhookButton() {
        $(WEBHOOK_DELETE_ICON).click();
        $(byText("Delete Webhook?")).shouldBe(visible);
        $x(DELETE_WEBHOOK_CONFIRM).click();
        $(byText(WEBHOOK_NOT_FOUND)).shouldBe(visible);
        return this;
    }

    @Step("Checking if webhook with name: {webhookName} is present.")
    public boolean isWebhookPresent(String webhookName) {
        try {
            return $x("//td[contains(.,'" + webhookName + "')]").isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Adding webhook with name: {webhookName}, payload URL: {payloadURL}, method: {method}, content type: {contentType}, " +
            "headers: {headers}, payload: {payload}, secret: {secret}, value: {value}.")
    public EditPage addWebhook(WebhookData webhookData) {
        log.info("Adding webhook with name: {}, payload URL: {}, method: {}, content type: {}, headers: {}," +
                        " payload: {}, secret: {}, value: {}",
                webhookData.getWebhookName(), webhookData.getPayloadURL(), webhookData.getMethod(),
                webhookData.getContentType(), webhookData.getHeaders(), webhookData.getPayload(), webhookData.getSecret(),
                webhookData.getValue());
        clickAddWebhookButton();
        fillWebhookForm(webhookData);
        log.info("Webhook added successfully.");
        return this;
    }

    private void fillWebhookForm(WebhookData webhookData) {
        $(WEBHOOK_NAME).setValue(webhookData.getWebhookName());
        $(By.name(PAYLOAD_URL)).setValue(webhookData.getPayloadURL());
        $(By.name(METHOD)).selectOption(webhookData.getMethod());
        $(By.name(CONTENT_TYPE)).selectOption(webhookData.getContentType());
        $(HEADERS).setValue(webhookData.getHeaders());
        $(PAYLOAD).setValue(webhookData.getPayload());
        $(By.name(SECRET)).setValue(webhookData.getSecret());
        CheckboxWrapper checkboxWrapper = new CheckboxWrapper();
        checkboxWrapper.clickCheckboxByValue(webhookData.getValue());
        $(By.name("projects[]")).click();
    }

    private void clickAddWebhookButton() {
        $(WEBHOOKS).click();
        $(byText("Add Webhook")).click();
    }

    @Step("Saving webhook.")
    public EditPage saveWebhook() {
        log.info("Saving webhook");
        $(SAVE).click();
        $(WARNING).shouldBe(visible);
        $(CONFIRM).click();

        $(DELETE_WEBHOOK).shouldBe(visible);
        log.info("Webhook saved successfully");
        return this;
    }

    @Step("Saving project.")
    public EditPage saveProject() {
        log.info("Saving project");
        $(DEFECTS).click();
        $(byText(ENTER_TEMPLATE)).click();
        $(ACCEPT).click();
        $(MESSAGE_SUCCESS_DIV_BOX).shouldBe(visible);
        log.info("Successfully updated the project.");
        return this;
    }

    @Step("Getting the message that the project was updated")
    public String getUpdatedProject() {
        $(MESSAGE_SUCCESS_DIV_BOX).shouldBe(visible);
        String updateMessage = $(MESSAGE_SUCCESS_DIV_BOX).getText();
        log.info("Retrieved update message: {}", updateMessage);
        return updateMessage;
    }
}
