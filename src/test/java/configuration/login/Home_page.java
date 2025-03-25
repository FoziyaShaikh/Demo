package configuration.login;

import com.microsoft.playwright.Page;

public class Home_page {
    private Page page;

    
    public Home_page(Page page) {
        this.page = page;
    }

    
    public void clickOnBackButton() {
        page.locator("(//button[contains(@class,'sc-bdVaJa jotAxC')])[1]").click();
    }

    public void clickOnMetrics() {
        page.locator("//b[normalize-space(text())='Metrics']").click();
    }

    public void clickOnPlans() {
        page.locator("//button[normalize-space(text())='Plans']").click();
    }

    public void clickOnRewards() {
        page.locator("//button[normalize-space(text())='Rewards']").click();
    }

    public void clickOnAccounting() {
        page.locator("//button[normalize-space(text())='Accounting']").click();
    }

    public void clickOnOrganization() {
        page.locator("//button[normalize-space(text())='Organization']").click();
    }

    public void clickOnOperations() {
        page.locator("//button[normalize-space(text())='Operations']").click();
    }

    public void clickOnPerformance() {
        page.locator("//button[contains(.,'Performance')]").click();
    }

    public void clickOnDashboard() {
        page.locator("//span[normalize-space(text())='Dashboard']").click();
    }

    public void clickOnPerformanceOnTop() {
        page.locator("//span[normalize-space(text())='Performance']").click();
    }

    public void clickOnGoals() {
        page.locator("//span[normalize-space(text())='Goals']").click();
    }

    public void clickOnAdmin() {
        page.locator("//span[normalize-space(text())='Admin']").click();
    }

    public void clickOnUserLogo() {
        page.locator("//div[@class='user-menu']").click();
    }

    public void clickOnSignOut() {
        page.locator("(//a[@class='dropdown-item']/following-sibling::a)[3]").click();
    }

    public void clickOnKinitroLogo() {
        page.locator("//img[@alt='Logo']").click();
    }
}
