package mars.metrics.defineobject.stepdefinitions;

import com.microsoft.playwright.Page;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import configuration.login.Kinitro_login_page;
import configuration.login.TestBase;
import java.util.List;
import java.util.Map;

public class Login_page_step_def {
    Logger logger = LoggerFactory.getLogger(Login_page_step_def.class);
    TestBase testBase = TestBase.getInstance();
    Page page = testBase.getPage();
    Kinitro_login_page loginPage;

    @Given("user is already logged in")
    public void user_is_already_logged_in() {
       // testBase.initialization();
        page.navigate(testBase.getProperty("mars_url"));
        page.waitForLoadState();
        page.locator("body").click();  // Ensures page is loaded

        loginPage = new Kinitro_login_page(page);
        loginPage.doLogin(testBase.getProperty("userName"), testBase.getProperty("password"));

        String currentUrl = page.url();
        logger.info("Login URL of Kinitro: {}", currentUrl);
    }

    @Given("the user is on login page")
    public void the_user_is_on_login_page() {
       // testBase.initialization();
        page.navigate(testBase.getProperty("mars_url"));
        page.waitForLoadState();
        logger.info("Browser launched and user is on login page.");
    }

    @When("entered valid email and password")
    public void entered_email_and_password(DataTable dataTable) {
        List<Map<String, String>> loginData = dataTable.asMaps(String.class, String.class);
        loginPage = new Kinitro_login_page(page);

        loginPage.enterUsername(loginData.get(0).get("username"));
        loginPage.enterPassword(loginData.get(0).get("password"));
        loginPage.clickOnLoginButton();

        logger.info("User successfully logged in.");
    }

    @Then("the user should be taken to home page")
    public void the_user_should_be_taken_to_home_page() throws InterruptedException {
    	page.waitForLoadState();
    	 Thread.sleep(3000);
        boolean actualResult = page.locator("//b[normalize-space(text())='Metrics']").isEnabled();
        Thread.sleep(3000);
        Assert.assertTrue(actualResult, "User should be on home page.");
    }

    @When("the user leaves the email and password field empty")
    public void the_user_leaves_the_email_and_password_field_empty() { 
        loginPage = new Kinitro_login_page(page);
        loginPage.clickOnLoginButton();
        logger.info("User clicked on login button with empty fields.");
    }

    @Then("the error message {string} should be displayed")
    public void the_error_message_should_be_displayed(String expectedError) {
        loginPage = new Kinitro_login_page(page);
        String actualError = "";

        try {
            if (expectedError.equals("Please Enter Username or Email")) {
                actualError = loginPage.getErrorMessageForEmail();
                
            } else if (expectedError.equals("Bad Credentials!")) {
                actualError = loginPage.getErrorMessageBadCredential();
            }
            
        } catch (Exception e) {
            logger.error("Error while fetching error message: " + e.getMessage());
        }

        Assert.assertEquals(actualError, expectedError, "Error message should match.");
        logger.info("Displayed Error message: " + actualError);
    }

    @When("entered valid email and invalid password")
    public void entered_valid_email_and_invalid_password(DataTable dataTable) {
        List<Map<String, String>> invalidLoginData = dataTable.asMaps(String.class, String.class);
        loginPage = new Kinitro_login_page(page);

        loginPage.enterUsername(invalidLoginData.get(0).get("valid_username"));
        loginPage.enterPassword(invalidLoginData.get(0).get("invalid_password"));
        loginPage.clickOnLoginButton();

        logger.info("User entered valid email and invalid password.");
    }
}
