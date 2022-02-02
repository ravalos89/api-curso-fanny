package library;

import org.testng.annotations.Test;

import base.Payloads;
import io.restassured.path.json.JsonPath;

public class ParsingJsonComplex {
	@Test
	public void jsonComplex() {

		JsonPath jp = new JsonPath(Payloads.jsonComplexLibrary()); // Parsing JSON

		// Get Total Amount?
		String totalAmount = jp.getString("dashboard.totalAmount");
		System.out.println("El total es: " + totalAmount);

		// Get amount books? JSON arrays
		int booksAmount = jp.getInt("books.size()");
		System.out.println("El total de libros es: " + booksAmount);

		// Print values from second book
		String secondBook = jp.getString("books[1].title");
		System.out.println("El nombre del segundo libro es: " + secondBook);

		// Print all information
		for (int i = 0; i < booksAmount; i++) {
			String title = jp.getString("books[" + i + "].title");
			String price = jp.getString("books[" + i + "].price");
			System.out.println(i + "# Title= " + title);
			System.out.println(i + "# Price= " + price);

			// Print values from third book

			// Print all copies

			// Print Website
		}
	}
}
