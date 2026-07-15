package com.aiacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ProfilePage {
    private final Page page;
    private final Locator profileMenu;
    private final Locator myProfileHeading;
    private final Locator accountInformationHeading;
    private final Locator userName;
    private final Locator email;
    private final Locator role;
    private final Locator memberSince;
    private final Locator courseCards;
    private final Locator assessmentCard;
    private final Locator assessmentHeading;
    private final Locator assessmentDescription;
    private final Locator buildMyPlanButton;
    private final Locator myCoursesHeading;
    private final Locator courseTitles;
    private final Locator courseDescriptions;
    private final Locator courseMetas;
    private final Locator changePasswordHeading;
    private final Locator currentPasswordTextbox;
    private final Locator newPasswordTextbox;
    private final Locator confirmPasswordTextbox;
    private final Locator changePasswordButton;
    private final Locator changePasswordErrorMessage;
    private final Locator changePasswordSuccessMessage;



    public ProfilePage(Page page) {
        this.page = page;
        profileMenu=page.locator("nav.topbar-nav").
                locator("a[href='/profile']");
        myProfileHeading=page.locator("h1");
        accountInformationHeading = page.locator("h2").filter(
                new Locator.FilterOptions().setHasText("Account Information"));
        userName = page.locator(".profile-info-item")
                .filter(new Locator.FilterOptions()
                        .setHasText("Name"))
                .locator(".profile-value");
        email = page.locator(".profile-info-item")
                .filter(new Locator.FilterOptions()
                        .setHasText("Email"))
                .locator(".profile-value");
        role = page.locator(".profile-info-item")
                .filter(new Locator.FilterOptions()
                        .setHasText("Role"))
                .locator(".profile-value");
        memberSince = page.locator(".profile-info-item")
                .filter(new Locator.FilterOptions()
                        .setHasText("Member Since"))
                .locator(".profile-value");
        courseCards = page.locator("a.profile-course-card");
        assessmentCard = page.locator(".next-step-card");
        assessmentHeading = page.locator(".next-step-card h3");
        assessmentDescription = page.locator(".next-step-card p");
        buildMyPlanButton = page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Build my plan →")
        );
        myCoursesHeading = page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("My Courses")
        );
        courseTitles = page.locator(".profile-course-card h3");
        courseDescriptions = page.locator(".profile-course-card p");
        courseMetas = page.locator(".profile-course-meta");
        changePasswordHeading = page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("Change Password")
        );
        currentPasswordTextbox = page.locator
                ("input[placeholder='Enter current password']");
        newPasswordTextbox = page.locator
                ("input[placeholder='Min 6 characters']");
        confirmPasswordTextbox = page.locator
                ("input[placeholder='Re-enter new password']");
        changePasswordButton = page.locator("button.auth-btn");
        changePasswordErrorMessage = page.locator("div.auth-error");
        changePasswordSuccessMessage =
                page.locator("div.profile-success");

    }
    public void clickProfileMenu() {
        profileMenu.waitFor();
        profileMenu.click();
        waitForProfilePage();
        myCoursesHeading.scrollIntoViewIfNeeded();
        courseCards.first().waitFor();
    }
    public boolean isProfileHeadingVisible() {
        return myProfileHeading.isVisible();
    }
    public String getCurrentUrl() {
        return page.url();
    }
    public void waitForProfilePage() {
        myProfileHeading.waitFor();
    }
    public boolean isProfilePageOpened() {
        return page.url().contains("/profile");
    }
    public boolean isAccountInformationHeadingVisible() {
        return accountInformationHeading.isVisible();
    }
    public String getUserName() {
        return userName.innerText().trim();
    }
    public String getEmail() {
        return email.innerText().trim();
    }
    public String getRole() {
        return role.innerText().trim();
    }
    public String getMemberSince() {
        return memberSince.innerText().trim();
    }
    public boolean isAssessmentCardVisible(){
        return assessmentCard.isVisible();
    }
    public boolean isAssessmentHeadingVisible(){
        return assessmentHeading.isVisible();
    }
    public boolean isAssessmentDescriptionVisible(){
        return assessmentDescription.isVisible();
    }
    public boolean isBuildMyPlanButtonVisible(){
        return buildMyPlanButton.isVisible();
    }
    public void clickBuildMyPlan(){
        buildMyPlanButton.scrollIntoViewIfNeeded();
        buildMyPlanButton.waitFor();
        buildMyPlanButton.click();
        waitForAssessmentPage();
    }
    public boolean isMyCoursesHeadingVisible() {
        scrollToMyCourses();
        myCoursesHeading.waitFor();
        return myCoursesHeading.isVisible();
    }
    public int getCourseCount() {
        return courseCards.count();
    }
    public void scrollToMyCourses() {
        myCoursesHeading.scrollIntoViewIfNeeded();
    }
    public String getCourseTitle(int index) {
        return courseTitles.nth(index).innerText().trim();
    }
    public String getCourseDescription(int index) {
        return courseDescriptions.nth(index).innerText().trim();
    }
    public boolean isCourseMetaVisible(int index) {
        return courseMetas.nth(index).isVisible();
    }
    public void waitForCourseCards() {
        courseCards.first().waitFor();
    }
    private void waitForMyCoursesSection() {
        scrollToMyCourses();
        myCoursesHeading.waitFor();
        courseCards.first().waitFor();

    }
    public CourseDetailsPage openCourse(int index) {

        waitForCourseCards();

        courseCards
                .nth(index)
                .scrollIntoViewIfNeeded();

        courseCards
                .nth(index)
                .click();

        CourseDetailsPage courseDetailsPage = new CourseDetailsPage(page);

        courseDetailsPage.waitForCoursePage();

        return courseDetailsPage;
    }
    public void navigateBackToProfile() {

        page.goBack();

        waitForCourseCards();
    }
    public void waitForAssessmentPage() {

        page.waitForURL("**/start");

    }
    public void waitForChangePasswordSection() {

        changePasswordHeading.scrollIntoViewIfNeeded();

        changePasswordHeading.waitFor();
    }
    public boolean isChangePasswordHeadingVisible() {

        waitForChangePasswordSection();

        return changePasswordHeading.isVisible();
    }
    public boolean isCurrentPasswordTextboxVisible() {

        return currentPasswordTextbox.isVisible();
    }
    public boolean isNewPasswordTextboxVisible() {

        return newPasswordTextbox.isVisible();
    }
    public boolean isConfirmPasswordTextboxVisible() {

        return confirmPasswordTextbox.isVisible();
    }
    public boolean isChangePasswordButtonVisible() {

        return changePasswordButton.isVisible();
    }
    public String getCurrentPasswordPlaceholder() {
        return currentPasswordTextbox.getAttribute("placeholder");
    }

    public String getNewPasswordPlaceholder() {
        return newPasswordTextbox.getAttribute("placeholder");
    }

    public String getConfirmPasswordPlaceholder() {
        return confirmPasswordTextbox.getAttribute("placeholder");
    }
    public void enterCurrentPassword(String password) {
        currentPasswordTextbox.fill(password);
    }

    public void enterNewPassword(String password) {
        newPasswordTextbox.fill(password);
    }

    public void enterConfirmPassword(String password) {
        confirmPasswordTextbox.fill(password);
    }

    public void clickChangePasswordButton() {
        changePasswordButton.click();
    }

    public void changePassword(String currentPassword,
                               String newPassword,
                               String confirmPassword) {

        enterCurrentPassword(currentPassword);
        enterNewPassword(newPassword);
        enterConfirmPassword(confirmPassword);

        clickChangePasswordButton();
    }
    public String getChangePasswordErrorMessage(){

        changePasswordErrorMessage.waitFor();

        return changePasswordErrorMessage.innerText().trim();
    }
    public String getChangePasswordSuccessMessage(){

        changePasswordSuccessMessage.waitFor();

        return changePasswordSuccessMessage.innerText().trim();
    }


}
