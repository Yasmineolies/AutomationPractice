package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthenticationPage {
	private WebDriver driver;
	private By authenticationFail = By.xpath("//div[@class=\"alert alert-danger\"]/ol/li");
	
	public AuthenticationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String readAuthenticationMessage() {
		return driver.findElement(authenticationFail).getText();
	}
}
