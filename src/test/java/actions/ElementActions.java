package actions;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class ElementActions {

    public static void clickElement(String selector) {
        $(selector).shouldBe(visible).click();
    }

    public static void clickElement(By selector) {
        $(selector).shouldBe(visible).click();
    }

    public static void setValue(String selector, String value) {
        $(selector).shouldBe(visible).setValue(value);
    }

    public static void setValue(By selector, String value) {
        $(selector).shouldBe(visible).setValue(value);
    }

    public static void selectOption(String selector, String option) {
        $(selector).shouldBe(visible).selectOption(option);
    }

    public static void selectOption(By selector, String option) {
        $(selector).shouldBe(visible).selectOption(option);
    }
}
