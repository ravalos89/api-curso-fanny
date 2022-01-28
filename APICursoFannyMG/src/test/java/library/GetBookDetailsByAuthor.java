package library;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class GetBookDetailsByAuthor {
  @Test
  public void getBookDetailsByAuthor() {
	  
	  //ENDPOINT
	  RestAssured.baseURI = "http://216.10.245.166";
	  
	  //REQUEST
	  String response = given().queryParam("AuthorName", "Antoine de Saint-Exupéry")
	  .when().get("Library/GetBook.php")
	  
	  //RESPONSE
	  .then().log().all().statusCode(200) // Assertion restassured
	  
	  .extract().asString();
	  
	  Assert.assertNotNull(response); // Assertion testng
  }
}
