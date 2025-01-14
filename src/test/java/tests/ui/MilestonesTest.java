package tests.ui;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Log4j2
@Epic("Milestones Management")
public class MilestonesTest extends BaseTest {

    @Owner("Elizaveta Nikolaenya")
    @Feature("Completed Milestones")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Create a completed milestone", priority = 1,
            description = "Verify that a completed milestone can be created with valid details")
    @Description("Creating a new milestone with specified details and verifying its creation.")
    public void checkCreateCompletedMilestones() {
        loginPage.openPage()
                .login(user, password);
        dashboardPage.waitTillOpened();
        milestonesPage.openPage("36")
                .addMilestone()
                .setMilestoneDetails("Test1", "TEST", "Qwer")
                .addDescription("Here’s an inline link to  [Google](https://www.google.com/)")
                .selectDates("14", "15")
                .completeMilestone()
                .saveMilestone();
        String actualMessage = milestonesPage.getMilestoneAdded();
        Assert.assertEquals(actualMessage, "Successfully added the new milestone.");
    }

    @Owner("Elizaveta Nikolaenya")
    @Feature("Upcoming Milestones")
    @Severity(SeverityLevel.NORMAL)
    @Test(testName = "Create a new upcoming milestone", priority = 2,
            description = "Verify that a new upcoming milestone can be created with valid details")
    @Description("Creating a new milestone with specified details and verifying its creation.")
    public void checkCreateUpcomingMilestones() {
        loginPage.openPage()
                .login(user, password);
        dashboardPage.waitTillOpened();
        milestonesPage.openPage("36")
                .addMilestone()
                .setMilestoneDetails("Test", "TEST", "Qwer")
                .selectImage()
                .selectDates("20", "21")
                .saveMilestone();
        String actualMessage = milestonesPage.getMilestoneAdded();
        Assert.assertEquals(actualMessage, "Successfully added the new milestone.");
    }
}