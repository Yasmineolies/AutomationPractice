package base;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import pages.HomePage;

public class BaseTest {
	public WebDriver driver;
	
	public HomePage getDriver() {
		return new HomePage(driver);
	}
 
	@BeforeSuite
	public void SetUp() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//executable//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
	}
	
	@AfterMethod
	public void ReturnToHomePage() {
		driver.get("http://automationpractice.com/index.php");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterSuite
	public void driverQuit() {
		driver.quit();
	}
}
