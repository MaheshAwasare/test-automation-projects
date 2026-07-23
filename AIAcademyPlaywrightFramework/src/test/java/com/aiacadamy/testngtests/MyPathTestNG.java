package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyPathTestNG extends BaseTest {
    @Test(description = "Verify user can navigate to My Path page")
    public void verifyMyPathNavigation() {
        dashboardPage.clickMyPath();
        Assert.assertTrue(
                myPathPage.isMyPathPageDisplayed(),
                "Failed to navigate to My Path page."
        );
        getTest().pass("Successfully navigated to My Path page.");
    }
    @Test(description = "Verify My Path page contents are displayed correctly")
    public void verifyMyPathPageContents() {

        dashboardPage.clickMyPath();

        /*Assert.assertTrue(
                myPathPage.isYourAIPlanSectionDisplayed(),
                "Your AI Plan section is not displayed."
        );*/

        Assert.assertTrue(
                myPathPage.isLearningPathSectionDisplayed(),
                "Learning Path section is not displayed."
        );

        Assert.assertTrue(
                myPathPage.isBuildMyPersonalisedPlanButtonDisplayed(),
                "Build My Personalised Plan button is not displayed."
        );

        Assert.assertTrue(
                myPathPage.isBrowseAllPathsButtonDisplayed(),
                "Browse All Paths button is not displayed."
        );

        getTest().pass("Verified My Path page contents successfully.");
    }
}
