package mars.metrics.defineobject.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jline.internal.Log;
import java.util.*;
import configuration.login.TestBase;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import configuration.login.Home_page;
import mars.metrics.pages.Object_Maps_page;

public class Object_Maps_Step_def {
	
	TestBase testbase= TestBase.getInstance();
	Logger logger = LoggerFactory.getLogger(Object_Maps_Step_def.class);
	Home_page home_page=new Home_page(testbase.getPage());
	Object_Maps_page object_Map_page=new Object_Maps_page(testbase.getPage());
	
	
	
    
	@Given("Object Map page list page is opened")
	public void object_map_page_list_page_is_opened() throws InterruptedException {
	logger.info("user is on home page");
	home_page.clickOnMetrics();
	object_Map_page.clickOnObjectMaps();  
	Log.info("Object Map page opened");
	}

	@When("user clicks on new button")
	public void user_clicks_on_new_button() {
	    
		object_Map_page.clickOnNewButton();
		Log.info("New page opened");
	}
	

	@When("enter Object Name")
	public void enter_object_name() {
		object_Map_page.enterObjectName("0909");
		
	    }

	String define_Map_Name;
	@When("Object Map Name")
	public void object_map_name() {
	    
	Random rand = new Random();
	int n=rand.nextInt(100);
	define_Map_Name=String.valueOf(n).concat("0");
    object_Map_page.enterMapName(define_Map_Name);
	}

	@When("click on save")
	public void click_on_save() throws InterruptedException {
		object_Map_page.clickOnSaveButton();
		logger.info("save");
	}
	
	
	@Then("it will open Define Object edit page")
	public void it_will_open_define_object_edit_page() throws InterruptedException {
		
	testbase.getPage().locator("//textarea[@id='object-mapping-objectDescription']").fill("jdshkafs");
		
	
	}
	
	@When("save")
	public void save() throws InterruptedException {
		testbase.getPage().locator("//button[text()='Save']").click();
		logger.info("canceled");
		//Thread.sleep(12000);
	}

	String expect1;
	@Then("it should show that Object Name on list page")
	public void it_should_show_that_object_name_on_list_page() throws InterruptedException {
	
		
		Thread.sleep(4000);
		String actual_map_name=null;
		String pagecount = testbase.getPage().waitForSelector("//div[@class='-pageJump']/following-sibling::span[1]").textContent();
		int totalPages = Integer.parseInt(pagecount.trim());
	    int currentPage = 1;
		while(currentPage < totalPages) {
		
			boolean object = false;

			try {
				
				object =testbase.getPage().locator("//div[text()='"+define_Map_Name+"']").isVisible();
			}
			catch (Exception e) {
				System.out.println(object+"false");
				System.out.println("Object map not present on current page ");
			}
			if (object) {
				actual_map_name =testbase.getPage().locator("//div[text()='"+define_Map_Name+"']").textContent();
				break;
			}
			else {
				testbase.getPage().locator("//button[normalize-space(text())='Next']").click();
			}
			currentPage++;
		}
		Assert.assertEquals(actual_map_name, define_Map_Name);
	
	}






}
