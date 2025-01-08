package tests;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestCases extends BaseTest {

    private static final int PROJECT_ID = 14,
            SECTION_ID = 5;
    private static final String BASE_URL = "https://meyenem698.testrail.io/";

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
                .get(BASE_URL + "index.php?/api/v2/get_user_by_email&email=elizabeth.frisky@gmail.com")
                .then()
                .log().all()
                .statusCode(200)
                .body("email", equalTo("elizabeth.frisky@gmail.com"));
    }

    @Test(testName = "Add a new test case",
            description = "Verify that a new test case can be added successfully")
    @Description("Adding a new test case with valid data.")
    public void addTestCase() {
        String requestBody = "{\n" +
                "    \"title\": \"This is a test case\",\n" +
                "    \"type_id\": 1,\n" +
                "    \"priority_id\": 3,\n" +
                "    \"estimate\": \"3m\",\n" +
                "    \"refs\": \"RF-1, RF-2\",\n" +
                "    \"custom_steps_separated\": [\n" +
                "        {\n" +
                "            \"content\": \"Step 1\",\n" +
                "            \"expected\": \"Expected Result 1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"content\": \"Step 2\",\n" +
                "            \"expected\": \"Expected Result 2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"shared_step_id\": 3\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        given()
                .auth()
                .preemptive()
                .basic(user, apiKey)
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "index.php?/api/v2/add_case/" + SECTION_ID)
                .then()
                .log().all()
                .statusCode(200)
                .body("title", equalTo("This is a test case"))
                .body("section_id", equalTo(SECTION_ID));
    }

    //этот тест не работает, его надо отредактировать
    @Test
    public void addRUN() {
        String requestBody = "{\n" +
                "    \"suite_id\": 2,\n" +
                "    \"name\": \"This is a new test run\",\n" +
                "    \"milestone_id\": 4,\n" +
                "    \"assignedto_id\": 1,\n" +
                "    \"include_all\": true,\n" +
                "    \"case_ids\": [5]\n" +
                "}";

        given().auth()
                .preemptive()
                .basic(user, apiKey)
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "index.php?/api/v2/add_run/{project_id}", PROJECT_ID)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("This is a new test run"))
                .body("suite_id", equalTo(1));
    }
}

