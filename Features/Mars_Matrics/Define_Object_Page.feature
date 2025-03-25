Feature: Define Object page

Background:
Given user is already logged in

@define_object
Scenario: verify Import spreadsheet For Metadata popup is open to enter metadata name and load file 

Given user on Define Object page
When click on the new button 
Then it should open that Import spreadsheet For Metadata page

@define_object
Scenario: verify Define Object page is open to add object details

Given user on Define Object page
When click on the new button 
And user enter metadata name and load file
    |Metadata_name|File_name          |
    |  Object     |Metadata file.xlsx |
    
Then it should open object page

@define_object
Scenario: Verify that the error message displayed for duplicate define object name   

Given user on Define Object page
When click on the new button 
And user enter exiting metadata name and load file
    |Metadata_name|File_name          |
    |  100        |Metadata file.xlsx |
    
Then error message "Metadata name must be unique." should be displayed

@define_object
Scenario: Create new Object metadata

Given user on Define Object page
When click on the new button 
And user enter metadata name and load file
    |Metadata_name|File_name          |
    |         0   |Metadata file.xlsx |
    
And following details enter on add define object page

|Calculation_for_Object_ID                   |A1:Id|
| 1st_Transaction_Description                |B1:Transaction_date|
|2nd_Transaction_Description                 |E1:Payment_type|
|Define_Calculation_for_Transaction_Date_Time|NOW()  |
|Define Priming Transactions                 |28/02/2025|
|Parent_Object_Name                          |100    |
|Define_Calculation_for_Parent_Object_ID     |A1:Id  |
    
Then it will create the Object with entered Object name 


@define_object
Scenario: Open Define Object page to edit

Given user is on home page
When user clicks on metrics menu
And clicks on Define Object menu
And click on the edit button of any object
Then it should open that object page

@define_object
Scenario: edit Object metadata

Given user on Define Object page
And user is on edit define object page

When following details enter on edit define object page

|Calculation_for_Object_ID                   |F1:Name|
| 1st_Transaction_Description                |E1:Payment_type|
|2nd_Transaction_Description                 |B1:Transaction_date|
|Define Priming Transactions                 |01/03/2025|


Then following details should be saved in the object 

|Calculation_for_Object_ID                   |F1:Name|
| 1st_Transaction_Description                |E1:Payment_type|
|2nd_Transaction_Description                 |B1:Transaction_date|
|Define Priming Transactions                 |03/01/2025|

@define_object
Scenario: Verify that an error message is displayed when the "Define Calculation for Object ID" field is left empty
Given user on Define Object page
When click on the new button 
And user enter metadata name and load file
    |Metadata_name|File_name          |
    |         0   |Metadata file.xlsx |
    
And Enter the following details for the defined object, except the field Define Calculation for Object ID

| 1st_Transaction_Description                |B1:Transaction_date|
|2nd_Transaction_Description                 |E1:Payment_type|
|Define_Calculation_for_Transaction_Date_Time|NOW()  |
|Define Priming Transactions                 |28/02/2025|
|Parent_Object_Name                          |100    |
|Define_Calculation_for_Parent_Object_ID     |A1:Id  |
    
Then error message "This field is required." should be displayed


@define_object
Scenario: Verify that an error message is displayed when the "1st Transaction Description" field is left empty
Given user on Define Object page
When click on the new button 
And user enter metadata name and load file
    |Metadata_name|File_name          |
    |         0   |Metadata file.xlsx |
    
And Enter the following details for the defined object, except the field 1st Transaction Description

|Calculation_for_Object_ID                   |A1:Id|
|2nd_Transaction_Description                 |E1:Payment_type|
|Define_Calculation_for_Transaction_Date_Time|NOW()  |
|Define Priming Transactions                 |28/02/2025|
|Parent_Object_Name                          |100    |
|Define_Calculation_for_Parent_Object_ID     |A1:Id  |
    
