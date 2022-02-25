package trello;

import org.testng.annotations.Test;

import base.Base;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ListScenarios {

	String endpoint, headerKeyContenttype, headerValueAppJson, resourceMemberInfo, resourceListsBoard,
			pathParamKeyIdBoard, queryParamKeyKey, queryParamValueKey, queryParamKeyToken, queryParamValueToken,
			idBoard, queryParamKeyName, resourceCreateList, autoName;

	@BeforeTest
	public void beforeTest() throws IOException {

		// Test Data
		endpoint = Base.getPropertiesValues("ENDPOINT_TRELLO_QA");
		headerKeyContenttype = Base.getPropertiesValues("HEADER_KEY_CONTENT_TYPE");
		headerValueAppJson = Base.getPropertiesValues("HEADER_VALUE_APP_JSON");
		resourceMemberInfo = Base.getPropertiesValues("RESOURCE_MEMBER_INFO");
		resourceListsBoard = Base.getPropertiesValues("RESOURCE_LISTS_FROM_BOARD");
		pathParamKeyIdBoard = Base.getPropertiesValues("PATH_PARAM_KEY_ID_BOARD");
		queryParamKeyKey = Base.getPropertiesValues("QUERY_PARAM_KEY_KEY");
		queryParamValueKey = Base.getPropertiesValues("QUERY_PARAM_VALUE_KEY");
		queryParamKeyToken = Base.getPropertiesValues("QUERY_PARAM_KEY_TOKEN");
		queryParamValueToken = Base.getPropertiesValues("QUERY_PARAM_VALUE_TOKEN");
		autoName = Base.getPropertiesValues("AUTOMATION_RANDOM_NAME");
		resourceCreateList = Base.getPropertiesValues("RESOURCE_CREATE_LIST");
		queryParamKeyName = Base.getPropertiesValues("QUERY_PARAM_KEY_NAME");

		// Endpoint
		RestAssured.baseURI = endpoint;

		// Get ID board - API
		String responseMembers = given().header(headerKeyContenttype, headerValueAppJson)
				.queryParam(queryParamKeyKey, queryParamValueKey).queryParam(queryParamKeyToken, queryParamValueToken)
				.when().log().all().get(resourceMemberInfo).then().log().all().assertThat().statusCode(200).extract()
				.response().asString();

		// Extract ID Board
		JsonPath jp = new JsonPath(responseMembers);
		idBoard = jp.getString("[0].id");
	}

	@Test
	public void tc002GetListsInfoFromBoard() throws IOException {

		/*
		 * Good practices
		 * 
		 * 1- Analizar el scenario o requerimiento. 2- Implementarlo manualmente en
		 * alguna herramienta de RESTful. 3- Implementacion en el codigo.
		 */

		// API request - Get lists information from board
		String responseGetLists = given().header(headerKeyContenttype, headerValueAppJson)
				.pathParam(pathParamKeyIdBoard, idBoard).queryParam(queryParamKeyKey, queryParamValueKey)
				.queryParam(queryParamKeyToken, queryParamValueToken).when().log().all().get(resourceListsBoard).then()
				.extract().response().asString();

		JsonPath jp2 = new JsonPath(responseGetLists);
		int listsAmount = jp2.getInt("array.size()");

		// Validation
		Assert.assertTrue(listsAmount != 0);

	}

	@Test
	public void tc003CreateNewList() {
		
		// Test Data
		String randomNameList = autoName+Base.randomNumber();
		
		// API request - Create a new list
		String response = given()
		.header(headerKeyContenttype, headerValueAppJson)
		.queryParam(queryParamKeyKey, queryParamValueKey)
		.queryParam(queryParamKeyToken, queryParamValueToken)
		.queryParam(queryParamKeyName, randomNameList)
		.queryParam(pathParamKeyIdBoard, idBoard)
		.when().log().all()
		.post(resourceCreateList).then().assertThat()
		// Validate status code
		.statusCode(200).extract().response().asString();
		
		// Get NAME list from response
		JsonPath js = new JsonPath(response);
		String actualNameList = js.getString("name");
		String actualId = js.getString("id");
		
		// Validations
		Assert.assertNotNull(actualId);
		Assert.assertEquals(actualNameList, randomNameList);
		
		
		
	}

	@AfterTest
	public void afterTest() {
	}

}
