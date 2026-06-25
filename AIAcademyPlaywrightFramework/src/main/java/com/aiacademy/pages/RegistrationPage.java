package com.aiacademy.pages;

import com.microsoft.playwright.Page;

public class RegistrationPage {
     private Page page;

     public RegistrationPage(Page page){
         this.page = page;
     }
     public void openRegistrationPage(){
         page.locator("a[href='/register']").click();
     }
     public void enterName(String name){
         page.locator("#name").fill(name);
     }
     public void enterEmail(String email){
         page.locator("#email").fill(email);
     }
     public void enterPassword(String password){
         page.locator("#password").fill(password);
     }
     public void clickTermsCheckbox(){
         page.locator("#agree").click();
     }
     public void clickCreateAccount(){
         page.locator("button[type='submit']").click();
     }
     public String getVerificationHeading(){
         return page.locator("h1").textContent();
     }
}
