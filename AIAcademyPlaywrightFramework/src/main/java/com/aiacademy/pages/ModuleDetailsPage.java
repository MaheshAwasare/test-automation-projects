package com.aiacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ModuleDetailsPage {
    private final Page page;
    private final Locator moduleOneTitle;
    private final Locator nextModuleOneButton;

    public ModuleDetailsPage(Page page){
        this.page=page;
        moduleOneTitle=page.locator("div.page-header h1");
        nextModuleOneButton=page.locator("div.nav-footer a.next");
    }
    public String getModuleOneTitle(){
        return moduleOneTitle.textContent().trim();
    }
}
