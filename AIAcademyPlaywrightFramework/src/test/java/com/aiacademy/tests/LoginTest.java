/*package com.aiacademy.tests;

import com.aiacadamy.base.BaseTest;
import com.aiacademy.pages.LoginPage;
import com.aiacademy.utils.ConfigReader;
import org.testng.Assert;

public class LoginTest extends BaseTest {

    public static void main(String[] args) {

        LoginTest test = new LoginTest();

        //test.setup()

        LoginPage loginPage=new LoginPage(page);

        loginPage.openHomePage();

        loginPage.clickLoginButton();

        loginPage.enterEmail(ConfigReader.getProperty("test.email"));

        loginPage.enterPassword(ConfigReader.getProperty("test.password"));

        loginPage.clickOnSignInButton();

        test.page.waitForTimeout(5000);

        //Assert.assertTrue(loginPage.isLogoutButtonVisible());

        Assert.assertTrue(false);

        System.out.println("Page Title: " + test.page.title());
        System.out.println("Current URL: "+ test.page.url());

        test.tearDown();
    }
}*/