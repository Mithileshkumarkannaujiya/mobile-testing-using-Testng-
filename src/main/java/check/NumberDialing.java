package check;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class NumberDialing {

	static AndroidDriver driver;

	@Test
	public void main() throws MalformedURLException, InterruptedException {

		DesiredCapabilities ds = new DesiredCapabilities();
		// ds.setCapability(MobileCapabilityType.FULL_RESET, false);
		ds.setCapability("appPackage", "com.google.android.dialer");
		ds.setCapability("appActivity", "com.google.android.dialer.extensions.GoogleDialtactsActivity");
		System.out.println("hi1");
//com.google.android.dialer/com.google.android.dialer.extensions.GoogleDialtactsActivity} 		

		// ds.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), ds);
		// driver.manage(r).timeouts().implicitlyWait(Duration.ofSeconds(20));

		System.out.println("hi brother");
		Thread.sleep(2000);

		driver.findElement(By.id("com.google.android.dialer:id/dialpad_fab")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"6,MNO\"]")).click();
		Thread.sleep(2000);
		WebElement three = driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"3,DEF\"]"));
		three.click();

		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//android.widget.FrameLayout[@content-desc=\"0\"]/android.widget.LinearLayout/android.widget.TextView"))
				.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath(
				"//android.widget.FrameLayout[@content-desc=\"7,PQRS\"]/android.widget.LinearLayout/android.widget.TextView"))
				.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath(
				"//android.widget.FrameLayout[@content-desc=\"9,WXYZ\"]/android.widget.LinearLayout/android.widget.TextView"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//android.widget.FrameLayout[@content-desc=\"0\"]/android.widget.LinearLayout/android.widget.TextView"))
				.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath(
				"//android.widget.FrameLayout[@content-desc=\"4,GHI\"]/android.widget.LinearLayout/android.widget.TextView"))
				.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath(
				"//android.widget.FrameLayout[@content-desc=\"9,WXYZ\"]/android.widget.LinearLayout/android.widget.TextView"))
				.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath(
				"//android.widget.FrameLayout[@content-desc=\"7,PQRS\"]/android.widget.LinearLayout/android.widget.TextView"))
				.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath(
				"//android.widget.FrameLayout[@content-desc=\"2,ABC\"]/android.widget.LinearLayout/android.widget.TextView"))
				.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"dial\"]")).click();
		Thread.sleep(10000);

		driver.findElement(By.xpath("com.google.android.dialer:id/incall_end_call")).click();

		((AndroidDriver)driver).getBatteryInfo();
		
		driver.getBatteryInfo();
		
		//driver.close();

	}

}
