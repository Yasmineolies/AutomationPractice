package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChooseDeliveryAddressPage {
	private WebDriver driver;
	private By address = By.xpath("//ul[@id=\"address_delivery\"]//li[4]");
	private By women = By.xpath("//div[@id=\"block_top_menu\"]//a[@title=\"Women\"]");
	private By proceedToCheckOut = By.xpath("//button[@name=\"processAddress\"]");
	
	public ChooseDeliveryAddressPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String checkDeliveryAddress() {
		String addressDelivery = driver.findElement(address).getText();
		System.out.println(addressDelivery);
		return addressDelivery;
	}	
	
	public WomenPage clickOnWomenButton() {
		driver.findElement(women).click();
		return new WomenPage(driver);
	}
	
	public ShippingPage proceedToCheckOut() {
		driver.findElement(proceedToCheckOut).click();
		return new ShippingPage(driver);
	}
}
