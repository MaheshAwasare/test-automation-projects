package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PacksTestNG extends BaseTest {
    @Test( priority = 10,
            groups = {"sanity", "regression", "packs"})
    public void verifyPacksPageNavigation() {
        dashboardPage.clickPacksMenu();
        packsPage.waitForPacksPage();
        Assert.assertTrue(
                packsPage.isPacksHeadingVisible(),
                "Packs heading is not visible."
        );
        Assert.assertEquals(
                packsPage.getPacksHeading(),
                "Buy a career path, not a course",
                "Incorrect Packs page heading."
        );
        Assert.assertTrue(
                packsPage.getCurrentUrl().contains("/packs"),
                "Incorrect Packs page URL."
        );
    }
    @Test( priority = 11,
            groups = {"sanity", "regression", "packs"})
    public void verifyAllPackCardsDisplayed() {
        dashboardPage.clickPacksMenu();
        packsPage.waitForPacksPage();
        packsPage.waitForPackCards();

        int packCount = packsPage.getPackCount();
        System.out.println("Pack Count Is : " + packCount);

        Assert.assertTrue(
                packCount > 0,
                "No pack cards are displayed."
        );

        for (int i = 0; i < packCount; i++) {

            Assert.assertTrue(
                    packsPage.isPackCardVisible(i),
                    "Pack card is not visible at index: " + i
            );

            Assert.assertFalse(
                    packsPage.getPackTitle(i).isBlank(),
                    "Pack title is empty at index: " + i
            );
        }
    }
    @Test( priority = 12,
            groups = {"regression", "packs"})
    public void verifyPackCardContents() {
        dashboardPage.clickPacksMenu();
        packsPage.waitForPacksPage();
        packsPage.waitForPackCards();

        int totalPacks = packsPage.getPackCount();

        Assert.assertTrue(totalPacks > 0,
                "No pack cards found.");

        for (int i = 0; i < totalPacks; i++) {

            Assert.assertFalse(
                    packsPage.getPackBadge(i).isBlank(),
                    "Pack badge missing at index " + i);

            Assert.assertFalse(
                    packsPage.getPackTitle(i).isBlank(),
                    "Pack title missing at index " + i);

            Assert.assertFalse(
                    packsPage.getPackDescription(i).isBlank(),
                    "Pack description missing at index " + i);

            Assert.assertTrue(
                    packsPage.isBuyPackButtonVisible(i),
                    "Buy Pack button missing at index " + i);

            Assert.assertTrue(
                    packsPage.isViewDetailsButtonVisible(i),
                    "View Details button missing at index " + i);
            String footer = packsPage.getPackFooterText(i);

            Assert.assertFalse(
                    footer.isBlank(),
                    "Pack footer text is empty at index " + i);

            Assert.assertTrue(
                    footer.contains("Lifetime access"),
                    "Lifetime access text missing at index " + i);

            Assert.assertTrue(
                    footer.toLowerCase().contains("courses included"),
                    "Courses included text missing at index " + i);

            Assert.assertTrue(
                    packsPage.isPackMetaVisible(i),
                    "Footer missing at index " + i);
        }
    }
    @Test(priority = 13,
            groups = {"sanity", "regression", "packs"})
    public void verifyViewDetailsExpandsSelectedPack() {
        dashboardPage.clickPacksMenu();
        packsPage.waitForPacksPage();
        packsPage.clickViewDetails(0);
        Assert.assertTrue(
                packsPage.isExpandedPackSectionVisible(),
                "Expanded pack section is not visible."
        );
        Assert.assertFalse(
                packsPage.getExpandedPackHeading().isEmpty(),
                "Expanded pack heading is empty."
        );

        Assert.assertTrue(
                packsPage.isExpandedBuyPackButtonVisible(),
                "Buy Pack button is not visible."
        );

        Assert.assertTrue(
                packsPage.getIncludedCourseCount() > 0,
                "No included course cards are displayed."
        );
    }
    @Test( priority = 14,
            groups = {"regression", "packs"})
    public void verifyPackDetailsContent() {
        dashboardPage.clickPacksMenu();
        packsPage.waitForPacksPage();

        packsPage.clickViewDetails(1);

        Assert.assertTrue(
                packsPage.isDescriptionVisible(),
                "Pack description is not visible."
        );

        Assert.assertTrue(
                packsPage.isBuyPackButtonVisible(),
                "Buy Pack button is not visible."
        );

        int totalCourses = packsPage.getIncludedCourseCount();

        Assert.assertTrue(
                totalCourses > 0,
                "No courses found inside selected pack."
        );

        for (int i = 0; i < totalCourses; i++) {

            Assert.assertFalse(
                    packsPage.getCourseTitle(i).isBlank(),
                    "Course title is empty at index " + i
            );

            Assert.assertFalse(
                    packsPage.getCourseDescription(i).isBlank(),
                    "Course description is empty at index " + i
            );

            Assert.assertFalse(
                    packsPage.getCoursePrice(i).isBlank(),
                    "Course price is empty at index " + i
            );
        }
    }
}
