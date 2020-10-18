package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShippingPage {
	private WebDriver driver;
	private By proceedToCheckOut = By.xpath("//button[@name=\"processCarrier\"]");
	private By termsOfServiceCheckbox = By.xpath("//p[@class=\"checkbox\"]//input");
	
	public ShippingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void agreeToTermsOfService() {
		driver.findElement(termsOfServiceCheckbox).click();
	}
	
	public PaymentMethodPage checkOut() {
		driver.findElement(proceedToCheckOut).click();
		return new PaymentMethodPage(driver);
	}
}
