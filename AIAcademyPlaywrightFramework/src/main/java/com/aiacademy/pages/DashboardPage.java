package com.aiacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardPage {

    private final Page page;

    private final Locator allCoursesButton;
    private final Locator topNavigationLinks;
    private final Locator startMyAIJourneyButton;

    public DashboardPage(Page page) {

        this.page = page;

        this.allCoursesButton = page.getByRole(
                AriaRole.TAB,
                new Page.GetByRoleOptions()
                        .setName("All Courses")
        );
        topNavigationLinks = page.locator("a.nav-btn");
        startMyAIJourneyButton = page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions()
                        .setName("Start My AI Journey")
        );
    }
    public void clickAllCourses() {

        allCoursesButton.waitFor();

        allCoursesButton.click();
    }
    public boolean isDashboardLoaded() {

        return allCoursesButton.isVisible();

    }
    public void clickTopNavigation(String menuName) {

        topNavigationLinks
                .filter(new Locator.FilterOptions().setHasText(menuName))
                .first()
                .click();

        page.waitForLoadState();
    }
    public String getCurrentUrl() {
        return page.url();
    }
    public void navigateBackToDashboard() {

        page.goBack();

        page.waitForLoadState();
    }
    public void clickStartMyAIJourney() {

        startMyAIJourneyButton.click();

        page.waitForLoadState();
    }
    public void navigateToCourses() {

        clickTopNavigation("Courses");
    }

}
