package com.aiacademy.pages;

import com.microsoft.playwright.Page;

public class BlogPage {
    private Page page;
    private String blogMenu;
    private String blogCards;

    public BlogPage(Page page) {
        this.page = page;
        blogMenu = "a[href='/blog']";
        blogCards = ".blog-card";
    }

    // Click Blog Menu
    public void clickBlogMenu() {
        page.locator(blogMenu).click();
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
        return page.locator(blogCards).count();
    }
    public void openFirstBlog(){
        page.locator(blogCards).first().click();
    }

}
