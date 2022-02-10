package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class Base {
	
	/*
	 * Random Number
	 * @author Ricardo Avalos
	 */
	
	public static int randomNumber() {
		return (int)(Math.random()*10000);
	}
	
	/*
	 * Get Value from File properties
	 * @author: ricardo.avalos
	 */
	
	public static String getPropertiesValues(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("./src/main/resources/global/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	/*
	 * Get value from Json
	 */
	public static String getValueFromJson(String response, String value) {
		JsonPath js = new JsonPath(response);
		  return js.getString(value);
	}
	
	/**
	 * Get Data from JSON file (Directly)
	 * 
	 * @author Ricardo Avalos
	 * @param jsonFile, jsonKey
	 * @return jsonValue
	 * @throws FileNotFoundException
	 */
	public static String getJSONData(String jsonFileObj, String jsonKey) throws FileNotFoundException {
		try {

			// JSON Data
			InputStream inputStream = new FileInputStream("./src/test/resources/testdata/" + jsonFileObj + ".json");
			JSONObject jsonObject = new JSONObject(new JSONTokener(inputStream));

			// Get Data
			String jsonValue = (String) jsonObject.get(jsonKey);
			return jsonValue;

		} catch (FileNotFoundException e) {
			Assert.fail("JSON file is not found");
			return null;
		}
	}
	
	/**
	 * Get Data from JSON file (Directly)
	 * 
	 * @author Ricardo Avalos
	 * @param jsonFile, jsonKey
	 * @return jsonValue
	 * @throws FileNotFoundException
	 */
	public static int getJSONDataInt(String jsonFileObj, String jsonKey) throws FileNotFoundException {
		try {

			// JSON Data
			InputStream inputStream = new FileInputStream("./src/test/resources/testdata/" + jsonFileObj + ".json");
			JSONObject jsonObject = new JSONObject(new JSONTokener(inputStream));

			// Get Data
			int jsonValue = (int) jsonObject.get(jsonKey);
			return jsonValue;

		} catch (FileNotFoundException e) {
			Assert.fail("JSON file is not found");
			return -1;
		}
	}
	
	/*
	 * Get String from Path Json (External Json)
	 * @author: ricardo.avalos@automationsensei.com
	 */	
	public static String getStringFromExternalJson(String jsonPath) throws IOException {
		String pathString = new String(Files.readAllBytes(Paths.get(jsonPath)));
		return pathString;
	}

}
