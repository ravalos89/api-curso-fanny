package libraryscenarios;

import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MarcelaTest {
	@Test
	public void f() {

		RestAssured.baseURI = "";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");

		JsonPath jsonPathEvaluator = response.jsonPath();

		List<ProductosResponse> allCategorias = jsonPathEvaluator.getList("data", ProductosResponse.class);

		for (ProductosResponse categorias : allCategorias) {
			System.out.println("Categorias: " + categorias.getCategoria());
		}
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

}
