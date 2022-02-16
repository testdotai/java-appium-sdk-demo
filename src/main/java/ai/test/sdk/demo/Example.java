package ai.test.sdk.demo;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import ai.test.sdk.TestAiDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * A brief example demonstrating the capabilities of test.ai enhanced Appium.
 * 
 * @author Alexander Wu (alec@test.ai)
 *
 */
public class Example
{
	/**
	 * Main driver
	 * 
	 * @param args Program arguments, should be an Array with a String with the user's api key.
	 * @throws Throwable if anything went wrong
	 */
	public static void main(String[] args) throws Throwable
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.APP, new File(Example.class.getClassLoader().getResource("wikipedia.apk").getPath()).getAbsolutePath());
		capabilities.setCapability("allowTestPackages", true);
		capabilities.setCapability("appWaitForLaunch", false);
		capabilities.setCapability("newCommandTimeout", 0);

		AndroidDriver<MobileElement> androidDriver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
		TestAiDriver<MobileElement> driver = new TestAiDriver<>(androidDriver, args[0]);

		MobileElement b = driver.findElementByXPath("//android.widget.Button[@text='SKIP']", "skip_button");
		b.click();

		Thread.sleep(5000);

		MobileElement searchField = driver.findElementByXPath("//android.widget.TextView[@text='Search Wikipedia']", "search_wiki_button");
		searchField.click();

		Thread.sleep(5000);

		searchField = driver.findElementByXPath("//android.widget.EditText[@text='Search Wikipedia']", "search_wiki_field");
		searchField.sendKeys("Shang-Chi");

		Thread.sleep(5000);

		searchField = driver.findElementByXPath("//android.widget.TextView[@text='Shang-Chi and the Legend of the Ten Rings']", "shang_chi_button");
		searchField.click();
	}
}