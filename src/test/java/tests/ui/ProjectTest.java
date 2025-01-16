package tests.ui;

import dto.ProjectData;
import dto.WebhookData;
import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static utils.TestDataFactory.createProjectData;
import static utils.TestDataFactory.createWebhookData;

@Log4j2
@Epic("Project Management")
public class ProjectTest extends BaseTest {

    @Owner("Elizaveta Nikolaenya")
    @Feature("Project Creation")
    @Test(description = "Verify project creation", priority = 1)
    @Severity(SeverityLevel.MINOR)
    @Description("Creating a new project with specified parameters and verifying the successful completion of the operation.")
    public void checkCreateProject() {
        loginPage.openPage()
                .login(user, password);
        dashboardPage.openPage()
                .waitTillOpened();
        projectPage.createProject("Project - test", "1");
        String actualMessage = projectPage.getCreationMessage();
        Assert.assertTrue(actualMessage.equals("Congratulations! You have created your first project") ||
                        actualMessage.equals("Successfully added the new project."),
                "Unexpected creation message: " + actualMessage);
    }

    @Owner("Elizaveta Nikolaenya")
    @Feature("Project Editing")
    @Test(description = "Verify project editing", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Editing an existing project with specified parameters and verifying the successful completion of the operation.")
    public void checkEditProject() {
        ProjectData projectData = createProjectData();
        WebhookData webhookData = createWebhookData(projectData);
        loginPage.openPage()
                .login(user, password);
        dashboardPage.openPage()
                .waitTillOpened();
        projectPage.openPage("41")
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
        Assert.assertEquals(actualMessage, "Successfully updated the project.");
    }

    @Owner("Elizaveta Nikolaenya")
    @Feature("Webhook Management")
    @Test(description = "Delete webhook", priority = 3, dependsOnMethods = "checkEditProject")
    @Severity(SeverityLevel.MINOR)
    @Description("Deleting a webhook")
    @Flaky
    public void checkDeleteWebhook() {
        loginPage.openPage()
                .login(user, password);
        dashboardPage.openPage()
                .waitTillOpened();
        projectPage.openPage("41")
                .clickEditButton();
        editPage.clickWebhookButton()
                .deleteWebhookButton();
        boolean isWebhookPresent = editPage.isWebhookPresent("https://github.com/your-repo/issues/%id%");
        Assert.assertFalse(isWebhookPresent, "Webhook should be deleted.");
    }
}
