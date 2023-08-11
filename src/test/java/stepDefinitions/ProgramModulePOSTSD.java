package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;

import Base.baseclass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Config;

public class ProgramModulePOSTSD extends baseclass {

	String uri;
	public RequestSpecification request;
	Response response;
	private static String ProgramIDvalue;
	Logger logger = LogManager.getLogger("ProgramModule POSTSD.java");

	// <--User sends request for Program module with valid request body-->
	@Given("User sends request for Program module with valid request body")
	public void user_sends_request_for_program_module_with_valid_request_body() {
		this.uri = Config.POSTProgram_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid data");
	}

	@When("User sends POST request data from {string} and {string}")
	public void user_sends_post_request_data_from_and(String SheetName, String TestCaseName)
			throws InvalidFormatException, IOException {
		int iFlag = 0;
		createPostRequest(SheetName, TestCaseName, iFlag);
		logger.info("Post Request sent with valid request body");
	}

	@SuppressWarnings("unchecked")
	public void createPostRequest(String SheetName, String TestCaseName, int iFlag)
			throws InvalidFormatException, IOException {
		logger.info("POSTTestCase Name= " + SheetName);
		logger.info("POSTTestCase Name= " + TestCaseName);
		ArrayList<String> programData = excelValue(SheetName, TestCaseName);
		String programName = "";
		if (iFlag == 0) {
			programName = programData.get(1) + randomestring();
		} else {
			programName = Config.programId;
		}

		String programDescription = programData.get(2);
		String programStatus = programData.get(3);

		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();

		// EXTRACTING THE PROGRAM ID FROM SUCESS RESPONSE
		if (response.getStatusCode() == 201) {
			ProgramIDvalue = response.jsonPath().getString("programId");
			Config.programId = ProgramIDvalue;
			logger.info("POSTREQUESTPRINTProgramIDvalueoutgettoken= " + ProgramIDvalue);
		}
	}

//	public static String getPrgramID() {
//		return ProgramIDvalue;
//	}

	@Then("User sucessfully receives status code {string} with reponse body")
	public void user_sucessfully_receives_status_code_with_reponse_body(String statusCode) {
		int Poststatuscode = response.getStatusCode();
		if (Poststatuscode == 201) {
			response.then().statusCode(Integer.parseInt(statusCode));
			logger.info("Post Request Successful");
		} else
			logger.info("Post Request unsuccessful with status code " + statusCode);
	}

	// <----- POST REQUEST 400 BAD REQUEST--------->
	@Given("User create post request with valid endpoint")
	public void user_create_post_request_with_valid_endpoint() {
		this.uri = Config.POSTProgram_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and existing data");
	}

	@When("User send the existing data for post request from {string} and {string}")
	public void user_send_the_existing_data_for_post_request_from_and(String SheetName, String TestCaseName)
			throws InvalidFormatException, IOException {
		createPostRequest(SheetName, TestCaseName,1);
		logger.info("Post Request sent with existing request body");
	}

	@Then("User should receives status code {string} bad request")
	public void user_should_receives_status_code_bad_request(String statusCode) {
		int Poststatuscode = response.getStatusCode();
		if (Poststatuscode == 400) {
			response.then().statusCode(Integer.parseInt(statusCode));
			logger.info("Post Request should recive 400 bad request");
		} else
			logger.info("Post Request unsuccessful with status code " + statusCode);
	}

	// <--------POST 500 INTERNAL SERVER ERROR--------------------->
	@Given("User create post request with valid LMS endpoint")
	public void user_create_post_request_with_valid_lms_endpoint() {
		this.uri = Config.POSTProgram_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and missing data");
	}

	@When("User send the missing data for post request from {string} and {string}")
	public void user_send_the_missing_data_for_post_request_from_and(String SheetName, String TestCaseName)
			throws InvalidFormatException, IOException {
		createPostRequest(SheetName, TestCaseName,0);
		logger.info("Post Request sent with missing values to request body");
	}

	@Then("User should receives status code {string} internal server error")
	public void user_should_receives_status_code_internal_server_error(String statusCode) {
		int Poststatuscode = response.getStatusCode();
		if (Poststatuscode == 500) {
			response.then().statusCode(Integer.parseInt(statusCode));
			logger.info("Post Request should recive 500 Internal server error");
		} else
			logger.info("Post Request unsuccessful with status code " + statusCode);

	}

}
