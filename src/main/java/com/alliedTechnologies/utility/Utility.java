package com.alliedTechnologies.utility;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.ListAutoNumber;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class Utility {
	private ExtentReports extRepoObj;
	private ExtentTest extLogger;
	private WebDriver driver;
	private Properties prop;
	private URL url;

	public Utility() {

	}

	//////// Gestures ////////////

	public WebDriver getDriver() {
		return driver;
	}

	public void launchApplication(String appPackage, String appActivity) {
//UiAutomator2Options options=new UiAutomator2Options();
//options.setPlatformName("Android");
//options.setCapability("appPackage", appPackage);
//options.setCapability("appActivity", appActivity);
		

		DesiredCapabilities ds = new DesiredCapabilities();
		ds.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
		ds.setCapability(MobileCapabilityType.NO_RESET, true);
		ds.setCapability("appPackage", appPackage);
		ds.setCapability("appActivity", appActivity);
		try {
			url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AppiumDriver(url, ds);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extLogger.log(Status.INFO, "Application has been launched  " + "appPackage is -" + appPackage
				+ "   appActivity is  -" + appActivity);

	}

	public void staticWait(int timeinSecond) {
		timeinSecond = timeinSecond * 1000;
		try {
			Thread.sleep(timeinSecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void launchApplication(String ThePathOfApps) {
		DesiredCapabilities ds = new DesiredCapabilities();
		ds.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
		ds.setCapability(MobileCapabilityType.NO_RESET, true);
		ds.setCapability(MobileCapabilityType.APP, ThePathOfApps);

		try {
			url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver(url, ds);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] appname = ThePathOfApps.split("\\\\");
		extLogger.log(Status.INFO,
				"Application has been launched  application name is  -  " + appname[appname.length - 1]);

	}

	public void launchBrowser() {
		DesiredCapabilities ds = new DesiredCapabilities();
		ds.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
		ds.setCapability(MobileCapabilityType.NO_RESET, true);
		ds.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		ds.setCapability("chromedriverExecutable", "D:\\new projects\\appiumclient\\driver\\chromedriver.exe");

		try {
			url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver(url, ds);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extLogger.log(Status.INFO, "The chrome browser has been lauched in mobile ");
	}

	public void implicitywait(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		// extLogger.log(Status.INFO, "Implicit wait has been applied for - " + time + "
		// second");
	}

	public void hideKeyBoard() {
		androidBTNS(AndroidKey.ENTER);
		staticWait(1);
		((HidesKeyboard) driver).hideKeyboard();

	}

	public void click(WebElement we) {
		// swipe(0, 0); java client 8 me work kar raha hai isase methods banaye aur use
		// kare sunday ko try kare
		// ((AndroidDriver) driver).getOrientation();-- It's working

		we.click();
		extLogger.log(Status.PASS, "clicked on the Element successfully");
	}

	public void click(AppiumElementLocatorFactory we) {
		// swipe(0, 0); java client 8 me work kar raha hai isase methods banaye aur use
		// kare sunday ko try kare
		// ((AndroidDriver) driver).getOrientation();-- It's working
//		
//		AppiumElementLocatorFactory ap=new AppiumElementLocatorFactory(driver, null, null)
//		((WebElement) we).click();
		extLogger.log(Status.PASS, "clicked on the Element successfully");
	}

	public void inputValue(WebElement emailField, String inputvalueTxt) {
		emailField.sendKeys(inputvalueTxt);
	}

	public void javaScriptSendKeys(String value, WebElement WebElement) {
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("arguments[0].value=arguments[1]", WebElement, value);

	}

	public void sendKey(WebElement we, String value) {
		if (we.isDisplayed() && we.isEnabled()) {
			try {
				we.clear();
				we.sendKeys(value);
				extLogger.log(Status.PASS, " The value is inputted , where the value is  " + value);
			} catch (NoSuchElementException e) {
				Actions act = new Actions(driver);
				we.clear();
				act.moveToElement(we).sendKeys(value).build().perform();
				extLogger.log(Status.PASS, " The value is inputed Using Actions class, where the value is  " + value);
				e.printStackTrace();
			} catch (Exception e) {
				we.clear();
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].value='" + value + "';", we);
				extLogger.log(Status.PASS, " The value is inputted Using Java Script , where the value is  " + value);
			}

		}
	}

	public void navigateBack() {
		driver.navigate().back();
		extLogger.log(Status.INFO, "Page is navigated Back");

	}
	///////////////// Gestures //////////////////////////

	public void tap(WebElement emailField) {
		try {
			TouchAction tp = new TouchAction((PerformsTouchActions) driver);
			tp.tap(ElementOption.element(emailField)).perform();
			extLogger.log(Status.PASS, "succesfully Tapped on the element  " + emailField.getText());

		} catch (Exception e) {
			extLogger.log(Status.FAIL, "Failed in tapping  Exception occures while tapping on the element  "
					+ emailField.getText() + " the exception is " + e.toString());

			// TODO: handle exception
		}

// agar tap ki jagah press likhate to press karata rahega		
		/*
		 * upar keval wwebdriver likha hai isiliye new
		 * TouchAction((PerformsTouchActions) driver); ye likha hai agar AdroidDriver
		 * likha hota to new TouchAction(driver); isase kaam chal jata
		 */
	}

	// appium inspector me ek teer rahata hai upar arrow jaisa - left side me upar
	// cursor rakhege to swipeBycordinates par click karake exact coOrdinate dekh
	// sakate hai thoda dhyan dege to ho jayega

	public void javaScriptCLick(WebElement we) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", we);

	}

	public void tap(int xAxis, int yAxis) {
		try {
			PointOption coOrdinateForPress = new PointOption().point(xAxis, yAxis);
			TouchAction tc = new TouchAction((PerformsTouchActions) driver);
			tc.tap(coOrdinateForPress).perform();
			extLogger.log(Status.PASS,
					"Tapped by the Co-Ordinated where x-axis is  " + xAxis + "   and y-axis is  " + yAxis);

		} catch (Exception e) {
			extLogger.log(Status.FAIL, "Failed in tapping on the basis of Co-Ordinates " + e.toString());

		}

// new Actions(driver).moveByOffset(0, 0).click();  Is tarike se click kara sakate hai yah tap by co-Ordinate ka jaagh le sakata hai
//new Actions(driver).moveByOffset(0, 0).clickAndHold().build().perform();-- long press ki jagah le sakaat hai

	}

	public void scrollAndClick() {

	}

// Left Right Up Down ------ ka bata(ratio wala methods banane hai taaki ration ke hisab se sabhi deviec me kaam kare)	

	public void swipe(int yaxisStartPointPercent, int yaxisEndPointPercet) {
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
		TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
		touchAction.press(startPoint).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2))).moveTo(endPoint)
				.release().perform();

		extLogger.log(Status.INFO,
				"Succesfully swiped where Starting poin is  " + startPoint + "  And End point is " + endPoint);
	}
// hame left swipe aur right swipe sabka bana lena hai taaki aasani ho (jisame keval percentage dena ho)

	public void swipe(int xStartPoint, int yStartPoint, int xEndPoint, int yEndPoint) {
		// perfoms touch actions
		PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
		PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

		TouchAction touchAction = null;

		try {
			touchAction = new TouchAction((PerformsTouchActions) driver);

		} catch (Exception e) {
			// TODO: handle exception
		}

		touchAction.press(startPoint).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2))).moveTo(endPoint)
				.release().perform();

		extLogger.log(Status.INFO, "Swiped succesfully by the Co-Ordinates" + "(" + xStartPoint + "," + yStartPoint
				+ ")" + "&" + "(" + xEndPoint + "," + yEndPoint + ")");

	}

	public void swipeUntilVisibilityOfTheElement() {

	}

	public void longPress(WebElement we, int timeInSecond) {
		try {
			TouchAction touch = new TouchAction((PerformsTouchActions) driver);
			touch.longPress(ElementOption.element(we))
					.waitAction(new WaitOptions().withDuration(Duration.ofSeconds(timeInSecond))).perform();
			extLogger.log(Status.PASS,
					"Long pressed Succesfully on " + we.getText() + "for " + timeInSecond + " second");

		} catch (Exception e) {

			extLogger.log(Status.FAIL,
					"Long pressing failed on the Element " + we.getText() + " due to exception " + e.toString());
		}
		// agar tap ki jagah press likhate to press karata rahega
		/*
		 * upar keval wwebdriver likha hai isiliye new
		 * TouchAction((PerformsTouchActions) driver); ye likha hai agar AdroidDriver
		 * likha hota to new TouchAction(driver); isase kaam chal jata
		 */

	}

	public void longPress(int xAxis, int yAxis, int timeInSecond) {
		PointOption coOrdinatefortouch = new PointOption().point(xAxis, yAxis);
		TouchAction touch = new TouchAction((PerformsTouchActions) driver);
		touch.longPress(coOrdinatefortouch).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(timeInSecond)))
				.perform();

		extLogger.log(Status.INFO, "Long Pressed on the Co-Ordinates " + "(" + xAxis + " ," + yAxis + ")");

