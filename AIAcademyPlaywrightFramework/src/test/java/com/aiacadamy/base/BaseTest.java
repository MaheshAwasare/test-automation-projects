package com.aiacadamy.base;

import com.aiacademy.utils.ConfigReader;
import com.aiacademy.utils.ExtentManager;
import com.aiacademy.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setup() {
        extent= ExtentManager.getExtentReports();

        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false).setSlowMo(500)
        );

        page = browser.newPage();

        page.navigate(ConfigReader.getProperty("base.url"));
    }
    @AfterMethod
    public void captureFailure(ITestResult result){
        if(result.getStatus()==ITestResult.FAILURE){
            ScreenshotUtil.takeScreenshot(page, result.getName());
            System.out.println("Failure Screenshot Captured");
        }
    }
    @AfterMethod
    public void tearDown() {
        extent.flush();
        browser.close();
        playwright.close();
    }
}