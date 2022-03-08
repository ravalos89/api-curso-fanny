package libraryscenarios;

import org.testng.annotations.Test;

import base.Payloads;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class AddBookScenarios {
	
	/*
	 * Pojo class: Se puede definir como una clase encapsula para manejo de datos
	 * 			   en archivos planos. (txt, json, xml, html)
	 * 
	 * Serializacion: En base a un objeto de Java construir un archivo plano.
	 * 
	 * Deserializacion: En base a un archivo plano se contruye un objeto.
	 */
	
	private String name, isbn, aisle, author;
	private AddBookRequest addBookReq;

  @BeforeTest
  public void beforeTest() {
	  
	  // Serializar body request
	  addBookReq = new AddBookRequest();
	  addBookReq.setName("Testing Book Name");
	  addBookReq.setIsbn("ABDCF");
	  addBookReq.setAisle("1235");
	  addBookReq.setAuthor("Ricardo");
  }
  
  @Test
  public void tc001ValidateStatusCode200() {
	  
	// ENDPOINT
	  RestAssured.baseURI="http://216.10.245.166";
		  
	  // Serializacion
	 AddBookResponse addBookResponse = given().header("Content-Type", "application/json")
	  .body(addBookReq)
	  .log().all()
	  .expect().defaultParser(Parser.JSON)
	  .when()
	  .post("Library/Addbook.php")
	  .as(AddBookResponse.class);
	 
	 // Deserializcion
	 System.out.println(addBookResponse.getId());
	 System.out.println(addBookResponse.getMsg());
	 
	 Assert.assertNotNull(addBookResponse.getId());
	 
	 
		  
  }

  @AfterTest
  public void afterTest() {
  }

}
