package com.domain.utility;

 

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public  class Utility {
	
	public static boolean isIdPresent(AndroidDriver<AndroidElement> driver, String id) {
		    
		try {

			driver.findElementById(id);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}



	}
	
	public static boolean isClassPresent(AndroidDriver<AndroidElement> driver, String className) {
	    
		try {

			driver.findElementByClassName(className);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}



	}
	
	public static boolean isElementPresent(AndroidDriver<AndroidElement> driver, By by) {
	    
		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}



	}

}
