package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StartMyAIJourneyTestNG extends BaseTest {
    @Test(priority = 1)
    public void verifyCuriousBeginnerLearningPlan() {

        getTest().info("Starting Start My AI Journey Assessment");

        startMyAIJourneyPage.clickStartMyAIJourney();

        Assert.assertTrue(
                startMyAIJourneyPage.isAssessmentPageOpened(),
                "Assessment page is not opened.");

        getTest().pass("Assessment page opened successfully.");

        //==============================
        // Question 1
        //==============================

        startMyAIJourneyPage.answerQuestion("I'm curious");

        //==============================
        // Question 2
        //==============================

        startMyAIJourneyPage.answerQuestion("Non-technical");

        //==============================
        // Question 3
        //==============================

        startMyAIJourneyPage.answerMultiSelectQuestion(
                "Nothing yet"
        );

        //==============================
        // Question 4
        //==============================

        startMyAIJourneyPage.answerQuestion("Save 5+");

        //==============================
        // Question 5
        //==============================

        startMyAIJourneyPage.answerQuestion("Less than 2 hours");

        //==============================
        // Question 6
        //==============================

        startMyAIJourneyPage.answerQuestion("Student");

        //==============================
        // Question 7
        //==============================

        startMyAIJourneyPage.answerQuestion("No GPU");

        //==============================
        // Question 8
        //==============================

        startMyAIJourneyPage.answerQuestion("₹0");

        //==============================
        // Question 9
        //==============================

        startMyAIJourneyPage.answerMultiSelectQuestion(
                "Writing"
        );

        //==============================
        // Question 10
        //==============================

        startMyAIJourneyPage.answerQuestion("This weekend");

        //==============================
        // Question 11
        //==============================

        startMyAIJourneyPage.answerQuestion("Reading");

        //==============================
        // Question 12
        //==============================

        startMyAIJourneyPage.answerMultiSelectQuestion(
                "None of the below"
        );

        getTest().info("Assessment completed successfully.");

        //===================================
        // Result Verification
        //===================================

        Assert.assertTrue(
                startMyAIJourneyPage.isResultPageOpened(),
                "Result page was not displayed.");

        Assert.assertTrue(
                startMyAIJourneyPage.verifyArchetype("The Curious Beginner"),
                "Incorrect Archetype displayed.");

        Assert.assertTrue(
                startMyAIJourneyPage.isBeginnerBadgeDisplayed(),
                "Beginner badge not displayed.");

        Assert.assertTrue(
                startMyAIJourneyPage.verifyCoursePresent(
                        "AI for Absolute Beginners"),
                "AI for Absolute Beginners course missing.");

        Assert.assertTrue(
                startMyAIJourneyPage.verifyCoursePresent(
                        "ChatGPT & Claude"),
                "ChatGPT & Claude course missing.");

        getTest().pass("Learning plan verified successfully.");

    }
}
