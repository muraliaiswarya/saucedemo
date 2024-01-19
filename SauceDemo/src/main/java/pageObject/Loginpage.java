package pageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
	WebDriver driver;

	public Loginpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "user-name")
	WebElement Username;
	@FindBy(name = "password")
	WebElement Password;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement Login;
	@FindBy(xpath = "//div[@class='login_logo']")
	WebElement LoginpageText;
	@FindBy(xpath = "//h3[@data-test='error']")
	WebElement errorMessage;

	public String currentURL() {
		return driver.getCurrentUrl();
	}

	public String loginPageText() {
		return LoginpageText.getText();
	}

	public WebElement errorMesgdisplay() {
		return errorMessage;
	}

	public WebElement userNameFieldVerification() {
		return Username;
	}

	public WebElement passWordFieldVerification() {
		return Password;
	}

	public WebElement loginButtonVerification() {
		return Login;
	}

	public void enterUserNameAndPassword(String un, String pw) {
		Username.sendKeys(un);
		Password.sendKeys(pw);
	}

	public void clickOnLogin() throws InterruptedException {
		Login.click();
		Thread.sleep(1000);
	}
	public void alertHandling() {
		Alert a = driver.switchTo().alert();
		System.out.println(a.getText());
		a.accept();
	}
}
