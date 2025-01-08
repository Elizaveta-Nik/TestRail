package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class ReportsTest extends BaseTest {

    @Test(testName = "Create a new report",
            description = "Verify that a new report can be created with valid data")
    @Description("Creating a new report with specified parameters and verifying its creation.")
    public void checkCreateReports() {
        loginPage.openPage()
                .login(user, password);
        dashboardPage.waitTillOpened();
        reportsPage.openPage("14")
                .createNewReport("Activity Summary")
                .addDescription(
                        "||| :Header 1 | :Header 2 | :Header 3 | :Header 4\n" +
                        "|| Row 1 .. | R1H2 | R1H3 | R1H4\n" +
                        "|| Row 2 .. | R2H2 | R2H3 | R2H4")
                .selectGroupTheChangesBy("Month")
                .selectTheFollowingTimeFrame("This month")
                .includeTheFollowingChanges()
                .testSuites("2")
                .testCases("Assigned To", "Vasia Pupkin","Created On",
                        "250","1", "Every week","Friday","09:00");
    }
}
