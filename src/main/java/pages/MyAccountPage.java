package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
	private WebDriver driver;
	private By bestSellers = By.xpath("//a[contains(., 'Best sellers')]");
	
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public BestSellersPage clickOnBestSellersInFooter() {
		driver.findElement(bestSellers).click();
		return new BestSellersPage(driver);
	}
}
