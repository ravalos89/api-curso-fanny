package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.io.IOException;

public class SpecBuilders {
	
	public static Response addBookSpecBuilder(Object body) throws IOException {
		// Specification builder
		RequestSpecification req = new RequestSpecBuilder()
		.setBaseUri(Base.getPropertiesValues("ENDPOINT_LIBRARY_QA"))
		.addHeader(Base.getPropertiesValues("HEADER_KEY_CONTENT_TYPE"), Base.getPropertiesValues("HEADER_VALUE_APP_JSON"))
		.setBody(body)
		.build();
		
		// Request
		return given()
				.spec(req)
				.when()
				.log().all()
				.post(Base.getPropertiesValues("RESOURCE_ADD_BOOK"));
				
	}

}
