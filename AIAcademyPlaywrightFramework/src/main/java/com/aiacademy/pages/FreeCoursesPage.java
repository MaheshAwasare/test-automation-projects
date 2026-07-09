package com.aiacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.ArrayList;
import java.util.List;

public class FreeCoursesPage {
    private final Page page;
    // Every course card
    private final Locator courseCards;
    // Every course title
    private final Locator courseTitles;
    private final Locator coursesMenu;
    // Constructor
    public FreeCoursesPage(Page page) {
        this.page = page;
        courseCards = page.locator("a.course-card");
        courseTitles = page.locator("a.course-card h3");
        coursesMenu = page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Courses"));
    }
    // Methods
    //Wait until course cards are visible.
    public void waitForCourseList() {

        courseCards.first().waitFor();
    }
    // Returns total number of courses.
    public int getCourseCount() {
        waitForCourseList();
        return courseCards.count();
    }
    //Returns course name by index.
    public String getCourseName(int index) {
        return courseTitles
                .nth(index)
                .innerText()
                .trim();
    }
    public boolean isFreeCourse(int index) {
        Locator card = courseCards.nth(index);
        // Check "Free course" label
        Locator freeLabel = card.locator("span.meta-item");
        if (freeLabel.count() > 0) {
            String label = freeLabel.last().innerText().trim();
            if (label.equalsIgnoreCase("Free course")) {
                return true;
            }
        }
        // Check FREE price badge
        Locator priceBadge = card.locator(".course-card-price");
        if (priceBadge.count() > 0) {
            String price = priceBadge.innerText().trim();
            if (price.equalsIgnoreCase("FREE")) {
                return true;
            }
        }
        // Check course title
        String title = getCourseName(index);
        return title.toUpperCase().contains("(FREE)");
    }
    public boolean isPaidCourse(int index) {
        return !isFreeCourse(index);
    }
    public CourseDetailsPage clickCourse(String courseName) {
        Locator course = page.locator("a.course-card")
                .filter(new Locator.FilterOptions().setHasText(courseName))
                .first();
        course.scrollIntoViewIfNeeded();
        course.waitFor();
        course.click();
        return new CourseDetailsPage(page);
    }
    //Navigate back to course list.
    public void navigateBackToCourses() {
        coursesMenu.click();
        waitForCourseList();
        System.out.println("Returned to All Courses");
    }
    //Returns true if course list is displayed.
    public boolean isCourseListDisplayed() {
        return courseCards.first().isVisible();
    }
    public List<String> getAllFreeCourseNames() {
        List<String> freeCourses = new ArrayList<>();
        int totalCourses = getCourseCount();
        for (int i = 0; i < totalCourses; i++) {
            if (isFreeCourse(i)) {
                freeCourses.add(getCourseName(i));
            }
        }
        return freeCourses;
    }
    public CourseDetailsPage openFreeCourse(String courseName) {

        int totalCourses = getCourseCount();

        for (int i = 0; i < totalCourses; i++) {

            String currentCourse = getCourseName(i);

            if (currentCourse.equalsIgnoreCase(courseName)) {

                courseCards.nth(i).scrollIntoViewIfNeeded();

                courseCards.nth(i).click();

                return new CourseDetailsPage(page);
            }
        }

        throw new RuntimeException(
                "Free Course Not Found : " + courseName);
    }
}

