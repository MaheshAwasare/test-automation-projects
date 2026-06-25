package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import com.aiacademy.pages.LoginPage;
import com.aiacademy.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTestNG extends BaseTest {
    @Test
    public void verifyLogout(){
        LoginPage loginPage=new LoginPage(page);
        loginPage.openHomePage();
        loginPage.clickLoginButton();
        loginPage.enterEmail(ConfigReader.getProperty("test.email"));
        loginPage.enterPassword(ConfigReader.getProperty("test.password"));
        loginPage.clickOnSignInButton();
        loginPage.clickLogoutButton();
        page.waitForTimeout(3000);

        Assert.assertTrue(loginPage.isLoginButtonVisible());

        System.out.println("Logout Successful");
    }
}
