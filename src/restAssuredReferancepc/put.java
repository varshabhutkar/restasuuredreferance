package restAssuredReferancepc;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import org.testng.Assert;


public class put {

	public static void main(String[] args) {
  //declare base url and request body variable
	RestAssured.baseURI="https://reqres.in/";
	String requestBody="{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"zion resident\"\r\n"
			+ "}";
	//step2: fetch request body parameter values.
	JsonPath jsprequest=new JsonPath(requestBody);
	String req_name=jsprequest.getString("name");
	System.out.println(req_name);
	String req_job=jsprequest.getString("job");
	System.out.println(req_job);
	
	//configure requestBody
int	statusCode=given().header("Content-Type","application/json").body(requestBody).when().put("/api/users/2").then().extract().statusCode();
System.out.println(statusCode);
String responseBody =given().header("Content-Type","application/json").body(requestBody).when().put("/api/users/2").then().extract().response().asString();
System.out.println(responseBody);
//step4:parse the responseBody
JsonPath jspresponse=new JsonPath(responseBody);
String res_name=jspresponse.getString("name");
String res_job=jspresponse.getString("job");
String res_updatedAt=jspresponse.getString("updatedAt");
System.out.println( res_updatedAt);

String currentdate=LocalDate.now().toString();
//res_updatedAt=res_updatedAt.substring(0,10);//


System.out.println("Status code is: "+ statusCode + " OK");  
System.out.println("name : "+res_name);
System.out.println("job : "+res_job);
System.out.println("updatedAt : "+res_updatedAt);

//Validate response body
//Validate response body
Assert.assertEquals(res_name , req_name);
Assert.assertEquals(res_job , req_job);
//Assert.assertEquals(res_updatedAt,currentdate);//
Assert.assertEquals(res_updatedAt.substring(0,10),currentdate);






	}

}
