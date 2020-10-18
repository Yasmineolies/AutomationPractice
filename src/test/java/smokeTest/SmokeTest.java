package smokeTest;

import org.junit.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.DressesPage;
import pages.AuthenticationPage;
import pages.BestSellersPage;
import pages.ChooseDeliveryAddressPage;
import pages.ContactPage;
import pages.DressSearchPage;
import pages.EveningDressesPage;
import pages.HomePage;
import pages.MyAccountPage;
import pages.OrderConfirmationPage;
import pages.PaymentMethodPage;
import pages.RedirectPage;
import pages.SecurePaymentPage;
import pages.ShippingPage;
import pages.ShoppingCartSummaryPage;
import pages.SignUpPage;
import pages.WomenPage;

public class SmokeTest extends BaseTest {
	
	@Test(priority=1)
	public void LoginWithoutPasswordTC1_A13() {
		HomePage home = getDriver();
		SignUpPage signUp = home.clickSignInButton();
		signUp.enterEmailOmmitPassword();
		AuthenticationPage auth = signUp.clickLoginOmmitingInput();
		String message = auth.readAuthenticationMessage();
		Assert.assertEquals("Password is required.", message);
	}
	
	@Test(priority=2)
	public void LoginWithoutEmailTC2_A14() {
		HomePage home = getDriver();
		SignUpPage signUp = home.clickSignInButton();
		signUp.enterPasswordOmmitEmail();
		AuthenticationPage auth = signUp.clickLoginOmmitingInput();
		String message = auth.readAuthenticationMessage();
		Assert.assertEquals("An email address required.", message);
	}
	
	@Test(priority=3)
	public void LoginWithoutEmailTC3_A15() {
		HomePage home = getDriver();
		SignUpPage signUp = home.clickSignInButton();
		AuthenticationPage auth = signUp.clickLoginOmmitingInput();
		String message = auth.readAuthenticationMessage();
		Assert.assertEquals("An email address required.", message);
	}
	
	@Test(priority=4)
	public void LoginWithIncorrectPasswordTC4_A17() {
		HomePage home = getDriver();
		SignUpPage signUp = home.clickSignInButton();
		signUp.enterValidEmailInvalidPassword();
		AuthenticationPage auth = signUp.clickLoginOmmitingInput();
		String message = auth.readAuthenticationMessage();
		Assert.assertEquals("Authentication failed.", message);
	}
	
	@Test(priority=5)
	public void LoginTC4_A12() {
		HomePage home = getDriver();
		SignUpPage signUp = home.clickSignInButton();
		signUp.enterEmailAddressAndPassword();
		MyAccountPage myPage = signUp.clickLoginButton();
		String actualPage = myPage.getPageUrl();
		Assert.assertEquals(actualPage, "http://automationpractice.com/index.php?controller=my-account");
	}
	
	@Test(priority=5)
	public void SearchFunctionalityTC5_B2() throws InterruptedException {
		HomePage home = getDriver();
		DressSearchPage dressReturn = home.searchForDresses();
		boolean dresses = dressReturn.areOnlyDressesDisplayed();
		Assert.assertFalse("The search functinality did not return valid output", dresses);
	}
	
