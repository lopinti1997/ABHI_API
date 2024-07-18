package payLoad;

public class Payload {

	public static String accountLoginDetails(String username,String password)
	{
		return "{\r\n"
				+ "    \"agentUserName\": \""+username+"\",\r\n"
				+ "     \"agentPassword\": \""+password+"\"\r\n"
				+ "}";
	}
	
	
}
