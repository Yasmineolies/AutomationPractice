package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactPage {
	private WebDriver driver;
	private By h1Text = By.xpath("//div[@id=\"center_column\"]//h1");
	private By myAccountButton = By.xpath("//a[contains(., 'My account')]");
	
	public ContactPage (WebDriver driver){
		this.driver = driver;
	}
	
	public String getPageTitle() {
		String title = driver.findElement(h1Text).getText();
		return title;
	} 
	
	public MyAccountPage clickMyAccountButton() throws InterruptedException {
		WebElement element = driver.findElement(myAccountButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		driver.findElement(myAccountButton).click();
		return new MyAccountPage(driver);
	}
}
