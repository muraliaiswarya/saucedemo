package com.ExcelrProject_1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.testng.Assert;

import pageObject.CartPage;
import pageObject.CheckOutComplete;
import pageObject.CheckOutStepTwo;
import pageObject.CheckoutStepOne;
import pageObject.InventoryPage;
import pageObject.Loginpage;

public class Testcases {
	WebDriver driver;
	Loginpage login;
	InventoryPage inventory;
	CartPage cp;
	CheckoutStepOne csop; 
	CheckOutStepTwo cstp;
	CheckOutComplete ccp;
	
	

	@BeforeMethod
	public void launchBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.saucedemo.com/");
		login = new Loginpage(driver);
		inventory = new InventoryPage(driver);
		cp=new CartPage(driver);
		csop=new CheckoutStepOne(driver);
		cstp=new CheckOutStepTwo(driver);
	}

	@Test(priority=1)
	public void loginPageValidation() {
		String CurrentURL = login.currentURL();
		Assert.assertEquals(CurrentURL, "https://www.saucedemo.com/");
		String LoginpageText = login.loginPageText();
		Assert.assertEquals(LoginpageText, "Swag Labs");
		WebElement UserName = login.userNameFieldVerification();
		Assert.assertTrue(UserName.isDisplayed(), "Username is not displayed");
		Assert.assertEquals(UserName.getAttribute("placeholder"), "Username");
		WebElement Password = login.passWordFieldVerification();
		Assert.assertTrue(Password.isDisplayed());
		Assert.assertEquals(Password.getAttribute("placeholder"), "Password");
		WebElement Loginbutton = login.loginButtonVerification();
		Assert.assertTrue(Loginbutton.isDisplayed(), "LoginButton is not displayed");
		Assert.assertTrue(Loginbutton.isEnabled());
		Assert.assertEquals(Loginbutton.getAttribute("value"), "Login");
	}

	@DataProvider(name = "ValidLoginData")
	public Object[][] validLoginData() {
		// Object[][]data=new Object[4][2]; //hard coded
		Object[][] data = { { "standard_user", "secret_sauce" }, { "problem_user", "secret_sauce" },
				{ "performance_glitch_user", "secret_sauce" }, { "error_user", "secret_sauce" },
				{ "visual_user", "secret_sauce" } };
		return data;
	}

	@Test(priority=2,dataProvider = "ValidLoginData")
	public void loginWithValidCredentials(String username, String password) throws InterruptedException {
		login.enterUserNameAndPassword(username, password);
		login.clickOnLogin();
		inventory.clickOnHamburgericon();
		inventory.clickOnHamburgerLogout();
	}

	@Test(priority=3)
	public void loginWithInvalidCredential() throws InterruptedException {
		login.enterUserNameAndPassword("asifthy", "act123");
		login.clickOnLogin();
		Assert.assertEquals(login.errorMesgdisplay().getText(),
				"Epic sadface: Username and password do not match any user in this service");
	}

	@Test(priority=4)
	public void loginWithValidUsernameInvalidPassword() throws InterruptedException {
		login.enterUserNameAndPassword("standard_user", "act123");
		login.clickOnLogin();
		Assert.assertEquals(login.errorMesgdisplay().getText(),
				"Epic sadface: Username and password do not match any user in this service");
	}

	@Test(priority=5)
	public void loginWithInvalidUsernameValidPassword() throws InterruptedException {
		login.enterUserNameAndPassword("asifthy", "secret_sauce");
		login.clickOnLogin();
		Assert.assertEquals(login.errorMesgdisplay().getText(),
				"Epic sadface: Username and password do not match any user in this service");
		// System.out.println(login.errorMesgdisplay().getText());
	}

	@Test(priority=6)
	public void inventoryPageValidation() throws InterruptedException {
		login.enterUserNameAndPassword("standard_user", "secret_sauce");
		login.clickOnLogin();
		Assert.assertEquals(login.currentURL(), "https://www.saucedemo.com/inventory.html");
		Assert.assertEquals(inventory.inventoryPageTitle(), "Swag Labs");
		Assert.assertEquals(inventory.Pagetitle(), "Products");
	}
	@Test(priority = 7)
	public void inventoryProductsDetails() throws InterruptedException {
		login.enterUserNameAndPassword("standard_user", "secret_sauce");
		login.clickOnLogin();
		List<WebElement> Productimage=inventory.inventoryProductimage();
		Assert.assertEquals(Productimage.size(),6);
		List <WebElement> ProductList=inventory.inventoryProductList();
		Assert.assertEquals(ProductList.size(),6);
		System.out.println("The Product List : ");
		for(int i=0;i<ProductList.size();i++)
		{
			ProductList.get(i).getText();
			System.out.println(ProductList.get(i).getText());
			}
		}
	@Test(priority=8)
	public void addToCartButtonValidation() throws InterruptedException {
		login.enterUserNameAndPassword("standard_user", "secret_sauce");
		login.clickOnLogin();
		WebElement AddToCart =inventory.addtoCartButtonPresence();		
		Assert.assertTrue(AddToCart.isDisplayed());
		Assert.assertTrue(AddToCart.isEnabled());
		Assert.assertEquals(AddToCart.getText(),"Add to cart");
	}
	@Test(priority = 9)
	public void clickOnAddToCartButtonValidation() throws InterruptedException {
		login.enterUserNameAndPassword("standard_user", "secret_sauce");
		login.clickOnLogin();
		inventory.addtoCartButtonVerification();
	}
	@Test(priority = 10)
	public void clickOnAnyProductvalidation() throws InterruptedException {
		login.enterUserNameAndPassword("standard_user", "secret_sauce");
		login.clickOnLogin();
		inventory.clickOnAnyProduct();
		Assert.assertTrue(login.currentURL().contains("inventory-item.html?id="));
		WebElement AddToCart =inventory.addToCart();
		Assert.assertTrue(AddToCart.isDisplayed());
		Assert.assertTrue(AddToCart.isEnabled());
		Assert.assertEquals(AddToCart.getText(), "Add to cart");
		inventory.clickOnAddtoCart();
		System.out.println(inventory.cartContent());
	}
