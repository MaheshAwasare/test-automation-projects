package com.aiacademy.pages;

import com.microsoft.playwright.Page;

public class ForgotPasswordPage {
    private Page page;

    public ForgotPasswordPage(Page page){
        this.page=page;
    }
    public void openForgotPasswordPage(){
        page.locator("text=Forgot your password?").click();
    }
    public String getCurrentURL(){
        return page.url();
    }
    public void enterEmail(String email){
        page.locator("#email").fill(email);
    }
    public void clickSendResetLink(){
        page.locator("button[type='submit']").click();
    }
    public String getSuccessMessage(){
        return page.getByText("password reset link has been sent").textContent();
    }
}
