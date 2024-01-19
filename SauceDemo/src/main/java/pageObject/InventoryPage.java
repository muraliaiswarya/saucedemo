package pageObject;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {
	WebDriver driver;

	public InventoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='app_logo']")
	private WebElement InventoryPageTitle;
	@FindBy(xpath = "//span[@class='title']")
	WebElement pageTitle;
	@FindBy(xpath = "//div[@class='inventory_list']/div")
	List<WebElement> inventoryList;
	@FindBy(xpath = "//div[@class='inventory_item_img']/a/img")
	List<WebElement> ProductImages;
	@FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory ']")
	List<WebElement> AddtocartButtonList;
	@FindBy(xpath = "//button[@id='react-burger-menu-btn']")
	WebElement hamburgericon;
	@FindBy(xpath = "//nav[@class='bm-item-list']/a")
	List<WebElement> hamburgerList;
	@FindBy(xpath = "//nav[@class='bm-item-list']/a[1]")
	WebElement hamburgerAllItems;
	@FindBy(xpath = "//nav[@class='bm-item-list']/a[2]")
	WebElement hamburgerAbout;
	@FindBy(id = "logout_sidebar_link")
	WebElement hamburgerLogout;
	@FindBy(xpath = "//nav[@class='bm-item-list']/a[4]")
	WebElement hamburgerResetAppState;
	@FindBy(id = "react-burger-cross-btn")
	private WebElement CloseIcon;
	@FindBy(xpath = "//select[@class='product_sort_container']")
	private WebElement DropDown;
	@FindBy(xpath = "//div[@class='inventory_details_desc_container']/button")
	private WebElement AddtocartButton;
	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	private WebElement CartIcon;
	@FindBy(xpath = "//a[@class='shopping_cart_link']/span")
	private WebElement shoppingCartContent;
	@FindBy(xpath = "//div[@id='contents_wrapper']/div/div[2]/div/button")
	private WebElement BackTOProductBtn;
	@FindBy(xpath = "//select[@class='product_sort_container']/option")
	private List<WebElement> DropDownList;
	@FindBy(xpath = "//div[@class='inventory_item_name ']")
	private List<WebElement> AlphabitcalNameAtoz;
	@FindBy(xpath = "//div[@class='inventory_list']/div/div[2]/div[1]/a[1]/div[1]")
	private List<WebElement> ProductNames;
	@FindBy(xpath = "//div[@class='inventory_list']/div/div[2]/div[2]/div[1]")
	private List<WebElement> ProductPrices;
	@FindBy(xpath = "//ul[@class='social']/li") private List<WebElement> NetWorkLinks;
	@FindBy(xpath = "//div[@class='footer_copy']") private WebElement FooterCopy;
	
	
	
	public String Pagetitle() {

		return pageTitle.getText();
	}

	public String inventoryPageTitle() {

		return InventoryPageTitle.getText();

	}

	public List<WebElement> HamburgerMenuListVerification() {
		return hamburgerList;
	}

	public WebElement hamburgerMenuVerification() {
		return hamburgericon;
	}

	public void clickOnHamburgericon() {
		hamburgericon.click();
	}

	public void clickOnHamburgerLogout() {
		hamburgerLogout.click();
	}

	public void clickOnHamburgerAbout() {
		hamburgerAbout.click();
	}

	public List<WebElement> inventoryProductList() {
		return inventoryList;
	}

	public void clickOnHamburgerAllitems() {
		hamburgerAllItems.click();
	}

	public List<WebElement> inventoryProductimage() {
		return ProductImages;
	}

	public WebElement hamburgerRestAppState() {
		return hamburgerResetAppState;
	}

	public WebElement addtoCartButtonPresence() {
		Random r = new Random();
		return AddtocartButtonList.get(r.nextInt(AddtocartButtonList.size()));
	}

	public void addtoCartButtonVerification() throws InterruptedException {
		Random r = new Random();
		for (int i = 1; i <= 3; i++) {
			AddtocartButtonList.get(r.nextInt(AddtocartButtonList.size())).click();
			Thread.sleep(500);
			System.out.println("The shopping cart content when clicked on " + i + " products it is: "
					+ shoppingCartContent.getText());
		}
	}

	public void clickOnCartIcon() {
		CartIcon.click();
	}

	public String cartIconContent() {
		return shoppingCartContent.getText();
	}

	public void clickOnAnyProduct() {
		Random rand = new Random();
		ProductImages.get(rand.nextInt(ProductImages.size())).click();
	}

	public void clickOnAddtoCart() {
		AddtocartButton.click();
	}

	public String cartContent() {
		return shoppingCartContent.getText();
	}

	public WebElement addToCart() {
		return AddtocartButton;
	}

	public WebElement backToProducts() {
		return BackTOProductBtn;
	}

	public void clickOnBackToProduct() {
		BackTOProductBtn.click();
	}

	public void ClickOnCloseIcon() {
		CloseIcon.click();
	}

	public List<WebElement> dropDownListVerification() {

		return DropDownList;
	}

	public List<WebElement> productNames() {

		return ProductNames;
	}

	public WebElement selectNameAtoZ() {
		Select s = new Select(DropDown);
		s.selectByVisibleText("Name (A to Z)");
		return DropDown;
	}

	public WebElement selectNameZtOA() {
		Select s = new Select(DropDown);
		DropDown.click();
		s.selectByVisibleText("Name (Z to A)");
		return DropDown;
}

	public List<WebElement> productPrices() {
		return ProductPrices;
	}
	public void selectPriceLowtoHigh() {
		Select s= new Select(DropDown);
		DropDown.click();
		s.selectByVisibleText("Price (low to high)");
	}
	public void selectPriceHightoLow() {
		Select s= new Select(DropDown);
		DropDown.click();
		s.selectByVisibleText("Price (high to low)");
	}

	public void clickOnMultipleProduct() throws InterruptedException {
			Random rand = new Random();
			for(int i=0;i<3;i++) {
			AddtocartButtonList.get(rand.nextInt(AddtocartButtonList.size())).click();
			Thread.sleep(500);
			}
		}

	public WebElement cartIconPresence() {
		return CartIcon;
	
	}
	
	
	public String footerCopyText() {
		return FooterCopy.getText();
	}

	public List<WebElement> presenceOfLinksVerification() {
		return NetWorkLinks;
}
}
		
	
