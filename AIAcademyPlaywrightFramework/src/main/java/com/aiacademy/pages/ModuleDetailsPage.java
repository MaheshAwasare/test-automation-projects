package com.aiacademy.pages;

import com.aiacademy.utils.ConfigReader;
import com.aventstack.extentreports.gherkin.model.ScenarioOutline;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class ModuleDetailsPage {
    static int count=0;
    private final Page page;
    // Locators
    private final Locator moduleTitle;
    private final Locator nextButton;
    private final Locator previousButton;
    private final Locator sidebarModules;
    private final Locator reelModeButton;

    // Constructor
    public ModuleDetailsPage(Page page) {
        this.page = page;
        moduleTitle = page.locator("main h1").first();
        nextButton = page.locator("a.next");
        previousButton = page.locator("a.prev");
        sidebarModules = page.locator("a.sidebar-link");
        reelModeButton = page.locator("button.reel-toggle");
    }
    // Wait Methods
    public void waitForModulePage() {
        page.waitForLoadState();

        moduleTitle.waitFor();
    }
    // Verification Methods
    public boolean isModulePageDisplayed() {
        waitForModulePage();
        return moduleTitle.isVisible();
    }
    public String getCurrentModuleTitle() {

        return moduleTitle.innerText().trim();
    }
    public boolean hasNextModule() {

        return nextButton.count() > 0;

    }
    public boolean hasPreviousModule() {
        return previousButton.count() > 0;
    }
    public boolean isSidebarVisible() {

        return sidebarModules.first().isVisible();
    }
    public boolean isReelModeButtonVisible() {

        return reelModeButton.isVisible();
    }
    // Navigation Methods
    public void clickNextModule() {
        if (nextButton.isVisible()) {
            nextButton.scrollIntoViewIfNeeded();
            System.out.println("Count "+ count++);
            nextButton.click();
            waitForModulePage();
        }
    }
    public void clickPreviousModule() {

        if (!hasPreviousModule()) {
            return;
        }

        previousButton.scrollIntoViewIfNeeded();

        previousButton.click();

        waitForModulePage();
    }
    // Sidebar Methods
    public int getSidebarModuleCount() {
        return sidebarModules.count();
    }
    public List<String> getSidebarModuleNames() {
        List<String> modules = new ArrayList<>();
        for (int i = 0; i < sidebarModules.count(); i++) {
            modules.add(
                    sidebarModules
                            .nth(i)
                            .innerText()
                            .trim());
        }
        return modules;
    }
    public void clickSidebarModule(int index) {

        Locator module = sidebarModules.nth(index);

        module.scrollIntoViewIfNeeded();

        module.click();

        waitForModulePage();
    }

    public void clickSidebarModule(String moduleName) {
        Locator module = page.locator("a.sidebar-link")
                .filter(new Locator.FilterOptions().setHasText(moduleName))
                .first();
        module.scrollIntoViewIfNeeded();
        module.click();
        waitForModulePage();
    }
    // Reel Mode
    public void clickReelMode() {
        reelModeButton.scrollIntoViewIfNeeded();
        reelModeButton.click();
    }
    // Reusable Verification Methods
    public void verifyForwardNavigation() {

        System.out.println("========== Forward Navigation Started ==========");

        int moduleCount = 1;

        while (hasNextModule()) {

            String currentTitle = getCurrentModuleTitle();

            System.out.println("Current Module : " + currentTitle);

            clickNextModule();

            String newTitle = getCurrentModuleTitle();

            if (currentTitle.equals(newTitle)) {

                throw new AssertionError(
                        "Forward Navigation Failed.\nCurrent Module : "
                                + currentTitle);
            }

            moduleCount++;

            System.out.println("Navigated To : " + newTitle);
        }

        System.out.println("Reached Last Module.");

        System.out.println("Total Modules Traversed : " + moduleCount);

        System.out.println("========== Forward Navigation Completed ==========");
    }
    public void verifyBackwardNavigation() {

        System.out.println("\n========== Backward Navigation Started ==========");

        int moduleCount = 1;

        while (hasPreviousModule()) {

            String currentTitle = getCurrentModuleTitle();

            System.out.println("Current Module : " + currentTitle);

            clickPreviousModule();

            waitForModulePage();

            String newTitle = getCurrentModuleTitle();

            if (currentTitle.equals(newTitle)) {

                throw new AssertionError(
                        "Backward Navigation Failed.\n" +
                                "Module did not change.\n" +
                                "Current Module : " + currentTitle);
            }

            System.out.println("Navigated To : " + newTitle);

            moduleCount++;
        }

        System.out.println("Reached First Module.");

        System.out.println("Total Modules Traversed : " + moduleCount);

        System.out.println("========== Backward Navigation Completed ==========\n");
    }
    public void verifySidebarNavigation() {

        System.out.println();
        System.out.println("========== Sidebar Navigation Started ==========");

        int totalModules = getSidebarModuleCount();

        System.out.println("Sidebar Modules : " + totalModules);

        for (int i = 1; i < totalModules; i++) {

            String previousUrl = page.url();

            clickSidebarModule(i);

            String currentUrl = page.url();

            if (previousUrl.equals(currentUrl)) {

                System.out.println("Skipping Current Module : " + i);

                continue;
            }

            System.out.println("--------------------------------");

            System.out.println("Sidebar Index : " + i);

            System.out.println("Current URL : " + currentUrl);

        }

        System.out.println();
        System.out.println("========== Sidebar Navigation Completed ==========");
    }
    public void verifyReelMode() {
        if (!isReelModeButtonVisible()) {
            throw new AssertionError("Reel Mode Button Not Visible");
        }
        clickReelMode();
    }
    public void verifyAllClickableElements() {
        if (hasPreviousModule()) {
            previousButton.isEnabled();
        }
        if (hasNextModule()) {
            nextButton.isEnabled();
        }
        reelModeButton.isEnabled();
        for (int i = 0; i < sidebarModules.count(); i++) {
            sidebarModules
                    .nth(i)
                    .isEnabled();
        }
    }
}
