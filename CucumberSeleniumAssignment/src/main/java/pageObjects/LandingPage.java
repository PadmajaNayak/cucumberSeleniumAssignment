package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import utilities.CommonFunctions;

public class LandingPage {

	static WebDriver driver;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "cm-content")
	static WebElement messageArea;
	
	@FindBy(xpath="//a[@class='dropdown-toggle']")
	static WebElement userIconDropdown;
	
	@FindBy(xpath="//i[@class='fa fa-envelope-o']/parent::p")
	static WebElement emailIdHolder;
	
	@FindBy(xpath = "//a[@title='Homepage']")
	static WebElement homePageHeader;

	public void verifyMessage(String firstName, String lastName, String message) {
		CommonFunctions.waitUntilVisible(driver, messageArea, 120);
		boolean actual =messageArea.getText().contains("Dear "+firstName+" "+lastName) && messageArea.getText().contains(message);
		Assert.assertEquals(true, actual);
	}
	
	public void verifyEmailId(String email) {
		userIconDropdown.click();
		Assert.assertEquals(true, emailIdHolder.getText().contains(email));
	}
	
	public void navigateToHomePage() {
		homePageHeader.click();
	}
}