@Test(priority = 11)
public void backTOProductsValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnAnyProduct();
	inventory.clickOnAddtoCart();
	System.out.println(inventory.cartContent());
	Assert.assertTrue(inventory.backToProducts().isDisplayed());
	inventory.clickOnBackToProduct();
	Assert.assertEquals(login.currentURL(), "https://www.saucedemo.com/inventory.html");
}

	@Test(priority=12)
	public void hamburgerMenuValidation() throws InterruptedException {
		login.enterUserNameAndPassword("standard_user", "secret_sauce");
		login.clickOnLogin();
		Assert.assertTrue(inventory.hamburgerMenuVerification().isDisplayed(), "Hamburger Menu is not displayed");
		Assert.assertTrue(inventory.hamburgerMenuVerification().isEnabled());
		inventory.clickOnHamburgericon();
		List<WebElement> HamburgerList = inventory.HamburgerMenuListVerification();
		Assert.assertEquals(HamburgerList.size(), 4);
		System.out.println("Hamburger Menu List: ");
		for (int i = 0; i < HamburgerList.size(); i++) {
			String HamList = HamburgerList.get(i).getText();
			System.out.println(HamList);

		}
	}

	@Test(priority=13)
	public void hamburgerAlltemsverification() throws InterruptedException {
		login.enterUserNameAndPassword("standard_user", "secret_sauce");
		login.clickOnLogin();
		inventory.clickOnHamburgericon();
		inventory.clickOnHamburgerAllitems();
		Assert.assertEquals(login.currentURL(),"https://www.saucedemo.com/inventory.html");
		inventory.ClickOnCloseIcon();
		}
	
	
	
	@Test(priority=14)
	public void hamburgerAboutVerification() throws InterruptedException {
		login.enterUserNameAndPassword("standard_user", "secret_sauce");
		login.clickOnLogin();
		inventory.clickOnHamburgericon();
		inventory.clickOnHamburgerAbout();
		Assert.assertEquals(login.currentURL(),"https://saucelabs.com/");
		driver.navigate().back();
		Assert.assertEquals(login.currentURL(),"https://www.saucedemo.com/inventory.html");
	}
	
	@Test(priority=15)
	public void hamburgerResetAppState() throws InterruptedException {
		login.enterUserNameAndPassword("standard_user", "secret_sauce");
		login.clickOnLogin();
		inventory.clickOnHamburgericon();
		inventory.hamburgerRestAppState();
		Assert.assertTrue(inventory.hamburgerRestAppState().isDisplayed());
		Assert.assertTrue(inventory.hamburgerRestAppState().isEnabled());
	}
	@Test(priority = 16)
	public void hamburgerLogoutValidation() throws InterruptedException {
		login.enterUserNameAndPassword("standard_user", "secret_sauce");
		login.clickOnLogin();
		inventory.clickOnHamburgericon();
		inventory.clickOnHamburgerLogout();
		Assert.assertEquals(login.currentURL(), "https://www.saucedemo.com/");
	}

	
	
