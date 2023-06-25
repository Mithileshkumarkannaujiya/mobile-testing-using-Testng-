package com.alliedTechnologies.utility;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	protected Utility webUtil;

	@BeforeSuite
	public void initialization() {
		webUtil = new Utility();
		webUtil.initHtmlReport();
	}
		
		
		@BeforeMethod
		public void launchapplication(Method method) {
		
	
		webUtil.setExtentLogger(method.getName());
    	//com.allied.sns/com.ryanheise.audioservice.AudioServiceActivity}
		webUtil.launchApplication("com.allied.sns", "com.ryanheise.audioservice.AudioServiceActivity");
		webUtil.implicitywait(40);
		webUtil.staticWait(5);
		
		}
	

	@AfterMethod
	public void flush(ITestResult result) {
		webUtil.resultStaus(result);
		webUtil.flushReport(); 
	    
				
	}
	

	public void ending() {
		webUtil.flushReport();
	}
	
	
	
}
