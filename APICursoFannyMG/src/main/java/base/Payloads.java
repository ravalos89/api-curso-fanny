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

}