@Test(priority = 17)
public void filterDropdownValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	List<WebElement> DropDownList = inventory.dropDownListVerification();
	for(int i =0; i<DropDownList.size();i++) {
		System.out.println(DropDownList.get(i).getText());
	}
}
@Test(priority = 18)
public void selectProductByAlphabeticalOrderValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	List<WebElement> ProductNamesBefore = inventory.productNames();
	inventory.selectNameAtoZ();
	List<WebElement> ProductNamesAfter = inventory.productNames();
	for(int i =0; i<ProductNamesBefore.size();i++) {
		Assert.assertEquals(ProductNamesBefore.get(i).getText(), ProductNamesAfter.get(i).getText());
	System.out.println(ProductNamesBefore.get(i).getText());
	}		
}
@Test(priority = 19)
public void selectProductByReverseAlphabeticalOrderValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	List<WebElement> ProductNamesBefore = inventory.productNames();
	inventory.selectNameZtOA();
	List<WebElement> ProductNamesAfter = inventory.productNames();
	for(int i =0; i<ProductNamesBefore.size();i++) {
	Assert.assertNotEquals(ProductNamesBefore.get(i).getText(),ProductNamesAfter.get(i).getText());
	//System.out.println(ProductNamesBefore.get(i).getText());
//	System.out.println(ProductNamesAfter.get(i).getText());
	}	
}
@Test(priority = 20)
public void selectProductByHighToLow() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	List<WebElement> ProductPriceBefore = inventory.productPrices();
			
	for(int i = 0; i< ProductPriceBefore.size();i++) {
		System.out.println(ProductPriceBefore.get(i).getText());
	}
   inventory.selectPriceHightoLow();
	List<WebElement> ProductPriceAfter = inventory.productPrices();
	for(int i = 0; i< ProductPriceAfter.size();i++) {
		System.out.println(ProductPriceAfter.get(i).getText());
	}
		}
@Test(priority = 21)
public void selectProductByLowToHigh() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	List<WebElement> ProductPriceBefore = inventory.productPrices();
			
	for(int i = 0; i< ProductPriceBefore.size();i++) {
		System.out.println(ProductPriceBefore.get(i).getText());
	}
   inventory.selectPriceLowtoHigh();
	List<WebElement> ProductPriceAfter = inventory.productPrices();
	for(int i = 0; i< ProductPriceAfter.size();i++) {
		System.out.println(ProductPriceAfter.get(i).getText());
	}
}

@Test(priority = 22)
public void cartPageValidation() throws InterruptedException {
	//checoutStepOnePageValidation
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	Assert.assertTrue(inventory.cartIconPresence().isDisplayed());
	cp.clickOnCartIcon();
	Assert.assertEquals(login.currentURL(), "https://www.saucedemo.com/cart.html");	
	Assert.assertEquals(cp.pageText(), "Your Cart");
}
@Test(priority = 23)
public void cartPageRemoveButtonValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	System.out.println("Before clickin on Remove button: "+inventory.cartContent());
	WebElement RemoveBtn = cp.removeButtonPresence();
	Assert.assertTrue(RemoveBtn.isDisplayed());
	Assert.assertTrue(RemoveBtn.isEnabled());
	Assert.assertEquals(RemoveBtn.getText(), "Remove");
	RemoveBtn.click();
	System.out.println("After clickin on Remove button: "+inventory.cartContent());
}
@Test(priority=24)
public void cartPageContinueBtnValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	WebElement ContinueShoppingBtn = cp.continueBtnVerification();		
	Assert.assertTrue(ContinueShoppingBtn.isDisplayed());
	Assert.assertTrue(ContinueShoppingBtn.isEnabled());
	Assert.assertEquals(ContinueShoppingBtn.getText(), "Continue Shopping");
	cp.clickOnContinueBtn();
	Assert.assertEquals(login.currentURL(), "https://www.saucedemo.com/inventory.html");		
}
@Test(priority=25)
public void cartPageCheckoutBtnValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	WebElement CheckoutBtn = cp.chekoutBtnVerification();
	Assert.assertTrue(CheckoutBtn.isDisplayed());
	Assert.assertTrue(CheckoutBtn.isEnabled());
	Assert.assertEquals(CheckoutBtn.getText(), "Checkout");
	cp.clickOnCheckout();
	Assert.assertEquals(login.currentURL(), "https://www.saucedemo.com/checkout-step-one.html");
}