	@Test(priority=6)
	public void AddDressToCartTC6_B3() throws InterruptedException {
		HomePage home = getDriver();
		EveningDressesPage eveningDresses = home.selectDressTypeFromDropdown();
		String actualPage= eveningDresses.getPageUrl();
		Assert.assertEquals(actualPage, "http://automationpractice.com/index.php?id_category=10&controller=category");
		boolean dresses = eveningDresses.areOnlyDressesDisplayed();
		Assert.assertFalse(dresses);
		DressesPage allDresses = eveningDresses.clickOnDressesButton(); 
		String dressesDisplayed = allDresses.dressesDisplay();
		Assert.assertEquals(dressesDisplayed, "Dresses");
		allDresses.openDressQuickView();
		ShoppingCartSummaryPage order = allDresses.selectDressPreferencesAndCheckout();
		String orederedItem = order.verifyPrintedDressIsInCart();
		Assert.assertEquals("Printed Summer Dress", orederedItem);
		String orderSpecs = order.verifyItemInCartIsOfCorrectColorAndSize();
		Assert.assertEquals(orderSpecs, "Color : White, Size : M");
		String noOfDresses = order.addOneMoreDressToCartAndCheckAmountInCart();
		Assert.assertEquals(noOfDresses, "2 Products");
		ChooseDeliveryAddressPage deliveryAddress = order.proceedToCheckOut();
		String delivery = deliveryAddress.checkDeliveryAddress();
		Assert.assertEquals("Arlington Avenue 23 Apartment 25", delivery);
		WomenPage womenItems = deliveryAddress.clickOnWomenButton();
		womenItems.addBlouseToCart();
		order = womenItems.clickProceedToCheckOut();
		Assert.assertEquals("Printed Summer Dress", orederedItem);
		String secondItem = order.verifyBlouseIsInCart();
		Assert.assertEquals("Blouse", secondItem);
		deliveryAddress = order.proceedToCheckOut();
		ShippingPage shipping = deliveryAddress.proceedToCheckOut(); 
		shipping.agreeToTermsOfService();
		PaymentMethodPage pay = shipping.checkOut();
		boolean calculationOfPricesIsCorrect = pay.arePricesCalculatedCorrectly();
		Assert.assertTrue(calculationOfPricesIsCorrect);
		pay.clickPayByCheck();
		OrderConfirmationPage ordered = pay.confirmMyOrder();
		ordered = pay.confirmMyOrder();
		ordered.confirmOrder();
		String placedOrderConfirmation = ordered.checkOrderConfirmation();
		Assert.assertEquals("Your order on My Store is complete.", placedOrderConfirmation);
	}
	
	@Test(priority=7)
	public void VerifyCatalogCheckboxesWorkTC7_B4() throws InterruptedException {
		HomePage home = getDriver();
		WomenPage women = home.clickOnWomenInMenu();
		women.slidePriceRange();
		DressesPage dress = women.clickCheckboxDresses();
		String actualPage= dress.getPageUrl();
		Assert.assertEquals(actualPage, "http://automationpractice.com/index.php?id_category=8&controller=category");
		//the automation of this test case was not concluded because the test fails all requirements, and I am uncertain how results will be returned
	}
	
	@Test(priority=8)
	public void NavigateThroughLinksTC8_B5() throws InterruptedException {
		HomePage home = getDriver();
		ContactPage contact = home.clickOnContactUsLink();
		String title = contact.getPageTitle(); 
		Assert.assertEquals("CUSTOMER SERVICE - CONTACT US", title);
		MyAccountPage account = contact.clickMyAccountButton();
		String url = account.getPageUrl();
		Assert.assertEquals("http://automationpractice.com/index.php?controller=my-account", url);
		BestSellersPage sellers = account.clickOnBestSellersInFooter();
		String sellersUrl = sellers.getPageUrl();
		Assert.assertEquals("http://automationpractice.com/index.php?controller=best-sales", sellersUrl);
		SecurePaymentPage securePayment = sellers.clickOnSecurePayment();
		String securePayUrl = securePayment.getPageUrl();
		Assert.assertEquals("http://automationpractice.com/index.php?id_cms=5&controller=cms", securePayUrl);
		ShoppingCartSummaryPage shopping = securePayment.ShoppingCart();
		String shoppingUrl = shopping.getPageUrl();
		Assert.assertEquals("http://automationpractice.com/index.php?controller=order", shoppingUrl);
		home = shopping.clickOnLogo();
		String homeUrl = home.getPageUrl();
		Assert.assertEquals("http://automationpractice.com/index.php", homeUrl);
		RedirectPage redirect = home.ClickSalesBanner();
		String redirectUrl = redirect.getPageUrl();
		Assert.assertEquals("https://www.prestashop.com/en", redirectUrl);
		home = redirect.navigateBack();
		home.clickOnTwitterLink();
		String twitterUrlTab = home.getPageUrl();
		Assert.assertEquals("https://twitter.com/seleniumfrmwrk", twitterUrlTab);
		home.switchBackToHomePageTab();
	}
	
	@Test(priority=9)
	public void userCanPurchaseItemsAtSalesPrices() throws InterruptedException {
		HomePage home = getDriver();
		ShoppingCartSummaryPage cart = home. identifyItemOnSale();
		boolean percentage = cart.isPercentOffCalculated();
		Assert.assertTrue(percentage);
	}
}
