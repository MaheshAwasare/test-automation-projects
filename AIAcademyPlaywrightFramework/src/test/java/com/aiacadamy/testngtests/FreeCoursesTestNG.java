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
    @Test
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

    @Test
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

    @Test
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
}
