package pages;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentMethodPage {
	private WebDriver driver;
	private By payByCheck = By.cssSelector(".cheque");
	private By confirmMyOrderButton = By.xpath("//p//button[@type=\"submit\"]");
	private By unitPriceFirstItemInCart = By.xpath("//tbody/tr[1]/td[4]//span[@class=\"price\"]//span");
	private By unitPriceSecondItemInCart = By.xpath("//tbody/tr[2]/td[4]//span[@class=\"price\"]//span");
	private By firstItemInCartQty = By.xpath("//tbody/tr[1]/td[5]/span");
	private By secondItemInCartQty = By.xpath("//tbody/tr[2]/td[5]/span");
	private By totalShipping = By.cssSelector("#total_shipping");
	private By totalPrice = By.cssSelector("#total_price");
	
	public PaymentMethodPage(WebDriver driver) {
		this.driver = driver; 
	}
	
	public void clickPayByCheck() {
		driver.findElement(payByCheck).click();
	}
	
	public OrderConfirmationPage confirmMyOrder() {
		driver.findElement(confirmMyOrderButton);
		return new OrderConfirmationPage(driver);
	}
	
	public boolean arePricesCalculatedCorrectly() {
		String priceOfFistItemInCart = driver.findElement(unitPriceFirstItemInCart).getText().replaceAll("[^0-9.]","");
		String priceOfSecondItemInCart = driver.findElement(unitPriceSecondItemInCart).getText().replaceAll("[^0-9.]","");
		String firstItemInCartQuantity = driver.findElement(firstItemInCartQty).getText().replaceAll("[^0-9.]","");
		String secondItemInCartQuantity = driver.findElement(secondItemInCartQty).getText().replaceAll("[^0-9.]","");
		String shippingPrice = driver.findElement(totalShipping).getText().replaceAll("[^0-9.]","");
		String total = driver.findElement(totalPrice).getText().replaceAll("[^0-9.]","");
		Double firstItem = new Double(priceOfFistItemInCart);
		BigDecimal firstItemPrice = BigDecimal.valueOf(firstItem);
		Double secondItem = new Double(priceOfSecondItemInCart);
		BigDecimal secondItemPrice = BigDecimal.valueOf(secondItem);
		Double firstCart = new Double(firstItemInCartQuantity);
		BigDecimal firstItemQty = BigDecimal.valueOf(firstCart);
		Double secondCart = new Double(secondItemInCartQuantity);
		BigDecimal secondItemQty = BigDecimal.valueOf(secondCart);
		Double shipping = new Double(shippingPrice);
		BigDecimal PriceOfShipping = BigDecimal.valueOf(shipping);
		Double sum = new Double(total);
		BigDecimal grandTotal = BigDecimal.valueOf(sum);
		BigDecimal firstItemTotalPrice = firstItemPrice.multiply(firstItemQty);
		BigDecimal secondItemTotalPrice = secondItemPrice.multiply(secondItemQty);
		BigDecimal totalPriceOfAllPurchasedItems = firstItemTotalPrice.add(secondItemTotalPrice);
		BigDecimal AmountToPay = totalPriceOfAllPurchasedItems.add(PriceOfShipping);
		int matchValues = AmountToPay.compareTo(grandTotal);
		if (matchValues == 0) {
			return true;
		}
		return false; 
	}
}