package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonFunctions;

public class SignUpPage {

	static WebDriver driver;

	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()=' Sign up! ']")
	static WebElement signUpBtn;

	@FindBy(id = "registration_firstname")
	static WebElement firstNameTextBox;

	@FindBy(id = "registration_lastname")
	static WebElement lastNameTextBox;

	@FindBy(id = "registration_email")
	static WebElement emailTextBox;
	
	@FindBy(id = "username")
	static WebElement userNameTextBox;

	@FindBy(id = "pass1")
	static WebElement passwordTextBox;
	
	@FindBy(id = "pass2")
	static WebElement confirmPasswordTextBox;
	
	@FindBy(id = "registration_submit")
	static WebElement submitBtn;
	
	public void clickOnSignup() {
		signUpBtn.click();
	}
	
	public void enterMandatoryFields(String firstName, String lastName, String email, String username, String pwd) {
		CommonFunctions.waitUntilVisible(driver, firstNameTextBox, 120);
		CommonFunctions.enterText(driver, firstNameTextBox, firstName);
		CommonFunctions.enterText(driver, lastNameTextBox, lastName);
		CommonFunctions.enterText(driver, emailTextBox, email);
		username = username + (int) (Math.random() * 100);
		CommonFunctions.enterText(driver, userNameTextBox, username);
		CommonFunctions.enterText(driver, passwordTextBox, pwd);
		CommonFunctions.enterText(driver, confirmPasswordTextBox, pwd);
	}
	
	public void clickOnRegister() {
		CommonFunctions.moveToElement(driver, submitBtn);
		submitBtn.click();
	}
	
}
