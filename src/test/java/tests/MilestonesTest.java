package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import pages.MilestonesPage;
import tests.base.BaseTest;

@Log4j2
public class MilestonesTest extends BaseTest {

    @Test(testName = "Create a new milestone",
            description = "Verify that a new milestone can be created with valid details")
    @Description("Creating a new milestone with specified details and verifying its creation.")
    public void checkCreateMilestones() {
        log.info("Starting test: checkCreateMilestones");
        loginPage.openPage()
                .login(user, password);
        dashboardPage.waitTillOpened();
        milestonesPage.openPage("14")
                .addMilestone()
                .setMilestoneDetails("Test", "TEST", "PreTest",
                        "*This is italicized*, and so is _this_.\\n\" +\n" +
                                "                \"**This is bold**, and so is __this__.\\n\" +\n" +
                                "                \"You can use ***italics and bold together*** if you ___have to___")
                .selectImage()
                .selectDate(MilestonesPage.START_DATE, "15")
                .selectDate(MilestonesPage.END_DATE, "16")
                .completeMilestone();
    }
}
