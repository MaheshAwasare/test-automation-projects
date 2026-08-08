package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import com.aiacademy.pages.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardTestNG extends BaseTest {
    @Test(groups = {"smoke", "regression", "dashboard"},
            priority = 2)
    public void verifyTopNavigation() {

        String[][] menus = {
                {"Packs", "/packs"},
                {"My Path", "/my-path"},
                {"Blog", "/blog"},
                {"Profile", "/profile"}
        };

        for (String[] menu : menus) {

            String menuName = menu[0];
            String expectedUrl = menu[1];

            System.out.println("-----------------------------------");
            System.out.println("Opening : " + menuName);

            dashboardPage.clickTopNavigation(menuName);
            System.out.println(dashboardPage.getCurrentUrl());

            Assert.assertTrue(
                    dashboardPage.getCurrentUrl().contains(expectedUrl),
                    menuName + " page did not open."
            );

            System.out.println(menuName + " opened successfully.");

            dashboardPage.navigateBackToDashboard();
        }
    }
    @Test(
            groups = {"wip"},
            enabled = false
    )
    public void verifyStartMyAIJourney() {

        System.out.println("----------------------------------------");
        System.out.println("Verifying Start My AI Journey");

        dashboardPage.clickStartMyAIJourney();

        System.out.println(dashboardPage.getCurrentUrl());

        Assert.assertTrue(
                dashboardPage.getCurrentUrl().contains("/start"),
                "Start My AI Journey page did not open."
        );

        System.out.println("Start My AI Journey opened successfully.");

        dashboardPage.navigateToCourses();

        Assert.assertEquals(
                dashboardPage.getCurrentUrl(),
                "https://aiacademy.conceptgood.com/",
                "Failed to navigate back to Courses page."
        );

        System.out.println("Returned to Courses successfully.");
    }
    @Test(
            groups = {"sanity", "regression", "theme"},
            priority = 8
    )
    public void verifyThemeToggle(){
        System.out.println("---------------------------------------------------");
        System.out.println("Verifying Theme Toggle");
        String initialTheme=dashboardPage.getCurrentTheme();
        System.out.println("Initial Theme : " + initialTheme);
        dashboardPage.clickThemeToggle();
        String changedTheme=dashboardPage.getCurrentTheme();
        System.out.println("Changed Theme : " + changedTheme);
        Assert.assertNotEquals(changedTheme,initialTheme,"Theme was not changed");
        Assert.assertTrue(changedTheme.equals("dark") || changedTheme.equals("light"),
                "Invalid Theme Value : " + changedTheme);
        dashboardPage.clickThemeToggle();
        String restoredTheme= dashboardPage.getCurrentTheme();
        System.out.println("Restored Theme : " + restoredTheme);
        Assert.assertEquals(restoredTheme,initialTheme,
                "Theme was not restored to the original theme");
        System.out.println("Theme toggle verified successfully");
    }
    @Test(
            groups = {"sanity", "regression", "dashboard"},
            priority = 3
    )
    public void verifyResumeButton(){
        System.out.println("-------------------------------------------------");
        System.out.println("Verifying Resume Button");
       // System.out.println("Current URL : " + dashboardPage.getCurrentUrl());
        //System.out.println(dashboardPage.isResumeButtonVisible());
        Assert.assertTrue(dashboardPage.isResumeButtonVisible(),
                "Resume Button is not visible");
        Assert.assertTrue(dashboardPage.isResumeButtonEnable(),
                "Resume Button is not Enable");
        Assert.assertEquals(dashboardPage.getResumeButtonText(),"Resume →",
                "Resume button text is incorrect.");
        dashboardPage.clickResumeButton();
        System.out.println(dashboardPage.getCurrentUrl());
        Assert.assertTrue(
                dashboardPage.getCurrentUrl().contains("/course/"),
                "Resume button did not open the course."
        );
        Assert.assertTrue(
                dashboardPage.getCurrentUrl().contains("/module/"),
                "Resume button did not open a module."
        );
        System.out.println("Resume Button verified successfully.");
    }
}

