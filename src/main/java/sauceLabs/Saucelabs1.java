package sauceLabs;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.MutableCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Saucelabs1 {

	@Test
	public void sauceLabs() throws MalformedURLException {
		MutableCapabilities caps = new MutableCapabilities();
		caps.setCapability("platformName","Android");
		caps.setCapability("appium:deviceName","Android GoogleAPI Emulator");
		caps.setCapability("appium:deviceOrientation", "portrait");
		caps.setCapability("appium:platformVersion","12.0");
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:app", "storage:filename=app-release (1).apk");
		MutableCapabilities sauceOptions = new MutableCapabilities();
		sauceOptions.setCapability("build", "appium-build-5SND8");
		//sauceOptions.setCapability("name", "<your test name>");
		sauceOptions.setCapability("name", "UiAutomator2");
		
		caps.setCapability("sauce:options", sauceOptions);
		
		
		
		URL url = new URL("https://oauth-mkchaudharyevs-3afd7:bdda36d8-0579-49a0-b0d7-144a36214b97@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
		AppiumDriver driver = new AndroidDriver(url, caps);
		
		
		
		
	}
}
