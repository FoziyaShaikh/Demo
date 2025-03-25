package configuration.login;

import com.microsoft.playwright.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class TestBase {
	public static Properties prop;
	private static TestBase instance = null;
	private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
	private static ThreadLocal<Browser> browser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
	private static ThreadLocal<Page> page = new ThreadLocal<>();

	public static TestBase getInstance() {
		if (instance == null) {
			instance = new TestBase();
		}
		return instance;
	}

	public TestBase() {
		try {
			prop = new Properties();
			String path = System.getProperty("user.dir") + "/features/Config.Properties";
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
		} catch (IOException e) {
			System.out.println("Error reading properties file: " + e.getMessage());
		}
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}

	public void initialization() {
		if (playwright.get() == null) {
			playwright.set(Playwright.create());
			browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			context.set(browser.get().newContext(new Browser.NewContextOptions()
					.setRecordVideoDir(Paths.get("target/videos")) 
					.setRecordVideoSize(1280, 720)
					));

			page.set(context.get().newPage()); 
		} 
	}

	public Page getPage() {

		if (page.get() != null) {
			page.get().setViewportSize(1300, 700); 
		}
		return page.get();
	}

	public BrowserContext getContext() {
		return context.get();
	}

	public Browser getBrowser() {
		return browser.get();
	}

	public void quitDriver() {
		if (page.get() != null) {
			page.get().close();
			page.remove();
		}
		if (context.get() != null) {
			context.get().close();
			context.remove();
		}
		if (browser.get() != null) {
			browser.get().close();
			browser.remove();
		}
		if (playwright.get() != null) {
			playwright.get().close();
			playwright.remove();
		}
	}
}
