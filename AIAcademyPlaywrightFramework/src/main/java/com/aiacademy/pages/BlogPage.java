package com.aiacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BlogPage {
    private Page page;
    private Locator blogMenu;
    private Locator blogCards;

    public BlogPage(Page page) {
        this.page = page;
        blogMenu = page.locator("header .nav-btn[href='/blog']");
        blogCards = page.locator(".blog-card");
    }

    // Click Blog Menu
    public void clickBlogMenu() {
        blogMenu.click();
    }

    // Current URL
    public String getCurrentURL() {
        return page.url();
    }

    // Verify Blog Page
    public boolean isBlogPageVisible() {
        return page.url().contains("/blog");
    }
    public boolean areBlogCardsDisplayed() {
        page.waitForSelector(".blog-card");
        int count = page.locator(".blog-card").count();

        System.out.println("Blog Count = " + count);

        return count > 0;
    }

    public int getBlogCount() {
        return blogCards.count();
    }
    public void openFirstBlog(){
        blogCards.first().click();
    }

}
