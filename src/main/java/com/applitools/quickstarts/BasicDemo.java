package com.applitools.quickstarts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResults;

public class BasicDemo {

	public static void main(String[] args) {

        if(args.length >0 && "--dry-run".equals(args[0])) {
            return;
        }

		// Use Chrome browser
		WebDriver driver = new ChromeDriver();

		// Initialize the eyes SDK and set your private API key.
		Eyes eyes = new Eyes();

		// Set the API key from the env variable. Please read the "Important Note"
		// section above.
		eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));

		try {

			// Start the test by setting AUT's name, test name and viewport size (width X height)
			eyes.open(driver, "Demo App", "Smoke Test", new RectangleSize(600, 800));

			// Navigate the browser to the "ACME" demo app
			driver.get("https://demo.applitools.com");

			//To see visual bugs, change the above URL to:
			//https://demo.applitools.com/index_v2.html and run the test again



			// Visual checkpoint #1 - Check the login page.
			eyes.checkWindow("Login Window");

			// This will create a test with two test steps.
			driver.findElement(By.id("log-in")).click();

			// Visual checkpoint #2 - Check the app page.
			eyes.checkWindow("App Window");

			// End the test.
			TestResults results = eyes.close();
			System.out.println(results);

		} catch (Exception e) {
			// Close the browser.
			driver.quit();

			//print stacktrace
			e.printStackTrace();

			//end the main test
			System.exit(0);
		} finally {
			// Close the browser.
			driver.quit();

			// If the test was aborted before eyes.close was called, ends the test as
			// aborted.
			eyes.abortIfNotClosed();

			// End main test
			System.exit(0);
		}

	}
}