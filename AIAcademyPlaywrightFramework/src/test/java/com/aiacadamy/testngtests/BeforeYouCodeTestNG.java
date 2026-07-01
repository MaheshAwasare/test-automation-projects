package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import com.aiacademy.pages.*;
import com.aiacademy.utils.ConfigReader;
import com.aiacademy.utils.ModuleDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class BeforeYouCodeTestNG extends BaseTest {
    @Test
    public void verifyCompleteCourseForwardNavigation()throws IOException {
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

        List<String> expectedTitles = ModuleDataReader.getModuleTitles();

        for(int i=0; i < expectedTitles.size(); i++){
            String expectedTitle= expectedTitles.get(i);
            String actualTitle=moduleDetailsPage.getModuleTitle();

            System.out.println("Verifying Module " + (i + 1) + " : " + expectedTitle);

            Assert.assertEquals(actualTitle, expectedTitle,
                    "Module title mismatch at Module " + (i+1));

            if(i < expectedTitles.size() - 1){
                moduleDetailsPage.clickNextModule();
            }
        }

    }
    @Test
    public void verifyCompleteCourseBackwardNavigation() throws IOException {

        DashboardPage dashboardPage = new DashboardPage(getPage());
        dashboardPage.clickProgrammingForBeginnersCategory();

        FreeCoursesPage freeCoursesPage = new FreeCoursesPage(getPage());
        freeCoursesPage.clickBeforeYouCodeCourse();

        CourseDetailsPage courseDetailsPage = new CourseDetailsPage(getPage());
        courseDetailsPage.clickFirstModule();

        ModuleDetailsPage moduleDetailsPage = new ModuleDetailsPage(getPage());

        List<String> expectedTitles = ModuleDataReader.getModuleTitles();

        for (int i = 0; i < expectedTitles.size() - 1; i++) {
            moduleDetailsPage.clickNextModule();
        }

        for (int i = expectedTitles.size() - 1; i >= 0; i--) {

            Assert.assertEquals(
                    moduleDetailsPage.getModuleTitle(),
                    expectedTitles.get(i),
                    "Module title mismatch at Module " + (i + 1)
            );

            if (i > 0) {
                moduleDetailsPage.clickPreviousModule();
            }
        }
    }
}
