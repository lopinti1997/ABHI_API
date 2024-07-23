package stepDefinations;

import extentReports.ExtentReportUtil;
import io.cucumber.java.en.*;
import readData.ReadData;
import resources.TestDataBuild2;

public class StepDefination {

	TestDataBuild2 td = new TestDataBuild2();

	// TC_001
	@Given("get the agent credentials from {string}")
	public void get_the_agent_credentials_from(String testflow) {
		ExtentReportUtil.logInfo("STEP::: get the agent credentials from " + testflow);

		td.getTheAgentCredentials(testflow);
	}

	@When("user calls AgentLogin with POST http request from {string}")
	public void user_calls_agent_login_with_post_http_request_from(String testflow) {
		ExtentReportUtil.logInfo("STEP::: user calls AgentLogin with POST http request from " + testflow);

		td.agentLoginWithPOST_HttpRequest(testflow);
	}

	@Then("verifying the AgentLogin status code as {string}")
	public void verifying_the_agent_login_status_code_as(String testflow) {
		ExtentReportUtil.logInfo("STEP::: verifying the AgentLogin status code as " + testflow);

		td.validatingSuccessCode(testflow);
	}

	@Then("store the Responce and Statuscode in to {string}")
	public void store_the_responce_and_statuscode_in_to(String testflow) {
		ExtentReportUtil.logInfo("STEP::: store the Responce and Statuscode in to " + testflow);

		td.storeTheResponceAndStatusCodeInToExcel(testflow);
	}

	@Then("verifying {string} and {string} in responce body as {string}")
	public void verifying_and_in_responce_body_as(String expusername, String expAgencyCode, String testflow) {

		ExtentReportUtil.logInfo("STEP::: verifying " + expusername + " and " + expAgencyCode + " in responce body as "
				+ testflow + " " + testflow);

		expusername = ReadData.getCellData("LoginData", testflow, "AgentUsername");
		expAgencyCode = ReadData.getCellData("TestData", testflow, "AgencyCode");
		td.verifyingAgentUserName_and_AgenceyCode(expusername, expAgencyCode);

	}

	@Then("verifying {string} message from the responce body")
	public void verifying_message_from_the_responce_body(String loginMessage) {
		ExtentReportUtil.logInfo("STEP::: verifying " + loginMessage + " message from the responce body");

		td.verifyingLoginSuccessMessage(loginMessage);
	}

	// TC_002
	@When("user calls Get produts for an agent with GET http request from {string}")
	public void user_calls_get_produts_for_an_agent_with_get_http_request_from(String testflow) {
		td.call_GetProducts_ForAgency_http_request(testflow);

	}

	@When("verifying {string} and {string} and {string} from responce body as {string}")
	public void verifying_and_and_from_responce_body_as(String expchannelName, String expInsurancetype,
			String expAgencyname, String testflow) {

		expchannelName = ReadData.getCellData("TestData", testflow, "ChannelName");
		expInsurancetype = ReadData.getCellData("TestData", testflow, "Insurancetype");
		expAgencyname = ReadData.getCellData("TestData", testflow, "Agencyname");

		td.verifyingChannelName_And_InsurenceType_And_Agencyname_from_Responce_body(expchannelName, expInsurancetype,
				expAgencyname);
	}

	// TC_003
	@When("user calls Get Product List For an Agency with GET http request from {string}")
	public void user_calls_get_product_list_for_an_agency_with_get_http_request_from(String testflow) {
		td.call_GetProducts_ForAgency_http_request(testflow);
	}

	@When("verify the all ProdutList as expected or not")
	public void verify_the_all_produt_list_as_expected_or_not() {
		td.verityingProductListAsExpectedOrNot();
	}

	// TC_004
	@When("user calls Get Form Config For a Product with GET http request from {string}")
	public void user_calls_get_form_config_for_a_product_with_get_http_request_from(String testflow) {
		td.callsGet_form_config_for_a_product_with_get_http_request_from(testflow);
	}

	@Then("Verifying ProductId in GetFormConfigForaProduct Responce body as we declared in to Query param from {string}")
	public void verifying_product_id_in_get_form_config_fora_product_responce_body_as_we_declared_in_to_query_param_from(
			String testflow) {
		td.verifyingProductId_inGetFormConfigForaProductResponceBody_as_we_declared_in_to_query_param(testflow);
	}

	@Then("Verifying Insureformconfiguration data should not be Null in responce body")
	public void verifying_insureformconfiguration_data_should_not_be_null_in_responce_body() {
		td.verifyingInsureformconfigurationData_in_responce_body();
	}

	// TC_005
	@When("user calls Get Form From Form ID For a Product with GET http request from {string}")
	public void user_calls_get_form_from_form_id_for_a_product_with_get_http_request_from(String string) {
		td.send_GetHttpRequestFor_GetFormFromFormId_for_a_product(string);
	}

	@Then("Verifying ProductId in GetFormFromFormIDForaProduct Responce body as we declared in to Query param from {string}")
	public void verifying_product_id_in_get_form_from_form_id_fora_product_responce_body_as_we_declared_in_to_query_param_from(
			String testflow) {
		td.verifyingProductId_inGetFormFromFormIDForaProductResponceBody_as_we_declared_in_to_query_param(testflow);
	}

	@Then("Verifying Jsonformdata data should not be Null in responce body")
	public void verifying_jsonformdata_data_should_not_be_null_in_responce_body() {
		td.verifyingJsonformdataData_should_not_be_null_in_responce_body();
	}

//	TC_006
	@When("user post rquest for Insert or Update Form Data For A Journey from {string}.")
	public void user_post_rquest_for_insert_or_update_form_data_for_a_journey_from(String testFlow) {

		td.user_post_rquest_for_insert_or_update_form_data_for_a_journey(testFlow);
	}

	@When("Validate response body and store it excell from {string}.")
	public void validate_response_body_and_store_it_excell_from(String testflow) {
		td.store_Config_Response(testflow);

	}

//	TC_007
	@When("user send get pincode request from sheet {string}.")
	public void user_send_get_pincode_request_from_sheet(String testflow) {
		td.sendGetPicodeDetails(testflow);

	}

	@When("validate response body.")
	public void validate_response_body() {

		td.validatePincodeResponse();
	}

	@When("Store response in sheet {string}.")
	public void store_response_in_sheet(String testflow) {
		td.storePinCodeResponseinSheet(testflow);

	}

//	TC_008
	@When("user sent post request for inserting lead details from sheet {string}.")
	public void user_sent_post_request_for_inserting_lead_details_from_sheet(String testflow) {
		td.sendPostRequestForInsertLeadDetails(testflow);

	}

	@When("Strore leadid and quoteId and proposalId from response body in the sheet from {string}.")
	public void strore_leadid_and_quote_id_and_proposal_id_from_response_body_in_the_sheet_from(String testflow) {
		td.storeLeadIDResponseInSheet(testflow);

	}

	@Then("Validate successfull message from response body and Store the responce and status code in to excel {string}.")
	public void validate_successfull_message_from_response_body_and_store_the_responce_and_status_code_in_to_excel(
			String testflow) {
		td.validateleadIDResponseBody(testflow);
	}

}
