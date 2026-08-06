package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import com.aiacademy.pages.ForgotPasswordPage;
import com.aiacademy.pages.LoginPage;
import com.aiacademy.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ForgotPasswordTestNG extends BaseTest {
    @Override
    protected boolean skipAutoLogin() {
        return true;
    }
    @Test
    public void verifyForgotPasswordLink(){
        LoginPage loginpage=new LoginPage(getPage());
        //loginpage.openHomePage();
        loginpage.clickLoginButton();

        ForgotPasswordPage forgotPasswordPage=new ForgotPasswordPage(getPage());
        forgotPasswordPage.openForgotPasswordPage();

        Assert.assertTrue(getPage().url().contains("forgot-password"));

        forgotPasswordPage.enterEmail(ConfigReader.getProperty("test.email"));
        forgotPasswordPage.clickSendResetLink();
        String actualMessage=forgotPasswordPage.getSuccessMessage();

        Assert.assertTrue(actualMessage.contains("password reset link has been sent"));

        System.out.println("Current URL:"+ forgotPasswordPage.getCurrentURL());
        System.out.println("Message " + actualMessage);

    }


}
