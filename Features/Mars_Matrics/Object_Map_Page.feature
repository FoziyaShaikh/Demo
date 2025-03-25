Feature: Object Map Page

Background:
	Given user is already logged in
	And Object Map page list page is opened
	
@S-12	
Scenario: Create Object Map
When user clicks on new button
And enter Object Name 
And Object Map Name
And click on save
Then it will open Define Object edit page
When save 
Then it should show that Object Name on list page