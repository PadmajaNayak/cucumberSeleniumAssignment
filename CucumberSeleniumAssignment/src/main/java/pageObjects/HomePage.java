package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import utilities.CommonFunctions;

public class HomePage {

	static WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Compose']")
	static WebElement composeLink;
	
	@FindBy(xpath = "//input[@class='select2-search__field']")
	static WebElement sendToTextBox;
	
	@FindBy(xpath = "//ul[@class='select2-results__options']/li[1]")
	static WebElement recipientName;
	
	@FindBy(xpath = "//input[@name='title']")
	static WebElement subjectTextBox;
	
	@FindBy(xpath = "//iframe[@class='cke_wysiwyg_frame cke_reset']")
	static WebElement messageiFrame;
	
	@FindBy(xpath = "//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
	static WebElement messageTestBox;
	
	@FindBy(id = "compose_message_compose")
	static WebElement sendMailBtn;
	
	@FindBy(xpath = "//div[@class='alert alert-success']")
	static WebElement successAlert;

	public void clickCompose() {
		CommonFunctions.waitUntilVisible(driver, composeLink, 60);
		composeLink.click();
	}

	public void composeMail(String recipient, String subject, String message) throws InterruptedException {
		CommonFunctions.waitUntilVisible(driver, sendToTextBox, 120);
		CommonFunctions.enterText(driver, sendToTextBox, recipient);
		Thread.sleep(3000);
		sendToTextBox.sendKeys(Keys.ENTER);
		CommonFunctions.enterText(driver, subjectTextBox, subject);
		Thread.sleep(5000);
		driver.switchTo().frame(messageiFrame);
		CommonFunctions.enterText(driver, messageTestBox, message);
		driver.switchTo().defaultContent();
	}
	
	public void clickSendMail() {
		CommonFunctions.moveToEle(driver, sendMailBtn);
		sendMailBtn.click();
	}

	public void verifyAcknowledgement() {
		try {
			CommonFunctions.waitUntilVisible(driver, successAlert, 120);
		}catch(Exception e) {
			Assert.fail();
		}
	}
}
