package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
import library.SeleniumWrapper;

public class LoginPage extends BaseClass{
	
	private By oUsernameText = By.id("username");
	private By oPasswordText = By.id("password");
	private By oSignInBtn = By.xpath("//button[text()='Sign In']");
	private By oForgotLink = By.xpath("//*[text()='Register For Account']");
	private By oRegisterLink = By.xpath("//*[text()='Register For Account']");
	private By oLoginFailedInnerText = By.xpath("//div[contains(text(),'login failed')]");
	private WebDriver driver;
	private SeleniumWrapper oWrap;
	
	public LoginPage(WebDriver driver,ExtentTest node) {
		this.driver = driver;
		this.node = node;
		oWrap = new SeleniumWrapper(driver, node);
	}
	
		
	public boolean verifyLoginElements() {
			
		if(oWrap.verifyDisplayedwithReturn(driver.findElement(oUsernameText)) && 
				oWrap.verifyDisplayedwithReturn(driver.findElement(oPasswordText)) && 
				oWrap.verifyDisplayedwithReturn(driver.findElement(oSignInBtn)) && 
				oWrap.verifyDisplayedwithReturn(driver.findElement(oForgotLink)) &&
				oWrap.verifyDisplayedwithReturn(driver.findElement(oRegisterLink))) {
			
			return true;
		}else {
			return false;
		}
		
		
	}
	
	public LoginPage typeUserName(String userName) {
		oWrap.type(driver.findElement(oUsernameText), userName);
		return this;
	}
	
	public LoginPage typePassword(String password) {
		oWrap.type(driver.findElement(oUsernameText), password);
		return this;
	}
	
	public HomePage clickSignOn() {
		oWrap.click(driver.findElement(oSignInBtn));
		return new HomePage(driver);
	}
	
	public LoginPage clickSingOnWithInValidCredential() {
		oWrap.click(driver.findElement(oSignInBtn));
		return this;
	}

	public boolean validateLoginFailedText() {
		boolean result = oWrap.verifyDisplayedwithReturn(driver.findElement(oLoginFailedInnerText));
		return result;
	}
	
	public RegistrationPage clickOnRegistrationLink() {
		oWrap.click(driver.findElement(oRegisterLink));
		return new RegistrationPage(driver,node);
	}
	

}
