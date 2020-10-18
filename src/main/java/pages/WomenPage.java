package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WomenPage {
	private WebDriver driver;
	private By blouse = By.xpath("//ul[@class=\"product_list grid row\"]/li[2]");
	private By blouseToCart = By.xpath("//ul//li[contains(., \"Blouse\")]//a[@title=\"Add to cart\"]");
	private By proceedToCheckOut = By.xpath("//a[@title=\"Proceed to checkout\"]");
	private By catalogBlock = By.id("layered_block_left");
	private By checkboxDresses = By.xpath("//ul[@id=\"ul_layered_category_0\"]//li[2]//input");
	private By sliderLeft = By.xpath("// div[@id=\"layered_price_slider\"]/a[1]");
		
	public WomenPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void addBlouseToCart() throws InterruptedException {
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(blouse);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		WebElement blackBlouse = driver.findElement(blouse);
		actions.moveToElement(blackBlouse).perform();
		driver.findElement(blouseToCart).click();
	}
	
	public DressesPage clickCheckboxDresses() throws InterruptedException {
		WebElement element = driver.findElement(catalogBlock);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
		driver.findElement(checkboxDresses).click();
		//assumption is being made (due to bug being present) that the user is redirected to the dresses page when clicking dresses checkbox
		return new DressesPage(driver);
	}
	
	public void slidePriceRange() throws InterruptedException {
		WebElement slider = driver.findElement(sliderLeft);
        Actions move = new Actions(driver);
        move.dragAndDropBy(slider, 52, 0).build().perform();
    }
	
	public ShoppingCartSummaryPage clickProceedToCheckOut() {
		driver.findElement(proceedToCheckOut).click();
		return new ShoppingCartSummaryPage(driver);
	}
}
