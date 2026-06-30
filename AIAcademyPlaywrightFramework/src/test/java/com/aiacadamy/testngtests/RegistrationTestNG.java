package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import com.aiacademy.pages.LoginPage;
import com.aiacademy.pages.RegistrationPage;
import com.aiacademy.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTestNG extends BaseTest {
    @Test
    public void verifyRegistration(){
        LoginPage loginpage=new LoginPage(getPage());
        loginpage.openHomePage();
        loginpage.clickLoginButton();

        RegistrationPage registrationPage=new RegistrationPage(getPage());
        registrationPage.openRegistrationPage();
        registrationPage.enterName(ConfigReader.getProperty("new.name"));
        registrationPage.enterEmail(ConfigReader.getProperty("new.email"));
        registrationPage.enterPassword(ConfigReader.getProperty("new.password"));
        registrationPage.clickTermsCheckbox();
        getPage().pause();
        registrationPage.clickCreateAccount();
        String actualHeading= registrationPage.getVerificationHeading();
        Assert.assertEquals(actualHeading,"Check Your Email");

    }

}
