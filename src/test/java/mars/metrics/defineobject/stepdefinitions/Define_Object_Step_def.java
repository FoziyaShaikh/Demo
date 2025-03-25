package mars.metrics.defineobject.stepdefinitions;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.python.modules.thread.thread;
import org.sikuli.hotkey.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import configuration.login.Home_page;
import configuration.login.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jline.internal.Log;
import mars.Operations.pages.Import_Spreadsheet_Page;
import mars.Utility.Calendors;
import mars.metrics.pages.Define_Object_Edit_page;
import mars.metrics.pages.Define_Object_page;

public class Define_Object_Step_def  {

	Logger logger = LoggerFactory.getLogger(Define_Object_Step_def.class);
	TestBase testbase= TestBase.getInstance();
	Define_Object_page define_Object_page;
	Import_Spreadsheet_Page import_Spreadsheet_Page;
	Define_Object_Edit_page define_Object_Edit_page;
	Home_page home_page;

	@Given("user is on home page")
	public void user_is_on_home_page() {
		testbase.getPage().locator("//b[contains(text(),'Metrics')]")
		    .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		
		boolean actual_result = testbase.getPage().locator("//b[contains(text(),'Metrics')]").isVisible();
        Assert.assertTrue(actual_result);
		logger.info("user is on home page");
	}

	@When("user clicks on metrics menu")
	public void user_clicks_on_metrics_menu() throws InterruptedException {
		home_page=new Home_page(testbase.getPage());
		home_page.clickOnMetrics();
		logger.info("user is on matrics page");
	}

	@When("clicks on Define Object menu")
	public void clicks_on_define_object_menu() throws InterruptedException {
		define_Object_page=new Define_Object_page(testbase.getPage());
		define_Object_page.clickOnDefineObject();
		logger.info("user is on define object page");
	}		

	String first_object;
	@When("click on the edit button of any object")
	public void click_on_the_edit_button_of_any_object() throws InterruptedException {
		define_Object_page=new Define_Object_page(testbase.getPage());
		Log.info("Hello4545");
		first_object = define_Object_page.getCreatedObjectName();
		define_Object_page.clickOnEditIcon();
		logger.info("user is click on edit icon of:- " +first_object);
	}

	@Then("it should open object page")
	public void it_should_open__object_page() throws InterruptedException {
		Thread.sleep(3000);
		boolean actual_result = testbase.getPage().locator("//label[normalize-space(text())='Object Name']").isVisible();
		Assert.assertTrue(actual_result);
		// testbase.quitDriver();
	}

	@Then("it should open that object page")
	public void it_should_open_that_object_page() {

		String actual_result = testbase.getPage().locator("(//label[normalize-space(text())='Object Name']/following::input)[1]").getAttribute("value");
		Assert.assertEquals(actual_result,first_object);

		logger.info("Opened Object is  :- " +actual_result);
		// testbase.quitDriver();
	}
	@When("click on the new button")
	public void click_on_the_new_button() {
		define_Object_page=new Define_Object_page(testbase.getPage());
		define_Object_page.clickOnNewButton();
		logger.info("user is Import spreadsheet For Metadata page");
	}


	@Given("user on Define Object page")
	public void user_on_define_object_page() throws InterruptedException {
		define_Object_page=new Define_Object_page(testbase.getPage());
		home_page=new Home_page(testbase.getPage());
		//Thread.sleep(2000);
		home_page.clickOnMetrics();
		define_Object_page.clickOnDefineObject();
	}
	

	
	@Then("it should open that Import spreadsheet For Metadata page")
	public void it_should_open_that_import_spreadsheet_for_metadata_page() {
		boolean actual_result = testbase.getPage().locator("//h5[normalize-space(text())='Import spreadsheet For Metadata']").isVisible();
		Assert.assertTrue(actual_result);
		
	}

	String generated_object_name;
	@When("user enter metadata name and load file")
	public void user_enter_metadata_name_and_load_file(DataTable dataTable) {
		Random rand = new Random();
		int randomDigit = rand.nextInt(100);
		List<Map<String, String>> object_name = dataTable.asMaps(String.class,String.class);
		generated_object_name = object_name.get(0).get("Metadata_name")+randomDigit;

		define_Object_page.enterObjectMetadataName(generated_object_name);
		define_Object_page.chooseFile(object_name.get(0).get("File_name"));

		define_Object_page.clickOnLoadButton();
		
		logger.info("Entered object meta data name :- "+generated_object_name);
		logger.info("user load meta data file from Import spreadsheet For Metadata page");

	}
	@When("user enter exiting metadata name and load file")
	public void user_enter_exiting_metadata_name_and_load_file(DataTable dataTable) {
		List<Map<String, String>> object_name = dataTable.asMaps(String.class,String.class);

		define_Object_page.enterObjectMetadataName(object_name.get(0).get("Metadata_name"));

		define_Object_page.chooseFile(object_name.get(0).get("File_name"));

		define_Object_page.clickOnLoadButton();
		logger.info("user load meta data file from Import spreadsheet For Metadata page");
	}

