package com.aiacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.List;

public class DashboardPage {

    private final Page page;

    private final Locator allCoursesButton;
    private final Locator topNavigationLinks;
    private final Locator startMyAIJourneyButton;
    private final Locator themeToggleButton;
    private final Locator htmlTag;
    private final Locator resumeButton;
    private final Locator searchInput;
    private final Locator clearSearchButton;
    private final Locator searchResultHeader;
    private final Locator courseCards;
    private final Locator courseTitles;
    private final Locator sidebar;


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
        themeToggleButton=page.locator("button[aria-label='Toggle theme']");
        htmlTag=page.locator("html");
        resumeButton=page.locator("a.dh-cta");
        searchInput = page.locator("input.hp-sb-search-input");
        clearSearchButton = page.getByLabel("Clear filter");
        searchResultHeader = page.locator(".search-results-header");
        courseCards = page.locator("a.course-card");
        courseTitles = page.locator("a.course-card h3");
        sidebar=page.locator("aside.hp-sidebar");

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
    public void clickThemeToggle(){
        themeToggleButton.click();
        page.waitForFunction
                ("() => document.documentElement.getAttribute('data-theme') !== null");
    }
    public String getCurrentTheme(){
        return htmlTag.getAttribute("data-theme");
    }
    public boolean isResumeButtonVisible(){
        resumeButton.waitFor();
        System.out.println(page.locator("a.dh-cta").count());
        return resumeButton.isVisible();
    }
    public boolean isResumeButtonEnable(){
        return resumeButton.isEnabled();
    }
    public String getResumeButtonText(){
        return resumeButton.innerText().trim();
    }
    public void clickResumeButton(){
        resumeButton.click();
        page.waitForLoadState();
    }

    public void enterSearchText(String searchText) {

        searchInput.scrollIntoViewIfNeeded();

        searchInput.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
        );

        searchInput.click();

        searchInput.clear();

        searchInput.fill(searchText);
    }

    public void clearSearch() {
        if (clearSearchButton.isVisible()) {
            clearSearchButton.click();
        }
    }
    public String getSearchResultHeader() {
        searchResultHeader.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
        );

        return searchResultHeader.innerText().trim();
    }

    public int getVisibleCourseCount() {
        return (int) courseCards.filter(
                new Locator.FilterOptions().setVisible(true)
        ).count();
    }

    public List<String> getVisibleCourseTitles() {
        return courseCards
                .filter(new Locator.FilterOptions().setVisible(true))
                .locator("h3")
                .allInnerTexts()
                .stream()
                .map(String::trim)
                .toList();
    }

    public void waitForDashboardToLoad() {
        page.waitForURL(url -> url.contains("dashboard"));

        page.waitForLoadState(LoadState.NETWORKIDLE);

        searchInput.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE));
    }

}


