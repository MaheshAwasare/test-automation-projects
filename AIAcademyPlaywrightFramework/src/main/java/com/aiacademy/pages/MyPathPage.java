package com.aiacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class MyPathPage {
    private final Page page;
    private final Locator learningPathHeading;
    private final Locator yourAIPlanLabel;
    private final Locator planName;
    private final Locator nextUpText;
    private final Locator continueButton;
    private final Locator seeFullPlanButton;
    private final Locator learningPathDescription;
    private final Locator buildMyPersonalisedPlanButton;
    private final Locator browseAllPathsButton;
    private final Locator learningPathCards;
    private final Locator learningPathTitles;
    private final Locator myPathSwitchSection;
    private final Locator startButton;
    private final Locator retakeQuizButton;
    private final Locator clearMyPathButton;

    public MyPathPage(Page page){
        this.page=page;

        learningPathHeading =
                page.getByRole(AriaRole.HEADING,
                        new Page.GetByRoleOptions().setName("Pick your learning path"));
        yourAIPlanLabel =
                page.getByText("Your AI Plan", new Page.GetByTextOptions().setExact(true));

        planName =
                page.locator("div.ns-body h3");

        nextUpText =
                page.locator("div.ns-body p");

        continueButton =
                page.locator("a.assess-btn-primary");

        seeFullPlanButton =
                page.locator("a[href='/my-plan']");

        learningPathDescription =
                page.getByText("Pick your learning path",
                        new Page.GetByTextOptions().setExact(true));

        buildMyPersonalisedPlanButton =
                page.getByRole(
                        AriaRole.BUTTON,
                        new Page.GetByRoleOptions()
                                .setName("Build my personalised plan")
                );

        browseAllPathsButton =
                page.getByRole(
                        AriaRole.LINK,
                        new Page.GetByRoleOptions()
                                .setName("Browse all paths")
                );
        learningPathCards = page.locator("div.mypath-card");

        learningPathTitles = page.locator("div.mypath-card h3");
        myPathSwitchSection = page.locator("section.mypath-switch");

        startButton = page.locator("button.mypath-primary");

        retakeQuizButton = page.locator("button.mypath-secondary");

        clearMyPathButton = page.locator("button.mypath-danger");

    }
    public boolean isMyPathPageDisplayed() {
        learningPathHeading.waitFor();

        System.out.println("Current URL : " + page.url());
        System.out.println("Heading Visible : " + learningPathHeading.isVisible());

        return page.url().contains("/my-path")
                && learningPathHeading.isVisible();
    }
    public boolean isYourAIPlanSectionDisplayed() {
        learningPathHeading.waitFor();
        learningPathDescription.waitFor();

        System.out.println("Label      : " + yourAIPlanLabel.count());
        System.out.println("Plan Name  : " + planName.count());
        System.out.println("Next Up    : " + nextUpText.count());
        System.out.println("Continue   : " + continueButton.count());
        System.out.println("See Plan   : " + seeFullPlanButton.count());

        return seeFullPlanButton.isVisible();
    }

    public boolean isLearningPathHeadingDisplayed() {
        learningPathHeading.waitFor();
        return learningPathHeading.isVisible();
    }
    public boolean isLearningPathDescriptionDisplayed(){
        learningPathDescription.waitFor();
        return learningPathDescription.isVisible();
    }
    public boolean isBuildMyPersonalisedPlanButtonDisplayed() {
        buildMyPersonalisedPlanButton.waitFor();
        return buildMyPersonalisedPlanButton.isVisible();
    }
    public boolean isBrowseAllPathsButtonDisplayed() {
        browseAllPathsButton.waitFor();
        return browseAllPathsButton.isVisible();
    }
    public int getLearningPathCardCount() {
        learningPathCards.first().waitFor();
        return learningPathCards.count();
    }
    public boolean areAllLearningPathCardsDisplayed() {

        learningPathCards.first().waitFor();

        for (int i = 0; i < learningPathCards.count(); i++) {

            if (!learningPathCards.nth(i).isVisible()) {
                return false;
            }
        }

        return true;
    }
    public boolean isLearningPathIconDisplayed(int index) {

        Locator icon = learningPathCards.nth(index).locator(".path-icon");

        return icon.isVisible();
    }
    public boolean isLearningPathTitleDisplayed(int index) {

        Locator title = learningPathCards.nth(index).locator("h3");

        return title.isVisible()
                && !title.textContent().trim().isEmpty();
    }
    public boolean isLearningPathDescriptionDisplayed(int index) {

        Locator description = learningPathCards.nth(index).locator("p");

        return description.isVisible()
                && !description.textContent().trim().isEmpty();
    }
    public boolean isChooseThisPathButtonDisplayed(int index) {

        Locator button = learningPathCards
                .nth(index)
                .locator("button.path-start-btn");

        return button.isVisible();
    }
    public void clickChooseThisPathButton(int index) {
        learningPathCards
                .nth(index)
                .locator("button.path-start-btn")
                .click();
    }
    public void navigateBackToMyPath() {
        page.goBack();
        page.waitForURL("**/my-path");
    }
    public boolean isMyPathSwitchSectionDisplayed() {

        myPathSwitchSection.waitFor();

        return myPathSwitchSection.isVisible();
    }
    public boolean isStartButtonDisplayed() {
        return startButton.isVisible();
    }
    public boolean isRetakeQuizButtonDisplayed() {
        return retakeQuizButton.isVisible();
    }
    public boolean isClearMyPathButtonDisplayed() {
        return clearMyPathButton.isVisible();
    }


}