@Test(priority=26)
public void checkoutStepOnePageValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	cp.clickOnCheckout();
	Assert.assertEquals(cp.pageText(), "Checkout: Your Information");
}
@Test(priority=27)
public void checkoutStepOneFieldsValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	cp.clickOnCheckout();
	Assert.assertTrue(csop.firstname().isDisplayed());
	Assert.assertEquals(csop.firstname().getAttribute("placeholder"), "First Name");
	Assert.assertTrue(csop.lastName().isDisplayed());
	Assert.assertEquals(csop.lastName().getAttribute("placeholder"), "Last Name");
	Assert.assertTrue(csop.postalCode().isDisplayed());
	Assert.assertEquals(csop.postalCode().getAttribute("placeholder"), "Zip/Postal Code");
	csop.enterChecoutDetails("aiswarya", "murali", "546155");
	
}
@Test(priority=28)
public void chekoutStepOnePageCancelBtnValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	cp.clickOnCheckout();		
	WebElement CancelButton = csop.checkoutCancelBtnVerification();
	Assert.assertTrue(CancelButton.isDisplayed());
	Assert.assertTrue(CancelButton.isEnabled());
	Assert.assertEquals(CancelButton.getText(), "Cancel");	
	csop.clickOnCancelBtn();
	Assert.assertEquals(login.currentURL(), "https://www.saucedemo.com/cart.html");
}
@Test(priority=29)
public void checkoutStepOnePageContinueBtnValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	cp.clickOnCheckout();		
	csop.enterChecoutDetails("aiswarya", "murali", "546155");
	WebElement ContinuButton = csop.checkoutContinueBtnVerification();
	Assert.assertTrue(ContinuButton.isDisplayed());
	Assert.assertTrue(ContinuButton.isEnabled());
	Assert.assertEquals(ContinuButton.getAttribute("value"),"Continue");
	csop.clickOnContinueBtn();
	Assert.assertEquals(login.currentURL(), "https://www.saucedemo.com/checkout-step-two.html");		 
}
@Test(priority=30)
public void checkoutStepOnePageContinueBtnWithoutDataValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	cp.clickOnCheckout();		
	csop.enterChecoutDetails("", "", "");
	WebElement ContinuButton = csop.checkoutContinueBtnVerification();
	Assert.assertTrue(ContinuButton.isDisplayed());
	Assert.assertTrue(ContinuButton.isEnabled());
	Assert.assertEquals(ContinuButton.getAttribute("value"),"Continue");
	csop.clickOnContinueBtn();
	Assert.assertEquals(login.errorMesgdisplay().getText(), "Error: First Name is required");
	Assert.assertEquals(login.currentURL(), "https://www.saucedemo.com/checkout-step-one.html");		 
}
@Test(priority=31)
public void checkoutStepOnePageFirstnameEmptyValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	cp.clickOnCheckout();
	csop.enterChecoutDetails("", "murali", "56734");
	csop.clickOnContinueBtn();
	Assert.assertEquals(login.errorMesgdisplay().getText(), "Error: First Name is required");
}
@Test(priority=32)
public void checkoutStepOnePageLastNameEmptyValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	cp.clickOnCheckout();
	csop.enterChecoutDetails("abc", "", "56743");
	csop.clickOnContinueBtn();
	Assert.assertEquals(login.errorMesgdisplay().getText(), "Error: Last Name is required");
}

