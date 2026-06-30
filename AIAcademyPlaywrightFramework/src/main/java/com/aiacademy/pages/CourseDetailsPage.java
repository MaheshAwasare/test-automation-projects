package com.aiacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CourseDetailsPage {
    private final Page page;
    private final Locator courseTitle;
    private final Locator firstModule;

    public CourseDetailsPage(Page page) {
        this.page = page;

        courseTitle = page.locator("h1");
        firstModule=page.locator
                ("a[href='/course/before-you-code/module/how-problems-get-solved']");
    }
    public String getCourseTitle(){
        return courseTitle.textContent().trim();
    }
    public ModuleDetailsPage clickFirstModule(){
        firstModule.click();
        return new ModuleDetailsPage(page);
    }
}
