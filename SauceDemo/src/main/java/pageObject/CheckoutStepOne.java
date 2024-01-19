package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepOne {

	WebDriver driver;

	public CheckoutStepOne(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "first-name")
	private WebElement FirstName;
	@FindBy(id = "last-name")
	private WebElement LastName;
	@FindBy(id = "postal-code")
	private WebElement PostalCode;
	@FindBy(id = "cancel")
	private WebElement CancelButton;
	@FindBy(id = "continue")
	private WebElement ContinueButton;

	public WebElement firstname() {
		return FirstName;
	}

	public WebElement lastName() {
		return LastName;
	}

	public WebElement postalCode() {
		return PostalCode;
	}

	public void enterChecoutDetails(String Fn, String Ln, String postal) {
		FirstName.sendKeys(Fn);
		LastName.sendKeys(Ln);
		PostalCode.sendKeys(postal);
	}

	public WebElement checkoutContinueBtnVerification() {
		return ContinueButton;
	}

	public void clickOnContinueBtn() {
		ContinueButton.click();
	}

	public void clickOnCancelBtn() {
		CancelButton.click();
	}

	public WebElement checkoutCancelBtnVerification() {
		return CancelButton;
	}

}
