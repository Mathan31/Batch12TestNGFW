package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;

public class TC001_Login extends BaseClass{
	
	@BeforeTest
	public void testCaseDataSetUp() {
		excelFileName = "TC001";
		authors = "Akash";
		category = "smoke";
		testName = "Login Test";
		testDescription = "Login Test Case Valiatation";
		module = "Login";
	}
	
	@Test(priority = 1)
	public void loginFieldValidation() {
		boolean result = new LoginPage(driver,node)
		.verifyLoginElements();
		Assert.assertEquals(result, true);
	}
	
	@Test(priority = 2,dataProvider = "TestData")
	public void loginWithValidCredential(String uName,String password) {
		boolean result = new LoginPage(driver,node)
		.typeUserName(uName)
		.typePassword(password)
		.clickSignOn()
		.validateHomePage()
		.clickonLogout()
		.verifyLoginElements();
		Assert.assertEquals(result, true);
	}
	
	@Test(priority = 3)
	public void loginWithInValidCredential() {
		boolean result = new LoginPage(driver,node)
		.typeUserName("Mathan")
		.typePassword("Testing321")
		.clickSingOnWithInValidCredential()
		.validateLoginFailedText();
		Assert.assertEquals(result, true);
	}

}
