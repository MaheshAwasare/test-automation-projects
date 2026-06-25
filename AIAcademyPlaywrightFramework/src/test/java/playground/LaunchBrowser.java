package playground;

import com.microsoft.playwright.*;        //Imports Playwright classes.

public class LaunchBrowser {

    public static void main(String[] args) {         //Program execution starts here

        try (Playwright playwright = Playwright.create()) {     //This starts the Playwright engine.

            Browser browser =
                    playwright.chromium()
                            .launch(                          //This launches a Chromium browser.
                                    new BrowserType.LaunchOptions()
                                            .setHeadless(false)
                            );

            Page page = browser.newPage();                   //Creates a new browser tab.

            page.navigate("https://aiacademy.conceptgood.com");   //Opens the website.

            page.waitForTimeout(10000);      //Waits for 10 seconds.

            browser.close();       //Closes the browser.
        }
    }
}

