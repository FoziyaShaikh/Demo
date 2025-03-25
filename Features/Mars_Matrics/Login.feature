Feature: Login Functionality

@login
Scenario: Login Successful with valid credential

Given the user is on login page

When entered valid email and password 
    |username           |  password |
    |hj45@mailinator.com|B!dCl!ps123|
   
Then the user should be taken to home page



@login
Scenario:  Verify error message when logging with empty email and password

Given the user is on login page

When the user leaves the email and password field empty

Then the error message "Please Enter Username or Email" should be displayed



@login
Scenario: Verify error message when logging with an invalid password

Given the user is on login page

When entered valid email and invalid password 
    |valid_username           |  invalid_password |
    |hj45@mailinator.com      |   Cl!ps123        |
   
Then the error message "Bad Credentials!" should be displayed


