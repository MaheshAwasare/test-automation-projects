package com.aiacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardPage {

    private final Page page;
    private final Locator programmingForBeginnersCategory;

    public DashboardPage(Page page) {
        this.page = page;
        programmingForBeginnersCategory =
                page.getByRole(
                        AriaRole.TAB,
                        new Page.GetByRoleOptions()
                                .setName("Programming for Beginners")
                );
    }

    public void clickProgrammingForBeginnersCategory(){
        programmingForBeginnersCategory.click();
    }
}
