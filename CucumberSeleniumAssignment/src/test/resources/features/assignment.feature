Feature: verify elearning application

Scenario Outline: Verify user is able to sign up 

    Given I am on the login page of the application on "<browser>"
    When I try to sign up to the application
    And I enter mandatory fields "<firstName>", "<lastName>", "<email>", "<username>", "<password>"
    And I click on register button
    Then I should see a confirmation message with "<firstName>", "<lastName>", "<registrationMessage>"
    And I should see my email "<email>" on the user icon dropdown
    And I navigate to home page of the application
		And I click on compose option
		And I enter "<recipient>", "<subject>", "<composeMessage>"
		And I click on send message button
		And I should see the acknowledgement

    Examples: 
    |browser	| firstName | lastName | email             | username | password | registrationMessage 												|recipient|subject				|composeMessage																							|
    |Chrome		| test      | name     | testmail@mail.com |testuser	|admin123	|Your personal settings have been registered.	|naveen		|Hi for Testing	|Hello Mr. Naveen,\n This is for Testing\n\nFrom,\nNaveen1	|