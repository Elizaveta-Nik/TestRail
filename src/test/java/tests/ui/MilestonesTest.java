package tests.ui;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

@Log4j2
public class MilestonesTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

    @Test(testName = "Create a completed milestone",
            description = "Verify that a completed milestone can be created with valid details")
    @Description("Creating a new milestone with specified details and verifying its creation.")
    public void checkCreateCompletedMilestones() {
        loginPage.openPage()
                .login(user, password);
        dashboardPage.waitTillOpened();
        milestonesPage.openPage("30")
                .addMilestone()
                .setMilestoneDetails("Test1", "TEST", "Qwer")
                .addDescription("Here’s an inline link to  [Google](https://www.google.com/)")
                .selectDates("15", "16")
                .completeMilestone()
                .saveMilestone();


        String actualMessage = milestonesPage.getMilestoneAdded();
        softAssert.assertTrue(actualMessage.equals("Successfully added the new milestone."),
                actualMessage);
        softAssert.assertAll();
    }

    @Test(testName = "Create a new upcoming milestone",
            description = "Verify that a new upcoming milestone can be created with valid details")
    @Description("Creating a new milestone with specified details and verifying its creation.")
    public void checkCreateUpcomingMilestones() {
        loginPage.openPage()
                .login(user, password);
        dashboardPage.waitTillOpened();
        milestonesPage.openPage("30")
                .addMilestone()
                .setMilestoneDetails("Test", "TEST", "Qwer")
                .selectImage()
                .selectDates("15", "16")
                .saveMilestone();

        String actualMessage = milestonesPage.getMilestoneAdded();
        softAssert.assertTrue(actualMessage.equals("Successfully added the new milestone."),
                actualMessage);
        softAssert.assertAll();
    }
}
