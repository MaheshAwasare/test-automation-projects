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

       /* Assert.assertTrue(
                myPathPage.isYourAIPlanSectionDisplayed(),
                "Your AI Plan section is not displayed."
        );*/

        Assert.assertTrue(
                myPathPage.isLearningPathHeadingDisplayed(),
                "Learning Path heading is not displayed."
        );

        Assert.assertTrue(
                myPathPage.isLearningPathDescriptionDisplayed(),
                "Learning Path description is not displayed."
        );

        Assert.assertTrue(
                myPathPage.isBrowseAllPathsButtonDisplayed(),
                "Browse All Paths button is not displayed."
        );
        /*Assert.assertTrue(myPathPage.isBuildMyPersonalisedPlanButtonDisplayed(),
                "Build My Personalised Plan Button is not displayed");*/

        getTest().pass("Verified My Path page contents successfully.");
    }
    @Test(description = "Verify all Learning Path cards are displayed")
    public void verifyAllLearningPathCardsDisplayed() {

        dashboardPage.clickMyPath();

        int actualCardCount = myPathPage.getLearningPathCardCount();

        System.out.println("Actual Card Count : " +actualCardCount );
        Assert.assertEquals(
                actualCardCount,
                8,
                "Unexpected number of Learning Path cards."
        );
        Assert.assertTrue(
                myPathPage.areAllLearningPathCardsDisplayed(),
                "One or more Learning Path cards are not displayed."
        );
        getTest().pass("Verified all Learning Path cards are displayed successfully.");
    }

    @Test(description = "Verify contents of all Learning Path cards")
    public void verifyLearningPathCardContents() {

        dashboardPage.clickMyPath();

        int totalCards = myPathPage.getLearningPathCardCount();
        System.out.println("Total no. of cards is : " + totalCards);

        for (int i = 0; i < totalCards; i++) {

            Assert.assertTrue(
                    myPathPage.isLearningPathIconDisplayed(i),
                    "Icon is not displayed for Learning Path card at index : " + i
            );

            Assert.assertTrue(
                    myPathPage.isLearningPathTitleDisplayed(i),
                    "Title is not displayed for Learning Path card at index : " + i
            );

            Assert.assertTrue(
                    myPathPage.isLearningPathDescriptionDisplayed(i),
                    "Description is not displayed for Learning Path card at index : " + i
            );

            Assert.assertTrue(
                    myPathPage.isChooseThisPathButtonDisplayed(i),
                    "Choose This Path button is not displayed for Learning Path card at index : " + i
            );
        }

        getTest().pass("Verified contents of all Learning Path cards successfully.");
    }
    @Test(description = "Verify Choose This Path navigation")
    public void verifyChoosePathNavigation() {

        dashboardPage.clickMyPath();

        int totalCards = myPathPage.getLearningPathCardCount();

        for (int i = 0; i < totalCards; i++) {

            myPathPage.clickChooseThisPathButton(i);

            Assert.assertTrue(
                    myPathPage.isMyPathSwitchSectionDisplayed(),
                    "My Path Switch section is not displayed."
            );

            Assert.assertTrue(
                    myPathPage.isStartButtonDisplayed(),
                    "Start button is not displayed."
            );

            Assert.assertTrue(
                    myPathPage.isRetakeQuizButtonDisplayed(),
                    "Retake Quiz button is not displayed."
            );

            Assert.assertTrue(
                    myPathPage.isClearMyPathButtonDisplayed(),
                    "Clear My Path button is not displayed."
            );

            getTest().pass("Choose This Path verified for card index : " + i);

            break;
        }
    }
}
