package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import com.aiacademy.pages.*;
import com.aiacademy.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BeforeYouCodeTestNG extends BaseTest {
    @Test
    public void verifyBeforeYouCodeCourseNavigation(){
        LoginPage loginPage=new LoginPage(getPage()) ;

        loginPage.openHomePage();
        loginPage.clickLoginButton();
        loginPage.enterEmail(ConfigReader.getProperty("test.email"));
        loginPage.enterPassword(ConfigReader.getProperty("test.password"));
        loginPage.clickOnSignInButton();

        DashboardPage dashboardPage=new DashboardPage(getPage());

        dashboardPage.clickProgrammingForBeginnersCategory();

        FreeCoursesPage freeCoursesPage=new FreeCoursesPage(getPage());

        CourseDetailsPage courseDetailsPage=freeCoursesPage.clickBeforeYouCodeCourse();

        Assert.assertEquals(courseDetailsPage.getCourseTitle(),
                "Before You Code (FREE)","Course title is incorrect.");

        ModuleDetailsPage moduleDetailsPage=courseDetailsPage.clickFirstModule();

        Assert.assertEquals(moduleDetailsPage.getModuleOneTitle(),
                "How Problems Really Get Solved","Module title is incorrect.");

    }
}
