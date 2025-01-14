package wrappers;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckboxWrapper {

    public void clickCheckboxByValue(String value) {
        SelenideElement checkbox = $(".checkbox-list-input[type='checkbox'][value='" + value + "']");
        checkbox.shouldBe(visible).click();
    }
}