@Test(priority = 33)
public void checkoutStepOnePagePostalCodeFieldEmptyValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	cp.clickOnCheckout();
	csop.enterChecoutDetails("abc", "ghi", "");
	csop.clickOnContinueBtn();
	Assert.assertEquals(login.errorMesgdisplay().getText(), "Error: Postal Code is required");
}
@Test(priority=34)
public void chekoutStepTwoPageValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	cp.clickOnCheckout();
	csop.enterChecoutDetails("aiswarya", "murali", "546155");
	csop.clickOnContinueBtn();
	Assert.assertEquals(cp.pageText(), "Checkout: Overview");
	Assert.assertEquals(cstp.paymentInformationText(), "Payment Information");
	Assert.assertEquals(cstp.paymentValue(), "SauceCard #31337");
	Assert.assertEquals(cstp.shippingInformation(), "Shipping Information");
	Assert.assertEquals(cstp.shippingValue(), "Free Pony Express Delivery!");
	Assert.assertEquals(cstp.totalPriceText(), "Price Total");
}
@Test(priority=35)
public void chekoutStepTwoPageCancelButtonValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	cp.clickOnCheckout();
	csop.enterChecoutDetails("aiswarya", "murali", "546155");
	csop.clickOnContinueBtn();
	WebElement CancelButton = cstp.cancelBtnVerification();
	Assert.assertTrue(CancelButton.isDisplayed());
	Assert.assertTrue(CancelButton.isEnabled());
	Assert.assertEquals(CancelButton.getText(), "Cancel");
	CancelButton.click();
	Assert.assertEquals(login.currentURL(), "https://www.saucedemo.com/inventory.html");
}
@Test(priority=36)
public void chekoutStepTwoPageFinishBtnValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	cp.clickOnCheckout();
	csop.enterChecoutDetails("asfdg", "sddgf", "546155");
	csop.clickOnContinueBtn();
	WebElement FinishButton = cstp.finishBtnVerification();
	Assert.assertTrue(FinishButton.isDisplayed());
	Assert.assertTrue(FinishButton.isEnabled());
	Assert.assertEquals(FinishButton.getText(), "Finish");
	FinishButton.click();
	Assert.assertEquals(login.currentURL(), "https://www.saucedemo.com/checkout-complete.html");				
}

@Test(priority=37)
public void checkoutStepTwoPageSubTotalValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user", "secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	cp.clickOnCheckout();
	csop.enterChecoutDetails("aiswarya", "murali", "546155");
	csop.clickOnContinueBtn();
	List<WebElement> Prices = cstp.priceList();
	float SubTotal = 0;
	for(int i = 0; i<Prices.size();i++) {
		Pattern p = Pattern.compile("[^0-9]*([0-9]+(\\.[0-9]*)?)");
	    Matcher m = p.matcher(Prices.get(i).getText());
	    m.matches();
	    String Price = m.group(1);
	    float d_num = Float.valueOf(Price);
	    SubTotal = SubTotal + d_num;
	}
	Assert.assertEquals(SubTotal, cstp.subTotalgValue());
}
@Test(priority=38)
public void checkoutCompletePageValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user","secret_sauce");
	login.clickOnLogin();
	inventory.clickOnMultipleProduct();
	cp.clickOnCartIcon();
	cp.clickOnCheckout();
	csop.enterChecoutDetails("aiswarya", "murali", "546155");
	csop.clickOnContinueBtn();
	cstp.clickOnFinishBtn();
	Assert.assertEquals(cp.pageText(), "Checkout: Complete!");
	Assert.assertEquals(ccp.thankYouText(), "Thank you for your order!");
	Assert.assertEquals(ccp.completeText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
	WebElement BackHomeBtn = ccp.BackHomeBtn();
	Assert.assertTrue(BackHomeBtn.isDisplayed());
	Assert.assertTrue(BackHomeBtn.isEnabled());
	Assert.assertEquals(BackHomeBtn.getText(), "Back Home");
	ccp.clickOnBackHomeBtn();
	Assert.assertEquals(login.currentURL(), "https://www.saucedemo.com/inventory.html");
}

@Test(priority=39)
public void footerLinksValidation() throws InterruptedException {
	login.enterUserNameAndPassword("standard_user","secret_sauce");
	login.clickOnLogin();
	List<WebElement> SocialNetworkLinks = inventory.presenceOfLinksVerification();
	Assert.assertEquals(SocialNetworkLinks.size(), 3);
	for(int i = 0;i<SocialNetworkLinks.size();i++) {
		System.out.println(SocialNetworkLinks.get(i).getText());			
	}		
	Assert.assertTrue(inventory.footerCopyText().contains("Terms of Service | Privacy Policy"));
}
}




