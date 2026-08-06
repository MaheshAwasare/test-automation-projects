package com.aiacadamy.testngtests;

import com.aiacadamy.dataproviders.LoginDataProvider;
import com.aiacademy.pages.LoginPage;
import com.aiacademy.utils.ConfigReader;
import com.aiacademy.utils.ExcelUtils;
import com.aiacademy.utils.ScreenshotUtil;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aiacadamy.base.BaseTest;

import java.io.IOException;

public class LoginTestNG extends BaseTest {
    @Override
    protected boolean skipAutoLogin() {
        return true;
    }
    @Test(priority = 1,groups = {"smoke", "sanity", "regression"})
    public void verifyLogin() {

        LoginPage loginPage = new LoginPage(getPage());

        // Open Login Page
        //loginPage.openHomePage();
        loginPage.clickLoginButton();

        // Enter Credentials
        loginPage.enterEmail(ConfigReader.getProperty("test.email"));
        loginPage.enterPassword(ConfigReader.getProperty("test.password"));

        // Login
        loginPage.clickOnSignInButton();

        // Wait for Dashboard
        getPage().waitForLoadState();

        // Verify Login
        Assert.assertTrue(
                loginPage.isLogoutButtonVisible(),
                "Login failed. Logout button is not visible."
        );

        getTest().pass("Login successful.");

        // Screenshot
        String screenshotPath = ScreenshotUtil.takeScreenshot(
                getPage(),
                "LoginSuccess"
        );

        getTest().pass(
                "Login Screenshot",
                MediaEntityBuilder.createScreenCaptureFromPath("../" + screenshotPath).build()
        );
    }


}
