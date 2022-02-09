package library;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import base.Payloads;
import io.restassured.RestAssured;

public class AddBookDataProvider {
	private final String BASE = "baseLibraryURL";
	private final String KEY_CONTENT_TYPE = "keyContentType";
	private final String VALUE_CONTENT_TYPE = "valueContentTypeAppJson";
	private final String RESOURCE = "resourceAddBook";
	private final String JSON_MESSAGES_RESPONSE = "messagesResponse";
	private final String JSON_MESSAGES_VALUE = "valueMsg";
	private final String JSON_MESSAGES_SUCCESSFULLY = "successfullyMsg";
	private final String JSON_STATUS_CODES = "statusCodes";
	private final String JSON_STATUS_CODES_SUCCESS = "Success";
	
  @Test(dataProvider = "bookData")
  public void addBook(String isbn, String aisle) throws IOException {
	  	  
	  // ENDPOINT
	  RestAssured.baseURI=Base.getPropertiesValues(BASE);
	  
	  // REQUEST
	  String response = given().header(Base.getPropertiesValues(KEY_CONTENT_TYPE), 
			  Base.getPropertiesValues(VALUE_CONTENT_TYPE))
	  .body(Payloads.addBookRandom(isbn, aisle))
	  .when().post(Base.getPropertiesValues(RESOURCE))	  
	  // RESPONSE
	  .then()
	  .statusCode(Base.getJSONDataInt(JSON_STATUS_CODES, JSON_STATUS_CODES_SUCCESS)) // Assertion Rest Assured	  
	  .extract().asString(); // GET RESPONSE BODY
	  
	  //SCENARIO VALIDATE ID from Body response	  
	  System.out.println(response); // EXAMPLE
	  
	  Assert.assertEquals(Base.getValueFromJson(response, Base.getJSONData(JSON_MESSAGES_RESPONSE, JSON_MESSAGES_VALUE)), 
			  Base.getJSONData(JSON_MESSAGES_RESPONSE, JSON_MESSAGES_SUCCESSFULLY));
  }
  
  @DataProvider()
  public Object[][] bookData(){
	  return new Object[][] {{"GHT", "6543"}, {"VBN", "6543"}, {"GFD", "6543"}};
  }
}
