package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BestSellersPage {
	private WebDriver driver;
	private By securePaymentLink = By.xpath("//a[@title='Secure payment']");
	
	public BestSellersPage (WebDriver driver) {
		this.driver = driver;
	} 
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public SecurePaymentPage clickOnSecurePayment() {
		driver.findElement(securePaymentLink).click();
		return new SecurePaymentPage(driver);
	}
}
