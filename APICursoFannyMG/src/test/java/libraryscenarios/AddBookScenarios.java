package libraryscenarios;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.SpecBuilders;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class AddBookScenarios {
	
	/*
	 * Pojo class: Se puede definir como una clase encapsula para manejo de datos
	 * 			   en archivos planos. (txt, json, xml, html)
	 * 
	 * Serializacion: En base a un objeto de Java construir un archivo plano.
	 * 
	 * Deserializacion: En base a un archivo plano se contruye un objeto.
	 */
	
	private AddBookRequest addBookReq;

  @BeforeTest
  public void beforeTest() {
	  
	  // Serializar body request
	  addBookReq = new AddBookRequest();
	  addBookReq.setName("Testing Book Name");
	  addBookReq.setIsbn("ABDCG");
	  addBookReq.setAisle("1246");
	  addBookReq.setAuthor("Ricardo");
  }
  
  @Test
  public void tc001ValidateStatusCode200() throws IOException {
	  
	Response addBookResponse = SpecBuilders.addBookSpecBuilder(addBookReq);
	Assert.assertEquals(addBookResponse.getStatusCode(), 200);
		  
  }
  
  @Test
  public void tc002VerifyFromPayloadResponse() throws IOException {
	  
	  Response addBookResponse = SpecBuilders.addBookSpecBuilder(addBookReq);
	  
	  ResponseBody<?> responseBook = addBookResponse.getBody();
	  String expectedID = addBookReq.getIsbn() + addBookReq.getAisle();
	  Assert.assertEquals(responseBook.jsonPath().getString("ID"), expectedID);
	  
  }

  @AfterTest
  public void afterTest() {
  }

}
