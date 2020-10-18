package pages;

import org.openqa.selenium.WebDriver;

public class RedirectPage {
	private WebDriver driver; 
	
	public RedirectPage(WebDriver driver) {
		this.driver = driver;
	} 
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public HomePage navigateBack() {
		driver.navigate().back();
		return new HomePage(driver);
	}
}
