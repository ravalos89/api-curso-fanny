package library;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class AddBook {
  @Test
  public void addBook() {
	  
	  // ENDPOINT
	  RestAssured.baseURI="http://216.10.245.166";
	  
	  // REQUEST
	  given().header("Content-Type", "application/json")
	  .body("{\n"
	  		+ "    \"name\":\"El principito\",\n"
	  		+ "    \"isbn\": \"XYZ\",\n"
	  		+ "    \"aisle\": \"5555\",\n"
	  		+ "    \"author\":\"Antoine de Saint-Exupéry\"\n"
	  		+ "}")
	  .when().post("Library/Addbook.php")
	  
	  // RESPONSE
	  .then().log().all().statusCode(200);
  }
}