// appium inspector me ek teer rahata hai upar arrow jaisa - left side me upar 
// cursor rakhege to swipeBycordinates par click karake exact coOrdinate dekh sakate hai thoda dhyan dege to ho jayega		

	}

	public void dragAndDrop(WebElement sourceWebElement, int x, int y) {
		try {
			TouchAction touch = new TouchAction((PerformsTouchActions) driver);
			touch.press(ElementOption.element(sourceWebElement))
					.waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
					.moveTo(new PointOption().point(x, y)).release().perform();
			extLogger.log(Status.PASS, "Drag and dropped the Element successfully on the co-Ordinates  ");
		} catch (Exception e) {
			extLogger.log(Status.FAIL, "Drag and Drop Operation is failed due to Exception " + e.toString());
		}

	}

	public void dragAndDrop(WebElement source, WebElement targetWebElement) {
		try {
			TouchAction touch = new TouchAction((PerformsTouchActions) driver);
			touch.press(ElementOption.element(source)).waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
					.moveTo(ElementOption.element(targetWebElement)).release().perform();

			extLogger.log(Status.PASS, "Drag and  Dropped succesfully frome one Element to another Element");
		} catch (Exception e) {

		}

	}

	public void zoomIn(int xAxis, int yAxis) {
		try {
			MultiTouchAction multiTouchAction = new MultiTouchAction((PerformsTouchActions) driver);
			PointOption startPointzoomout = new PointOption().withCoordinates(xAxis, yAxis - 10);
			PointOption endpointzoomout = new PointOption().withCoordinates(xAxis, yAxis - 200);
			TouchAction zoomOut = new TouchAction((PerformsTouchActions) driver);
			zoomOut.press(startPointzoomout).moveTo(endpointzoomout).release();
			PointOption startPointzoomIn = new PointOption().withCoordinates(xAxis, yAxis + 10);
			PointOption endPointzoomOut = new PointOption().withCoordinates(xAxis, yAxis + 200);
			TouchAction zoomIn = new TouchAction((PerformsTouchActions) driver);
			zoomIn.press(startPointzoomIn).moveTo(endPointzoomOut).release();

			multiTouchAction.add(zoomOut).add(zoomIn).perform();
			extLogger.log(Status.PASS, "Zoom In operatoin is performed ");
		} catch (Exception e) {
			extLogger.log(Status.FAIL, "ZoomIn operation couldn't perfromed due to Exception " + e.toString());
		}
	}

	public void zoomOut(int xAxis, int yAxis) {
		// ise coOrdinate ke base par karana hai
		try {
			MultiTouchAction multiTouchAction = new MultiTouchAction((PerformsTouchActions) driver);

			PointOption startPointzoomout = new PointOption().withCoordinates(xAxis, yAxis - 10);
			PointOption endpointzoomout = new PointOption().withCoordinates(xAxis, yAxis - 200);
			TouchAction zoomOut = new TouchAction((PerformsTouchActions) driver);
			zoomOut.press(startPointzoomout).moveTo(endpointzoomout).release();

			PointOption startPointzoomIn = new PointOption().withCoordinates(xAxis, yAxis + 10);
			PointOption endPointzoomOut = new PointOption().withCoordinates(xAxis, yAxis + 200);
			TouchAction zoomIn = new TouchAction((PerformsTouchActions) driver);
			zoomIn.press(startPointzoomIn).moveTo(endPointzoomOut).release();

			multiTouchAction.add(zoomIn).add(zoomOut).perform();

			extLogger.log(Status.PASS, "Zoom Out operation is performed successfully");
		} catch (Exception e) {
			extLogger.log(Status.FAIL, "  Zoom Out Operation Failed due to Exception  " + e.toString());
		}
	}

	public void zoomInByElement(WebElement we) {
		// we.getSize().height();

	}

	public void zoomTwoFinger(TouchAction finger1, TouchAction finger2) {
		MultiTouchAction multiTouch = new MultiTouchAction((PerformsTouchActions) driver);
		multiTouch.add(finger1).add(finger2).perform();

	}

	public void zoomThreeFinger(TouchAction finger1, TouchAction finger2, TouchAction finger3) {
		MultiTouchAction multiToch = new MultiTouchAction((PerformsTouchActions) driver);
		multiToch.add(finger1).add(finger2).add(finger3).perform();

	}

	//////////// BUTTONS--- LIKE HOME BTN, POWER BTN./////////////////
	public void homeBtn() {
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.HOME));
		extLogger.log(Status.INFO, "HOME Btn Pressed succesfully");

	}

	public void switchApps() {
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
		extLogger.log(Status.INFO, "SWITCHED APP   succesfully");
	}

	public void camera() {
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.CAMERA));
		extLogger.log(Status.INFO, "SWITCHED To Camera   succesfully");
	}

	public void check() {
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.WINDOW));
		// sabhi btn ka use dekhana hai

		extLogger.log(Status.INFO, "SWITCHED To Camera   succesfully");
	}

	public void volumeBtn(String btn) {
		if (btn.equalsIgnoreCase("up")) {
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.VOLUME_UP));
			extLogger.log(Status.INFO, "VOLUME_UP Btn Pressed successfully");
		} else if (btn.equalsIgnoreCase("down")) {
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.VOLUME_DOWN));
			extLogger.log(Status.INFO, "VOLUME_DOWN Btn Pressed successfully");
		} else if (btn.equalsIgnoreCase("mute")) {
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.VOLUME_MUTE));
			extLogger.log(Status.INFO, "VOLUME_MUTE Btn Pressed successfully");
		} else {
			extLogger.log(Status.INFO, "You have Entered wrong BTN");
			System.out.println("You have Entered wrong BTN ");
		}
	}

	public void androidBTNS(AndroidKey button) {
		((AndroidDriver) driver).pressKey(new KeyEvent(button));
		extLogger.log(Status.PASS, button + " Pressed Successfully ");

		// parameter seedha(androidkey.btnkanaam likhe )
	}

	public String getCurrentTime() {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy_mm_dd_hh_mm_ss");
		Date objdate = new Date();
		String date = simpledateformat.format(objdate);
		return date;
	}

	public String getAttributeMethodToPrinttheNameofElementsIntheMethod(WebElement we) {

		String elementText = null;
		if (we.getText() != null) {
			elementText = we.getText();

		} else if (we.getAttribute("content-desc") != null) {
			elementText = we.getAttribute("content-desc");
		} else if (we.getAttribute("Attribute") != null) {
			elementText = we.getAttribute("Attribute");
		} else {
		}
		return elementText;
	}

	/////////////// Verifycation Code //////////////

	/**
	 * @param we
	 * @param i
	 */
	public void waitForVisibiltyElement(WebElement we, Duration i) {
		WebDriverWait webWait = new WebDriverWait(driver, i);
		webWait.until(ExpectedConditions.visibilityOf(we));
	}

