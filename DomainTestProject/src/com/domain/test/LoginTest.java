package com.domain.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import com.domain.utility.Config;
import com.domain.utility.Utility;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LoginTest extends Base{
	
//	Test login functionality with valid credentials
@Test(priority=1)
public void sucessfulLoginTest() throws IOException {
		AndroidDriver<AndroidElement> driver = Capability();	
		navigateToLoginPage(driver, Config.getStringValue("correctUserName", "defaultValue"),Config.getStringValue("correctPassword", "defaultValue"));	
		
		// On successful login, User Profile will be shown
		boolean profileResult = driver.findElementById("com.fairfax.domain:id/profile_image_layout").isDisplayed();
		Assert.assertTrue(profileResult);	
}

// Test login functionality with invalid credentials
@Test(priority=2)
public void unsucessfulLoginTest() throws IOException {		
		AndroidDriver<AndroidElement> driver = Capability();	
		navigateToLoginPage(driver, Config.getStringValue("IncorrectUserName", "defaultValue"),Config.getStringValue("IncorrectPassword", "defaultValue"));	
		
		// A Login failure pop-up message is displayed 
		String failedLogin = driver.findElementById("com.fairfax.domain:id/dialog_lblHeader").getText();
		Assert.assertEquals(failedLogin, "Login failure");
	
}
	
	public void navigateToLoginPage(AndroidDriver<AndroidElement> driver, String username , String password) throws MalformedURLException {
		 

	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	// Click on Not Now for search on Near By places pop-up, this is in conditional block as the below pop-up does not appear every time
	if(Utility.isIdPresent(driver, "com.fairfax.domain:id/not_now")) {
		driver.findElementById("com.fairfax.domain:id/not_now").click();
	}
	
	// Click on More text at the Search Screen page
	driver.findElementByXPath("//android.widget.TextView[@text='More']").click();
	
	/*Click on More text at the home owner page. This click is in try/catch block as we were 
	  getting the staleElementException and this issue is intermittent as well
	*/
	try {
		
		driver.findElementByXPath("//android.widget.TextView[@text='More']").click();
		
	}catch (StaleElementReferenceException e) {
		driver.findElementByXPath("//android.widget.TextView[@text='More']").click();
	}
	
	// Click on signLogin button
	driver.findElementById("com.fairfax.domain:id/profile_signup_login_button").click();
	
	// Click on Continue with Email button on SignUp page
	driver.findElementByXPath("//android.widget.Button[@text='Continue with email']").click();
	
	// Hiding the keyboard as elements are not visible
	driver.hideKeyboard();

	// Enter the userName in edit box
	driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys(username);
	
	// Click and hide keyboard is to make element visible
	driver.findElementsByClassName("android.widget.EditText").get(0).click();
	
	// Click on Deny for "Allow Domain to Access Contact pop-up" and it is also conditional as the pop-up does not appear regularly
	if(Utility.isIdPresent(driver,"com.android.permissioncontroller:id/permission_message")) {
		driver.findElementByXPath("//android.widget.Button[@text='Deny']").click();
	}
	driver.hideKeyboard();
	
	// Enter the password in the edit box
	driver.findElementsByClassName("android.widget.EditText").get(1).sendKeys(password);
	
	driver.hideKeyboard();
	

	
	// Click on Login button after entering the userName and password
	driver.findElementById("com.fairfax.domain:id/membership_btnlogin").click();
		
	}


}
