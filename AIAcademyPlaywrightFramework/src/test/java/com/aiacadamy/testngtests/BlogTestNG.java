package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BlogTestNG extends BaseTest {
    @Test(priority = 1,
            groups = {"smoke","sanity","Regression"})
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
            groups = {"sanity","Regression"})
    public void verifyBlogCardsDisplayed() {

        blogPage.clickBlogMenu();

        Assert.assertTrue(
                blogPage.areBlogCardsDisplayed(),
                "Blog cards are not displayed."
        );
    }
    @Test(priority = 3,
            groups = {"sanity", "Regression"})
    public void verifyReadArticle() {

        blogPage.clickBlogMenu();

        String blogListingURL = blogPage.getCurrentURL();

        blogPage.openFirstBlog();

        Assert.assertNotEquals(
                blogPage.getCurrentURL(),
                blogListingURL,
                "Blog article did not open."
        );

        Assert.assertTrue(
                blogPage.getCurrentURL().contains("/blog/"),
                "Invalid blog article URL."
        );
    }

}
