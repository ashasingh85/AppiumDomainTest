package com.domain.test;


import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.domain.utility.Config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {

	public static AndroidDriver<AndroidElement> Capability() throws MalformedURLException {
		/*
		Desired Capabilities is a class used to declare a set of basic requirements in keys and values pair  encoded in a JSON object, 
		sent by Appium clients to the server when a new automation session is requested. 
		They tell the Appium drivers all kinds of important things about how test should work.
		To launch the app on virtual device APP_PACKAGE and APP_ACTIVITY needs to be provided
		*/
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, Config.getStringValue("deviceName", "defaultValue"));
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, Config.getStringValue("automator", "defaultValue"));
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, Config.getStringValue("appPackageName", "defaultValue"));
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, Config.getStringValue("appMainActivityName", "defaultValue"));
		 
		// Running the Appium server on localhost
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		return driver;

}
}
