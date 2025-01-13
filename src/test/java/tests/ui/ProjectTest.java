package tests.ui;

import dto.ProjectData;
import dto.WebhookData;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import utils.TestDataFactory;

@Log4j2
public class ProjectTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

    @Test(description = "Verify project creation")
    @Description("Creating a new project with specified parameters and verifying the successful completion of the operation.")
    public void checkCreateProject() {
        SoftAssert softAssert = new SoftAssert();

        loginPage.openPage()
                .login(user, password);
        dashboardPage.openPage()
                .waitTillOpened();
        projectPage.createProject("Project - test", "1");
        // выбор репозитория 1-2-3
        String actualMessage = projectPage.getCreationMessage();
        softAssert.assertTrue(actualMessage.equals("Congratulations! You have created your first project") ||
                        actualMessage.equals("Successfully added the new project."),
                "Unexpected creation message: " + actualMessage);
        softAssert.assertAll();
    }

    @Test(description = "Verify project editing")
    @Description("Editing an existing project with specified parameters and verifying the successful completion of the operation.")
    public void checkEditProject() {
        ProjectData projectData = TestDataFactory.createProjectData();
        WebhookData webhookData = TestDataFactory.createWebhookData(projectData);

        loginPage.openPage()
                .login(user, password);
        dashboardPage.openPage()
                .waitTillOpened();
        projectPage.openPage("30")
                .clickEditButton();
        editPage.access(projectData.getUser(), projectData.getAccess())
                .defect(projectData.getDefectUrl(), projectData.getDefectUrl(), "GitHub")
                .references(projectData.getReferencesUrl(), projectData.getReferencesUrl(), "GitHub")
                .userVariables(projectData.getUserVariable1(), projectData.getUserVariable2(), projectData.getUserVariable3(),
                        projectData.getUserVariable4(), projectData.getUserVariable5())
                .addWebhook(webhookData)
                .saveWebhook()
                .saveProject();

        String actualMessage = editPage.getUpdatedProject();
        softAssert.assertEquals(actualMessage, "Successfully updated the project.");
        softAssert.assertAll();

    }

    @Test(description = "Delete webhook", dependsOnMethods = "checkEditProject" )
    @Description("Deleting a webhook")
    public void checkDeleteWebhook() {
        loginPage.openPage()
                .login(user, password);
        dashboardPage.openPage()
                .waitTillOpened();
        projectPage.openPage("14")
                .clickEditButton();
        editPage.clickWebhookButton()
                .deleteWebhookButton();

        boolean isWebhookPresent = editPage.isWebhookPresent("https://github.com/your-repo/issues/%id%");
        softAssert.assertFalse(isWebhookPresent, "Webhook should be deleted.");
        softAssert.assertAll();
    }
}
