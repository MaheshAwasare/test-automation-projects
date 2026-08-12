package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import com.aiacademy.pages.*;
import com.aiacademy.utils.ConfigReader;
import com.aiacademy.utils.ModuleDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FreeCoursesTestNG extends BaseTest {
    @Test(groups = {"sanity", "regression", "freecourse"},
            priority = 5)
    public void verifyAllFreeCoursesForwardNavigation() {

        dashboardPage.clickAllCourses();

        List<String> freeCourses = freeCoursesPage.getAllFreeCourseNames();

        System.out.println("Free Courses Found : " + freeCourses.size());
        System.out.println("Free Courses Name : " + freeCourses);


        for (String courseName : freeCourses) {

            System.out.println();
            System.out.println("==================================");
            System.out.println("Testing Course : " + courseName);
            System.out.println("==================================");

            CourseDetailsPage coursePage =
                    freeCoursesPage.openFreeCourse(courseName);

            System.out.println("Opened Course : " + BaseTest.page.get().url());

            ModuleDetailsPage modulePage =
                    coursePage.openFirstModule();
            System.out.println("Module URL : " + BaseTest.page.get().url());

            modulePage.verifyForwardNavigation();

            freeCoursesPage.navigateBackToCourses();
            System.out.println("Returned URL : " + BaseTest.page.get());
        }

    }

    @Test(groups = {"sanity", "regression", "freecourse"},
            priority = 6)
    public void verifyAllFreeCoursesBackwardNavigation() {
        dashboardPage.clickAllCourses();

        List<String> freeCourses = freeCoursesPage.getAllFreeCourseNames();

        for (String courseName : freeCourses) {

            System.out.println();
            System.out.println("==================================");
            System.out.println("Testing Course : " + courseName);
            System.out.println("==================================");

            CourseDetailsPage coursePage =
                    freeCoursesPage.openFreeCourse(courseName);

            System.out.println("Opened Course : " + BaseTest.page.get().url());

            ModuleDetailsPage modulePage =
                    coursePage.openFirstModule();
            System.out.println("Module URL : " + BaseTest.page.get().url());

            modulePage.verifyForwardNavigation();

            modulePage.verifyBackwardNavigation();

            freeCoursesPage.navigateBackToCourses();
            System.out.println("Returned URL : " + BaseTest.page.get());


        }
    }

    @Test( groups = {"sanity", "regression", "freecourse"},
            priority = 7)
    public void verifyAllFreeCoursesSidebarNavigation() {
        dashboardPage.clickAllCourses();

        List<String> freeCourses = freeCoursesPage.getAllFreeCourseNames();

        for (String courseName : freeCourses) {

            System.out.println();
            System.out.println("==================================");
            System.out.println("Testing Course : " + courseName);
            System.out.println("==================================");

            CourseDetailsPage coursePage =
                    freeCoursesPage.openFreeCourse(courseName);

            System.out.println("Opened Course : " + BaseTest.page.get().url());

            ModuleDetailsPage modulePage =
                    coursePage.openFirstModule();
            System.out.println("Module URL : " + BaseTest.page.get().url());

            modulePage.verifySidebarNavigation();

            freeCoursesPage.navigateBackToCourses();
            System.out.println("Returned URL : " + BaseTest.page.get());


        }
    }
    @Test(groups = {"regression", "freecourse"},
            priority = 9)
    public void verifyAllFreeCoursesReelMode() {
        dashboardPage.clickAllCourses();
        List<String> freeCourses = freeCoursesPage.getAllFreeCourseNames();

        for (String courseName : freeCourses) {

            System.out.println();
            System.out.println("==================================");
            System.out.println("Testing Course : " + courseName);
            System.out.println("==================================");
            CourseDetailsPage coursePage =
                    freeCoursesPage.openFreeCourse(courseName);

            System.out.println("Opened Course : " + BaseTest.page.get().url());

            ModuleDetailsPage modulePage =
                    coursePage.openFirstModule();

            modulePage.verifyReelMode();

            freeCoursesPage.navigateBackToCourses();


        }
    }
    @Test( groups = {"sanity", "regression", "freecourse"},
            priority = 10)
    public void verifyBreadcrumb() {
        dashboardPage.clickAllCourses();
        List<String> freeCourses = freeCoursesPage.getAllFreeCourseNames();

        for (String courseName : freeCourses) {

            System.out.println();
            System.out.println("==================================");
            System.out.println("Testing Course : " + courseName);
            System.out.println("==================================");
            CourseDetailsPage coursePage =
                    freeCoursesPage.openFreeCourse(courseName);

            System.out.println("Opened Course : " + BaseTest.page.get().url());

            ModuleDetailsPage modulePage =
                    coursePage.openFirstModule();

            Assert.assertTrue(moduleDetailsPage.isBreadcrumbVisible(),
                    "Breadcrumb is not visible");
            Assert.assertTrue(moduleDetailsPage.isHomeBreadcrumbVisible(),
                    "Home breadcrumb is not visible");
            String breadcrumbText = moduleDetailsPage.getBreadcrumbText();
            System.out.println("Breadcrumb : " + breadcrumbText);
            String[] items = breadcrumbText.split("/");
            Assert.assertEquals(items[0].trim(), "Home",
                    "Home breadcrumb is incorrect");
            Assert.assertFalse(items[1].trim().isEmpty(),
                    "Course name is missing");
            Assert.assertTrue(items[2].trim().startsWith("Module"),
                    "Module breadcrumb is incorrect");
            System.out.println("Breadcrumb verified successfully");

            freeCoursesPage.navigateBackToCourses();

        }
    }

}
