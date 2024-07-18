package resources;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import readData.ReadData;

public class Utils {
	
	static Response  response=null;
	
	public static String getJsonPath(Response response,String key)
	{
		  String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}
	
	public static Response getHttpRequest(String sheetname,String testflow)
	{
		String TC_HttpMethod=ReadData.getCellData(sheetname, testflow, "HttpMethod");
		String requestBody=ReadData.getCellData(sheetname, testflow, "RequestBody");
		String endPointUrl=ReadData.getCellData(sheetname, testflow, "API URL");
		
		
		if(TC_HttpMethod.equalsIgnoreCase("POST"))
		{
			response=given().baseUri(endPointUrl)
            .header("Content-type", "application/json")
            .and()
            .body(requestBody)
            .when()
            .post();

			System.err.println("POST Response:\n" + response.getBody().asString());
		}
		else if (TC_HttpMethod.equalsIgnoreCase("GET"))
		{
			response=given()
            .when()
            .get(endPointUrl);

			System.err.println("GET Response:\n" + response.getBody().asString());
		}
		else if (TC_HttpMethod.equalsIgnoreCase("PUT"))
		{
			response=given()
            .header("Content-type", "application/json")
            .and()
            .body(requestBody)
            .when()
            .put(endPointUrl);

			System.err.println("PUT Response:\n" + response.getBody().asString());
		}
		else if (TC_HttpMethod.equalsIgnoreCase("PATCH"))
		{
			response=given()
            .header("Content-type", "application/json")
            .and()
            .body(requestBody)
            .when()
            .patch(endPointUrl);

			System.err.println("PATCH Response:\n" + response.getBody().asString());
		}
		else if (TC_HttpMethod.equalsIgnoreCase("DELETE"))
		{
			response=given()
            .when()
            .delete(endPointUrl);

			System.err.println("DELETE Response:\n" + response.getBody().asString());
		}
		
		return response;
	}
	
	public static Map<String, String> getQueryParamsFromURL(String sheetname, String testflow) {
		 
		 String urlString = ReadData.getCellData(sheetname, testflow, "API URL");
		 Map<String, String> queryParams=new HashMap<>();
	        try {
	            URI uri = new URI(urlString);
	            
	            // Get query parameters
	            queryParams = Stream.of(uri.getQuery().split("&"))
	                    .map(param -> param.split("="))
	                    .collect(Collectors.toMap(param -> param[0], param -> param[1]));

	            // Print query parameters
	            queryParams.forEach((key, value) -> System.out.println(key + " = " + value));
	            
	        } catch (URISyntaxException e) {
	            e.printStackTrace();
	        }
	        
	        return queryParams;
	}
	
}
