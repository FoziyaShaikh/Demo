package mars.metrics.pages;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Page;

public class Define_Object_page {
    private Page page;

       public Define_Object_page(Page page) {
        this.page = page;
    }

   
    private String defineObject = "//b[normalize-space(text())='Define Object']";
    private String editIcon = "(//div[@role='gridcell']//button)[1]";
    private String deleteIcon = "(//div[@role='gridcell']//button)[2]";
    private String nextButton = "//button[normalize-space(text())='Next']";
    private String previousButton = "//button[normalize-space(text())='Previous']";
    private String rowSizeDropdown = "//select[@aria-label='rows per page']";
    private String newButton = "(//button[text()='New'])[1]";
    private String deleteButton = "//button[text()='Delete']";
    private String objectMetadataNameTextbox = "#objectmetadataName";
    private String chooseFile = "//input[@class='form-control form-control-file']";
    private String changeInValueDropdown = "//select[contains(@class,'is-touched is-pristine')]";
    private String loadButton = "//button[@type='submit']";
    private String createdObjectName = "(//div[@role='gridcell'])[1]";
    private String selectPrimingDateError = "//div[contains(text(),'Please select priming date.')]";
    private String thisFieldIsRequiredError = "//div[contains(text(),'This field is required.')]";

  
    public void clickOnDefineObject() {
        page.locator(defineObject).click();
    }

    public void clickOnEditIcon() {
        page.locator(editIcon).click();
    }

    public void clickOnDeleteIcon() {
        page.locator(deleteIcon).click();
    }

    public void clickOnNextButton() {
        page.locator(nextButton).click();
    }

    public void clickOnPreviousButton() {
        page.locator(previousButton).click();
    }

    public void clickOnNewButton() {
        page.locator(newButton).click();
    }

    public void clickOnDeleteButton() {
        page.locator(deleteButton).click();
    }

    public void clickOnLoadButton() {
        page.locator(loadButton).click();
    }

 
    public void selectRowSize(String value) {
        page.locator(rowSizeDropdown).selectOption(value);
    }

    public void selectChangeInValueDropdown(String value) {
        page.locator(changeInValueDropdown).selectOption(value);
    }

 
    public void enterObjectMetadataName(String metadataname) {
        page.locator(objectMetadataNameTextbox).fill(metadataname);
    }

    public void chooseFile(String filepath) {
        Path absoluteFilePath = Paths.get(System.getProperty("user.dir"), "Mars_TestData", filepath).toAbsolutePath();
        
        page.locator(chooseFile).setInputFiles(absoluteFilePath);
    }

    
    public String getCreatedObjectName() {
        return page.locator(createdObjectName).textContent();
    }

    public String getErrorSelectPrimingDate() {
        return page.locator(selectPrimingDateError).textContent();
    }

    public String getErrorThisFieldIsRequired() {
        return page.locator(thisFieldIsRequiredError).textContent();
    }

   
}
