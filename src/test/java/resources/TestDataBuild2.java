package resources;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Assert;
import extentReports.ExtentReportUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import readData.ReadData;


public class TestDataBuild2 {

	RequestSpecification agentCredintials_RS;
	RequestSpecification getProductsForAgency_RS;
	Response login_Responce = null;
	Response getProductsForAgency_Responce = null;
	Response getFormConfigForaProductRes = null;
	Response getFormFromFormIdForProd_Res = null;
	Response inser_form_data_response=null;
	Response get_Pincode_Details_Res=null;
	Response insert_Lead_Details_Res=null;

	
	// TC_001
	public RequestSpecification getTheAgentCredentials(String testflow) {
		return agentCredintials_RS;
	}

	public Response agentLoginWithPOST_HttpRequest(String testflow) {

		login_Responce = Utils.getHttpRequest("TestData", testflow);

		return login_Responce;
	}

	public void validatingSuccessCode(String statusCode) {

		String actualStatuscode = String.valueOf(login_Responce.getStatusCode());
//		System.out.println("actualStatuscode Is ::: " + actualStatuscode);
		Assert.assertEquals(statusCode, actualStatuscode);
	}

	public void storeTheResponceAndStatusCodeInToExcel(String testflow) {
		String loginResponce = login_Responce.asString();
		ReadData.writeDataIntoCell("TestData", testflow, "Responce", loginResponce);
		
		String loginResCode = login_Responce.getStatusLine();
		loginResCode = loginResCode.split(" ")[1] + " " + loginResCode.split(" ")[2];
		ReadData.writeDataIntoCell("TestData", testflow, "Status Code", loginResCode);
		
		ExtentReportUtil.logInfo("SuccessFully Captured Statuscode :::" + loginResCode);
		ExtentReportUtil.logInfo("SuccessFully Captured Responce :::" + loginResponce);		
	}

	public void verifyingAgentUserName_and_AgenceyCode(String expUsername, String expAgencyCode) {

//		System.out.println(login_Responce.asString());
		JsonPath js = new JsonPath(login_Responce.asString());
		String actualAgencyCode = js.getString("agencyCode");
		String actualAgentUsername = js.getString("userName");

		Assert.assertEquals(expUsername, actualAgentUsername);
		Assert.assertEquals(expAgencyCode, actualAgencyCode);
		
		ExtentReportUtil.logPass("Successfully varified Agent username and Agencey code");
	}

	public void verifyingLoginSuccessMessage(String expMessage) {

		JsonPath js = new JsonPath(login_Responce.asString());
		String actualLoginMessage = js.getString("message");

		Assert.assertEquals(expMessage, actualLoginMessage);
			
		ExtentReportUtil.logPass("Successfully varified Login Success message as:::"+actualLoginMessage);
//		ExtentReportUtil.flushReports();
	}

	// TC_002
	public void call_GetProducts_ForAgency_http_request(String testflow) {
		
		getProductsForAgency_Responce = Utils.getHttpRequest("TestData", testflow);
		String getProductsResponce = getProductsForAgency_Responce.asString();
		ReadData.writeDataIntoCell("TestData", testflow, "Responce", getProductsResponce);
		
		String getProductsResCode = getProductsForAgency_Responce.getStatusLine();
		getProductsResCode = getProductsResCode.split(" ")[1] + " " + getProductsResCode.split(" ")[2];
		ReadData.writeDataIntoCell("TestData", testflow, "Status Code", getProductsResCode);

		ExtentReportUtil.logInfo("SuccessFully Captured Statuscode :::" + getProductsResCode);
		ExtentReportUtil.logInfo("SuccessFully Captured Responce :::" + getProductsResponce);				
	}

	public void verifyingChannelName_And_InsurenceType_And_Agencyname_from_Responce_body(String expChannelName,
			String expInsurenceName, String expAgencyName) {

		JsonPath js = new JsonPath(getProductsForAgency_Responce.asString());

		String actualChannelName = js.getString("channel_name[0]");
//		System.out.println(actualChannelName);
		String actualInsurancetype = js.getString("insurancetype[0]");
//		System.out.println(actualInsurancetype);
		String actualAgencyname = js.getString("agencyname[0]");
//		System.out.println(actualAgencyname);

		Assert.assertEquals(expChannelName, actualChannelName);
		Assert.assertEquals(expInsurenceName, actualInsurancetype);
		Assert.assertEquals(expAgencyName, actualAgencyname);
		
		ExtentReportUtil.logPass("Successfully varified ChannelName & InsurenceName & AgencyName");
	}

