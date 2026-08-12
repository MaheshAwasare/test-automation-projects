package com.aiacadamy.base;

import com.aiacademy.pages.*;
import com.aiacademy.utils.ConfigReader;
import com.aiacademy.utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import com.aiacadamy.annotations.SkipLogin;
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
    // Page Objects
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected FreeCoursesPage freeCoursesPage;
    protected CourseDetailsPage courseDetailsPage;
    protected ModuleDetailsPage moduleDetailsPage;
    protected ProfilePage profilePage;
    protected PacksPage packsPage;
    protected MyPathPage myPathPage;
    protected StartMyAIJourneyPage startMyAIJourneyPage;
    protected BlogPage blogPage;


    @BeforeMethod(alwaysRun = true)
    public void setup(Method method) {

        System.out.println("===== SETUP STARTED =====");
        System.out.println("Test Name: " + method.getName());

        extent = ExtentManager.getExtentReports();
        test.set(extent.createTest(method.getName()));
        test.get().info("Test Started : " + method.getName());

        playwright.set(Playwright.create());

        browser.set(
                playwright.get().chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false)
                                .setSlowMo(500)
                )
        );

        context.set(browser.get().newContext());
        page.set(context.get().newPage());

        page.get().navigate(ConfigReader.getProperty("base.url"));

        // Auto login (unless skipped)
        if (!skipAutoLogin()) {

            loginPage = new LoginPage(getPage());

            loginPage.clickLoginButton();
            loginPage.enterEmail(ConfigReader.getProperty("test.email"));
            loginPage.enterPassword(ConfigReader.getProperty("test.password"));
            loginPage.clickOnSignInButton();
            page.get().waitForTimeout(5000);
            dashboardPage = new DashboardPage(getPage());
            dashboardPage.waitForDashboardToLoad();
            freeCoursesPage = new FreeCoursesPage(getPage());
            courseDetailsPage = new CourseDetailsPage(getPage());
            moduleDetailsPage = new ModuleDetailsPage(getPage());
            profilePage = new ProfilePage(getPage());
            packsPage = new PacksPage(getPage());
            myPathPage = new MyPathPage(getPage());
            startMyAIJourneyPage = new StartMyAIJourneyPage(getPage());
            blogPage = new BlogPage(getPage());
        }
    }
    @AfterMethod
    public void tearDown() {
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
    protected boolean skipAutoLogin() {
        return false;
    }

}