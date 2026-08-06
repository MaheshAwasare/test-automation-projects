package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BlogTestNG extends BaseTest {
    @Test(priority = 1,
            groups = {"Smoke","Sanity","Regression"})
    public void verifyBlogNavigation() {

        blogPage.clickBlogMenu();

        Assert.assertTrue(
                blogPage.getCurrentURL().contains("/blog"),
                "Blog page URL is incorrect."
        );

        Assert.assertTrue(
                blogPage.isBlogPageVisible(),
                "Blog page is not visible."
        );
    }
    @Test(priority = 2,
            groups = {"Sanity","Regression"})
    public void verifyBlogCardsDisplayed() {

        blogPage.clickBlogMenu();

        Assert.assertTrue(
                blogPage.areBlogCardsDisplayed(),
                "Blog cards are not displayed."
        );

        System.out.println("Total Blogs : " + blogPage.getBlogCount());
    }
}
