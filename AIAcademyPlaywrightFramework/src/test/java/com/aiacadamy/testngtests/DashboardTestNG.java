package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardTestNG extends BaseTest {
    @Test
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
    @Test
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
}

