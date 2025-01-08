package pages;

import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage {

    private static final String USER = "#name",
            PASSWORD = "#password",
            BUTTON_NAME = "#button_primary";

    public LoginPage openPage() {
        log.info("Opening the login page");
        open("auth/login");
        return this;
    }

    public LoginPage login(String user, String password) {
        log.info("Logging in with user: {}", user);
        $(USER).setValue(user);
        $(PASSWORD).setValue(password);
        $(BUTTON_NAME).click();
        return this;
    }
}