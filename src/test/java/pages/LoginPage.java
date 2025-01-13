package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage {

    private static final String USER = "#name",
            PASSWORD = "#password",
            BUTTON_NAME = "#button_primary";

    @Step("Opening the login page for user authentication.")
    public LoginPage openPage() {
        log.info("Opening the login page for user authentication.");
        open("auth/login");
        return this;
    }

    @Step("Logging in with user: {user}.")
    public LoginPage login(String user, String password) {
        log.info("Logging in with user: {}", user);
        $(USER).setValue(user);
        $(PASSWORD).setValue(password);
        $(BUTTON_NAME).click();
        log.info("Login button clicked. Awaiting response...");
        return this;
    }
}