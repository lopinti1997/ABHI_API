package resources;

import static io.restassured.RestAssured.given;
import org.junit.Assert;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payLoad.Payload;
import readData.ReadData;

public class TestDataBuild {
	
	RequestSpecification agentCredintials_RS;
	RequestSpecification getProductsForAgency_RS;
	Response login_Responce=null;
	Response getProductsForAgency_Responce=null;
	
		
	public RequestSpecification getTheAgentCredentials(String testflow) {
		
		agentCredintials_RS= new RequestSpecBuilder().setBaseUri(ReadData.getCellData("TestData", testflow, "API URL")).setContentType("application/json").build();
		return agentCredintials_RS;
	}
	public Response agentLoginWithPOST_HttpRequest(String testflow) {
		
		login_Responce=given().spec(agentCredintials_RS)
				.body(Payload.accountLoginDetails(ReadData.getCellData("LoginData", testflow, "AgentUsername"), ReadData.getCellData("LoginData", testflow, "AgentPassword"))).when().post();
		return login_Responce;
	}
	
	public void validatingSuccessCode(String statusCode) {
		
		String actualStatuscode=String.valueOf(login_Responce.getStatusCode());
		System.out.println("actualStatuscode Is ::: "+actualStatuscode);
		Assert.assertEquals(statusCode,actualStatuscode);
	}
	
	public void storeTheResponceAndStatusCodeInToExcel(String testflow)
	{
		String loginResponce=login_Responce.asString();
		ReadData.writeDataIntoCell("TestData", testflow, "Responce",loginResponce );
		
		String loginResCode=login_Responce.getStatusLine();
		loginResCode=loginResCode.split(" ")[1] +" "+loginResCode.split(" ")[2];
		ReadData.writeDataIntoCell("TestData", testflow, "Status Code",loginResCode);
		System.out.println("Data added successfully added");
	}
	
	public void verifyingAgentUserName_and_AgenceyCode(String expUsername,String expAgencyCode) {
		
		System.out.println(login_Responce.asString());
		JsonPath js= new JsonPath(login_Responce.asString());
		String actualAgencyCode=js.getString("agencyCode");
		String actualAgentUsername=js.getString("userName");
		
		Assert.assertEquals(expUsername,actualAgentUsername);
		Assert.assertEquals(expAgencyCode,actualAgencyCode);
	}
	
	public void verifyingLoginSuccessMessage(String expMessage) {
		
		System.out.println(login_Responce.asString());
		JsonPath js= new JsonPath(login_Responce.asString());
		String actualLoginMessage=js.getString("message");
		
		Assert.assertEquals(expMessage,actualLoginMessage);
	}
	
	public void call_GetProducts_ForAgency_http_request(String testflow) {

		getProductsForAgency_Responce=given().header("Content-Type", "application/json").when().get(ReadData.getCellData("TestData", testflow, "API URL"));
		String getProductsResponce=getProductsForAgency_Responce.asString();
		ReadData.writeDataIntoCell("TestData", testflow, "Responce",getProductsResponce );
		
		String getProductsResCode=getProductsForAgency_Responce.getStatusLine();
		getProductsResCode=getProductsResCode.split(" ")[1] +" "+getProductsResCode.split(" ")[2];
		ReadData.writeDataIntoCell("TestData", testflow, "Status Code",getProductsResCode);
		System.out.println("Data added successfully added");
	}
	
	public void verifyingChannelName_And_InsurenceType_And_Agencyname_from_Responce_body(String expChannelName, String expInsurenceName, String expAgencyName) {
	    
		System.err.println(getProductsForAgency_Responce.asString());
		JsonPath js= new JsonPath(getProductsForAgency_Responce.asString());
		

		String actualChannelName=js.getString("channel_name[0]");
		System.out.println(actualChannelName);
		String actualInsurancetype=js.getString("insurancetype[0]");
		System.out.println(actualInsurancetype);
		String actualAgencyname=js.getString("agencyname[0]");
		System.out.println(actualAgencyname);

		Assert.assertEquals(expChannelName,actualChannelName);
		Assert.assertEquals(expInsurenceName,actualInsurancetype);
		Assert.assertEquals(expAgencyName,actualAgencyname);

	}
	
	

}