	@Then("error message {string} should be displayed")
	public void error_message_should_be_displayed(String expected_error) {

		String actual_error_message = testbase.getPage().locator("//div[contains(@class,'alert alert-danger') or contains(text(),'This field is required.') or contains(text(),'Please select priming date.')]").textContent();

		Assert.assertEquals(actual_error_message, expected_error);
		//testbase.quitDriver();
	}



	@When("following details enter on add define object page")
	public void following_details_enter_on_add_define_object_page(DataTable dataTable) throws InterruptedException {

		List<List<String>> object_details = dataTable.asLists(String.class);
		define_Object_Edit_page=new Define_Object_Edit_page(testbase.getPage());
	
		Thread.sleep(6000);
		define_Object_Edit_page.enterDefineCalculationForObjectID(object_details.get(0).get(1));
		Thread.sleep(4000);
		define_Object_Edit_page.enterFirstTransactionDescription(object_details.get(1).get(1));
		define_Object_Edit_page.enterSecondTransactionDescription(object_details.get(2).get(1));
		define_Object_Edit_page.enterDefineCalculationForTransactionDateTime(object_details.get(3).get(1));
		define_Object_Edit_page.clickOnDefinePrimingTransactionsDate();
		Calendors calendor=new Calendors(testbase.getPage());
		calendor.selectDate(object_details.get(4).get(1));
		define_Object_Edit_page.clickOnDoNotCreateKPIsCheckbox();
//		define_Object_Edit_page.enterParentObjectName(object_details.get(5).get(1));
//		define_Object_Edit_page.enterDefineCalculationForParentObjectID(object_details.get(6).get(1));
		define_Object_Edit_page.clickOnSave();
		Thread.sleep(3000);
	}
	@Then("it will create the Object with entered Object name")
	public void it_will_create_the_object_with_entered_object_name() {
		String actaul_object_name = null;
		String pagecount = testbase.getPage().locator("//div[@class='-pageJump']/following-sibling::span[1]").textContent();
		int totalPages = Integer.parseInt(pagecount.trim());
	    int currentPage = 1;
		while(currentPage < totalPages) {
		
			boolean object = false;

			try {
				object =testbase.getPage().locator("//div[text()='"+generated_object_name+"']").isVisible();
			}
			catch (Exception e) {
				System.out.println("Object map not present on current page ");
			}
			if (object) {
				actaul_object_name =testbase.getPage().locator("//div[text()='"+generated_object_name+"']").textContent();
				break;
			}
			else {
				testbase.getPage().locator("//button[normalize-space(text())='Next']").click();
			}
			currentPage++;
		}


		Assert.assertEquals(actaul_object_name, generated_object_name);
	}

	String tobedeleted ;
	@When("click on the delete button of any object")
	public void click_on_the_delete_button_of_any_object() throws InterruptedException {
		define_Object_page=new Define_Object_page(testbase.getPage());
		tobedeleted = define_Object_page.getCreatedObjectName();
		define_Object_page.clickOnDeleteIcon();
		define_Object_page.clickOnDeleteButton();
		Thread.sleep(3000);
		logger.info("user is on delete icon of object :- "+ tobedeleted );
	}
	@Then("that object should be delete from object list")
	public void that_object_should_be_delete_from_object_list() {

		Assert.assertNotEquals(define_Object_page.getCreatedObjectName(), tobedeleted);
		logger.info("user successfully deleted object :- "+tobedeleted);
	}



