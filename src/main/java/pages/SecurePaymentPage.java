package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecurePaymentPage {
	private WebDriver driver;
	private By shoppingCart = By.xpath("//div[@class=\"shopping_cart\"]/a");
	
	public SecurePaymentPage (WebDriver driver) {
		this.driver = driver;
	} 
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public ShoppingCartSummaryPage ShoppingCart() {
		driver.findElement(shoppingCart).click();
		return new ShoppingCartSummaryPage(driver);
	}
}
