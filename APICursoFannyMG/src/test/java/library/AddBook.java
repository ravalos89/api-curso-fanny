package library;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class AddBook {
  @Test
  public void addBook() {
	  
	  // ENDPOINT
	  RestAssured.baseURI="http://216.10.245.166";
	  
	  // REQUEST
	  String response = given().header("Content-Type", "application/json")
	  .body("{\n"
	  		+ "    \"name\":\"El principito\",\n"
	  		+ "    \"isbn\": \"BBB\",\n"
	  		+ "    \"aisle\": \"5555\",\n"
	  		+ "    \"author\":\"Antoine de Saint-Exupéry\"\n"
	  		+ "}")
	  .when().post("Library/Addbook.php")
	  
	  // RESPONSE
	  .then()
	  .statusCode(200) // Assertion Rest Assured
	  
	  .extract().asString(); // GET RESPONSE BODY
	  
	  //SCENARIO VALIDATE ID from Body response
	  
	  System.out.println(response);
	  
	  // PARSING - JSOn PATH OBJECT
	  JsonPath js = new JsonPath(response);
	  String msg = js.getString("Msg");
	  
	  System.out.println(msg);
	  
	  Assert.assertEquals(msg, "successfully added");
  }
}
