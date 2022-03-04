package trello;

import java.io.File;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class CardScenarios {
	
	String idCard, key, token, name, url, endpoint;
	File file ;

	@BeforeTest
	public void beforeTest() throws IOException {
		endpoint = "https://api.trello.com";
		RestAssured.baseURI = endpoint;
		idCard = "62215bb632483e688f2eea7e";
		key="da1c26d2c68ccaad3feff30f2a36f4f1";
		token="0a8edbee4e73ee52cb7a09d78e664bb8ba24e8584d4ae9228b2c23a36c24be42";
		name="AutomationRestAssured";
		url="https://";
		file = new File("/Users/ricardoavalos/git/repository3/APICursoFannyMG/src/test/resources/attachments/AutomationTrello.txt");
	}
	
	@Test
	public void tc007() {
		given()
		.pathParam("idCard", idCard)
		.queryParam("key", key)
		.queryParam("token", token)
		.queryParam("name", name)
		.queryParam("url", url)
		.multiPart("file",file)
		.when()
		.post("/1/cards/{idCard}/attachments")
		.then().log().all();
		
	}

	

	@AfterTest
	public void afterTest() {
	}

}
