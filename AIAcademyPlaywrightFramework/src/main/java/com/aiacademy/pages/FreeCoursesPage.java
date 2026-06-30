package com.aiacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class FreeCoursesPage {
    private final Page page;
    private final Locator beforeYouCodeCourse;

    public FreeCoursesPage(Page page) {
        this.page = page;
        beforeYouCodeCourse=page.locator("a[href='/course/before-you-code']");
    }
    public CourseDetailsPage clickBeforeYouCodeCourse(){
        beforeYouCodeCourse.click();
        return new CourseDetailsPage(page);
    }
}
