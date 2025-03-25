package mars.metrics.pages;

import com.microsoft.playwright.Page;

public class Object_Maps_page {
    private Page page;

    // Constructor to initialize Page instance
    public Object_Maps_page(Page page) {
        this.page = page;
    }

    // Locators
    private String objectMaps = "//b[normalize-space(text())='Object Maps']";
    private String editIcon = "(//div[@role='gridcell']//button)[1]";
    private String nextButton = "//button[normalize-space(text())='Next']";
    private String previousButton = "//button[normalize-space(text())='Previous']";
    private String rowSizeDropdown = "//select[@aria-label='rows per page']";
    private String newButton = "(//button[text()='New'])[1]";
    private String objectNameTextbox = "//input[@role='combobox']";
    private String mapNameTextbox = "(//input[@type='text'])[2]";
    private String descriptionTextbox = "//textarea[@id='object-mapping-objectDescription']";
    private String saveButton = "//button[@type='submit']";
    private String cancelButton = "//button[@id='cancel-save']";

    // Click Methods
    public void clickOnObjectMaps() {
        page.locator(objectMaps).click();
    }

    public void clickOnEditIcon() {
        page.locator(editIcon).click();
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

    public void clickOnSaveButton() {
        page.locator(saveButton).click();
    }

    public void clickOnCancelButton() {
        page.locator(cancelButton).click();
    }

    // Dropdown Selection
    public void selectRowSize(String value) {
        page.locator(rowSizeDropdown).selectOption(value);
    }

    // Input Fields
    public void enterObjectName(String objectName) {
        page.locator(objectNameTextbox).click();
        page.locator(objectNameTextbox).fill(objectName);
        page.locator("//mark[text()='" + objectName + "']").click();
        System.out.println(objectName);
    }

    public void enterMapName(String mapName) {
        page.locator(mapNameTextbox).fill(mapName);
    }
    
    
    public String getMapName(String mapName)
    {
	
     return page.locator("//div[text()='"+mapName+"']").textContent();
     
     
    }


    public void enterDescription(String description) {
        page.locator(descriptionTextbox).fill(description);
    }
}
