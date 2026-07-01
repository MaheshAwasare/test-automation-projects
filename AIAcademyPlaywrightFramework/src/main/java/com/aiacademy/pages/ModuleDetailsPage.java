package com.aiacademy.pages;

import com.aiacademy.utils.ConfigReader;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ModuleDetailsPage {
    private final Page page;
    private final Locator moduleTitle;
    private final Locator nextModuleOneButton;
    private final Locator previousModuleButton;
    private final Locator sidebarModules;

    public ModuleDetailsPage(Page page){
        this.page=page;
        moduleTitle=page.locator("div.page-header h1");
        nextModuleOneButton=page.locator("div.nav-footer a.next");
        previousModuleButton = page.locator("div.nav-footer a.prev");
        sidebarModules = page.locator("a.sidebar-link");
    }
    public String getModuleTitle(){
        return moduleTitle.textContent().trim();
    }
    public ModuleDetailsPage clickNextModule(){
        long delay = Long.parseLong(
                ConfigReader.getProperty("module.navigation.delay")
        );
        nextModuleOneButton.click();
        page.waitForTimeout(delay);
        return this;

    }
    public ModuleDetailsPage clickPreviousModule() {
        previousModuleButton.click();
        return this;
    }
    public ModuleDetailsPage clickModuleFromSidebar(int index) {
        sidebarModules.nth(index).click();
        return this;
    }
}
