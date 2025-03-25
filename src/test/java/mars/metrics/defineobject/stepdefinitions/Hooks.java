package mars.metrics.defineobject.stepdefinitions;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import configuration.login.TestBase;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Hooks {
	TestBase testBase = TestBase.getInstance();

	@Before
	public void setUp(Scenario scenario) {
		testBase.initialization(); 

		if (testBase.getContext() != null) {
			testBase.getContext().tracing().start(new Tracing.StartOptions()
					.setScreenshots(true)
					.setSnapshots(true)
					.setSources(true));
		}
	}

	@After
	public void tearDown(Scenario scenario) {
		String scenarioName = scenario.getName().replace(" ", "_");
		String timestamp = new SimpleDateFormat("_dd_MM_yyyy_hh_mm_ss").format(new Date());


		try {

			Files.createDirectories(Paths.get("target/screenshots"));
			Files.createDirectories(Paths.get("target/traces"));
			Files.createDirectories(Paths.get("target/videos"));

			testBase.getPage().waitForTimeout(2000);

			if (scenario.isFailed()) {
				String screenshotPath = "target/screenshots/" + scenarioName + timestamp + ".png";
				testBase.getPage().screenshot(new Page.ScreenshotOptions().setFullPage(true).setPath(Paths.get(screenshotPath)));
				System.out.println(" Screenshot saved: " + screenshotPath);

				String tracePath = "target/traces/" + scenarioName + timestamp + ".zip";
				testBase.getContext().tracing().stop(new Tracing.StopOptions().setPath(Paths.get(tracePath)));
				System.out.println(" Trace saved: " + tracePath);

			}
			Path videoFilePath = testBase.getPage().video() != null ? testBase.getPage().video().path() : null;
			testBase.getPage().close();

			if (videoFilePath != null) {
				Path renamedVideoPath = Paths.get("target/videos/" + scenarioName + timestamp + ".webm");
				int retries = 0;
				while (!Files.exists(videoFilePath) && retries < 10) {
					System.out.println("Waiting for Playwright to release the video file...");
					Thread.sleep(1000);
					retries++;
				}
				retries = 0;
				boolean fileMoved = false;
				while (retries < 5) {
					try {
						Files.move(videoFilePath, renamedVideoPath);
						fileMoved = true;
						System.out.println(" Video renamed successfully: " + renamedVideoPath);
						break;
					} catch (IOException e) {
						System.out.println("File is locked, retrying... Attempt: " + (retries + 1));
						Thread.sleep(1000);
						retries++;
					}
				}

				if (!fileMoved) {
					System.out.println(" Failed to rename video file after multiple attempts.");
				}
			} else {
				System.out.println(" No video file path retrieved from Playwright.");
			}

			testBase.getContext().close(); 
		} catch (Exception e) {
			System.out.println(" Error during teardown: " + e.getMessage());
		} finally {

			testBase.quitDriver();
		}
	}



}