package library;

import org.testng.annotations.Test;

import base.Payloads;
import io.restassured.path.json.JsonPath;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class TestCaseSumValidation {
	
	int expectedSum;

	@BeforeTest
	public void beforeTest() {
		//Data
		expectedSum = 910;
	}

	@Test
	public void testCaseSumValidation() {
		
		JsonPath jp = new JsonPath(Payloads.jsonComplexLibrary());
		
		int booksAmount = jp.getInt("books.size()");
		
		// Logic
		int actualSum=0;
		for(int i=0; i<booksAmount;i++) {
			int priceBook = jp.getInt("books["+i+"].price");
			int copiesBook = jp.getInt("books["+i+"].copies");
			
			int mult = priceBook*copiesBook;
			
			actualSum += mult;
		}
		
		int expectedAmount = jp.getInt("dashboard.totalAmount");
		
		// Validation TEST NG
		Assert.assertEquals(actualSum, expectedAmount);
	}

}
