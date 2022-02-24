package trello;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ListScenarios {

  @BeforeTest
  public void beforeTest() {
  }
  
  @Test
  public void tc002GetListsInfoFromBoard() {
	  
	  /*
	   * Good practices
	   * 
	   * 1- Analizar el scenario o requerimiento.
	   * 2- Implementarlo manualmente en alguna herramienta de RESTful.
	   * 3- Implementacion en el codigo.
	   */
	  
	  // Endpoint
	  RestAssured.baseURI="https://api.trello.com";
	  
	  // Get ID board - API
	  String responseMembers = given()
	  .header("Content-Type", "application/json")
	  .queryParam("key", "da1c26d2c68ccaad3feff30f2a36f4f1")
	  .queryParam("token", "0a8edbee4e73ee52cb7a09d78e664bb8ba24e8584d4ae9228b2c23a36c24be42")
	  .when()
	  .log().all()
	  .get("/1/members/me/boards")
	  .then().log().all().assertThat().statusCode(200)
	  .extract().response().asString();
	  
	  // Extract ID Board
	  JsonPath jp = new JsonPath(responseMembers);
	  String idBoard = jp.getString("array.id");
	  
	  // API request - Get lists information from board
	  String responseGetLists = given()
	  .header("Content-Type", "application/json")
	  .pathParam("idBoard", idBoard)
	  .queryParam("key", "da1c26d2c68ccaad3feff30f2a36f4f1")
	  .queryParam("token", "0a8edbee4e73ee52cb7a09d78e664bb8ba24e8584d4ae9228b2c23a36c24be42")
	  .when()
	  .log().all()
	  .get("/1/boards/{idBoard}/lists")
	  .then().extract().response().asString();
	  
	  jp = new JsonPath(responseGetLists);
	  int listsAmount = jp.getInt("array.size()");
	  
	  // Validation
	  Assert.assertTrue(listsAmount!=0);
	  
	  
	  
  }

  @AfterTest
  public void afterTest() {
  }

}
