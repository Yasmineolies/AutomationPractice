package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DressesPage {
	private WebDriver driver;
	private By itemsDisplayed = By.xpath("//div[@class=\"content_scene_cat\"]//span");
	private By printedSummerDress = By.xpath("//ul[@class=\"product_list grid row\"]/li[4]");
	private By quickViewSummerDress  = By.xpath("//ul[@class=\"product_list grid row\"]/li[4]//a[@class=\"quick-view\"]");
	private By whiteColor = By.xpath("//body[@id=\"product\"]//a[@id=\"color_8\"]");
	private By selectSize = By.id("uniform-group_1");
	private By addToCart = By.id("add_to_cart");
	private By proceedToCheckOut = By.xpath("//a[@title=\"Proceed to checkout\"]");
	private By checkboxForSizeM = By.xpath("//ul[@id=\"ul_layered_id_attribute_group_1\"]//li[2]//input");
	private By catalogBlock = By.id("layered_block_left");
	private By checkboxYellow = By.xpath("//ul[@id=\"ul_layered_id_attribute_group_3\"]//li[7]//input");
	private By checkboxCotton = By.xpath("//ul[@id=\"ul_layered_id_feature_5\"]//li[1]//input");
	private By checkboxDressy = By.xpath("//ul[@id=\"ul_layered_id_feature_6\"]//li[2]//input");
	private By checkboxColorfulDress = By.xpath("//ul[@id=\"ul_layered_id_feature_7\"]//li[1]//input");
	private By checkboxInStock = By.xpath("//ul[@id=\"ul_layered_quantity_0\"]//li[1]//input");
	private By sizeM = By.xpath("//select[@name=\"group_1\"]//option[2]");
	
	public DressesPage(WebDriver driver) {
		this.driver = driver;
	}
		
	public String dressesDisplay() {
		String listedItems = driver.findElement(itemsDisplayed).getText();
		System.out.println(listedItems);
		return listedItems;
	}
	
	public void openDressQuickView() throws InterruptedException {
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(printedSummerDress);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
		WebElement dresses = driver.findElement(printedSummerDress);
		actions.moveToElement(dresses).perform();
		driver.findElement(quickViewSummerDress).click();
	}
	
	public ShoppingCartSummaryPage selectDressPreferencesAndCheckout() {		
		driver.switchTo().frame(0);
		driver.findElement(selectSize).click();
		driver.findElement(selectSize).click(); 
		driver.findElement(sizeM).click();		
		driver.findElement(whiteColor).click();
		driver.findElement(addToCart).click();
		driver.switchTo().defaultContent();
		driver.findElement(proceedToCheckOut).click();
		return new ShoppingCartSummaryPage(driver);
	}
	
	public void clickCheckboxPreferences() throws InterruptedException {
		WebElement element = driver.findElement(catalogBlock);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
		driver.findElement(checkboxForSizeM).click();
		driver.findElement(checkboxYellow).click();
		driver.findElement(checkboxCotton).click();
		driver.findElement(checkboxDressy).click();
		driver.findElement(checkboxColorfulDress).click();
		driver.findElement(checkboxInStock).click();
	}
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
}
