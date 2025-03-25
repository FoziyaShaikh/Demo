package configuration.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		
		         features = { "Features/Mars_Matrics/Object_Map_Page.feature" }, 
	

                     glue= {"mars.metrics.defineobject.stepdefinitions"}, 
					monochrome = true, 
				//	dryRun = true,

							plugin= {
									"pretty", 
									
									"html:reports/Mars_Metrics_runner.html", 
							}
                  ,tags =  "@S-12" 
)


 
public class Mars_Metrics_runner extends AbstractTestNGCucumberTests{

/*@DataProvider(parallel = true)
	@Override
	public Object[] [] scenarios(){
		return super.scenarios();
	} 
	*/
	
	
}




 


