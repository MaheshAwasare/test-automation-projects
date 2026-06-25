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
    @Test(dataProvider = "loginData",dataProviderClass = LoginDataProvider.class)
    public void verifyLogin(String email,String password,String expectedResult){
        test=extent.createTest("Login Test");
        LoginPage loginpage=new LoginPage(page);
        loginpage.openHomePage();
        loginpage.clickLoginButton();
        loginpage.enterEmail(email);
        loginpage.enterPassword(password);
        //loginpage.enterEmail(ConfigReader.getProperty("test.email"));
        //loginpage.enterPassword(ConfigReader.getProperty("test.password"));
        loginpage.clickOnSignInButton();
        page.waitForTimeout(5000);
        if(expectedResult.equalsIgnoreCase("Pass")){
            Assert.assertTrue
                    (loginpage.isLogoutButtonVisible(),
                            "Login failed for valid credentials");
            test.pass("Login successful with valid credentials");
        }else{
            Assert.assertEquals(loginpage.getErrorMessageText(),
                    "Invalid email or password");
            test.pass("Error message validated for invalid credentials");
        }
       /* System.out.println("Current URL = " + page.url());
        System.out.println("Page Title = " + page.title()); */

        String actualURL=page.url();
        String expectedURL=ConfigReader.getProperty("base.url");

        Assert.assertEquals(actualURL,expectedURL);

        //ScreenshotUtil.takeScreenshot(page,"LoginPassed");
        //test.log(Status.PASS,"Login Successful");
        String screenshotPath=ScreenshotUtil.takeScreenshot(page,"LoginPassed");
        test.pass("Login Successful", MediaEntityBuilder.
                createScreenCaptureFromPath("../"+ screenshotPath).build());
    }

}
