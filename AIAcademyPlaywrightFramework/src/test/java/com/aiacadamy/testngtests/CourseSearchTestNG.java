package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import com.aiacademy.pages.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CourseSearchTestNG extends BaseTest {

    @Test(description = "Verify user can search a course using complete course name")
    public void verifySearchCourseByCompleteCourseName() {

        String expectedCourseName = "Before You Code (FREE)";
        //dashboardPage.waitForDashboardToLoad();
        dashboardPage.enterSearchText(expectedCourseName);

        Assert.assertFalse(
                dashboardPage.getSearchResultHeader().isEmpty(),
                "Search result header is not displayed."
        );

        Assert.assertEquals(
                dashboardPage.getVisibleCourseCount(),
                1,
                "Expected exactly one course to be displayed."
        );

        List<String> visibleCourses = dashboardPage.getVisibleCourseTitles();

        Assert.assertEquals(
                visibleCourses.get(0),
                expectedCourseName,
                "Displayed course name does not match the searched course."
        );
    }

}
