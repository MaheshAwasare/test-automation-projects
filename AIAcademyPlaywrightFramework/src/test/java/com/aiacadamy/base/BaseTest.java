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
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;


@Listeners(com.aiacadamy.listeners.TestListener.class)
public class BaseTest {

    //protected Playwright playwright;
    protected static ThreadLocal<Playwright>playwright=new ThreadLocal<>();
    //protected Browser browser;
    protected static ThreadLocal<Browser>browser=new ThreadLocal<>();
    //protected Page page;
    protected static ThreadLocal<BrowserContext>context=new ThreadLocal<>();
    protected static ThreadLocal<Page>page=new ThreadLocal<>();

    protected ExtentReports extent;
    //protected ExtentTest test;
    protected static ThreadLocal<ExtentTest>test = new ThreadLocal<>();

    @BeforeMethod
    public void setup(Method method) {
        extent= ExtentManager.getExtentReports();
        //test= extent.createTest(method.getName());
        test.set(extent.createTest(method.getName()));

        playwright.set(Playwright.create());

        browser.set(playwright.get().chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false).setSlowMo(500))
        );

       // page.set(browser.get().newPage());
        context.set(browser.get().newContext());
        page.set(context.get().newPage());

        page.get().navigate(ConfigReader.getProperty("base.url"));
    }
   /* @AfterMethod
    public void captureFailure(ITestResult result){
        if(result.getStatus()==ITestResult.FAILURE){
            ScreenshotUtil.takeScreenshot(page, result.getName());
            System.out.println("Failure Screenshot Captured");
        }
    }*/
    @AfterMethod
    public void tearDown() {
        extent.flush();
        context.get().close();
        browser.get().close();
        playwright.get().close();
    }

    public Page getPage(){
        return page.get();
    }

    public ExtentTest getTest(){
        return test.get();
    }

    public Playwright getPlaywright(){
        return playwright.get();
    }

    public BrowserContext getContext(){
        return context.get();
    }

}