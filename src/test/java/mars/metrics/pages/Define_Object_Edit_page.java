package mars.metrics.pages;

import com.microsoft.playwright.Page;

public class Define_Object_Edit_page {
    private Page page;

   
    public Define_Object_Edit_page(Page page) {
        this.page = page;
    }

  
    private String objectNameTextbox = "(//label[normalize-space(text())='Object Name']/following::input)[1]";
    private String changeInValueDropdown = "//select[contains(@class,'is-touched is-pristine')]";
    private String manageFieldsButton = "//button[normalize-space(text())='Manage Fields']";
    private String objectIDFormulaToggle = "(//div[@class='react-switch-handle'])[1]";
    private String transactionDescriptionFormulaToggle = "(//div[@class='react-switch-handle'])[2]";
    private String transactionDateTimeFormulaToggle = "(//div[@class='react-switch-handle'])[3]";
    private String defineCalculationForObjectIDTextbox = "(//div[@class='view-line'])[1]";
    private String firstTransactionDescriptionTextbox = "(//div[@class='view-lines'])[2]";
    private String secondTransactionDescriptionTextbox = "(//div[@class='view-lines'])[3]";
    private String defineCalculationForTransactionDateTimeTextbox = "(//div[@class='view-lines'])[4]";
    private String defineCalculationForParentObjectIDTextbox = "(//div[@class='view-lines'])[5]";
    private String parentObjectDescriptionTextbox = "(//div[@class='view-lines'])[6]";
    private String rejectFileIfDuplicateCheckbox = "(//input[@class='form-check-input'])[1]";
    private String doNotCreateKPIsCheckbox = "(//input[@class='form-check-input'])[2]";
    private String definePrimingTransactionsDate = "//input[@placeholder='Please Select From Date']";
    private String parentObjectNameTextbox = "(//label[normalize-space(text())='Parent Object Name']/following::input)[1]";
    private String saveButton = "//button[@type='submit']";
    private String cancelButton = "//button[text()='Cancel']";

 
    public void enterObjectName(String objectName) {
        page.locator(objectNameTextbox).fill(objectName);
    }

    public void enterParentObjectName(String objectName) {
        page.locator(parentObjectNameTextbox).fill(objectName);
        page.locator(parentObjectNameTextbox).press("ArrowDown");
        page.locator(parentObjectNameTextbox).press("Enter");
    }

    public void enterDefineCalculationForObjectID(String objectID) {
        page.locator(defineCalculationForObjectIDTextbox).click();
        page.keyboard().press("Control+A");
        page.keyboard().press("Backspace");
        page.keyboard().type(objectID);
    }

    public void enterFirstTransactionDescription(String description) {
        page.locator(firstTransactionDescriptionTextbox).click();
        page.keyboard().press("Control+A");
        page.keyboard().press("Backspace");
        page.keyboard().type(description);
    }

    public void enterSecondTransactionDescription(String description) {
        page.locator(secondTransactionDescriptionTextbox).click();
        page.keyboard().press("Control+A");
        page.keyboard().press("Backspace");
        page.keyboard().type(description);
    }

    public void enterDefineCalculationForTransactionDateTime(String timeFunction) {
        page.locator(defineCalculationForTransactionDateTimeTextbox).click();
        page.keyboard().type(timeFunction);
        page.keyboard().press("Enter");
    }

    public void enterDefineCalculationForParentObjectID(String defineCalculation) {
        page.locator(defineCalculationForParentObjectIDTextbox).click();
        page.keyboard().type(defineCalculation);
    }

    public void enterParentObjectDescription(String description) {
        page.locator(parentObjectDescriptionTextbox).click();
        page.keyboard().type(description);
    }

       public void clickOnManageFieldsButton() {
        page.locator(manageFieldsButton).click();
    }

    public void clickOnObjectIDFormulaToggle() {
        page.locator(objectIDFormulaToggle).click();
    }

    public void clickOnTransactionDescriptionFormulaToggle() {
        page.locator(transactionDescriptionFormulaToggle).click();
    }

    public void clickOnTransactionDateTimeFormulaToggle() {
        page.locator(transactionDateTimeFormulaToggle).click();
    }

    public void clickOnRejectFileIfDuplicateCheckbox() {
        page.locator(rejectFileIfDuplicateCheckbox).click();
    }

    public void clickOnDoNotCreateKPIsCheckbox() {
        page.locator(doNotCreateKPIsCheckbox).click();
    }

    public void clickOnDefinePrimingTransactionsDate() {
        page.locator(definePrimingTransactionsDate).click();
    }

    public void clickOnSave() {
        page.locator(saveButton).scrollIntoViewIfNeeded();
        page.locator(saveButton).click();
    }

    public void clickOnCancel() {
        page.locator(cancelButton).click();
    }

    
    public void selectChangeInValueDropdown(String value) {
        page.locator(changeInValueDropdown).selectOption(value);
    }

    public void clickOnCommissionIsSplitBetweenMultipleAssociates(String yesOrNo) {
        page.locator("//label[text()='" + yesOrNo + "']").click();
    }
}
