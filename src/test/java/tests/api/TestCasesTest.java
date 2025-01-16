package tests.api;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static dto.TestCaseData.getAddTestCaseRequestBody;
import static dto.TestCaseData.getUpdateTestCase;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("API Testing")
@Feature("Test Case Management API")
public class TestCasesTest extends BaseTest {

    private static final int SECTION_ID = 6;
    private static final String CHECK_EMAIL_URL = "index.php?/api/v2/get_user_by_email&email=meyenem698@mowline.com",
            ADD_CASE_URL = "index.php?/api/v2/add_case/",
            CHECK_TEST_CASE = "index.php?/api/v2/get_case/",
            UPDATE_TEST_CASE = "index.php?/api/v2/update_case/";

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
                .body("email", equalTo(user));
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

    @Owner("Elizaveta Nikolaenya")
    @Severity(SeverityLevel.MINOR)
    @Test(testName = "Get a test case by ID", description = "Verify that a test case can be retrieved by its ID")
    @Description("Retrieving a test case using its ID.")
    public void getCaseTest() {

        given()
                .auth()
                .preemptive()
                .basic(user, apiKey)
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(baseUrlAPI + CHECK_TEST_CASE + 16)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(16));
    }

    @Owner("Elizaveta Nikolaenya")
    @Severity(SeverityLevel.CRITICAL)
    @Test(testName = "Update an existing test case", description = "Verify that an existing test case can be updated successfully")
    @Description("Updating an existing test case with valid data.")
    public void updateCaseTest() {

        String requestBody = getUpdateTestCase();

        given()
                .auth()
                .preemptive()
                .basic(user, apiKey)
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(baseUrlAPI + UPDATE_TEST_CASE + 16)
                .then()
                .log().all()
                .statusCode(200)
                .body("priority_id", equalTo(1))
                .body("estimate", equalTo("5m"));
    }
}