package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EveningDressesPage {
	private WebDriver driver;
	private By allListedItems = By.xpath("//ul[@class=\"product_list grid row\"]//a[@class=\"product-name\"]");
	private By dressesButton = By.xpath("//div[@id=\"block_top_menu\"]/ul/child::li[2]");
			
	public EveningDressesPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public boolean areOnlyDressesDisplayed() {
		List<WebElement> eveningDressesList = driver.findElements(allListedItems);
		for (WebElement i : eveningDressesList) {
			String text = i.getText();
			if (!text.contains("Dress")) {
				System.out.println("The listed items contain the word dress");
				return true;
			}
		}
		return false;
	}
	
	public DressesPage clickOnDressesButton() {
		driver.findElement(dressesButton).click();
		return new DressesPage(driver);
	}
}