Then error message "This field is required." should be displayed

@define_object
Scenario: Verify that an error message is displayed when the "Define Calculation for Transaction Date Time" field is left empty
Given user on Define Object page
When click on the new button 
And user enter metadata name and load file
    |Metadata_name|File_name          |
    |         0   |Metadata file.xlsx |
    
And Enter the following details for the defined object, except the field Define Calculation for Transaction Date Time

|Calculation_for_Object_ID                   |A1:Id|
| 1st_Transaction_Description                |B1:Transaction_date|
|2nd_Transaction_Description                 |E1:Payment_type|
|Define Priming Transactions                 |28/02/2025|
|Parent_Object_Name                          |100    |
|Define_Calculation_for_Parent_Object_ID     |A1:Id  |
    
Then error message "This field is required.." should be displayed

@define_object
Scenario: Verify that an error message is displayed when the "Define Priming Transactions" field is left empty
Given user on Define Object page
When click on the new button 
And user enter metadata name and load file
    |Metadata_name|File_name          |
    |         0   |Metadata file.xlsx |
    
And Enter the following details for the defined object, except the field Define Priming Transactions

|Calculation_for_Object_ID                   |A1:Id|
| 1st_Transaction_Description                |B1:Transaction_date|
|2nd_Transaction_Description                 |E1:Payment_type|
|Define_Calculation_for_Transaction_Date_Time|NOW()  |
|Parent_Object_Name                          |100    |
|Define_Calculation_for_Parent_Object_ID     |A1:Id  |
    
Then error message "Please select priming date." should be displayed

@define_object
Scenario: Verify that an error message is displayed when the "Field Containing associate source system ID" field is left empty
Given user on Define Object page
When click on the new button 
And user enter metadata name and load file
    |Metadata_name|File_name          |
    |         0   |Metadata file.xlsx |
    
And Enter the following details for the defined object, except the field Field Containing associate source system ID

|Calculation_for_Object_ID                   |A1:Id|
| 1st_Transaction_Description                |B1:Transaction_date|
|2nd_Transaction_Description                 |E1:Payment_type|
|Define_Calculation_for_Transaction_Date_Time|NOW()  |
|Define Priming Transactions                 |28/02/2025|
|Parent_Object_Name                          |100       |
|Define_Calculation_for_Parent_Object_ID     |A1:Id      |

    
Then error message "This field is required." should be displayed

@define_object
Scenario: Verify that an error message is displayed when the "Parent Object Description - Line 1" field is left empty
Given user on Define Object page
When click on the new button 
And user enter metadata name and load file
    |Metadata_name|File_name          |
    |         0   |Metadata file.xlsx |
    
And Enter the following details for the defined object, except the field Parent Object Description Line 1

|Calculation_for_Object_ID                   |A1:Id|
| 1st_Transaction_Description                |B1:Transaction_date|
|2nd_Transaction_Description                 |E1:Payment_type|
|Define_Calculation_for_Transaction_Date_Time|NOW()  |
|Define Priming Transactions                 |28/02/2025|
|Parent_Object_Name                          |parent object   | 
|Define_Calculation_for_Parent_Object_ID     |abdc      |
    
Then error message "This field is required." should be displayed

@define_object
Scenario: Verify that an error message is displayed when select existing parent object name and the "Define calculation for Parent Object Id" field is left empty
Given user on Define Object page
When click on the new button 
And user enter metadata name and load file
    |Metadata_name|File_name          |
    |         0   |Metadata file.xlsx |
    
And Enter the following details for the defined object, except the field Define calculation for Parent Object Id

|Calculation_for_Object_ID                   |A1:Id|
| 1st_Transaction_Description                |B1:Transaction_date|
|2nd_Transaction_Description                 |E1:Payment_type|
|Define_Calculation_for_Transaction_Date_Time|NOW()  |
|Define Priming Transactions                 |28/02/2025|
|Parent_Object_Name                          |100   | 

    
Then error message "This field is required." should be displayed


