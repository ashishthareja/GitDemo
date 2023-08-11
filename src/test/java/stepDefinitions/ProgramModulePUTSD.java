package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProgramModulePUTSD extends baseclass {
	String uri;
	public RequestSpecification request;
	String programName;
	Response response;
	String programId;
	Logger logger = LogManager.getLogger("ProgramModulePUTSD.java");

	@Given("User create PUT request for program module with valid endpoint and programID")
	public void user_create_put_request_for_program_module_with_valid_endpoint_and_program_id() {

		this.uri =Config.PUTProgram_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid program ID");
	}

	@When("User sends PUT http request and request body from {string} and {string}")
	public void user_sends_put_http_request_and_request_body_from_and(String SheetName, String TestCaseName) throws InvalidFormatException, IOException {

		createPUTRequest(SheetName, TestCaseName); 
		logger.info("PUT Request sent with valid request body");

	}
	@SuppressWarnings("unchecked")
	public void createPUTRequest(String SheetName, String TestCaseName) throws InvalidFormatException, IOException {
		logger.info("PUTTestCase Name= " + SheetName);
		logger.info("PUTTestCase Name= " + TestCaseName);	
		ArrayList<String> programData = excelValue(SheetName, TestCaseName);

		String programName = programData.get(1)+randomestring();
		String programDescription = programData.get(2);
		String programStatus = programData.get(3);
		//ProgramID = programData.get(4);
		if (TestCaseName.equalsIgnoreCase("PUTProgramID"))
		{
			programId = Config.programId;
			logger.info("GETREQUESTPRINTProgramIDvalue= " + programId);
		}else if (TestCaseName.equalsIgnoreCase("PUTInvalidProgramID"))
		{
			programId = programData.get(4);
		}


		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);
		body.put("programId", programId);



		response = this.request
				.body(body.toJSONString())
				.when()
				.put(this.uri + "/" + programId)
				.then()
				.log().all().extract().response();

	}


	@Then("User sucessfully receives status code {string} OK with valid reponse body")
	public void user_sucessfully_receives_status_code_ok_with_valid_reponse_body(String statusCode) {

		int Putstatuscode = response.getStatusCode();
		if (Putstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			logger.info("PUT Request Successful");
		}
		else 
			logger.info("PUT Request unsuccessful with status code " + statusCode);

	}

	// PUT invalid ProgramID
	@Given("User create PUT request for program module with valid endpoint and Invalid programID")
	public void user_create_put_request_for_program_module_with_valid_endpoint_and_invalid_program_id() {
		this.uri =Config.PUTProgram_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid program ID");    

	}

	@When("User sends PUT http request and request body from {string} and {string} for invalid programID")
	public void user_sends_put_http_request_and_request_body_from_and_for_invalid_program_id(String SheetName, String TestCaseName) throws InvalidFormatException, IOException {
		createPUTRequest(SheetName, TestCaseName); 
		logger.info("PUT Request sent with valid request body");  


	}

	@Then("User receives status code {string} not found for invalid programID")
	public void user_receives_status_code_not_found_for_invalid_program_id(String statusCode) {

		int Putstatuscode = response.getStatusCode();
		if (Putstatuscode == 404) {
			response.then().statusCode(Integer.parseInt(statusCode));
			logger.info("PUT Request 404 Successful");
		}
		else 
			logger.info("PUT Request unsuccessful with status code " + statusCode);

	}

	// PUT request for 400 bad request
	@Given("User create PUT request for program module with valid endpoint and valid programID")
	public void user_create_put_request_for_program_module_with_valid_endpoint_and_valid_program_id() {
		this.uri =Config.PUTProgram_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid program ID");

	}

	@SuppressWarnings("unchecked")
	@When("User sends PUT http request and request body from {string} and {string} for missing fields")
	public void user_sends_put_http_request_and_request_body_from_and_for_missing_fields(String SheetName, String TestCaseName) throws InvalidFormatException, IOException {

		//createPUTRequest(SheetName, TestCaseName); 
		logger.info("PUT Request sent with valid request body"); 
		JSONObject body = new JSONObject();
		body.put("programId", " ");
		body.put("programName", " ");
		body.put("programDescription", " ");
		body.put("programStatus"," ");
		body.put("creationTime", " ");
		body.put("lastModTime", " ");
		logger.info(body);

		response = this.request
				.body(body.toJSONString())
				.when()
				.put(this.uri + "/" + programId)
				.then()
				.log().all().extract().response();

		logger.info("PUT Request sent with valid request body");
	}

	@Then("User receives status code {string} Bad request for missing fileds")
	public void user_receives_status_code_bad_request_for_missing_fileds(String statusCode) {
		int Putstatuscode = response.getStatusCode();
		if (Putstatuscode == 400) {
			response.then().statusCode(Integer.parseInt(statusCode));
			logger.info("PUT Request 400 bad request");
		}	
		else 
			logger.info("PUT Request unsuccessful with status code " + statusCode);


	}

	//Put request for valid programname

	@Given("User create PUT request for program module with valid endpoint and ProgramName")
	public void user_create_put_request_for_program_module_with_valid_endpoint_and_program_name() {
		this.uri =Config.PUTProgramName_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid Name");

	}

	@When("User sends PUT http request and request body from {string} and {string} for ProgramName")
	public void user_sends_put_http_request_and_request_body_from_and_for_program_name(String SheetName, String TestCaseName) throws InvalidFormatException, IOException {

		createPUTRequestname(SheetName, TestCaseName); 
		logger.info("PUT Request sent with valid request body");

	}
	@SuppressWarnings("unchecked")
	public void createPUTRequestname(String SheetName, String TestCaseName) throws InvalidFormatException, IOException {
		logger.info("SheetName Name= " + SheetName + "TestCase Name=" + TestCaseName);

		ArrayList<String> programData = excelValue(SheetName, TestCaseName);

		programName = programData.get(1);
		String programDescription = programData.get(2);
		String programStatus = programData.get(3);

		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);

		response = this.request
				.body(body.toJSONString())
				.when()
				.put(this.uri + "/" + programName)
				.then()
				.log().all().extract().response();	
	}

	@Then("User sucessfully receives status code {string} OK with valid reponse body for ProgramName")
	public void user_sucessfully_receives_status_code_ok_with_valid_reponse_body_for_program_name(String statusCode) {
		int Putstatuscode = response.getStatusCode();
		if (Putstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			logger.info("PUT Request Successful");
		}
		else 
			logger.info("PUT Request unsuccessful with status code " + statusCode); 

	}

	//Put request for Invalid program name

	@Given("User create PUT request for program module with valid endpoint and Invalid ProgramName")
	public void user_create_put_request_for_program_module_with_valid_endpoint_and_invalid_program_name() {
		this.uri =Config.PUTProgramName_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid program ID"); 

	}

	@When("User sends PUT http request and request body from {string} and {string} for invalid ProgramName")
	public void user_sends_put_http_request_and_request_body_from_and_for_invalid_program_name(String SheetName, String TestCaseName) throws InvalidFormatException, IOException {
		createPUTRequestname(SheetName, TestCaseName); 
		logger.info("PUT Request sent with valid request body and invalid program name");

	}

	@Then("User receives status code {string} not found for invalid ProgramName for ProgramName")
	public void user_receives_status_code_not_found_for_invalid_program_name_for_program_name(String statusCode) {

		int Putstatuscode = response.getStatusCode();
		if (Putstatuscode == 404) {
			response.then().statusCode(Integer.parseInt(statusCode));
			logger.info("PUT Request 404 not found");
		}
		else 
			logger.info("PUT Request unsuccessful with status code " + statusCode);

	}

	@Given("User create PUT request for program module with valid Missing ProgramName")
	public void user_create_put_request_for_program_module_with_valid_missing_program_name() {
		this.uri =Config.PUTProgramName_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid program Name");


	}

	@SuppressWarnings("unchecked")
	@When("User sends PUT http request and request body for ProgramName missing fields")
	public void user_sends_put_http_request_and_request_body_for_program_name_missing_fields() {
		JSONObject body = new JSONObject();
		body.put("programId", " ");
		body.put("programName", " ");
		body.put("programDescription", " ");
		body.put("programStatus"," ");
		body.put("creationTime", " ");
		body.put("lastModTime", " ");
		logger.info(body);

		response = this.request
				.body(body.toJSONString())
				.when()
				.put(this.uri + "/" + programName)
				.then()
				.log().all().extract().response();

		logger.info("PUT Request sent with valid request body");

	}

	@Then("User receives status code {string} Bad request for missing fileds for ProgramName")
	public void user_receives_status_code_bad_request_for_missing_fileds_for_program_name(String statusCode) {

		int Putstatuscode = response.getStatusCode();
		if (Putstatuscode == 404) {
			response.then().statusCode(Integer.parseInt(statusCode));
			logger.info("PUT Request 404 not found");
		}
		else 
			logger.info("PUT Request unsuccessful with status code " + statusCode);

	}



}