//	public void verifyInnerText(WebElement we, String exptInnertext) {
//		waitForVisibiltyElement(we, 15);
//
//		String actualText = getInerText(we);
//		if (actualText.equalsIgnoreCase(exptInnertext)) {
//			extentTestObj.log(Status.INFO,
//					"Verifycation Passed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//			Reporter.log("Verifycation Passed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//		} else {
//			//getScreenShot(actualText);
//			extentTestObj.log(Status.INFO,
//					"Verifycation Failed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//			System.out.println("Verifycation Failed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//			SoftAssert soft = new SoftAssert();
//			soft.assertEquals(actualText, exptInnertext);
//		}
//	}
//
//	public void verifyInnerTextContains(WebElement we, String exptInnertext) {
//		String actualText = getInerText(we);
//		if (actualText.contains(exptInnertext)) {
//			extentTestObj.log(Status.INFO,
//					"Verifycation Passed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//			Reporter.log("Verifycation Passed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//		} else {
//			//getScreenShot(actualText);
//			extentTestObj.log(Status.INFO,
//					"Verifycation Failed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//			System.out.println("Verifycation Failed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//			SoftAssert soft = new SoftAssert();
//			soft.assertEquals(actualText, exptInnertext);
//		}
//	}
//
//	public void verifyAttribute(WebElement we, String exptAttributeValue, String attributeName) {
//		String actualAttributeValue = getAtrbtText(we, attributeName);
//		if (actualAttributeValue.contains(exptAttributeValue)) {
//			extentTestObj.log(Status.INFO, "Verifycation Passed. Where actual -" + actualAttributeValue
//					+ " & Expected -" + exptAttributeValue);
//			Reporter.log("Verifycation Passed. Where actual -" + actualAttributeValue + " & Expected -"
//					+ exptAttributeValue);
//		} else {
//			//getScreenShot(actualAttributeValue);
//			extentTestObj.log(Status.INFO, "Verifycation Failed. Where actual -" + actualAttributeValue
//					+ " & Expected -" + exptAttributeValue);
//			System.out.println("Verifycation Failed. Where actual -" + actualAttributeValue + " & Expected -"
//					+ exptAttributeValue);
//			SoftAssert soft = new SoftAssert();
//			soft.assertEquals(actualAttributeValue, exptAttributeValue);
//		}
//	}
//
//	public void verifyElementIsEnabled(WebElement we) {
//		boolean actualEnabled = enabled(we);
//		if (actualEnabled == true) {
//			extentTestObj.log(Status.INFO, "Verifycation Passed. Element is Enabled ");
//			Reporter.log("Verifycation Passed. Element is Enabled ");
//		} else {
//			extentTestObj.log(Status.INFO, "Verifycation Passed. Element is Disabled ");
//			System.out.println("Verifycation Passed. Element is Disabled ");
//			Assert.fail();
//		}
//	}
//
//	private boolean enabled(WebElement we) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public void verifyElementIsSelected(WebElement we) {
//		boolean actualChecked = selected(we);
//		if (actualChecked == true) {
//			extentTestObj.log(Status.INFO, "Verifycation Passed. Check box checked ");
//			Reporter.log("Verifycation Passed. Check box checked ");
//
//		} else {
//			extentTestObj.log(Status.INFO, "Verifycation Passed. Check box unchecked ");
//			System.out.println("Verifycation Passed. Check box unchecked ");
//			Assert.fail();
//		}
//	}
//
//	public void verifyUnChecked(WebElement we) {
//		boolean actualChecked = selected(we);
//		if (actualChecked == false) {
//			extentTestObj.log(Status.INFO, "Verifycation Passed. Check box unchecked ");
//			Reporter.log("Verifycation Passed. Check box unchecked ");
//		} else {
//			extentTestObj.log(Status.INFO, "Verifycation Passed. Check box checked ");
//			System.out.println("Verifycation Passed. Check box checked ");
//			Assert.fail();
//		}
//	}
//
//	public String verifyTitle(String expTitle) {
//		String actualTitle = driver.getTitle();
//		if (actualTitle.equalsIgnoreCase(expTitle)) {
//			extentTestObj.log(Status.INFO, "Verifycation passed .Where actual Title is : " + actualTitle
//					+ " & Expected title is : " + expTitle);
//			System.out.println("Verifycation passed .Where actual Title is : " + actualTitle + " & Expected title is : "
//					+ expTitle);
//			Reporter.log("Verifycation Passed. Where actual -" + actualTitle + " & Expected -" + expTitle);
//
//		} else {
//			//getScreenShot(actualTitle);
//			extentTestObj.log(Status.INFO, "Verifycation passed .Where actual Title is : " + actualTitle
//					+ " & Expected title is : " + expTitle);
//			System.out.println("Verifycation passed .Where actual Title is : " + actualTitle + " & Expected title is : "
//					+ expTitle);
//			SoftAssert soft = new SoftAssert();
//			soft.assertEquals(actualTitle, expTitle);
//
//		}
//		return actualTitle;
//	}
//
//	public void verifyElementVisible(WebElement we) {
//
//		if (we.isDisplayed() & we.getSize().getHeight() > 0 & we.getSize().getWidth() > 0) {
//			extentTestObj.log(Status.INFO, "Validation passed .Element is visible : ");
//			System.out.println("Validation passed .Element is visible : ");
//			Reporter.log("Verifycation passed .Element is visible : ");
//		} else {
//			extentTestObj.log(Status.INFO, "Verifycation passed .Element is invisible : ");
//			System.out.println("Verifycation passed .Element is invisible : ");
//			Assert.fail();
//		}
//
//	}
//
//	public void verifyElementUnVisible(WebElement we) {
//		if (!we.isDisplayed() & we.getSize().getHeight() == 0 & we.getSize().getWidth() == 0) {
//			extentTestObj.log(Status.INFO, "Verifycation passed .Element is invisible : ");
//			System.out.println("Verifycation passed .Element is invisible : ");
//			Reporter.log("Verifycation passed .Element is invisible : ");
//
//		} else {
//			extentTestObj.log(Status.INFO, "Verifycation passed .Element is visible : ");
//			System.out.println("Verifycation passed .Element is visible : ");
//			Assert.fail();
//		}
//
//	}
//
//	public void verifyCurrentUrl(String expUrl) {
//		String actualUrl = driver.getCurrentUrl();
//		if (actualUrl.equalsIgnoreCase(expUrl)) {
//			extentTestObj.log(Status.INFO,
//					"Verifycation Passed. Where actual -" + actualUrl + " & Expected -" + expUrl);
//			Reporter.log("Verifycation Passed. Where actual -" + actualUrl + " & Expected -" + expUrl);
//
//		} else {
//			//getScreenShot(actualUrl);
//			extentTestObj.log(Status.INFO,
//					"VeriFication Field. Where actual url is :" + actualUrl + " & Expected url is :" + expUrl);
//			System.out
//					.println("VeriFication Field. Where actual url is :" + actualUrl + " & Expected url is :" + expUrl);
//			SoftAssert soft = new SoftAssert();
//			soft.assertEquals(actualUrl, expUrl);
//		}
//	}
//
//	////////////////////// Validation/////////////////////
//
//	public void validateInnerText(WebElement we, String exptInnertext) {
//		String actualText = getInerText(we);
//		if (actualText.equalsIgnoreCase(exptInnertext)) {
//			extentTestObj.log(Status.INFO,
//					"Validation Passed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//			Reporter.log("Validation Passed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//		} else {
//			extentTestObj.log(Status.INFO,
//					"Validation Failed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//			System.out.println("Validation Failed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//			Assert.assertEquals(actualText, exptInnertext);
//		}
//	}
//
//	public void validateInnerTextContains(WebElement we, String exptInnertext) {
//		String actualText = getInerText(we);
//		if (actualText.contains(exptInnertext)) {
//			extentTestObj.log(Status.INFO,
//					"Validation Passed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//			Reporter.log("Validation Passed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//		} else {
//			extentTestObj.log(Status.INFO,
//					"Validation Failed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//			System.out.println("Validation Failed. Where actual -" + actualText + " & Expected -" + exptInnertext);
//			Assert.assertEquals(actualText, exptInnertext);
//		}
//	}
//
//	public void validateAttribute(WebElement we, String exptAttributeValue, String attributeName) {
//		String actualAttributeValue = getAtrbtText(we, attributeName);
//		if (actualAttributeValue.contains(exptAttributeValue)) {
//			extentTestObj.log(Status.INFO,
//					"Validation Passed. Where actual -" + actualAttributeValue + " & Expected -" + exptAttributeValue);
//			Reporter.log(
//					"Validation Passed. Where actual -" + actualAttributeValue + " & Expected -" + exptAttributeValue);
//
//		} else {
//			extentTestObj.log(Status.INFO,
//					"Validation Failed. Where actual -" + actualAttributeValue + " & Expected -" + exptAttributeValue);
//			System.out.println(
//					"Validation Failed. Where actual -" + actualAttributeValue + " & Expected -" + exptAttributeValue);
//			Assert.assertEquals(actualAttributeValue, exptAttributeValue);
//		}
//	}

