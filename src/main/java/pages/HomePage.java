package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
	private WebDriver driver;
	private By signInButton = By.xpath("//a[@class='login']");
	private By dressesButton = By.xpath("//div[@id=\"block_top_menu\"]/ul/child::li[2]");
	private By eveningDresses = By.xpath("//div[@id=\"block_top_menu\"]/ul/child::li[2]/ul//a[@title=\"Evening Dresses\"]");
	private By searchFiled = By.id("search_query_top");
	private By women = By.xpath("//div[@id=\"block_top_menu\"]//a[@title=\"Women\"]");
	private By contactUs = By.id("contact-link");
	private By sale45Percent = By.xpath("//div[@id=\"htmlcontent_top\"]/ul//li[2]");
	private By twitterLink = By.xpath("//section[@id=\"social_block\"]//li[@class=\"twitter\"]");
	private By displayed = By.xpath("//div[@id=\"center_column\"]");
	private By listedItemsOnSale = By.xpath("//ul[@id=\"homefeatured\"]//div[@class=\"right-block\"]//span[@class=\"price-percent-reduction\"]");
	private By addToCartButton = By.xpath("//span[contains(., 'Add to cart')]");
	private By proceedToCheckOut = By.xpath("//a[@title='Proceed to checkout']");
			
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public SignUpPage clickSignInButton() {
		driver.findElement(signInButton).click();
		return new SignUpPage(driver);
	}
	
	public EveningDressesPage selectDressTypeFromDropdown() {
		Actions actions = new Actions(driver);
		WebElement dresses = driver.findElement(dressesButton);
		actions.moveToElement(dresses).perform();
		driver.findElement(eveningDresses).click();
		return new EveningDressesPage(driver);
	}
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public RedirectPage ClickSalesBanner() {
		driver.findElement(sale45Percent).click();
		return new RedirectPage(driver);
	}
	
	public DressSearchPage searchForDresses() {
		driver.findElement(searchFiled).sendKeys("dress");
		driver.findElement(searchFiled).sendKeys(Keys.ENTER);
		return new DressSearchPage(driver);
	}
	
	public WomenPage clickOnWomenInMenu() {
		driver.findElement(women).click();
		return new WomenPage(driver);
	}
	
	public ContactPage clickOnContactUsLink() {
		driver.findElement(contactUs).click();
		return new ContactPage(driver);
	}
	
	public void clickOnTwitterLink() {
		driver.findElement(twitterLink).click();
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		System.out.println(tabs.size());
		driver.switchTo().window(tabs.get(1));
		String url = driver.getCurrentUrl();
		System.out.println(url);
	}
	
	public void switchBackToHomePageTab() {
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}
	
	public ShoppingCartSummaryPage identifyItemOnSale() throws InterruptedException {
		WebElement element = driver.findElement(displayed);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		List<WebElement> items =  driver.findElements(listedItemsOnSale);
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getText().contains("-20%")) {			
				items.get(i).click();
				break;
			} 
		} 
		driver.findElement(addToCartButton).click();
		driver.findElement(proceedToCheckOut).click();
		return new ShoppingCartSummaryPage(driver);
	}
}
