package check;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
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
	static URL url;
	static AndroidDriver driver;

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

		swipejavaClient8();
	//	tap(415, 2184);
	//	swipeTouchActions(70, 10);
		System.out.println("HI");

	}

	public static void swipeTouchActions(int yaxisStartPointPercent, int yaxisEndPointPercet) {
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

	public void performSingleTap(WebElement singleTapButton) {

		Point sourceLocation = singleTapButton.getLocation();
		Dimension sourceSize = singleTapButton.getSize();
		int centerX = sourceLocation.getX() + sourceSize.getWidth() / 2;
		int centerY = sourceLocation.getY() + sourceSize.getHeight() / 2;

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence tap = new Sequence(finger, 1);

		tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, centerY));

		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		//driver.perform(List.of(tap));

		driver.perform(Arrays.asList(tap));
	}

	public static void swipejavaClient8() {

		int startX = driver.manage().window().getSize().getWidth() / 2;
		int startY = driver.manage().window().getSize().getHeight() / 2;

		int endY = (int) (driver.manage().window().getSize().getHeight() * 0.2);

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence scroll = new Sequence(finger, 0);

		scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
		scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		scroll.addAction(
				finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
		scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(scroll));

	}

	  public static void tap(int x, int y) {
		  PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	  
	        Sequence tap = new Sequence(finger, 1);
	        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
	        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
	        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
	        driver.perform(Arrays.asList(tap));
	    }
}