	//TC_003
	public void verityingProductListAsExpectedOrNot() {
		
		JsonPath js = new JsonPath(getProductsForAgency_Responce.asString());
		int responceSize = js.getInt("size()");
//		System.err.println("Responce Size is ::: " + responceSize);

		Map<String, String> actualProductList = new HashMap<>();

		for (int i = 0; i < responceSize; i++) {
			String prodId = js.getString("productid[" + i + "]");
			String prodName = js.getString("productname[" + i + "]");
			actualProductList.put(prodId, prodName);
		}

		// From Excel
		String productList[][] = ReadData.getSheetData("ProductList");
		Map<String, String> expectedProductList = new HashMap<>();

		for (String[] pair : productList) {
			if (pair.length >= 2) { // Ensure each pair has at least two elements (key and value)
				expectedProductList.put(pair[0], pair[1]);
			} else {
				// Handle invalid data or log a warning if necessary
				System.out.println("Invalid pair: " + Arrays.toString(pair));
			}
		}

		// Sorting & Comparing Two Maps

		TreeMap<String, String> sortedActualProductList = new TreeMap<>(actualProductList);
		TreeMap<String, String> sortedexpEctedProductList = new TreeMap<>(expectedProductList);

		Assert.assertTrue("Test Case Failed Because of Product List is not as expected",
				sortedActualProductList.equals(sortedexpEctedProductList));
		
		ExtentReportUtil.logPass("Successfully varified Products List");
	}

	// TC_004
	public void callsGet_form_config_for_a_product_with_get_http_request_from(String testflow) {
		getFormConfigForaProductRes = Utils.getHttpRequest("TestData", testflow);
		String getFormConfigForaProductResValue = getFormConfigForaProductRes.asString();
		ReadData.writeDataIntoCell("TestData", testflow, "Responce", getFormConfigForaProductResValue);

		String getResCode = getFormConfigForaProductRes.getStatusLine();
		getResCode = getResCode.split(" ")[1] + " " + getResCode.split(" ")[2];
		ReadData.writeDataIntoCell("TestData", testflow, "Status Code", getResCode);
		
		ExtentReportUtil.logInfo("SuccessFully Captured Statuscode :::" + getResCode);
		ExtentReportUtil.logInfo("SuccessFully Captured Responce :::" + getFormConfigForaProductResValue);	
	}

	public void verifyingProductId_inGetFormConfigForaProductResponceBody_as_we_declared_in_to_query_param(String testflow) {

		Map<String, String> queryParams = Utils.getQueryParamsFromURL("TestData", testflow);
		String expectedProdId = queryParams.get("productId");
//		System.err.println("Expected Product Id is ::: " + expectedProdId);
		String getFormConfigForaProductResponce = getFormConfigForaProductRes.asString();
		JsonPath js = new JsonPath(getFormConfigForaProductResponce);
		String actualProdId = js.getString("productid");
//		System.err.println("Actual Product Id ::: " + actualProdId);
		Assert.assertEquals(expectedProdId, actualProdId);
		
		ExtentReportUtil.logPass("Successfully varified Product ID's");
	}

	public void verifyingInsureformconfigurationData_in_responce_body() {
		String getFormConfigForaProductResponce = getFormConfigForaProductRes.asString();
		JsonPath js = new JsonPath(getFormConfigForaProductResponce);
		String insureformconfiguration = js.getString("insureformconfiguration");
//		System.err.println("insureformconfiguration Res Value ::: " + insureformconfiguration);
		Assert.assertTrue(insureformconfiguration != null);
		
		ExtentReportUtil.logPass("Successfully varified InsureFormConfig Data from response body");
	}

	// TC_005
	public void send_GetHttpRequestFor_GetFormFromFormId_for_a_product(String testflow) {
		getFormFromFormIdForProd_Res = Utils.getHttpRequest("TestData", testflow);
		String getFormFromFormIdForProd_ResValue = getFormFromFormIdForProd_Res.asString();
		ReadData.writeDataIntoCell("TestData", testflow, "Responce", getFormFromFormIdForProd_ResValue);

		String getResCode = getFormFromFormIdForProd_Res.getStatusLine();
		getResCode = getResCode.split(" ")[1] + " " + getResCode.split(" ")[2];
		ReadData.writeDataIntoCell("TestData", testflow, "Status Code", getResCode);

		ExtentReportUtil.logInfo("SuccessFully Captured Statuscode :::" + getResCode);
		ExtentReportUtil.logInfo("SuccessFully Captured Responce :::" + getFormFromFormIdForProd_ResValue);	
	}

	public void verifyingProductId_inGetFormFromFormIDForaProductResponceBody_as_we_declared_in_to_query_param(String testflow) {

		Map<String, String> queryParams = Utils.getQueryParamsFromURL("TestData", testflow);
		String expectedProdId = queryParams.get("productId");
//		System.err.println("Expected Product Id is ::: " + expectedProdId);
		String getFormConfigForaProductResponce = getFormFromFormIdForProd_Res.asString();
		JsonPath js = new JsonPath(getFormConfigForaProductResponce);
		String actualProdId = js.getString("productid");
//		System.err.println("Actual Product Id ::: " + actualProdId);
		Assert.assertEquals(expectedProdId, actualProdId);
		ExtentReportUtil.logPass("Successfully varified Product ID's");
	}

