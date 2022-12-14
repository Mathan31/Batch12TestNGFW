package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;
import pages.RegistrationPage;
import utility.FakerDataFactory;

public class TC002_Registration extends BaseClass{
	
	@BeforeTest
	public void testCaseDataSetUp() {
		
		authors = "Yogeshwari";
		category = "Regression";
		testName = "Registration Test";
		testDescription = "Registration Test Case Valiatation";
		module = "Registration";
	}
	
	@Test(priority = 1)
	public void registrationFieldValidation() {
		boolean registrationResult = new LoginPage(driver,node)
		.clickOnRegistrationLink()
		.verifyAllTheRegistrationFields();
				
		boolean loginResult = new RegistrationPage(driver,node)
		.clickOnUILogo()
		.verifyLoginElements();
		
		Assert.assertEquals(registrationResult, true);
		Assert.assertEquals(loginResult, true);
	}
	
	@Test(priority = 2)
	public void registrationWithMandatoryFields() {
		boolean result = new LoginPage(driver,node)
		.clickOnRegistrationLink()
		.enterFirstName(FakerDataFactory.getFirstName())
		.selectTitle(FakerDataFactory.getTitle())
		.enterMiddleName()
		.enterLastName(FakerDataFactory.getLastName())
		.selectGender(FakerDataFactory.getGender())
		.enterUserName(FakerDataFactory.getUserName()+FakerDataFactory.getRandomNumber())
		.enterEmail(FakerDataFactory.getEmailAddress())
		.enterPassword(FakerDataFactory.getFirstName())
		.clickRegisterLink()
		.verifyUserRegistration()
		.clickOnUILogo()
		.verifyLoginElements();
		Assert.assertEquals(result, true);
	}
	
	//@Test(priority = 3)
	public void registrationWithAlltheFields() {
		
	}
}
