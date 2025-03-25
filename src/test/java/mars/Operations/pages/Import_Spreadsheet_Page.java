package mars.Operations.pages;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Page;

public class Import_Spreadsheet_Page {

  private Page page;

public Import_Spreadsheet_Page(Page page) {
	this.page=page;
}


public void click_On_Operations() {
	
	page.locator("//button[normalize-space(text())='Operations']").click();
}

public void click_On_Import_Spreadsheet() {
	
	page.locator("//a[normalize-space(text())='Import Spreadsheet']").click();
}
public void click_On_Import() {
	
	page.locator("//button[normalize-space(text())='Import']").click();
}
public void click_On_Transaction_or_TeamCredit(String name) {
	
	page.locator("(//button[text()='"+name+"'])[1]").click();
}

public void click_On_plusIcon_To_expand(String filename) {
	
	page.locator("//div[text()='"+filename+"']/following::div[3]").click();
}

public void chooseFile(String filepath) {
	Path absoluteFilePath = Paths.get(System.getProperty("user.dir"), "Mars_TestData", filepath).toAbsolutePath();

	page.locator("//input[@placeholder='Search']").setInputFiles(absoluteFilePath);
}
public void clickOnLoadButton() {
	page.locator("//button[@type='submit']").click();
}


}