	public void verifyingJsonformdataData_should_not_be_null_in_responce_body() {
		String getFormFromFormIdForProd_ResValue = getFormFromFormIdForProd_Res.asString();
		JsonPath js = new JsonPath(getFormFromFormIdForProd_ResValue);
		String jsonformdataFromRes = js.getString("jsonformdata");
//		System.err.println("JsonformdataFromRes Is  ::: " + jsonformdataFromRes);
		Assert.assertTrue(jsonformdataFromRes != null);
		ExtentReportUtil.logPass("Successfully varified jsonformdataFromRes Data from response body");
	}
	
	//TC006
	public void user_post_rquest_for_insert_or_update_form_data_for_a_journey(String testflow) {
		inser_form_data_response = Utils.getHttpRequest("TestData", testflow);
 
		String response = inser_form_data_response.asString();
		JsonPath js = new JsonPath(response);
		String actual_msg = js.getString("message");
		Assert.assertEquals("Form data inserted successfully.", actual_msg);
		
		ExtentReportUtil.logPass("Successfully varified Insert Form Data response message as :::"+actual_msg);		
		}
	
		public void store_Config_Response(String testflow) {
			String response = inser_form_data_response.asString();
			ReadData.writeDataIntoCell("TestData", testflow, "Responce", response);
			
			String getResCode = inser_form_data_response.getStatusLine();
			getResCode = getResCode.split(" ")[1] + " " + getResCode.split(" ")[2];
			ReadData.writeDataIntoCell("TestData", testflow, "Status Code", getResCode);

			ExtentReportUtil.logInfo("SuccessFully Captured Statuscode :::" + getResCode);
			ExtentReportUtil.logInfo("SuccessFully Captured Responce :::" + response);	
		}
	 
//		TC007
		public void sendGetPicodeDetails(String testflow) {
	 
			get_Pincode_Details_Res = Utils.getHttpRequest("TestData", testflow);
					
		}
	 
		public void validatePincodeResponse() {
	 
			JsonPath js = new JsonPath(get_Pincode_Details_Res.asString());
	 
			String actual_State = js.getString("strstate");
	 
			Assert.assertEquals(actual_State, "TELANGANA");
			ExtentReportUtil.logPass("Successfully varified State ");	
		}
	 
		public void storePinCodeResponseinSheet(String testflow) {
			String responseBody = get_Pincode_Details_Res.asString();
			ReadData.writeDataIntoCell("TestData", testflow, "Responce", responseBody);
			
			String getResCode = get_Pincode_Details_Res.getStatusLine();
			getResCode = getResCode.split(" ")[1] + " " + getResCode.split(" ")[2];
			ReadData.writeDataIntoCell("TestData", testflow, "Status Code", getResCode);

			ExtentReportUtil.logInfo("SuccessFully Captured Statuscode :::" + getResCode);
			ExtentReportUtil.logInfo("SuccessFully Captured Responce :::" + responseBody);	
		}
	 
//		TC_008
		public void sendPostRequestForInsertLeadDetails(String testflow) {
			insert_Lead_Details_Res = Utils.getHttpRequest("TestData", testflow);
		}
	 
		public void storeLeadIDResponseInSheet(String testflow) {
			String responseBody = insert_Lead_Details_Res.asString();
	 
			ReadData.writeDataIntoCell("TestData", testflow, "Responce", responseBody);
	 
			JsonPath js = new JsonPath(responseBody);
			String leadId = js.getString("leadId");
			String quoteId = js.getString("quoteId");
			String proposalId = js.getString("proposalId");
	 
			ReadData.writeDataIntoCell("TestData", testflow, "Lead ID", leadId);
			ReadData.writeDataIntoCell("TestData", testflow, "Quote ID", quoteId);
			ReadData.writeDataIntoCell("TestData", testflow, "Proposal ID", proposalId);
	 
		}
	 
		public void validateleadIDResponseBody(String testflow) {
			String response_Lead = insert_Lead_Details_Res.asString();
	 
			String getResCode = insert_Lead_Details_Res.getStatusLine();
			getResCode = getResCode.split(" ")[1] + " " + getResCode.split(" ")[2];
			ReadData.writeDataIntoCell("TestData", testflow, "Status Code", getResCode);

			ExtentReportUtil.logInfo("SuccessFully Captured Statuscode :::" + getResCode);
			ExtentReportUtil.logInfo("SuccessFully Captured Responce :::" + response_Lead);	
			
			JsonPath js = new JsonPath(response_Lead);
			String actual = js.getString("message");
			
			Assert.assertEquals("Lead Details Inserted Successfully.",actual);	
			ExtentReportUtil.logPass("Successfully varified Lead Details Inserted Successfully Message ");		
		}
}
