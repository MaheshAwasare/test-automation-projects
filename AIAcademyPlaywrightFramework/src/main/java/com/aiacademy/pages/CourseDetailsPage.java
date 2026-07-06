package com.aiacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailsPage {
    private final Page page;
    // Locators
    // Course Title
    private final Locator courseTitle;
    // All Module Cards
    private final Locator moduleCards;
    // Module Titles
    private final Locator moduleTitles;
    // Constructor
    public CourseDetailsPage(Page page) {
        this.page = page;
        courseTitle = page.locator("h1");
        moduleCards = page.locator("a.module-item");
        moduleTitles = page.locator("a.module-item h3");
    }
    // Wait Methods
    //Wait until Course Details page is loaded.
    public void waitForCoursePage() {
        courseTitle.waitFor();
    }
    // Verification Methods
    // Verify Course Details page is displayed.
    public boolean isCoursePageDisplayed() {
        waitForCoursePage();
        return courseTitle.isVisible();
    }
     //Returns Course Title.
    public String getCourseTitle() {
        waitForCoursePage();
        return courseTitle.innerText().trim();
    }
    // Module Methods
    //Returns total modules available
    public int getModuleCount() {
        return moduleCards.count();
    }
    //Returns Module Name by index.
    public String getModuleName(int index) {
        return moduleTitles
                .nth(index)
                .innerText()
                .trim();
    }
    //Returns all Module Names.
    public List<String> getModuleNames() {
        List<String> modules = new ArrayList<>();
        int totalModules = getModuleCount();
        for (int i = 0; i < totalModules; i++) {
            modules.add(getModuleName(i));
        }
        return modules;
    }
    //Opens First Module.
    public ModuleDetailsPage openFirstModule() {
        moduleCards.first().scrollIntoViewIfNeeded();

        moduleCards.first().click();

        ModuleDetailsPage modulePage = new ModuleDetailsPage(page);

        modulePage.waitForModulePage();

        return modulePage;
    }

    //Opens Module by Index.
    public ModuleDetailsPage openModule(int index) {
        moduleCards
                .nth(index)
                .scrollIntoViewIfNeeded();
        moduleCards
                .nth(index)
                .click();
        return new ModuleDetailsPage(page);
    }

    //Opens Module by Name.
    public ModuleDetailsPage openModule(String moduleName) {
        Locator module = page.locator("a.module-item")
                .filter(new Locator.FilterOptions().setHasText(moduleName))
                .first();
        module.scrollIntoViewIfNeeded();
        module.click();
        return new ModuleDetailsPage(page);
    }
}
