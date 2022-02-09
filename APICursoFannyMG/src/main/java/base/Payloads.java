package base;

public class Payloads {
	
	public static String addBookRandom() {
		return "{\n"
				+ "    \"name\":\"El principito\",\n"
				+ "    \"isbn\": \"ABCD\",\n"
				+ "    \"aisle\": \""+Base.randomNumber()+"\",\n"
				+ "    \"author\":\"Antoine de Saint-Exupéry\"\n"
				+ "}";
	}
	
	// Overloading
	public static String addBookRandom(String isbn, String aisle) {
		return "{\n"
				+ "    \"name\":\"El principito\",\n"
				+ "    \"isbn\": \""+isbn+"\",\n"
				+ "    \"aisle\": \""+aisle+"\",\n"
				+ "    \"author\":\"Antoine de Saint-Exupéry\"\n"
				+ "}";
	}
	
	public static String jsonComplexLibrary() {
		return "{\n"
				+ "	\"dashboard\":{\n"
				+ "		\"totalAmount\": 2330,\n"
				+ "		\"website\": \"books.com\"\n"
				+ "	},\n"
				+ "	\"books\":[\n"
				+ "		{\n"
				+ "			\"title\":\"Principito\",\n"
				+ "			\"price\": 50,\n"
				+ "			\"copies\": 6\n"
				+ "		},\n"
				+ "		{\n"
				+ "			\"title\":\"El arte de la guerra\",\n"
				+ "			\"price\": 40,\n"
				+ "			\"copies\": 6\n"
				+ "		},\n"
				+ "		{\n"
				+ "			\"title\":\"Pedro Paramo\",\n"
				+ "			\"price\": 45,\n"
				+ "			\"copies\": 10\n"
				+ "		},\n"
				+ "		{\n"
				+ "			\"title\":\"El simbolo\",\n"
				+ "			\"price\": 80,\n"
				+ "			\"copies\": 10\n"
				+ "		},\n"
				+ "		{\n"
				+ "			\"title\":\"Habitos atomicos\",\n"
				+ "			\"price\": 54,\n"
				+ "			\"copies\": 10\n"
				+ "		}\n"
				+ "	]\n"
				+ "}";
	}

}