	@Given("user is on edit define object page")
	public void user_is_on_edit_define_object_page() throws InterruptedException {
		define_Object_page.clickOnNewButton();
		logger.info("user is Import spreadsheet For Metadata page");
		Random rand = new Random();
		int randomDigit = rand.nextInt(100);
		generated_object_name ="0"+randomDigit;

		define_Object_page.enterObjectMetadataName(generated_object_name);
		define_Object_page.chooseFile("Metadata file.xlsx");

		define_Object_page.clickOnLoadButton();
		logger.info("Entered object meta data name :- "+generated_object_name);
		logger.info("user load meta data file from Import spreadsheet For Metadata page");
		Thread.sleep(6000);
		define_Object_Edit_page=new Define_Object_Edit_page(testbase.getPage());
		define_Object_Edit_page.enterDefineCalculationForObjectID("A1:Id");
		Thread.sleep(4000);
		define_Object_Edit_page.enterFirstTransactionDescription("B1:Transaction_date");
		define_Object_Edit_page.enterSecondTransactionDescription("E1:Payment_type");
		define_Object_Edit_page.enterDefineCalculationForTransactionDateTime("NOW()");
		define_Object_Edit_page.clickOnDefinePrimingTransactionsDate();
		Calendors calendor=new Calendors(testbase.getPage());
		calendor.selectDate("28/02/2025");
		define_Object_Edit_page.clickOnDoNotCreateKPIsCheckbox();
//		define_Object_Edit_page.enterParentObjectName("100");
//		define_Object_Edit_page.enterDefineCalculationForParentObjectID("A1:Id");
		define_Object_Edit_page.clickOnSave();
		Thread.sleep(3000);
		logger.info("Define object is created successfully with all mandatory field");
		define_Object_page.clickOnEditIcon();
		logger.info("User is on edit define object page");
	}
	@When("following details enter on edit define object page")
	public void following_details_enter_on_edit_define_object_page(DataTable dataTable) throws InterruptedException {

		List<List<String>> object_details = dataTable.asLists(String.class);
		define_Object_Edit_page=new Define_Object_Edit_page(testbase.getPage());
		Thread.sleep(6000);
		define_Object_Edit_page.enterDefineCalculationForObjectID(object_details.get(0).get(1));
		Thread.sleep(4000);
		define_Object_Edit_page.enterFirstTransactionDescription(object_details.get(1).get(1));
		define_Object_Edit_page.enterSecondTransactionDescription(object_details.get(2).get(1));
		define_Object_Edit_page.clickOnDefinePrimingTransactionsDate();
		Calendors calendor=new Calendors(testbase.getPage());
		calendor.selectDate(object_details.get(3).get(1));
		define_Object_Edit_page.clickOnSave();
		Thread.sleep(3000);
		logger.info("Entered Define Calculation for Object ID :- "+object_details.get(0).get(1));
		logger.info("Entered first Transaction  Description :- "+object_details.get(1).get(1));
		logger.info("Entered Second Transaction Description :- "+object_details.get(2).get(1));
		logger.info("Entered Define Priming Transactions( Transactions dated prior to below date are priming transactions) date :- "+object_details.get(3).get(1));



	}
	@Then("following details should be saved in the object")
	public void following_details_should_be_saved_in_the_object(DataTable dataTable) throws InterruptedException {
		Thread.sleep(3000);
		List<List<String>> expected_object_details = dataTable.asLists(String.class);
		define_Object_page.clickOnEditIcon();

		String actual_Define_Calculation_for_Object_ID_ =testbase.getPage().locator("((//div[@class='view-lines'])[1]//span)[2]").textContent();
			
		String actual_first_Transaction_Description  =testbase.getPage().locator("((//div[@class='view-lines'])[2]//span)[2]").textContent();
				
		String actual_Second_Transaction_Description  =testbase.getPage().locator("((//div[@class='view-lines'])[3]//span)[2]").textContent();
				
		String actual_date  = testbase.getPage().locator("//input[@placeholder='Please Select From Date']").getAttribute("value");
				

		Assert.assertEquals(actual_Define_Calculation_for_Object_ID_, expected_object_details.get(0).get(1));
		Assert.assertEquals(actual_first_Transaction_Description, expected_object_details.get(1).get(1));
		Assert.assertEquals(actual_Second_Transaction_Description, expected_object_details.get(2).get(1));
		Assert.assertEquals(actual_date, expected_object_details.get(3).get(1));

		logger.info("Actual Define Calculation for Object ID :- "+actual_Define_Calculation_for_Object_ID_);
		logger.info("Actual first Transaction  Description :- "+actual_first_Transaction_Description);
		logger.info("Actual Second Transaction Description :- "+actual_Second_Transaction_Description);
		logger.info("Actual Define Priming Transactions( Transactions dated prior to below date are priming transactions) date :- "+actual_date);



	}


	@When("Enter the following details for the defined object, except the field Define Calculation for Object ID")
	public void enter_the_following_details_for_the_defined_object_except_the_field_define_calculation_for_object_id(DataTable dataTable) throws InterruptedException {
		List<List<String>> object_details = dataTable.asLists(String.class);
		define_Object_Edit_page=new Define_Object_Edit_page(testbase.getPage());
		Thread.sleep(4000);
		define_Object_Edit_page.enterFirstTransactionDescription(object_details.get(0).get(1));
		define_Object_Edit_page.enterSecondTransactionDescription(object_details.get(1).get(1));
		define_Object_Edit_page.enterDefineCalculationForTransactionDateTime(object_details.get(2).get(1));
		define_Object_Edit_page.clickOnDefinePrimingTransactionsDate();
		Calendors calendor=new Calendors(testbase.getPage());
		calendor.selectDate(object_details.get(3).get(1));
		define_Object_Edit_page.clickOnDoNotCreateKPIsCheckbox();
		define_Object_Edit_page.enterParentObjectName(object_details.get(4).get(1));
		define_Object_Edit_page.enterDefineCalculationForParentObjectID(object_details.get(5).get(1));
		define_Object_Edit_page.clickOnSave();
		Thread.sleep(3000);


	}


