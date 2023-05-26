package restAssuredReferancepc;

import io.restassured.RestAssured;
import io.restassured.path.xml.*;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
public class Soapreference2 {

	public static void main(String[] args) {
		//declare base urI
	RestAssured.baseURI="https://www.dataaccess.com";
	String requestbody="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
			+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
			+ "  <soap:Body>\r\n"
			+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
			+ "      <ubiNum>100</ubiNum>\r\n"
			+ "    </NumberToWords>\r\n"
			+ "  </soap:Body>\r\n"
			+ "</soap:Envelope>";
	//Fetch request body parameters
	XmlPath xmlrequest=new XmlPath(requestbody);
	String req_uniNum=xmlrequest.getString("uniNum");
	System.out.println(req_uniNum);
	//configure request apI and fetch response body 
	
	String responsebody=given().header("Content-Type","text/xml; charset=utf-8").body(requestbody).when().post("/webservicesserver/NumberConversion.wso")
			.then().extract().response().asString();
	System.out.println(responsebody);
	//parse the rsponseBody
	XmlPath xmlresponse=new XmlPath(responsebody);
      String Result=xmlresponse.getString("NumberToWordsResult");
      System.out.println(Result);
      //validation
      Assert.assertEquals(Result,"one hundred");
		
	
	
	
	
	
	
	

	
	
			


	}

}