@define_object
Scenario: Verify that an error message is displayed when select non existing parent object name and the "Define calculation for Parent Object Id" field is left empty
Given user on Define Object page
When click on the new button 
And user enter metadata name and load file
    |Metadata_name|File_name          |
    |         0   |Metadata file.xlsx |
    
And Enter the following details for the defined object and select non existing parent object name and Define calculation for Parent Object Id field is left empty

|Calculation_for_Object_ID                   |A1:Id|
| 1st_Transaction_Description                |B1:Transaction_date|
|2nd_Transaction_Description                 |E1:Payment_type|
|Define_Calculation_for_Transaction_Date_Time|NOW()  |
|Define Priming Transactions                 |28/02/2025|
|Parent_Object_Name                          |parent object   | 

    
Then error message "This field is required." should be displayed

@define_object
Scenario: delete Define Object 
Given user is on home page
When user clicks on metrics menu
And clicks on Define Object menu
And click on the delete button of any object
Then that object should be delete from object list


@define_object
Scenario: Delete object metadata (only Object Map exists) 
Given user is on home page
When user clicks on metrics menu
And clicks on Define Object menu
And click on the delete button of "66" object
Then user cannot delete object metatda and message "Sorry, can not delete !... Object Map for this metadata exists" should be displayed on popup


@define_object
Scenario: Delete object metadata (only transaction exists) 
Given user is on home page
When user clicks on metrics menu
And clicks on Define Object menu
And click on the delete button of "33" object
Then user cannot delete object metatda and message "Sorry, can not delete !... Transaction for this metadata exists" should be displayed on popup

@define_object
Scenario: Delete object metadata then calcel  
Given user is on home page
When user clicks on metrics menu
And clicks on Define Object menu
And click on the delete button of "55" object
And click on cancel button
Then define object "55" shoud not be delete


@define_object
Scenario: Open Define Object page to edit then enter new object name and cancel

Given user on Define Object page
When click on the edit icon button of "100" object
And enter object name "New Object"
And click on cancel button
Then entered object name should not be saved 

@define_object
Scenario: Verify Formula for javascript toggle button is available for Object Id, transaction date time and Parent Object Id calculation.
Given user on Define Object page
When click on the new button 
And user enter metadata name and load file
    |Metadata_name|File_name          |
    |         0   |Metadata file.xlsx |
    
Then toggle button should be available for Object Id, transaction date time and Parent Object Id calculation.


@define_object
Scenario: Verify If yes option is selected for Commission is split between multiple associates then one percentage column should be there and user can add max 5 column
Given user on Define Object page
And user is on add define object page
When Commission is split between multiple associates is selected as yes
Then one percentage column should be there
And user can add only five percentage row
And percentage name should be unique
When user delete percentage row using cross icon
Then one percentage column should be there



@define_object1
Scenario: Reject Whole file for record which is duplicate. (checkbox) - selected
Given user on Define Object page
And user is on add define object page
When User not select checkbox of the Reject file if duplicate records are found
And user import file for transation and load file
   |File_name          |
   |Metadata file.xlsx |
And user import file with some of the duplicate and new record for transation and load file
   |File_name          |
   |Metadata_reject_file.xlsx |
Then following status should be there for new record and duplicate record
 |Object_id |   Status  |
 |12        |  Pending  |
 |11        |  Pending  |
 |10        |  Pending  |
 |9         |  Pending  |
 |8         |  Duplicate|
 |3         |  Duplicate|
 |2         |  Duplicate|
 |1         |  Duplicate|


@S-29.6.2
Scenario: Upload spreadsheet with header but empty values in column
Given user on Define Object page
When click on the new button
And user enter metadata name and load file
|Metadata_name|File_name            |
|         0   |Metadata23 file.xlsx |
But no data for some headers
Then it should get uploaded without any error message
And it should open object page
























