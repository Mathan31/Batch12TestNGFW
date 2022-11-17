package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import utility.ExcelReader;
import utility.PropertiesReader;

public class BaseClass {
	
	public WebDriver driver;
	public String sFileName = "Environment_Details";
	public String excelFileName = "";
	public String iBrowserType = PropertiesReader.getPropertyValue(sFileName, "browser");
	String sURL = PropertiesReader.getPropertyValue(sFileName, "production");
	//4654654651321
	@BeforeClass
	public void invokeBrowser() {
		String browserType = iBrowserType.toLowerCase();
		switch (browserType) {
		case "chrome":
			System.out.println("User Option is : "+iBrowserType+", So invoking Chrome browser.");
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.out.println("User Option is : "+iBrowserType+", So invoking FireFox browser.");
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "edge":
			System.out.println("User Option is : "+iBrowserType+", So invoking Edge browser.");
			System.setProperty("webdriver.edge.driver", "./drivers/msedgedriver.exe");
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("User Option is wrong: "+iBrowserType+", So invoking the default Chrome browser.");
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(sURL);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	
	@DataProvider(name="TestData")
	public Object[][] excelData() {
		Object[][] values = ExcelReader.getValueFromExcel(excelFileName);
		return values;
	}
	

}
