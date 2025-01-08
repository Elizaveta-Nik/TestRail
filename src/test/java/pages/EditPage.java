package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import wrappers.CheckboxWrapper;
import wrappers.JavaScriptExecutorWrapper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

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
            DELETE_WEBHOOK = "#admin-integration-form-delete";

    public EditPage access(String defaultOption, String userAccessDropdown) {
        log.info("Setting access with default option: {} and user access dropdown: {}",
                defaultOption, userAccessDropdown);
        $(ACCESS).click();
        $(ACCESS_OPTION).selectOption(defaultOption);
        $(USER_ACCESS_DROPDOWN).click();
        $(byLinkText(userAccessDropdown)).click();
        return this;
    }

    public EditPage defect(String defectURL, String defectAddURL, String defectPluginValue) {
        log.info("Setting defect with URL: {}, Add URL: {}, Plugin Value: {}",
                defectURL, defectAddURL, defectPluginValue);
        $(DEFECTS).click();
        $(DEFECT_VIEW_URL).setValue(defectURL);
        $(DEFECT_ADD_URL).setValue(defectAddURL);
        JavaScriptExecutorWrapper.dispatchMouseEvent($(DEFECT_PLUGIN));
        $(DEFECT_PLUGIN_VALUE).setValue(defectPluginValue).pressEnter();
        return this;
    }

    public EditPage references(String referencesURL, String referencesAddURL, String referencesPluginValue) {
        log.info("Setting references with URL: {}, Add URL: {}, Plugin Value: {}",
                referencesURL, referencesAddURL, referencesPluginValue);
        $(REFERENCES).click();
        $(REFERENCES_VIEW_URL).setValue(referencesURL);
        $(REFERENCES_ADD_URL).setValue(referencesAddURL);
        JavaScriptExecutorWrapper.dispatchMouseEvent($(REFERENCES_PLUGIN));
        $(REFERENCES_PLUGIN_VALUE).setValue(referencesPluginValue).pressEnter();
        return this;
    }

    public EditPage userVariables(String label, String description, String systemName, String type, String fallback) {
        log.info("Adding user variable with label: {}, description: {}, systemName: {}, type: {}, fallback: {}",
                label, description, systemName, type, fallback);
        $(USER_VARIABLES).click();
        $(byText("Add User Variable")).click();
        JavaScriptExecutorWrapper.setValueUsingJavaScript($(LABEL_FIELD), label);
        $(DESCRIPTION_FIELD).setValue(description);
        $(SYSTEM_NAME_FIELD).setValue(systemName);
        $(TYPE_FIELD).selectOption(type);
        $(FALLBACK_FIELD).setValue(fallback);
        $(OK_BUTTON).click();
        return this;
    }

    public EditPage webhooks(String webhookName, String payloadURL, String method, String contentType, String headers,
                             String payload, String secret, String value) {
        log.info("Adding webhook with name: {}, payload URL: {}, method: {}, content type: {}, headers: {}, payload: {}, secret: {}, value: {}",
                webhookName, payloadURL, method, contentType, headers, payload, secret, value);
        $(WEBHOOKS).click();
        $(byText("Add Webhook")).click();
        $(WEBHOOK_NAME).setValue(webhookName);
        $(By.name(PAYLOAD_URL)).setValue(payloadURL);
        $(By.name(METHOD)).selectOption(method);
        $(By.name(CONTENT_TYPE)).selectOption(contentType);
        $(HEADERS).setValue(headers);
        $(PAYLOAD).setValue(payload);
        $(By.name(SECRET)).setValue(secret);
        CheckboxWrapper checkboxWrapper = new CheckboxWrapper();
        checkboxWrapper.clickCheckboxByValue(value);
        $(By.name("projects[]")).click();
        return this;
    }

    public void saveProject() {
        log.info("Saving project");
        $(SAVE).click();
        $(WARNING).shouldBe(visible);
        $(CONFIRM).click();
        $(DELETE_WEBHOOK).shouldBe(visible);
        log.info("Project saved successfully");
    }
}
