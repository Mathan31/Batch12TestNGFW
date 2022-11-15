package tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.LoginPage;
import pages.RegistrationPage;

public class TC002_Registration extends BaseClass{
	
	@Test(priority = 1)
	public void registrationFieldValidation() {
		boolean registrationResult = new LoginPage()
		.clickOnRegistrationLink()
		.verifyAllTheRegistrationFields();
				
		boolean loginResult = new RegistrationPage()
		.clickOnUILogo()
		.verifyLoginElements();
		
		Assert.assertEquals(registrationResult, true);
		Assert.assertEquals(loginResult, true);
	}
	
	@Test(priority = 2)
	public void registrationWithMandatoryFields() {
		boolean result = new LoginPage()
		.clickOnRegistrationLink()
		.enterFirstName("Credo")
		.selectTitle("Mr")
		.enterMiddleName()
		.enterLastName("Systemz")
		.selectGender("Male")
		.enterUserName("Credo"+getRandomIntNumber(1111, 9999)+"Systemz")
		.enterEmail("Credo"+getRandomIntNumber(1111, 9999)+"Systemz"+"@credo.com")
		.enterPassword("testing123")
		.clickRegisterLink()
		.verifyUserRegistration()
		.clickOnUILogo()
		.verifyLoginElements();
		Assert.assertEquals(result, true);
	}
	
	//@Test(priority = 3)
	public void registrationWithAlltheFields() {
		
	}

	
	public int getRandomIntNumber(int min,int max) {
		Random ran = new Random();
		int result = ran.nextInt((max-min)+1)+min;
		return result;
	}
}
