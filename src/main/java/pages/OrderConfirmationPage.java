package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage {
	private WebDriver driver;
	private By confirmOrderButton = By.xpath("//button[@type=\"submit\"][contains(., \"I confirm my order\")]");
	private By orderConfirmation = By.xpath("//p[@class=\"alert alert-success\"]");
	
	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void confirmOrder() {
		driver.findElement(confirmOrderButton).click();
	}
	
	public String checkOrderConfirmation() {
		String orderMessage = driver.findElement(orderConfirmation).getText();
		System.out.println(orderMessage);
		return orderMessage;
	}	
}
