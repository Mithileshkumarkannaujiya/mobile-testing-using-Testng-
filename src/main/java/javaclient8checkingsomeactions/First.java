package javaclient8checkingsomeactions;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class First {
	public static URL url;
	public static WebDriver driver;
	public static void main(String[] args) {
		DesiredCapabilities ds = new DesiredCapabilities();
		ds.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
		ds.setCapability(MobileCapabilityType.NO_RESET, true);
		ds.setCapability("appPackage", "");
		ds.setCapability("appActivity", "");
		//io.appium.android.apis/io.appium.android.apis
		try {
			url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver(url, ds);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
