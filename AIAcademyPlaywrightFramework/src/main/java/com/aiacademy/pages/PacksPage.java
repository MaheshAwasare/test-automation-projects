package com.aiacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PacksPage {
    private final Page page;
    private final Locator packsHeading;
    private final Locator packCards;
    private final Locator packTitles;
    private final Locator packBadges;
    private final Locator packDescriptions;
    private final Locator buyPackButtons;
    private final Locator viewDetailsButtons;
    private final Locator packMeta;
    private final Locator expandedPackSection;
    private final Locator expandedPackHeading;
    private final Locator expandedPackPrice;
    private final Locator expandedBuyPackButton;
    private final Locator includedCourseCards;
    private final Locator description;
    private final Locator buyPackButton;

    private final Locator courseCards;
    private final Locator courseTitles;
    private final Locator courseDescriptions;
    private final Locator coursePrices;


    public PacksPage(Page page){
        this.page=page;
        packsHeading=page.locator("h1.packs-title");
        packCards = page.locator("article.bundle-card");
        packTitles = page.locator("h3.bundle-card-title");
        packBadges = page.locator(".bundle-card-badge");
        packDescriptions = page.locator("p.bundle-card-sub");
        buyPackButtons = page.locator("button:has-text('Buy Pack')");
        viewDetailsButtons = page.locator("button:has-text('View details')");
        packMeta = page.locator(".bundle-card-meta");
        expandedPackSection = page.locator("section.bundle-detail-panel");
        expandedPackHeading = page.locator("section.bundle-detail-panel h2");
        expandedPackPrice = page.locator("section.bundle-detail-panel h2");
        expandedBuyPackButton = page.locator("section.bundle-detail-panel button").
                filter(new Locator.FilterOptions().setHasText("Buy Pack")
        );
        includedCourseCards = page.locator("section.bundle-detail-panel .course-card");
        description = page.locator("section.bundle-detail-panel > p");
        buyPackButton = page.locator("section.bundle-detail-panel button")
                .filter(new Locator.FilterOptions().setHasText("Buy Pack"));
        courseCards = page.locator("section.bundle-detail-panel .course-card");
        courseTitles = page.locator("section.bundle-detail-panel .course-card h3");
        courseDescriptions = page.locator("section.bundle-detail-panel .course-card p");
        coursePrices = page.locator("section.bundle-detail-panel " +
                ".course-card .course-card-price");

    }
    public void waitForPacksPage() {
        page.waitForURL("**/packs");
        packsHeading.waitFor();
    }

    public boolean isPacksHeadingVisible() {
        return packsHeading.isVisible();
    }

    public String getPacksHeading() {
        return packsHeading.innerText().trim();
    }

    public String getCurrentUrl() {
        return page.url();
    }
    public void waitForPackCards() {
        packCards.first().waitFor();
    }

    public int getPackCount() {
        return packCards.count();
    }

    public boolean isPackCardVisible(int index) {
        return packCards.nth(index).isVisible();
    }

    public String getPackTitle(int index) {
        return packTitles.nth(index).innerText().trim();
    }
    public String getPackBadge(int index) {
        return packBadges.nth(index).innerText().trim();
    }

    public String getPackDescription(int index) {
        return packDescriptions.nth(index).innerText().trim();
    }

    public boolean isBuyPackButtonVisible(int index) {
        return buyPackButtons.nth(index).isVisible();
    }

    public boolean isViewDetailsButtonVisible(int index) {
        return viewDetailsButtons.nth(index).isVisible();
    }

    public boolean isPackMetaVisible(int index) {
        return packMeta.nth(index).isVisible();
    }
    public String getPackFooterText(int index) {
        return packMeta.nth(index).innerText().trim();
    }

    public void navigateBackToPacks() {

        page.goBack();

        waitForPackCards();
    }
    public void waitForExpandedPackSection() {

        expandedPackSection.waitFor();
    }
    public void clickViewDetails(int index) {

        viewDetailsButtons.nth(index).scrollIntoViewIfNeeded();
        viewDetailsButtons.nth(index).waitFor();
        viewDetailsButtons.nth(index).click();

        waitForExpandedPackSection();
    }
    public boolean isExpandedPackSectionVisible() {

        return expandedPackSection.isVisible();
    }
    public String getExpandedPackHeading() {

        return expandedPackHeading.innerText().trim();
    }
    public String getExpandedPackPrice() {

        return expandedPackPrice.innerText().trim();
    }
    public boolean isExpandedBuyPackButtonVisible() {

        return expandedBuyPackButton.isVisible();
    }
    public int getIncludedCourseCount() {

        return includedCourseCards.count();
    }
    public boolean isDescriptionVisible() {
        return description.isVisible();
    }
    public boolean isBuyPackButtonVisible() {
        return buyPackButton.isVisible();
    }
    public String getCourseTitle(int index) {
        System.out.println("Course Title : "+courseTitles.nth(index).innerText().trim());
        return courseTitles.nth(index).innerText().trim();
    }
    public String getCourseDescription(int index) {
        return courseDescriptions.nth(index).innerText().trim();
    }
    public String getCoursePrice(int index) {
        System.out.println("Course Price : " +coursePrices.nth(index).innerText().trim());
        return coursePrices.nth(index).innerText().trim();
    }



}