	@When("Enter the following details for the defined object, except the field 1st Transaction Description")
	public void enter_the_following_details_for_the_defined_object_except_the_field_1st_transaction_description(DataTable dataTable) throws InterruptedException {
		List<List<String>> object_details = dataTable.asLists(String.class);
		define_Object_Edit_page=new Define_Object_Edit_page(testbase.getPage());
		Thread.sleep(4000);
		define_Object_Edit_page.enterDefineCalculationForObjectID(object_details.get(0).get(1));
		define_Object_Edit_page.enterSecondTransactionDescription(object_details.get(1).get(1));
		define_Object_Edit_page.enterDefineCalculationForTransactionDateTime(object_details.get(2).get(1));
		define_Object_Edit_page.clickOnDefinePrimingTransactionsDate();
		Calendors calendor=new Calendors(testbase.getPage());
		calendor.selectDate(object_details.get(3).get(1));
		define_Object_Edit_page.clickOnDoNotCreateKPIsCheckbox();
		define_Object_Edit_page.enterParentObjectName(object_details.get(4).get(1));
		define_Object_Edit_page.enterDefineCalculationForParentObjectID(object_details.get(5).get(1));
		define_Object_Edit_page.clickOnSave();
		Thread.sleep(3000);


	}


	@When("Enter the following details for the defined object, except the field Define Calculation for Transaction Date Time")
	public void enter_the_following_details_for_the_defined_object_except_the_field_define_calculation_for_transaction_date_time(DataTable dataTable) throws InterruptedException {

		List<List<String>> object_details = dataTable.asLists(String.class);
		define_Object_Edit_page=new Define_Object_Edit_page(testbase.getPage());
		Thread.sleep(6000);
		define_Object_Edit_page.enterDefineCalculationForObjectID(object_details.get(0).get(1));
		Thread.sleep(4000);
		define_Object_Edit_page.enterFirstTransactionDescription(object_details.get(1).get(1));
		define_Object_Edit_page.enterSecondTransactionDescription(object_details.get(2).get(1));
		define_Object_Edit_page.clickOnDefinePrimingTransactionsDate();
		Calendors calendor=new Calendors(testbase.getPage());
		calendor.selectDate(object_details.get(3).get(1));
		define_Object_Edit_page.clickOnDoNotCreateKPIsCheckbox();
		define_Object_Edit_page.enterParentObjectName(object_details.get(4).get(1));
		define_Object_Edit_page.enterDefineCalculationForParentObjectID(object_details.get(5).get(1));
		define_Object_Edit_page.clickOnSave();
		Thread.sleep(3000);

	}


	@When("Enter the following details for the defined object, except the field Parent Object Description Line {int}")
	public void enter_the_following_details_for_the_defined_object_except_the_field_parent_object_description_line(Integer int1,DataTable dataTable) throws InterruptedException {

		List<List<String>> object_details = dataTable.asLists(String.class);
		 define_Object_Edit_page=new Define_Object_Edit_page(testbase.getPage());
		Thread.sleep(6000);
		define_Object_Edit_page.enterDefineCalculationForObjectID(object_details.get(0).get(1));
		Thread.sleep(4000);
		define_Object_Edit_page.enterFirstTransactionDescription(object_details.get(1).get(1));
		define_Object_Edit_page.enterSecondTransactionDescription(object_details.get(2).get(1));
		define_Object_Edit_page.enterDefineCalculationForTransactionDateTime(object_details.get(3).get(1));
		define_Object_Edit_page.clickOnDefinePrimingTransactionsDate();
		Calendors calendor=new Calendors(testbase.getPage());
		calendor.selectDate(object_details.get(4).get(1));
		define_Object_Edit_page.clickOnDoNotCreateKPIsCheckbox();
		testbase.getPage().locator("(//label[normalize-space(text())='Parent Object Name']/following::input)[1]").fill(object_details.get(5).get(1));
		define_Object_Edit_page.enterDefineCalculationForParentObjectID(object_details.get(6).get(1));
		define_Object_Edit_page.clickOnSave();
		Thread.sleep(3000);



	}


