package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
	private WebDriver driver;
	private By emailField = By.cssSelector("#email");
	private By passwordField = By.cssSelector("#passwd");
	private By loginButton = By.cssSelector("#SubmitLogin");
	
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterEmailOmmitPassword() {
		driver.findElement(emailField).sendKeys("testing.account@g.com");
	}
	
	public void enterPasswordOmmitEmail() {
		driver.findElement(passwordField).sendKeys("test321GO");
	}
	
	public void enterValidEmailInvalidPassword() {
		driver.findElement(emailField).sendKeys("testing.account@g.com");
		driver.findElement(passwordField).sendKeys("123456");
	}
	
	public void enterEmailAddressAndPassword() {
		driver.findElement(emailField).sendKeys("testing.account@g.com");
		driver.findElement(passwordField).sendKeys("test321GO");
	}
	
	public MyAccountPage clickLoginButton() {
		driver.findElement(loginButton).click();
		return new MyAccountPage(driver);
	}
	
	public AuthenticationPage clickLoginOmmitingInput() {
		driver.findElement(loginButton).click();
		return new AuthenticationPage(driver);
	}
}
