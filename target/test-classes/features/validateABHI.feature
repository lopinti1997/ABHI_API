Feature: Validating ABHI
		
@TC_001 @ALL
Scenario: 1.Verify Login functionality of ABHI is working
	  Given get the agent credentials from "TC_001" 
	  When user calls AgentLogin with POST http request from "TC_001"
	  Then verifying the AgentLogin status code as "200"
	  And store the Responce and Statuscode in to "TC_001"
		And verifying "Username" and "AgencyCode" in responce body as "TC_001"
		And verifying "Login Successful!" message from the responce body
	
@TC_002 @ALL
Scenario: 2.Verify get Products for Agency is working
		
		When user calls Get produts for an agent with GET http request from "TC_002"
   	Then verifying "ChannelName" and "Insurancetype" and "Agencyname" from responce body as "TC_002"
   	
@TC_003 @ALL
Scenario: 3.Verify Get Product List For Agency
		
		When user calls Get Product List For an Agency with GET http request from "TC_003"
   	Then verifying "ChannelName" and "Insurancetype" and "Agencyname" from responce body as "TC_003"
   	And verify the all ProdutList as expected or not
   	
 @TC_004 @ALL
 Scenario: 4.Verify Get Form Config For a Product
 
 		When user calls Get Form Config For a Product with GET http request from "TC_004" 
    Then Verifying ProductId in GetFormConfigForaProduct Responce body as we declared in to Query param from "TC_004"
 		And Verifying Insureformconfiguration data should not be Null in responce body
 		
 @TC_005 @ALL
 Scenario: 5.Verify Get Form From Form ID For a Product
 
 		When user calls Get Form From Form ID For a Product with GET http request from "TC_005" 
    Then Verifying ProductId in GetFormFromFormIDForaProduct Responce body as we declared in to Query param from "TC_005"
 		And Verifying Jsonformdata data should not be Null in responce body
 		
 @TC_006 @ALL
Scenario: 6.verify Insert or Update Form Data For A Journey.
	When user post rquest for Insert or Update Form Data For A Journey from "TC_006".
	Then Validate response body and store it excell from "TC_006".
	
@TC_007	@ALL
Scenario: 7.TC_007_Verify get pincode details request.
	When user send get pincode request from sheet "TC_007".
	Then validate response body.
	And Store response in sheet "TC_007".
	
@TC_008	@ALL
Scenario: 8.TC_008 verify post request for Insert Lead Details.
	When user sent post request for inserting lead details from sheet "TC_008".
	And Strore leadid and quoteId and proposalId from response body in the sheet from "TC_008".
	Then Validate successfull message from response body and Store the responce and status code in to excel "TC_008".
	