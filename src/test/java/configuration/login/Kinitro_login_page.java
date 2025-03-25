package configuration.login;

import com.microsoft.playwright.Page;

public class Kinitro_login_page {
    private Page page;

    
    public Kinitro_login_page(Page page) {
        this.page = page;
    }

   
    private String userNameOrEmail = "//input[@placeholder='Email']";
    private String password = "//input[@placeholder='Password']";
    private String loginBtn = "//button[normalize-space(text())='LOG IN']";
    private String errorMessageForEmail = "//div[@class='invalid-tooltip']";
    private String badCredentialError = "//div[@class='alert alert-danger fade show']";

   
    public void enterUsername(String user) {
        page.locator(userNameOrEmail).fill(user);
    }

    public void enterPassword(String pass) {
        page.locator(password).fill(pass);
    }

    public void clickOnLoginButton() {
        page.locator(loginBtn).click();
    }

    public void doLogin(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickOnLoginButton();
    }

    public String getErrorMessageForEmail() {
        return page.locator(errorMessageForEmail).textContent();
    }

    public String getErrorMessageBadCredential() {
        return page.locator(badCredentialError).textContent();
    }
}