//	public void validateElementIsEnabled(WebElement we) {
//		boolean actualEnabled = enabled(we);
//		if (actualEnabled == true) {
//			extentTestObj.log(Status.INFO, "Validation Passed. Element is Enabled ");
//			Reporter.log("Validation Passed. Element is Enabled ");
//		} else {
//			extentTestObj.log(Status.INFO, "Validation Passed. Element is Disabled ");
//			System.out.println("Validation Passed. Element is Disabled ");
//			Assert.fail();
//		}
//	}
//
//	public void validateElementIsSelected(WebElement we) {
//		boolean actualChecked = selected(we);
//		if (actualChecked == true) {
//			extentTestObj.log(Status.INFO, "Validation Passed. Check box checked ");
//			Reporter.log("Validation Passed. Check box checked ");
//
//		} else {
//			extentTestObj.log(Status.INFO, "Validation Passed. Check box unchecked ");
//			System.out.println("Validation Passed. Check box unchecked ");
//			Assert.fail();
//		}
//	}
//
//	public void validateUnChecked(WebElement we) {
//		boolean actualChecked = selected(we);
//		if (actualChecked == false) {
//			extentTestObj.log(Status.INFO, "Validation Passed. Check box unchecked ");
//			Reporter.log("Validation Passed. Check box unchecked ");
//
//		} else {
//			extentTestObj.log(Status.INFO, "Validation Passed. Check box checked ");
//			System.out.println("Validation Passed. Check box checked ");
//			Assert.fail();
//		}
//	}
//
//	private boolean selected(WebElement we) {
//	
//		return false;
//	}
//
//	public String validateTitle(String expTitle) {
//		String actualTitle = driver.getTitle();
//		if (actualTitle.equalsIgnoreCase(expTitle)) {
//			extentTestObj.log(Status.INFO,
//					"Validation passed .Where actual Title is : " + actualTitle + " & Expected title is : " + expTitle);
//			System.out.println(
//					"Validation passed .Where actual Title is : " + actualTitle + " & Expected title is : " + expTitle);
//			Reporter.log("Validation Passed. Where actual -" + actualTitle + " & Expected -" + expTitle);
//
//		} else {
//			extentTestObj.log(Status.INFO,
//					"Validation passed .Where actual Title is : " + actualTitle + " & Expected title is : " + expTitle);
//			System.out.println(
//					"Validation passed .Where actual Title is : " + actualTitle + " & Expected title is : " + expTitle);
//			Assert.assertEquals(actualTitle, expTitle);
//		}
//		return actualTitle;
//	}
//
//	public void validateElementVisible(WebElement we) {
//
//		if (we.isDisplayed() & we.getSize().getHeight() > 0 & we.getSize().getWidth() > 0) {
//			extentTestObj.log(Status.INFO, "Validation passed .Element is visible : ");
//			System.out.println("Validation passed .Element is visible : ");
//			Reporter.log("Validation passed .Element is visible : ");
//
//		} else {
//			extentTestObj.log(Status.INFO, "Validation passed .Element is invisible : ");
//			System.out.println("Validation passed .Element is invisible : ");
//			Assert.fail();
//		}
//
//	}
//
//	public void validateElementUnVisible(WebElement we) {
//		if (!we.isDisplayed() & we.getSize().getHeight() == 0 & we.getSize().getWidth() == 0) {
//			extentTestObj.log(Status.INFO, "Validation passed .Element is invisible : ");
//			System.out.println("Validation passed .Element is invisible : ");
//			Reporter.log("Validation passed .Element is invisible : ");
//
//		} else {
//			extentTestObj.log(Status.INFO, "Validation passed .Element is visible : ");
//			System.out.println("Validation passed .Element is visible : ");
//			Assert.fail();
//		}
//	}
//
//	public void validateCurrentUrl(String expUrl) {
//		String actualUrl = driver.getCurrentUrl();
//		if (actualUrl.equalsIgnoreCase(expUrl)) {
//			extentTestObj.log(Status.INFO, "Validation passed .Element is visible : ");
//			Reporter.log("Validation Passed. Where actual -" + actualUrl + " & Expected -" + expUrl);
//
//		} else {
//			extentTestObj.log(Status.INFO,
//					"VeriFication Field. Where actual url is :" + actualUrl + " & Expected url is :" + expUrl);
//			System.out
//					.println("VeriFication Field. Where actual url is :" + actualUrl + " & Expected url is :" + expUrl);
//			Assert.assertEquals(actualUrl, expUrl);
//		}
//	}
//
//	//////////////// Extent Report code /////////////////
//
	public void initHtmlReport() {
		ExtentSparkReporter htmlReport = new ExtentSparkReporter(
				"Report/" + " Extent Report " + getCurrentTime() + ".html");
		htmlReport.config().setDocumentTitle("AutoMation Report");
		htmlReport.config().setReportName("Functional Report");
		htmlReport.config().setTheme(Theme.DARK);
		extRepoObj = new ExtentReports();
		extRepoObj.attachReporter(htmlReport);

	}

	public void setExtentLogger(String tcName) {
		extLogger = extRepoObj.createTest(tcName);
	}

	public void flushReport() {
		extRepoObj.flush();
	}

	public String fortakesnapshot(String snapshotname) {
		TakesScreenshot takesscrenshot = (TakesScreenshot) driver;
		File sourceFile = takesscrenshot.getScreenshotAs(OutputType.FILE);
		String time = getCurrentTime();
		File destinationfile = new File("Report//" + snapshotname + time + ".jpg");

		try {
			Files.copy(sourceFile, destinationfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return destinationfile.getAbsolutePath();
	}

	public void resultStaus(ITestResult result) {
		if (result.getStatus() == result.SUCCESS) {
			extLogger.pass(result.getMethod().getMethodName() + "  is passed succesfully");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			extLogger.fail(result.getMethod().getMethodName() + "  is Failed");
			extLogger.fail(result.getThrowable().toString() + " is Failed");
			extLogger.addScreenCaptureFromPath(fortakesnapshot(result.getMethod().getMethodName()));
		} else if (result.getStatus() == ITestResult.SKIP) {
			extLogger.skip(result.getMethod().getMethodName() + " is Skipped");
			extLogger.skip(result.getThrowable().toString() + " is Skipped");
		}

	}

}
