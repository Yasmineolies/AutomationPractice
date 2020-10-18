package pages;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCartSummaryPage {
	private WebDriver driver;
	private By firstItemInCart = By.xpath("//table/tbody/tr[1]//p[@class=\"product-name\"]");
	private By secondItemInCart = By.xpath("//table/tbody/tr[2]//p[@class=\"product-name\"]");
	private By getItemParameters = By.xpath("//td[@class=\"cart_description\"]//small//a");
	private By addToCartButton = By.xpath("//a[@title=\"Add\"]");
	private By itemsInCart = By.xpath("//span[@id=\"summary_products_quantity\"]");
	private By checkoutButton = By.xpath("//p//a[@title=\"Proceed to checkout\"]");
	private By logo = By.xpath("//div[@id=\"header_logo\"]//img");
	private By cartFirstItemOldPrice = By.xpath("//table[@id=\"cart_summary\"]/tbody/tr//td[4]//span[3]");
	private By cartFirstItemReducedPrice = By.xpath("//table[@id=\"cart_summary\"]/tbody/tr//td[4]//span[@class=\"price special-price\"]");
	private By cartPercentageOff = By.xpath("//table[@id=\"cart_summary\"]/tbody/tr//td[4]//span[2]");
		
	public ShoppingCartSummaryPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String verifyPrintedDressIsInCart() {
		 String orderedItem = driver.findElement(firstItemInCart).getText();
		 return orderedItem;
	}
	
	public String verifyBlouseIsInCart() {
		 String orderedItem = driver.findElement(secondItemInCart).getText();
		 return orderedItem;
	}
	
	public String verifyItemInCartIsOfCorrectColorAndSize() {
		String parameters = driver.findElement(getItemParameters).getText();
		System.out.println(parameters);
		return parameters;
	}
	
	public String addOneMoreDressToCartAndCheckAmountInCart() throws InterruptedException {
		driver.findElement(addToCartButton).click();
		Thread.sleep(2000);
		String dresses = driver.findElement(itemsInCart).getText();
		System.out.println(dresses);
		return dresses;
	}
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public ChooseDeliveryAddressPage proceedToCheckOut() {
		driver.findElement(checkoutButton).click();
		return new ChooseDeliveryAddressPage(driver);
	}
	
	public ChooseDeliveryAddressPage ProceedToCheckOut() throws InterruptedException {
		WebElement element = driver.findElement(checkoutButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		driver.findElement(checkoutButton).click();
		return new ChooseDeliveryAddressPage(driver); 
	}
	
	public HomePage clickOnLogo() {
		driver.findElement(logo).click();
		return new HomePage(driver);
	}
	
	public boolean isPercentOffCalculated() {
		String oldPriceGet = driver.findElement(cartFirstItemOldPrice).getText();
		String percentOffGet = driver.findElement(cartPercentageOff).getText();
		String reducedPriceGet = driver.findElement(cartFirstItemReducedPrice).getText();
		String oldPrice = oldPriceGet.replaceAll("[^0-9.]","");
		String percentOff = percentOffGet.replaceAll("[^0-9]","");
		String reducedPrice = reducedPriceGet.replaceAll("[^0-9.]","");
		Double obj = new Double(oldPrice);
        BigDecimal old = BigDecimal.valueOf(obj);
        Double obj2 = new Double(reducedPrice);
        BigDecimal reduced = BigDecimal.valueOf(obj2);
        Double obj3 = new Double(percentOff);
        BigDecimal off = BigDecimal.valueOf(obj3);
        BigDecimal priceReducedBy = old.subtract(reduced);
        BigDecimal percentOffReturns = old.multiply(off).divide(new BigDecimal(100));
        int matchValues = priceReducedBy.compareTo(percentOffReturns);
        if (matchValues == 0) {
        	return true;
        }
        return false;
	}
}
