package restAssuredReferancepc;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import java.time.LocalDate;
public class patchreference {

	public static void main(String[] args) {
		//declare base url
		RestAssured.baseURI="https://reqres.in/";
		String requestBody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		//extract request body param
		JsonPath jsprequest=new JsonPath(requestBody);
		String req_name=jsprequest.getString("name");
		String req_job=jsprequest.getString("job");
		System.out.println(req_name);
		System.out.println(req_job);
	//configure requestBody
	int statusCode =given().header("Content-Type","application/json").body(requestBody).when().patch("/api/users/2").then()
			       .extract().statusCode();
	String responseBody=given().header("Content-Type","application/json").body(requestBody).when().patch("/api/users/2").then()
			        .extract().response().asString();
	System.out.println(statusCode);
	System.out.println(responseBody);
	//parse the responsebody para
	JsonPath jspresponse=new JsonPath(responseBody);
	String res_name=jspresponse.getString("name");
	String res_job=jspresponse.getString("job");
	String res_updatedAt=jspresponse.getString("updatedAt");
	System.out.println("Status code is:"+statusCode+"OK");
	System.out.println("name:"+res_name);
	System.out.println("job:"+res_job);
	System.out.println("updatedAt:"+res_updatedAt);

  String currentdate=LocalDate.now().toString();
  //validate response
  Assert.assertEquals(req_name,res_name);
  Assert.assertEquals(req_job, res_job);
  Assert.assertEquals(res_updatedAt.substring(0,10),currentdate);
  
  
	
	
		
		 
		

	}

}
