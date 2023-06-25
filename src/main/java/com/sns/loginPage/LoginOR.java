package com.sns.loginPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.alliedTechnologies.utility.Utility;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;

public class LoginOR{


@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Welcome Onboard\nEnter email to continue \n\"]/android.widget.EditText")
protected WebElement emailBox;

@AndroidBy(accessibility = "Verify Email")
protected WebElement emailField ;



//@FindBy(xpath = "//android.view.View[@content-desc=\"Verify Email\"]")
//protected   WebElement emailField;




//@AndroidFindBy(className = "UIAKeyboard")
//private AppiumElementLocatorFactory keyboard;
//@AndroidFindBy(id = "name")
//private AndroidElement nameElement;
//@AndroidFindBy(id = "password")
//private AndroidElement passwordElement;
//@AndroidFindBy(id = "login")
//private AndroidElement loginElement;



@AndroidFindBy(accessibility = "")
protected WebElement ele;



}


//android.widget.ImageView[@content-desc="Welcome Onboard Enter email to continue "]/android.widget.EditText
