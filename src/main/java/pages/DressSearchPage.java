package pages;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DressSearchPage {
	private WebDriver driver;
	private By allListedItems = By.xpath("//ul[@class=\"product_list grid row\"]//a[@class=\"product-name\"]");
	private By secondRow = By.xpath("//ul[@class=\"product_list grid row\"]/li[4]");
	
	public DressSearchPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean areOnlyDressesDisplayed() throws InterruptedException {
		WebElement element = driver.findElement(secondRow);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		ArrayList<WebElement> listedItems = new ArrayList<>(driver.findElements(allListedItems));
		for (WebElement e: listedItems) {
			   String itemListText = e.getText();
			   System.out.println(itemListText);
			   if (!itemListText.contains("Dress")) {
				   return true;
			   }
		} 
		return false;
	}
}
