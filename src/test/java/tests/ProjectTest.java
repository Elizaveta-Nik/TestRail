package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
public class ProjectTest extends BaseTest {

    @Test(description = "Verify project creation")
    @Description("Creating a new project with specified parameters and verifying the successful completion of the operation.")
    public void checkCreateProject() {
        log.info("Starting test: checkCreateProject");
        loginPage.openPage()
                .login(user,password);
        dashboardPage.openPage()
                .waitTillOpened();
        projectPage.createProject("Project 7", "1");
        //выбор репозитория 1-2-3
        log.info("Test checkCreateProject completed successfully");
    }

    @Test(description = "Verify project editing")
    @Description("Editing an existing project with specified parameters and verifying the successful completion of the operation.")
    public void checkEditProject() {
        log.info("Starting test: checkEditProject");

        loginPage.openPage()
                .login(user,password);
        dashboardPage.openPage()
                .waitTillOpened();

        projectPage.openPage("14")
                .clickEditButton();

        editPage.access("Lead", "No Access")
                .defect("https://github.com/your-repo/issues/%id%", "https://github.com/your-repo/issues/%id%", "GitHub")
                .references("https://github.com/your-repo/issues/%id%", "https://github.com/your-repo/issues/%id%", "GitHub")
                .userVariables("test", "project test", "project", "String", "test")
                .webhooks("https://github.com/your-repo/issues/%id%",
                        "https://github.com/your-repo/issues/%id%",
                        "GET",
                        "application/json",
                        "content-encoding: br",
                        "{\n" +
                                "    \"comment\": {\n" +
                                "        \"id\": 12345,\n" +
                                "        \"author\": \"John Doe\",\n" +
                                "        \"content\": \"This is a new comment\",\n" +
                                "        \"created_at\": \"2024-12-27T13:45:00Z\"\n" +
                                "    },\n" +
                                "    \"project\": {\n" +
                                "        \"id\": 67890,\n" +
                                "        \" name\": \"Project Name\"\n" +
                                "    }\n" +
                                "}\n",
                        "test",
                        "plan_updated")
                .saveProject();
        log.info("Test checkEditProject completed successfully");
    }
}
