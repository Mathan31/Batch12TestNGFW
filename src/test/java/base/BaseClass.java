package base;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import library.HTMLReport;
import utility.ExcelReader;
import utility.PropertiesReader;

public class BaseClass extends HTMLReport{
	
	public WebDriver driver;
	public String sFileName = "Environment_Details";
	public String excelFileName = "";
	public String testName,testDescription,module;
	public String iBrowserType = PropertiesReader.getPropertyValue(sFileName, "browser");
	String sURL = PropertiesReader.getPropertyValue(sFileName, "production");
	
	@BeforeSuite
	public void reportInitialize() {
		startReport();
	}
	
	@AfterSuite
	public void stopReport() {
		endReport();
	}

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
		startTestCase(testName, testDescription);
		startTestcase(module);
		
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

	@Override
	public String takeScreenshot() {
		String sPath = System.getProperty("user.dir")+"/snap/img"+System.currentTimeMillis()+".png";
		TakesScreenshot oShot = (TakesScreenshot)driver;
		File osrc = oShot.getScreenshotAs(OutputType.FILE);
		File dis = new File(sPath);
		try {
			FileUtils.copyFile(osrc, dis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sPath;
	}
	

}
