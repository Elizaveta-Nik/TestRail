package tests.api;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static dto.TestCaseData.getAddTestCaseRequestBody;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("API Testing")
@Feature("Test Case Management API")
public class TestCasesTest extends BaseTest {

    private static final int SECTION_ID = 5;
    private static final String CHECK_EMAIL_URL = "index.php?/api/v2/get_user_by_email&email=elizabeth.frisky@gmail.com",
            EMAIL = "elizabeth.frisky@gmail.com",
            ADD_CASE_URL = "index.php?/api/v2/add_case/";

    @Owner("Elizaveta Nikolaenya")
    @Severity(SeverityLevel.MINOR)
    @Test(testName = "Get user by email",
            description = "Verify that user information can be retrieved by email address")
    @Description("Retrieving user information using the email address.")
    public void getUserByEmail() {

        given()
                .auth()
                .preemptive()
                .basic(user, apiKey)
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .get(baseUrlAPI + CHECK_EMAIL_URL)
        .then()
                .log().all()
                .statusCode(200)
                .body("email", equalTo(EMAIL));
    }

    @Owner("Elizaveta Nikolaenya")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Add a new test case", description = "Verify that a new test case can be added successfully")
    @Description("Adding a new test case with valid data.")
    public void addTestCase() {
        String requestBody = getAddTestCaseRequestBody();

        given()
                .auth()
                .preemptive()
                .basic(user, apiKey)
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post(baseUrlAPI + ADD_CASE_URL + SECTION_ID)
        .then()
                .log().all()
                .statusCode(200)
                .body("title", equalTo("This is a test case"))
                .body("section_id", equalTo(SECTION_ID));
    }
}
