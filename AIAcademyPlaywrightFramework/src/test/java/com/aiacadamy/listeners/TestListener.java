package com.aiacadamy.listeners;

import com.aiacadamy.base.BaseTest;
import com.aiacademy.utils.ScreenshotUtil;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
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

        if (baseTest.getTest() != null) {
            baseTest.getTest().fail(result.getThrowable());
        }

        try {

            if (baseTest.getPage() != null) {

                String screenshotPath = ScreenshotUtil.takeScreenshot(
                        baseTest.getPage(),
                        result.getName());

                if (baseTest.getTest() != null) {
                    baseTest.getTest().fail(
                            "Failure Screenshot",
                            MediaEntityBuilder.createScreenCaptureFromPath("../" + screenshotPath).build());
                }

                System.out.println("Failure Screenshot Captured");
            }

        } catch (Exception e) {

            System.out.println("Screenshot could not be captured.");
        }
    }
}
