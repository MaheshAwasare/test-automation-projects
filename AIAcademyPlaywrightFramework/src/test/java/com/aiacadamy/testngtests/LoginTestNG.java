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
    @Test(dataProvider = "loginData",dataProviderClass = LoginDataProvider.class,
            groups = {"smoke", "sanity", "regression", "login"},
    priority = 1)
    public void verifyLogin(String email,String password,
                            String expectedResult,String expectedMessage){
        //test=extent.createTest("Login Test");----(added in BaseTest)
        LoginPage loginpage=new LoginPage(getPage());
        loginpage.openHomePage();
        loginpage.clickLoginButton();
        loginpage.enterEmail(email);
        loginpage.enterPassword(password);
        //loginpage.enterEmail(ConfigReader.getProperty("test.email"));
        //loginpage.enterPassword(ConfigReader.getProperty("test.password"));
        loginpage.clickOnSignInButton();
        getPage().waitForTimeout(5000);
        if(expectedResult.equalsIgnoreCase("Pass")){
            Assert.assertTrue
                    (loginpage.isLogoutButtonVisible(),
                            "Login failed for valid credentials");
            getTest().pass("Login successful with valid credentials");
        }else if (expectedResult.equalsIgnoreCase("Fail")){
            Assert.assertEquals(loginpage.getErrorMessageText(),expectedMessage);
            getTest().pass("Error message validated for invalid credentials");
        }else{
            Assert.fail("Invalid ExpectedResult value in Excel : "+expectedResult);

        }
       /* System.out.println("Current URL = " + page.url());
        System.out.println("Page Title = " + page.title()); */

       // String actualURL=page.url();
       // String expectedURL=ConfigReader.getProperty("base.url");

        //Assert.assertEquals(actualURL,expectedURL);

        //ScreenshotUtil.takeScreenshot(page,"LoginPassed");
        //test.log(Status.PASS,"Login Successful");
        String screenshotPath=ScreenshotUtil.takeScreenshot(getPage(),"LoginPassed");
        getTest().pass("Login Successful", MediaEntityBuilder.
                createScreenCaptureFromPath("../"+ screenshotPath).build());
    }

}
