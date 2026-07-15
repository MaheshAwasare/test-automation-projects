package com.aiacadamy.testngtests;

import com.aiacadamy.base.BaseTest;
import com.aiacademy.pages.CourseDetailsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTestNG extends BaseTest {
    @Test
    public void verifyProfilePageNavigation() {
        profilePage.clickProfileMenu();

        Assert.assertTrue(profilePage.getCurrentUrl().contains("/profile"));

        Assert.assertTrue(profilePage.isProfileHeadingVisible());
    }
    @Test(priority = 2)
    public void verifyAccountInformation() {
        profilePage.clickProfileMenu();
        Assert.assertTrue(
                profilePage.isAccountInformationHeadingVisible(),
                "Account Information heading is not visible."
        );
        Assert.assertFalse(
                profilePage.getUserName().isBlank(),
                "User Name is empty."
        );
        Assert.assertFalse(
                profilePage.getEmail().isBlank(),
                "Email is empty."
        );
        Assert.assertFalse(
                profilePage.getRole().isBlank(),
                "Role is empty."
        );
        Assert.assertFalse(
                profilePage.getMemberSince().isBlank(),
                "Member Since is empty."
        );
    }
    @Test(priority = 4)
    public void verifyMyCoursesSection() {
        profilePage.clickProfileMenu();
        Assert.assertTrue(
                profilePage.isMyCoursesHeadingVisible(),
                "My Courses heading is not visible."
        );
        Assert.assertTrue(
                profilePage.getCourseCount() > 0,
                "No enrolled courses are displayed."
        );
    }
    @Test(priority = 5)
    public void verifyAllCourseCards() {

        profilePage.clickProfileMenu();

        int totalCourses = profilePage.getCourseCount();

        System.out.println("Total Courses : " + totalCourses);

        for (int i = 0; i < totalCourses; i++) {

            Assert.assertFalse(
                    profilePage.getCourseTitle(i).isBlank(),
                    "Course title is empty for course index : " + i
            );

            Assert.assertFalse(
                    profilePage.getCourseDescription(i).isBlank(),
                    "Course description is empty for course index : " + i
            );

            Assert.assertTrue(
                    profilePage.isCourseMetaVisible(i),
                    "Course metadata is not visible for course index : " + i
            );
        }
    }
    @Test(priority = 6)
    public void verifyCourseNavigationFromProfile() {

        profilePage.clickProfileMenu();

        int totalCourses = profilePage.getCourseCount();

        System.out.println("Total Courses : " + totalCourses);

        for (int i = 0; i < totalCourses; i++) {

            String expectedCourseTitle = profilePage.getCourseTitle(i);

            CourseDetailsPage courseDetailsPage = profilePage.openCourse(i);

            Assert.assertTrue(
                    courseDetailsPage.isCoursePageDisplayed(),
                    "Course Details page is not displayed."
            );

            Assert.assertEquals(
                    courseDetailsPage.getCourseTitle(),
                    expectedCourseTitle,
                    "Course title mismatch."
            );

            profilePage.navigateBackToProfile();
        }
    }
    @Test(priority = 7)
    public void verifyBuildMyPlanNavigation() {

        profilePage.clickProfileMenu();

        profilePage.clickBuildMyPlan();

        Assert.assertTrue(
                profilePage.getCurrentUrl().contains("/start"),
                "Assessment page is not opened."
        );

        profilePage.navigateBackToProfile();

        Assert.assertTrue(
                profilePage.isProfilePageOpened(),
                "Profile page is not displayed after navigating back."
        );

    }
    @Test(priority = 8)
    public void verifyChangePasswordSection() {

        // Navigate to Profile Page
        profilePage.clickProfileMenu();

        // Scroll until Change Password section is visible
        profilePage.waitForChangePasswordSection();

        // Verify Change Password Heading
        Assert.assertTrue(
                profilePage.isChangePasswordHeadingVisible(),
                "Change Password heading is not visible."
        );

        // Verify Current Password Textbox
        Assert.assertTrue(
                profilePage.isCurrentPasswordTextboxVisible(),
                "Current Password textbox is not visible."
        );

        // Verify New Password Textbox
        Assert.assertTrue(
                profilePage.isNewPasswordTextboxVisible(),
                "New Password textbox is not visible."
        );

        // Verify Confirm Password Textbox
        Assert.assertTrue(
                profilePage.isConfirmPasswordTextboxVisible(),
                "Confirm Password textbox is not visible."
        );

        // Verify Change Password Button
        Assert.assertTrue(
                profilePage.isChangePasswordButtonVisible(),
                "Change Password button is not visible."
        );
    }
    @Test(priority = 9)
    public void verifyChangePasswordPlaceholders() {
        profilePage.clickProfileMenu();
        profilePage.waitForProfilePage();
        profilePage.waitForChangePasswordSection();

        Assert.assertEquals(
                profilePage.getCurrentPasswordPlaceholder(),
                "Enter current password",
                "Current Password placeholder mismatch."
        );

        Assert.assertEquals(
                profilePage.getNewPasswordPlaceholder(),
                "Min 6 characters",
                "New Password placeholder mismatch."
        );

        Assert.assertEquals(
                profilePage.getConfirmPasswordPlaceholder(),
                "Re-enter new password",
                "Confirm Password placeholder mismatch."
        );
    }
    @Test(priority = 10)
    public void verifyPasswordMismatchValidation() {
        profilePage.clickProfileMenu();
        profilePage.waitForProfilePage();
        profilePage.waitForChangePasswordSection();

        profilePage.enterCurrentPassword("Testing@123");
        profilePage.enterNewPassword("Password123");
        profilePage.enterConfirmPassword("Password456");

        profilePage.clickChangePasswordButton();

        Assert.assertEquals(
                profilePage.getChangePasswordErrorMessage(),
                "New passwords do not match",
                "Incorrect validation message displayed."
        );
    }
    @Test(priority = 11)
    public void verifyIncorrectCurrentPasswordValidation() {
        profilePage.clickProfileMenu();

        profilePage.waitForProfilePage();
        profilePage.waitForChangePasswordSection();

        profilePage.changePassword(
                "WrongPassword123",
                "Password@123",
                "Password@123"
        );

        Assert.assertEquals(
                profilePage.getChangePasswordErrorMessage(),
                "Current password is incorrect",
                "Incorrect validation message displayed."
        );
    }
    @Test
    public void verifySuccessfulPasswordChangeValidation() {
        profilePage.clickProfileMenu();

        profilePage.waitForProfilePage();
        profilePage.waitForChangePasswordSection();

        profilePage.changePassword(
                "Password@1234",
                "Testing@123",
                "Testing@123"
        );

        Assert.assertEquals(
                profilePage.getChangePasswordSuccessMessage(),
                "Password changed successfully",
                "Incorrect validation message displayed."
        );
    }

}
