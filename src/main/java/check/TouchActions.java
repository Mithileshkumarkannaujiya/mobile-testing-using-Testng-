package check;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class TouchActions {
	static URL url ;
	static AndroidDriver driver ;
	public static void main(String[] args) {


	DesiredCapabilities ds = new DesiredCapabilities();
	ds.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
	ds.setCapability(MobileCapabilityType.NO_RESET, true);

	
	try {
	 url = new URL("http://127.0.0.1:4723/wd/hub");
	driver = new AndroidDriver(url, ds);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	swipe(10, 90);
	System.out.println("HI");
	
	
	
	
	
	
	
	
	
	
	
	
}
	
public static void swipe(int yaxisStartPointPercent, int yaxisEndPointPercet) {
	Dimension windowSize = driver.manage().window().getSize();
	int screenHeight = windowSize.getHeight();
	int screenWidth = windowSize.getWidth();

	// init start point and end point to touch and release
	int xStartPoint = 50 * screenWidth / 100;
	int xEndPoint = xStartPoint;
	int yStartPoint = yaxisStartPointPercent * screenHeight / 100;
	int yEndPoint = yaxisEndPointPercet * screenHeight / 100;
	// perfoms touch actions
	PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
	PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
	// scroll up -- swipe from bottom to top
	TouchAction touchAction = new TouchAction(driver);
	touchAction.press(startPoint).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2))).moveTo(endPoint)
			.release().perform();

}
//hame left swipe aur right swipe sabka bana lena hai taaki aasani ho (jisame keval percentage dena ho)

// android driver ka user karake ek framework banana hai thiks hai
	

}
