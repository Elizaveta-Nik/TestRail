package wrappers;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class CheckboxWrapper {

    // Метод для клика по чекбоксу с заданным значением
    public void clickCheckboxByValue(String value) {
        SelenideElement checkbox = $(".checkbox-list-input[type='checkbox'][value='" + value + "']");
        checkbox.shouldBe(visible).click();
    }

    // Метод для проверки, выбран ли чекбокс
    public boolean isCheckboxSelected(String value) {
        SelenideElement checkbox = $(".checkbox-list-input[type='checkbox'][value='" + value + "']");
        return checkbox.isSelected();
    }

    // Метод для установки состояния чекбокса (выбрать/снять выбор)
    public void setCheckboxState(String value, boolean state) {
        SelenideElement checkbox = $(".checkbox-list-input[type='checkbox'][value='" + value + "']");
        if (checkbox.isSelected() != state) {
            checkbox.shouldBe(visible).click();
        }
    }
}
