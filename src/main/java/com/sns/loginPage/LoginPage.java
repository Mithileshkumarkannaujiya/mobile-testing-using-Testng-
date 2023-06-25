package com.sns.loginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.alliedTechnologies.utility.Utility;

public class LoginPage extends LoginOR {
	private Utility webUtil;

	public LoginPage(Utility webUtil) {

		this.webUtil = webUtil;
		PageFactory.initElements(webUtil.getDriver(), this);

	}

	public void loginTest() {
	
//	webUtil.swipe(200,657,200,1900);
		
		
		webUtil.click(emailBox);
	webUtil.sendKey(emailBox, "Hi");
   		webUtil.hideKeyBoard();
//		
//		
//	webUtil.click(emailField);
	}
}
