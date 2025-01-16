package tests.ui;

import dto.ReportsData;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

@Epic("Reports Management")
public class ReportsTest extends BaseTest {

    @Owner("Elizaveta Nikolaenya")
    @Feature("Report Creation")
    @Severity(SeverityLevel.CRITICAL)
    @Flaky
    @Test(testName = "Create a new report",
            description = "Verify that a new report can be created with valid data")
    @Description("Creating a new report with specified parameters and verifying its creation.")
    public void checkCreateReports() {
        ReportsData reportData = new ReportsData();
        loginPage.openPage()
                .login(user, password);
        dashboardPage.waitTillOpened();
        reportsPage.openPage("41")
                .createNewReport(reportData.getReportName())
                .addDescription(reportData.getReportDescription())
                .selectGroupTheChangesBy(reportData.getGroupBy())
                .selectTheFollowingTimeFrame(reportData.getTimeFrame())
                .includeTheFollowingChanges()
                .reportSection()
                .testCases(reportData.getFilterForTheTestCases(), reportData.getDropdownOption(),
                        reportData.getColumnChoose(), reportData.getCasesToDisplay(), reportData.getAccessValue(),
                        reportData.getScheduleOption(), reportData.getDayOption(), reportData.getHourOption());
        String actualMessage = reportsPage.getReportAdded();
        Assert.assertEquals(actualMessage, "Successfully added the new report/scheduled report.");
    }
}