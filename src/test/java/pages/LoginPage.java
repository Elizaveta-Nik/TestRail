package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class LoginPage {

    private static final String USER = "#name",
            PASSWORD = "#password",
            BUTTON_NAME = "#button_primary",
            ERROR_MESSAGE = "//div[contains(@class, 'loginpage-message')] ";

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

    @Step("Entering email: {email}.")
    public LoginPage enterEmail(String email) {
        log.info("Entering email: {}", email);
        $(USER).setValue(email);
        return this;
    }

    @Step("Entering password.")
    public LoginPage enterPassword(String password) {
        log.info("Entering password.");
        $(PASSWORD).setValue(password);
        return this;
    }

    @Step("Submitting login form.")
    public LoginPage submitLogin() {
        log.info("Submitting login form.");
        $(BUTTON_NAME).click();
        return this;
    }

    @Step("Verifying error message: {expectedMessage}.")
    public void verifyErrorMessage(String expectedMessage) {
        log.info("Verifying error message: {}", expectedMessage);
        $x(ERROR_MESSAGE).shouldHave(text(expectedMessage));
    }
}