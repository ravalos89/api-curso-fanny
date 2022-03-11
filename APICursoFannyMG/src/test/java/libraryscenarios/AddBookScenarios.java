package libraryscenarios;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.SpecBuilders;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class AddBookScenarios {

	/*
	 * Pojo class: Se puede definir como una clase encapsula para manejo de datos en
	 * archivos planos. (txt, json, xml, html)
	 * 
	 * Serializacion: En base a un objeto de Java construir un archivo plano.
	 * 
	 * Deserializacion: En base a un archivo plano se contruye un objeto.
	 */

	private AddBookRequest addBookReq;
	ExtentReports extent;
	ExtentTest logger;

	@BeforeTest
	public void beforeTest() {

		// Serializar body request
		addBookReq = new AddBookRequest();
		addBookReq.setName("Testing Book Name");
		addBookReq.setIsbn("ABDFE");
		addBookReq.setAisle("1248");
		addBookReq.setAuthor("Ricardo");

		/*
		 * Extent Report
		 */
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/APIExtentReport.html", true);
		extent
		.addSystemInfo("Host Name", "API Automation REST-Assured")
		.addSystemInfo("Environment", "API Automation")
		.addSystemInfo("User Name", "Ricardo Avalos");
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
	}

	@Test
	public void tc001ValidateStatusCode200() throws IOException {
		
		// EXTENT REPORT START TEST
		logger = extent.startTest("tc001ValidateStatusCode200");

		Response addBookResponse = SpecBuilders.addBookSpecBuilder(addBookReq);
		Assert.assertEquals(addBookResponse.getStatusCode(), 200);
		
		// EXTENT REPORT VALIDATION
		logger.log(LogStatus.PASS, "Validate status code 200 Add book request");

	}

	@Test
	public void tc002VerifyFromPayloadResponse() throws IOException {
		
		logger = extent.startTest("tc002VerifyFromPayloadResponse");

		Response addBookResponse = SpecBuilders.addBookSpecBuilder(addBookReq);

		ResponseBody<?> responseBook = addBookResponse.getBody();
		String expectedID = addBookReq.getIsbn() + addBookReq.getAisle();
		Assert.assertEquals(responseBook.jsonPath().getString("ID"), expectedID);
		
		logger.log(LogStatus.PASS, "Validate ID Book "+responseBook.jsonPath().getString("ID"));

	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		extent.endTest(logger);
	}

	@AfterTest
	public void afterTest() {
		extent.flush();
		extent.close();
	}

}
