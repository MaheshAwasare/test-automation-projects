package com.aiacademy.pages;

import com.aiacademy.utils.ConfigReader;
import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;
    public LoginPage(Page page){
        this.page=page;
    }
    public void openHomePage(){
      //  page.navigate("https://aiacademy.conceptgood.com");
        page.navigate(ConfigReader.getProperty("base.url"));

        page.waitForTimeout(5000);
    }
    public void clickLoginButton(){
        page.locator("text=Login").click();

        page.waitForTimeout(5000);
    }
    public void enterEmail(String email){
        page.locator("#email").fill(email);
    }
    public void enterPassword(String password){
        page.locator("#password").fill(password);
    }
    public void clickOnSignInButton(){
        page.locator(".auth-btn").click();
    }
    public void clickLogoutButton() {
        page.locator("text=Logout").click();
    }
    public boolean isLoginButtonVisible() {
        return page.locator("text=Login").isVisible();
    }
    public boolean isLogoutButtonVisible(){
        return page.locator("text=logout").isVisible();
    }
    public boolean isErrorMessageDisplayed(){
        return page.locator(".auth-error").isVisible();
    }
    public String getErrorMessageText() {
        return page.locator(".auth-error").textContent().trim();
    }
}
