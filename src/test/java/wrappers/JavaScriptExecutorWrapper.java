package wrappers;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import com.codeborne.selenide.SelenideElement;

public class JavaScriptExecutorWrapper {

    public static void dispatchMouseEvent(SelenideElement element) {
        executeJavaScript(
                "arguments[0].dispatchEvent(new MouseEvent('mousedown', {bubbles: true, cancelable: true}));",
                element);
    }

    public static void setValueUsingJavaScript(SelenideElement element, String value) {
        executeJavaScript("arguments[0].value='" + value + "';", element);
    }
}
