package com.aiacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardPage {

    private final Page page;

    private final Locator allCoursesButton;

    public DashboardPage(Page page) {

        this.page = page;

        this.allCoursesButton = page.getByRole(
                AriaRole.TAB,
                new Page.GetByRoleOptions()
                        .setName("All Courses")
        );
    }
    public void clickAllCourses() {

        allCoursesButton.waitFor();

        allCoursesButton.click();
    }
    public boolean isDashboardLoaded() {

        return allCoursesButton.isVisible();

    }
}
