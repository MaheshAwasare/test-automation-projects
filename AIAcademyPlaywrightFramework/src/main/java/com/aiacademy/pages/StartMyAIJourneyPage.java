package com.aiacademy.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class StartMyAIJourneyPage {
    private final Page page;
    private final Locator startMyAIJourneyMenu;
    private final Locator assessmentHeading;
    private final Locator progressBar;
    private final Locator questionNumber;
    private final Locator questionHeading;
    private final Locator questionDescription;

    private final Locator optionCards;
    private final Locator continueButton;
    private final Locator backButton;
//=========================================================
    // Question 1
    //=========================================================

    private final Locator curiousOption;
    private final Locator dayJobOption;
    private final Locator buildThingsOption;
    private final Locator strategyOption;
    private final Locator careerOption;

    //=========================================================
    // Question 2
    //=========================================================

    private final Locator nonTechnicalOption;
    private final Locator spreadsheetOption;
    private final Locator scriptingOption;
    private final Locator developerOption;
    private final Locator aiEngineerOption;

    //=========================================================
    // Question 3 (Multi Select)
    //=========================================================

    private final Locator nothingYetOption;
    private final Locator chatGPTOption;
    private final Locator claudeOption;
    private final Locator apiOption;
    private final Locator agentOption;
    private final Locator fineTuneOption;

    //=========================================================
    // Question 4
    //=========================================================

    private final Locator saveTimeOption;
    private final Locator promotionOption;
    private final Locator buildProductOption;
    private final Locator hiredOption;

    //=========================================================
    // Result Page
    //=========================================================

    private final Locator archetypeBadge;
    private final Locator archetypeTitle;
    private final Locator archetypeTagline;
    private final Locator planSteps;
    //=========================================================
// Question 5
//=========================================================

    private final Locator outcomeOptions;

//=========================================================
// Question 6
//=========================================================

    private final Locator timeOptions;

//=========================================================
// Question 7
//=========================================================

    private final Locator roleOptions;

//=========================================================
// Question 8
//=========================================================

    private final Locator gpuOptions;

//=========================================================
// Question 9
//=========================================================

    private final Locator budgetOptions;

//=========================================================
// Question 10 (Multi Select)
//=========================================================

    private final Locator interestOptions;

//=========================================================
// Question 11
//=========================================================

    private final Locator timelineOptions;

//=========================================================
// Question 12
//=========================================================

    private final Locator learningOptions;

//=========================================================
// Optional Courses
//=========================================================

    private final Locator previousCourseOptions;

    public StartMyAIJourneyPage(Page page) {
        this.page = page;
        startMyAIJourneyMenu =
                page.locator("nav.topbar-nav a.nav-btn.nav-btn-accent[href='/start']");

        //-----------------------------------------------------
        // Common
        //-----------------------------------------------------

        assessmentHeading =
                page.locator("h1")
                        .filter(new Locator.FilterOptions()
                                .setHasText("Where should I start with AI?"));

        progressBar =
                page.locator(".assess-progress");

        questionNumber =
                page.locator(".assess-progress-label");

        questionHeading =
                page.locator(".assess-q-label");

        questionDescription =
                page.locator(".assess-q-help");

        optionCards =
                page.locator(".assess-option");

        continueButton =
                page.locator("button.assess-btn-primary");

        backButton =
                page.locator("button")
                        .filter(new Locator.FilterOptions()
                                .setHasText("Back"));
        //-----------------------------------------------------
        // Question 1
        //-----------------------------------------------------

        curiousOption =
                page.getByText("I'm curious", new Page.GetByTextOptions().setExact(false));

        dayJobOption =
                page.getByText("I want to use AI for my day job", new Page.GetByTextOptions().setExact(false));

        buildThingsOption =
                page.getByText("I want to build things with AI", new Page.GetByTextOptions().setExact(false));

        strategyOption =
                page.getByText("AI strategy", new Page.GetByTextOptions().setExact(false));

        careerOption =
                page.getByText("change careers", new Page.GetByTextOptions().setExact(false));

        //-----------------------------------------------------
        // Question 2
        //-----------------------------------------------------

        nonTechnicalOption =
                page.getByText("Non-technical", new Page.GetByTextOptions().setExact(false));

        spreadsheetOption =
                page.getByText("spreadsheets", new Page.GetByTextOptions().setExact(false));

        scriptingOption =
                page.getByText("Python", new Page.GetByTextOptions().setExact(false));

        developerOption =
                page.getByText("full applications", new Page.GetByTextOptions().setExact(false));

        aiEngineerOption =
                page.getByText("production today", new Page.GetByTextOptions().setExact(false));

        //-----------------------------------------------------
        // Question 3
        //-----------------------------------------------------

        nothingYetOption =
                page.getByText("Nothing yet", new Page.GetByTextOptions().setExact(false));

        chatGPTOption =
                page.getByText("Used ChatGPT", new Page.GetByTextOptions().setExact(false));

        claudeOption =
                page.getByText("Used Claude", new Page.GetByTextOptions().setExact(false));

        apiOption =
                page.getByText("LLM API", new Page.GetByTextOptions().setExact(false));

        agentOption =
                page.getByText("Built an agent", new Page.GetByTextOptions().setExact(false));

        fineTuneOption =
                page.getByText("Fine-tuned", new Page.GetByTextOptions().setExact(false));

        //-----------------------------------------------------
        // Question 4
        //-----------------------------------------------------

        saveTimeOption =
                page.getByText("Save 5+", new Page.GetByTextOptions().setExact(false));

        promotionOption =
                page.getByText("Get promoted", new Page.GetByTextOptions().setExact(false));

        buildProductOption =
                page.getByText("Build and ship", new Page.GetByTextOptions().setExact(false));

        hiredOption =
                page.getByText("Get hired", new Page.GetByTextOptions().setExact(false));

        //-----------------------------------------------------
        // Result Page
        //-----------------------------------------------------

        archetypeBadge =
                page.locator(".result-level-pill");

        archetypeTitle =
                page.locator(".result-archetype-title");

        archetypeTagline =
                page.locator(".result-archetype-tagline");

        planSteps =
                page.locator(".result-step-title");
        //-----------------------------------------------------
// Question 5
//-----------------------------------------------------

        outcomeOptions =
                page.locator(".assess-option");

//-----------------------------------------------------
// Question 6
//-----------------------------------------------------

        timeOptions =
                page.locator(".assess-option");

//-----------------------------------------------------
// Question 7
//-----------------------------------------------------

        roleOptions =
                page.locator(".assess-option");

//-----------------------------------------------------
// Question 8
//-----------------------------------------------------

        gpuOptions =
                page.locator(".assess-option");

//-----------------------------------------------------
// Question 9
//-----------------------------------------------------

        budgetOptions =
                page.locator(".assess-option");

//-----------------------------------------------------
// Question 10
//-----------------------------------------------------

        interestOptions =
                page.locator(".assess-option");

//-----------------------------------------------------
// Question 11
//-----------------------------------------------------

        timelineOptions =
                page.locator(".assess-option");

//-----------------------------------------------------
// Question 12
//-----------------------------------------------------

        learningOptions =
                page.locator(".assess-option");

//-----------------------------------------------------
// Optional Courses
//-----------------------------------------------------

        previousCourseOptions =
                page.locator(".assess-option");
    }
    //=========================================================
    // Common Methods
    //=========================================================

    public void clickStartMyAIJourney() {
        startMyAIJourneyMenu.waitFor();
        startMyAIJourneyMenu.click();
    }

    public boolean isAssessmentPageOpened() {
        assessmentHeading.waitFor();
        return assessmentHeading.isVisible();
    }

    public String getCurrentQuestion() {
        return questionHeading.innerText().trim();
    }

    public String getQuestionNumber() {
        return questionNumber.innerText().trim();
    }

    public boolean isContinueButtonVisible() {
        return continueButton.isVisible();
    }

    public void clickContinue() {
        continueButton.click();
    }

    public int getVisibleOptionCount() {
        return optionCards.count();
    }

    public String getArchetypeTitle() {
        archetypeTitle.waitFor();
        return archetypeTitle.innerText().trim();
    }

    public String getArchetypeTagline() {
        return archetypeTagline.innerText().trim();

    }
    public void selectOptionContainingText(String text) {

        Locator option =
                page.locator(".assess-option")
                        .filter(new Locator.FilterOptions()
                                .setHasText(text));

        option.waitFor();
        option.click();
    }
    public void selectMultipleOptions(String... options){

        for(String option : options){

            page.locator(".assess-option")
                    .filter(new Locator.FilterOptions()
                            .setHasText(option))
                    .click();
        }

    }
    public void clickContinueIfVisible(){

        if(continueButton.isVisible()){

            continueButton.click();

        }

    }
    public void waitForNextQuestion(){

        page.waitForTimeout(800);
    }
    public void answerQuestion(String answer){

        selectOptionContainingText(answer);

        clickContinueIfVisible();

        waitForNextQuestion();

    }
    public void answerMultiSelectQuestion(String... answers){

        selectMultipleOptions(answers);

        clickContinueIfVisible();

        waitForNextQuestion();

    }
    public boolean isQuestionDisplayed(String question){

        return questionHeading.innerText().trim().contains(question);

    }
    public String getProgressLabel(){

        return questionNumber.innerText().trim();

    }
    public boolean isBackButtonVisible(){

        return backButton.isVisible();

    }
    public boolean isContinueDisplayed(){

        return continueButton.isVisible();

    }
    public boolean isResultPageDisplayed(){

        archetypeTitle.waitFor();

        return archetypeTitle.isVisible();

    }
    public int getRecommendedCourseCount(){

        return planSteps.count();

    }
    public String getRecommendedCourse(int index){

        return planSteps.nth(index).innerText().trim();

    }
    public boolean isCoursePresent(String courseName){

        return page.locator(".result-step-title")
                .filter(new Locator.FilterOptions()
                        .setHasText(courseName))
                .isVisible();

    }
    public void waitForResultPage() {

        archetypeTitle.waitFor();

    }
    public String getResultArchetype() {

        waitForResultPage();

        return archetypeTitle.innerText().trim();

    }
    public String getResultTagline() {

        return archetypeTagline.innerText().trim();

    }
    public boolean isBeginnerBadgeDisplayed() {

        return archetypeBadge.innerText().trim().equalsIgnoreCase("BEGINNER");

    }
    public boolean verifyCoursePresent(String courseName) {

        return page.locator(".result-step-title")
                .filter(new Locator.FilterOptions()
                        .setHasText(courseName))
                .count() > 0;

    }
    public int getTotalRecommendedCourses() {

        return planSteps.count();

    }
    public String getCourseName(int index) {

        return planSteps.nth(index).innerText().trim();

    }
    public boolean verifyFirstCourse(String expectedCourse) {

        return getCourseName(0)
                .equalsIgnoreCase(expectedCourse);

    }
    public boolean verifySecondCourse(String expectedCourse) {

        return getCourseName(1)
                .equalsIgnoreCase(expectedCourse);

    }
    public boolean verifyRecommendedCourses(String... expectedCourses) {

        for (String expected : expectedCourses) {

            if (!verifyCoursePresent(expected)) {
                return false;
            }
        }

        return true;

    }
    public boolean verifyArchetype(String expectedArchetype) {

        return getResultArchetype()
                .equalsIgnoreCase(expectedArchetype);

    }
    public boolean verifyTagline(String expectedTagline) {

        return getResultTagline()
                .equalsIgnoreCase(expectedTagline);

    }
    public boolean isResultPageOpened() {

        return page.url().contains("/my-plan");

    }
    public boolean isResultDisplayed() {

        return archetypeTitle.isVisible()
                && archetypeBadge.isVisible();

    }
    public void scrollToPlan() {

        planSteps.first().scrollIntoViewIfNeeded();

    }
    public java.util.List<String> getAllRecommendedCourses() {

        int count = planSteps.count();

        java.util.List<String> courses = new java.util.ArrayList<>();

        for (int i = 0; i < count; i++) {

            courses.add(planSteps.nth(i).innerText().trim());

        }

        return courses;

    }

}



