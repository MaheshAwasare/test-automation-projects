package com.aiacadamy.listeners;

import com.aiacadamy.base.BaseTest;
import com.aiacademy.utils.ScreenshotUtil;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result){
        BaseTest baseTest = (BaseTest)result.getInstance();
        baseTest.getTest().info("Test Started : "
                + result.getName());

    }
    @Override
    public void onTestSuccess(ITestResult result){
        BaseTest baseTest = (BaseTest)result.getInstance();
        baseTest.getTest().pass("Test Passed : "
                + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result){
        System.out.println("Test Failed : "
                + result.getName());
        BaseTest baseTest = (BaseTest)result.getInstance();

        String screenshotPath = ScreenshotUtil.takeScreenshot(baseTest.getPage(),
                result.getName());

        baseTest.getTest().fail(result.getThrowable());

        baseTest.getTest().fail("Failure Screenshot",
                MediaEntityBuilder.createScreenCaptureFromPath("../" + screenshotPath).build());

        System.out.println("Failure Screenshot Captured");
    }

    @Override
    public void onTestSkipped(ITestResult result){
        BaseTest baseTest = (BaseTest)result.getInstance();
        baseTest.getTest().info("Test Skipped : "
                + result.getName());
    }

}
