package com.aiacademy.utils;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class ScreenshotUtil {
    public static String takeScreenshot(Page page, String fileName) {
        String path = "screenshots/" + fileName + ".png";
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get
                (path)));
        //System.out.println("Screenshot Path : " + path);

        return path;
    }
}