	@When("Enter the following details for the defined object, except the field Define calculation for Parent Object Id")
	public void enter_the_following_details_for_the_defined_object_except_the_field_define_calculation_for_parent_object_id(DataTable dataTable) throws InterruptedException {
		List<List<String>> object_details = dataTable.asLists(String.class);
		define_Object_Edit_page=new Define_Object_Edit_page(testbase.getPage());
		Thread.sleep(6000);
		define_Object_Edit_page.enterDefineCalculationForObjectID(object_details.get(0).get(1));
		Thread.sleep(4000);
		define_Object_Edit_page.enterFirstTransactionDescription(object_details.get(1).get(1));
		define_Object_Edit_page.enterSecondTransactionDescription(object_details.get(2).get(1));
		define_Object_Edit_page.enterDefineCalculationForTransactionDateTime(object_details.get(3).get(1));
		define_Object_Edit_page.clickOnDefinePrimingTransactionsDate();
		Calendors calendor=new Calendors(testbase.getPage());
		calendor.selectDate(object_details.get(4).get(1));
		define_Object_Edit_page.clickOnDoNotCreateKPIsCheckbox();
		define_Object_Edit_page.enterParentObjectName(object_details.get(5).get(1));
		define_Object_Edit_page.clickOnSave();
		Thread.sleep(3000);


	}
	@When("Enter the following details for the defined object, except the field Define Priming Transactions")
	public void enter_the_following_details_for_the_defined_object_except_the_field_define_priming_transactions(DataTable dataTable) throws InterruptedException {

		List<List<String>> object_details = dataTable.asLists(String.class);
		define_Object_Edit_page=new Define_Object_Edit_page(testbase.getPage());
		Thread.sleep(6000);
		define_Object_Edit_page.enterDefineCalculationForObjectID(object_details.get(0).get(1));
		Thread.sleep(4000);
		define_Object_Edit_page.enterFirstTransactionDescription(object_details.get(1).get(1));
		define_Object_Edit_page.enterSecondTransactionDescription(object_details.get(2).get(1));
		define_Object_Edit_page.enterDefineCalculationForTransactionDateTime(object_details.get(3).get(1));
		define_Object_Edit_page.clickOnDoNotCreateKPIsCheckbox();
		define_Object_Edit_page.enterParentObjectName(object_details.get(4).get(1));
		define_Object_Edit_page.enterDefineCalculationForParentObjectID(object_details.get(5).get(1));
		define_Object_Edit_page.clickOnSave();
		Thread.sleep(3000);

	}

	@When("Enter the following details for the defined object, except the field Field Containing associate source system ID")
	public void enter_the_following_details_for_the_defined_object_except_the_field_field_containing_associate_source_system_id(DataTable dataTable) throws InterruptedException {
		List<List<String>> object_details = dataTable.asLists(String.class);
		define_Object_Edit_page=new Define_Object_Edit_page(testbase.getPage());
		Thread.sleep(6000);
		define_Object_Edit_page.enterDefineCalculationForObjectID(object_details.get(0).get(1));
		Thread.sleep(4000);
		define_Object_Edit_page.enterFirstTransactionDescription(object_details.get(1).get(1));
		define_Object_Edit_page.enterSecondTransactionDescription(object_details.get(2).get(1));
		define_Object_Edit_page.enterDefineCalculationForTransactionDateTime(object_details.get(3).get(1));
		define_Object_Edit_page.clickOnDefinePrimingTransactionsDate();
		Calendors calendor=new Calendors(testbase.getPage());
		calendor.selectDate(object_details.get(4).get(1));
		define_Object_Edit_page.clickOnDoNotCreateKPIsCheckbox();
		define_Object_Edit_page.enterParentObjectName(object_details.get(5).get(1));
		define_Object_Edit_page.enterDefineCalculationForParentObjectID(object_details.get(6).get(1));
		define_Object_Edit_page.clickOnCommissionIsSplitBetweenMultipleAssociates("No");
		define_Object_Edit_page.clickOnSave();
		Thread.sleep(3000);

	}


	@When("Enter the following details for the defined object and select non existing parent object name and Define calculation for Parent Object Id field is left empty")
	public void enter_the_following_details_for_the_defined_object_and_select_non_existing_parent_object_name_and_define_calculation_for_parent_object_id_field_is_left_empty(DataTable dataTable) throws InterruptedException {
		List<List<String>> object_details = dataTable.asLists(String.class);
		define_Object_Edit_page=new Define_Object_Edit_page(testbase.getPage());
		Thread.sleep(6000);
		define_Object_Edit_page.enterDefineCalculationForObjectID(object_details.get(0).get(1));
		Thread.sleep(4000);
		define_Object_Edit_page.enterFirstTransactionDescription(object_details.get(1).get(1));
		define_Object_Edit_page.enterSecondTransactionDescription(object_details.get(2).get(1));
		define_Object_Edit_page.enterDefineCalculationForTransactionDateTime(object_details.get(3).get(1));
		define_Object_Edit_page.clickOnDefinePrimingTransactionsDate();
		Calendors calendor=new Calendors(testbase.getPage());
		calendor.selectDate(object_details.get(4).get(1));
		define_Object_Edit_page.clickOnDoNotCreateKPIsCheckbox();
		testbase.getPage().locator("(//label[normalize-space(text())='Parent Object Name']/following::input)[1]").fill(object_details.get(5).get(1));
		Thread.sleep(2000);
		define_Object_Edit_page.clickOnSave();
		Thread.sleep(3000);
		
		
	}



