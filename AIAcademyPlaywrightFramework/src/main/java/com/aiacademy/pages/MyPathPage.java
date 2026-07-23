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
                page.locator("a.assess-btn-ghost");

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
                        AriaRole.BUTTON,
                        new Page.GetByRoleOptions()
                                .setName("Browse all paths")
                );

    }
    public boolean isMyPathPageDisplayed() {
        learningPathHeading.waitFor();

        System.out.println("Current URL : " + page.url());
        System.out.println("Heading Visible : " + learningPathHeading.isVisible());

        return page.url().contains("/my-path")
                && learningPathHeading.isVisible();
    }
    public boolean isYourAIPlanSectionDisplayed() {

        System.out.println("Label      : " + yourAIPlanLabel.count());
        System.out.println("Plan Name  : " + planName.count());
        System.out.println("Next Up    : " + nextUpText.count());
        System.out.println("Continue   : " + continueButton.count());
        System.out.println("See Plan   : " + seeFullPlanButton.count());

        return yourAIPlanLabel.isVisible()
                && planName.isVisible()
                && nextUpText.isVisible()
                && continueButton.isVisible()
                && seeFullPlanButton.isVisible();
    }

    public boolean isLearningPathSectionDisplayed() {

        return learningPathHeading.isVisible()
                && learningPathDescription.isVisible();
    }

    public boolean isBuildMyPersonalisedPlanButtonDisplayed() {

        return buildMyPersonalisedPlanButton.isVisible();
    }

    public boolean isBrowseAllPathsButtonDisplayed() {

        return browseAllPathsButton.isVisible();
    }

}

