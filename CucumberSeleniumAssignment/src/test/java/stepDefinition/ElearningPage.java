package stepDefinition;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import pageObjects.SignUpPage;
import pageObjects.HomePage;
import utilities.DriverUtility;

public class ElearningPage {
	WebDriver driver;
	String url = "http://elearningm1.upskills.in/";
	SignUpPage signupPage;
	LandingPage landingPage;
	HomePage homePage;

	@Given("I am on the login page of the application on {string}")
	public void i_am_on_the_login_page_of_the_application(String browserName) {
		driver = DriverUtility.setDriver(browserName);
		driver.get(url);
	}

	@When("I try to sign up to the application")
	public void i_try_to_sign_up_to_the_application() {
		signupPage = new SignUpPage(driver);
		signupPage.clickOnSignup();
	}

	@When("I enter mandatory fields {string}, {string}, {string}, {string}, {string}")
	public void i_enter_mandatory_fields(String firstName, String lastName, String email, String username, String pwd) {
		signupPage.enterMandatoryFields(firstName, lastName, email, username, pwd);
	}

	@When("I click on register button")
	public void i_click_on_register_btn() {
		signupPage.clickOnRegister();
	}

	@Then("I should see a confirmation message with {string}, {string}, {string}")
	public void i_should_see_a_confirmation_message(String firstName, String lastName, String email) {
		landingPage = new LandingPage(driver);
		landingPage.verifyMessage(firstName, lastName, email);
	}

	@Given("I should see my email {string} on the user icon dropdown")
	public void i_should_see_my_email_on_the_user_icon_dropdown(String email) {
		landingPage.verifyEmailId(email);
	}

	@Given("I navigate to home page of the application")
	public void i_navigate_to_home_page_of_the_application() {
		landingPage.navigateToHomePage();
		homePage = new HomePage(driver);
	}

	@When("I click on compose option")
	public void i_click_on_compose_option() {
		homePage.clickCompose();
	}

	@When("I enter {string}, {string}, {string}")
	public void i_enter(String recipient, String subject, String message) throws InterruptedException {
		homePage.composeMail(recipient,subject,message);
	}

	@When("I click on send message button")
	public void i_click_on_send_message_button() {
		homePage.clickSendMail();
	}

	@Then("I should see the acknowledgement")
	public void i_should_see_the_acknowledgement() {
		homePage.verifyAcknowledgement();
		driver.quit();
	}

}