	@When("click on the delete button of {string} object")
	public void click_on_the_delete_button_of_object(String object_name) throws InterruptedException {

		String pagecount = testbase.getPage().locator("//div[@class='-pageJump']/following-sibling::span[1]").textContent();
		int totalPages = Integer.parseInt(pagecount.trim());
		int currentPage = 1;

		while (currentPage <= totalPages) {
		    boolean delete = false;
		    try {
		        testbase.getPage().locator("//div[text()='"+object_name+"']/following-sibling::div//button[2]")
		            .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                 Thread.sleep(2000);
		        delete = testbase.getPage().locator("//div[text()='"+object_name+"']/following-sibling::div//button[2]").isVisible();
		    } catch (Exception e) {
		        System.out.println("Object map not present on current page: ");
		    }

		    if (delete) {
		        testbase.getPage().locator("//div[text()='"+object_name+"']/following-sibling::div//button[2]").click();
		        break;
		    } else {
		        List<Locator> nextButton = testbase.getPage().locator("//button[normalize-space(text())='Next']").all();
		        if (nextButton.isEmpty()) {
		            System.out.println("Reached last page, object not found.");
		            break;
		        }
		        nextButton.get(0).click();
		    }

		    currentPage++;
		}

		Thread.sleep(2000);
		logger.info("user click on delete button of object metadata :- "+object_name);
	}
	@Then("user cannot delete object metatda and message {string} should be displayed on popup")
	public void user_cannot_delete_object_metatda_and_message_should_be_displayed_on_popup(String expected) {

		String actual_messsage =testbase.getPage().locator("//div[@class='modal-header']/following-sibling::div[1]").textContent();
			
		Assert.assertEquals(actual_messsage, expected);
		logger.info("user is not able to delete Object metadata info message is:- "+actual_messsage);
		//testbase.quitDriver();
	}


	@When("click on cancel button")
	public void click_cancel_button() {

		define_Object_Edit_page=new Define_Object_Edit_page(testbase.getPage());
		define_Object_Edit_page.clickOnCancel();
		logger.info("Click on cancel button");
		
	}
	
	@Then("define object {string} shoud not be delete")
	public void define_object_shoud_not_be_delete(String objectname) {

		String actaul_object_name = null;
		String pagecount = testbase.getPage().locator("//div[@class='-pageJump']/following-sibling::span[1]").textContent();
				
		int totalPages = Integer.parseInt(pagecount.trim());
	    int currentPage = 1;
		while(currentPage < totalPages) {
			boolean object = false;

			try {
				object =testbase.getPage().locator("//div[text()='"+objectname+"']").isVisible();
					
			}
			catch (Exception e) {
				System.out.println("Object map not present on current page ");
			}
			if (object) {
				actaul_object_name =testbase.getPage().locator("//div[text()='"+objectname+"']").textContent();
						
				break;
			}
			else {
				testbase.getPage().locator("//button[normalize-space(text())='Next']").click();
			}
			currentPage++;
		}

		Assert.assertEquals(actaul_object_name, objectname);
		logger.info("Object is not delete:- "+actaul_object_name);

	}


@When("click on the edit icon button of {string} object")
public void click_on_the_edit_icon_button_of_object(String object_name) {
	
	String pagecount =testbase.getPage().locator("//div[@class='-pageJump']/following-sibling::span[1]").textContent();
	int totalPages = Integer.parseInt(pagecount.trim());
    int currentPage = 1;
	while(currentPage < totalPages) {
		boolean edit = false;
		try {
			edit =testbase.getPage().locator("//div[text()='"+object_name+"']/following-sibling::div//button[1]").isVisible();
			}
		catch (Exception e) {
			System.out.println("Object map not present on current page ");
		}
		if (edit) {
			testbase.getPage().locator("//div[text()='"+object_name+"']/following-sibling::div//button[1]").click();
			break;
		}
		else {
			testbase.getPage().locator("//button[normalize-space(text())='Next']").click();
			
		}
		currentPage++;
	}
	logger.info("user click on edit button of object metadata :- "+object_name);
}
String new_object_name2;
@When("enter object name {string}")
public void enter_object_name(String New_object_name) {
    new_object_name2 = New_object_name;
	 Locator object_name_textbox = testbase.getPage().locator("(//label[normalize-space(text())='Object Name']/following::input)[1]");
	object_name_textbox.click();
	testbase.getPage().keyboard().press("Control+A");
	testbase.getPage().keyboard().press("Backspace");
	object_name_textbox.fill(New_object_name);
	logger.info("Entered New object name is   :- "+new_object_name2);
}
@Then("entered object name should not be saved")
public void entered_object_name_should_not_be_saved() {
	String pagecount =testbase.getPage().locator("//div[@class='-pageJump']/following-sibling::span[1]").textContent();
			
	int totalPages = Integer.parseInt(pagecount.trim());
    int currentPage = 1;
	boolean object = false;
	
	while(currentPage < totalPages) {
		try {
			object =testbase.getPage().locator("//div[text()='"+new_object_name2+"']").isVisible();
			
		}
		catch (Exception e) {
			System.out.println("Object map not present on current page ");
		}
		if (object) {

			break;
		}
		else {
			testbase.getPage().locator("//button[normalize-space(text())='Next']").click();
			
		}
		 currentPage++;
	}

	Assert.assertFalse(object);
	logger.info("Edited object name is not saved after press the cancel button   :- "+new_object_name2);
	
}


@Then("toggle button should be available for Object Id, transaction date time and Parent Object Id calculation.")
public void toggle_button_should_be_available_for_object_id_transaction_date_time_and_parent_object_id_calculation() {
  
	 Locator totaltogalbutton = testbase.getPage().locator("//div [@class='react-switch-handle']"); 
			
	for ( Locator toggleButton : totaltogalbutton.all()) {
		assertTrue(toggleButton .isEnabled());
		logger.info("Javascript toggal button is enabled  :- "+toggleButton .isEnabled());
	}
}


@Given("user is on add define object page")
public void user_is_on_add_define_object_page() throws InterruptedException {
	define_Object_page.clickOnNewButton();
	logger.info("user is Import spreadsheet For Metadata page");
	Random rand = new Random();
	int randomDigit = rand.nextInt(100);
	generated_object_name ="0"+randomDigit;

	define_Object_page.enterObjectMetadataName(generated_object_name);

	define_Object_page.chooseFile("Metadata file.xlsx");

	define_Object_page.clickOnLoadButton();
	logger.info("Entered object meta data name :- "+generated_object_name);
	logger.info("user load meta data file from Import spreadsheet For Metadata page");
	Thread.sleep(6000);
	define_Object_Edit_page=new Define_Object_Edit_page(testbase.getPage());
	define_Object_Edit_page.enterDefineCalculationForObjectID("A1:Id");
	Thread.sleep(4000);
	define_Object_Edit_page.enterFirstTransactionDescription("B1:Transaction_date");
	define_Object_Edit_page.enterSecondTransactionDescription("E1:Payment_type");
	define_Object_Edit_page.enterDefineCalculationForTransactionDateTime("NOW()");
	define_Object_Edit_page.clickOnDefinePrimingTransactionsDate();
	Calendors calendor=new Calendors(testbase.getPage());
	calendor.selectDate("28/02/2025");
	define_Object_Edit_page.clickOnDoNotCreateKPIsCheckbox();
//	define_Object_Edit_page.enterParentObjectName("100");
//	define_Object_Edit_page.enterDefineCalculationForParentObjectID("A1:Id");
	Thread.sleep(3000);
	logger.info("Define object is created successfully with all mandatory field");
	
	
}

@When("Commission is split between multiple associates is selected as yes")
public void commission_is_split_between_multiple_associates_is_selected_as_yes() {
	boolean yes_no=false;
	try {
		yes_no= testbase.getPage().locator("(//input[@name='radio1'])[1]").isChecked();
				
		if (!yes_no) {
			testbase.getPage().locator("(//input[@name='radio1'])[1]").click();
			
		}
	} catch (Exception e) {
		
	}
	Assert.assertTrue(yes_no);
}
@Then("one percentage column should be there")
public void one_percentage_column_should_be_there() {
	List<Locator> textbox = testbase.getPage().locator("//input[@class='form-control-sm form-control']").all();
	int percentage_column = textbox.size();
	System.out.println("Total percentage columns: " + percentage_column);
	Assert.assertEquals(percentage_column,1);
}
@Then("user can add only five percentage row")
public void user_can_add_only_five_percentage_row() {
	boolean addRow = true;
	int percentage_column = 0;

	while (addRow) { 
	    try {
	        Locator addRowButton = testbase.getPage().locator("//button[normalize-space(text())='Add Row']");

	        if (addRowButton.isVisible()) {
	            addRowButton.click();
	        } else {
	            addRow = false;
	        }

	    } catch (Exception e) {
	        System.out.println("Exception occurred: " + e.getMessage());
	        break; 
	    }
	}
	List<Locator> textbox = testbase.getPage().locator("//input[@class='form-control-sm form-control']").all();
	percentage_column = textbox.size();
	System.out.println("Total percentage columns: " + percentage_column);

	Assert.assertEquals(percentage_column,5);
	
}
@Then("percentage name should be unique")
public void percentage_name_should_be_unique() {
    
	define_Object_Edit_page.clickOnSave();
	String actual_text =testbase.getPage().locator("(//input[@value='Percentage']/following-sibling::div)[2]").textContent();
			
	Assert.assertEquals(actual_text.trim(),"Already exists");
	
}


@When("user delete percentage row using cross icon")
public void user_delete_percentage_row_using_cross_icon() {
	boolean cross_icon = true;

	while (cross_icon) {
	    try {
	        List<Locator> removeIcons = testbase.getPage().locator("//div[@class='text-center']").all();
	        
	        if (!removeIcons.isEmpty()) { 
	            removeIcons.get(0).click();
	            testbase.getPage().waitForTimeout(500); 
	        } else {
	            cross_icon = false; 
	        }
	        
	    } catch (Exception e) {
	        System.out.println("Exception occurred: " + e.getMessage());
	        break;
	    }
	}

}


@When("User not select checkbox of the Reject file if duplicate records are found")
public void user_not_select_checkbox_of_the_reject_file_if_duplicate_records_are_found() {
	if (testbase.getPage().locator("(//input[@class='form-check-input'])[1]").isChecked()) {
		 define_Object_Edit_page.clickOnRejectFileIfDuplicateCheckbox();
	}
	  assertFalse(testbase.getPage().locator("(//input[@class='form-check-input'])[1]").isChecked());
	   define_Object_Edit_page.clickOnSave();
}


@When("user import file for transation and load file")
public void user_import_file_for_transation_and_load_file(DataTable dataTable) throws InterruptedException {
	Thread.sleep(1000);
   home_page.clickOnBackButton();
   Thread.sleep(1000);
   home_page.clickOnBackButton();
   Thread.sleep(1000);
   home_page.clickOnOperations();
   import_Spreadsheet_Page=new Import_Spreadsheet_Page(testbase.getPage());
   Thread.sleep(1000);
   import_Spreadsheet_Page.click_On_Import_Spreadsheet();
   Thread.sleep(1000);
   import_Spreadsheet_Page.click_On_Import();
   Thread.sleep(1000);
   import_Spreadsheet_Page.click_On_Transaction_or_TeamCredit("Transaction");
   Thread.sleep(1000);
   List<Map<String, String>> filename = dataTable.asMaps(String.class,String.class);
   Thread.sleep(1000);
   import_Spreadsheet_Page.chooseFile(filename.get(0).get("File_name"));
   Thread.sleep(1000);
   import_Spreadsheet_Page.clickOnLoadButton();
   Thread.sleep(10000);
}

String filename1;
@When("user import file with some of the duplicate and new record for transation and load file")
public void user_import_file_with_some_of_the_duplicate_and_new_record_for_transation_and_load_file(DataTable dataTable) throws InterruptedException {
	import_Spreadsheet_Page.click_On_Import();
	   import_Spreadsheet_Page.click_On_Transaction_or_TeamCredit("Transaction");
	   List<Map<String, String>> filename = dataTable.asMaps(String.class,String.class);
	   import_Spreadsheet_Page.chooseFile(filename.get(0).get("File_name"));
	   import_Spreadsheet_Page.clickOnLoadButton();
	   Thread.sleep(20000);
	   testbase.getPage().reload();
	   import_Spreadsheet_Page.click_On_plusIcon_To_expand(filename.get(0).get("File_name"));
	    filename1 = filename.get(0).get("File_name");
}

@Then("following status should be there for new record and duplicate record")
public void following_status_should_be_there_for_new_record_and_duplicate_record(DataTable dataTable) throws InterruptedException {
	 List<Map<String, String>> objectNameList = dataTable.asMaps(String.class,String.class);
	 String acutalObjectStatus = "";
	 boolean istrue=true;
	 while (istrue) {
			
		 try {
			  if (testbase.getPage().locator("(//div[text()='"+objectNameList.get(0).get("Object_id")+"']/following-sibling::div)[3]").isVisible()) {
				  istrue=false;
			}
			  else {
				  testbase.getPage().reload();
				  Thread.sleep(2000);
				  import_Spreadsheet_Page.click_On_plusIcon_To_expand(filename1);
				  Thread.sleep(2000);
			}
			   
		} catch (Exception e) {
	    logger.info("object is not loaded refresh the page");
		}
	 }
	 for (int i=0;i<objectNameList.size();i++ ) {
		 String acutalObject = testbase.getPage().locator("//body//div[@id='root']//div[@class='rt-tbody']//div[@class='rt-tbody']//div["+(i+1)+"]//div[1]//div[1]").textContent();
		 acutalObjectStatus = testbase.getPage().locator("//body//div[@id='root']//div[@class='rt-tbody']//div[@class='rt-tbody']//div["+(i+1)+"]//div[1]//div[4]").textContent();
		 
		 Thread.sleep(2000);		 
		 String expectedObjectStatus = objectNameList.get(i).get("Status");
		 
		 System.out.println("for the given object id  :-" +acutalObject  +"  Actual object status displayed :- "+acutalObjectStatus);
		 
		Assert.assertEquals(acutalObjectStatus, expectedObjectStatus);
		
		 
	  }
	
	
}

@When("no data for some headers")
public void no_data_for_some_headers() {
    Log.info("File uploaded sucessfully but no value for 'Invoice' and 'Order Type' fields.");
    
}

@Then("it should get uploaded without any error message")
public void it_should_get_uploaded_without_any_error_message() {
	 Log.info("File uploaded sucessfully with no error.");
}
}

