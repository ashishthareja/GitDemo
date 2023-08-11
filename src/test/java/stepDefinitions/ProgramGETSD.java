package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.baseclass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Config;

public class ProgramGETSD extends baseclass {

	Logger logger = LogManager.getLogger("ProgramGETSD.java");
	String uri;
	public RequestSpecification request;
	Response response;

	// <---------------Get All programs-------------------->
	@Given("User create get request for program module with valid endpoint")
	public void user_create_get_request_for_program_module_with_valid_endpoint() {
		this.uri = Config.Getallprogram_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid data");
	}

	@When("User sends GET Http request data")
	public void user_sends_get_http_request_data() {
		response = this.request.get(this.uri);
		response.then().log().all();
		logger.info("when get all programs");
	}

	@Then("User sucessfully receives status code {string} with valid reponse body")
	public void user_sucessfully_receives_status_code_with_valid_reponse_body(String statusCode) {
		int GetAllstatuscode = response.getStatusCode();

		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("get all programs success");
		}
	}

	// Get request program ID
	@Given("User set GET request for program module with valid endpoint for program ID")
	public void user_set_get_request_for_program_module_with_valid_endpoint_for_program_id() {
		this.uri = Config.GetProgramID_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid data");
	}

	@When("User GET Http request by program ID from from {string} and {string}")
	public void user_get_http_request_by_program_id_from_from_and(String string, String string2) throws IOException {
		logger.info("TestCase Name= " + string);

		ArrayList<String> programId = excelValue(string, string2);

		String GetProgramIDvalue = Config.programId;

		logger.info("GETREQUESTPRINTProgramIDvalue= " + GetProgramIDvalue);
		response = this.request.when().get(Config.GetProgramID_URL + "/" + Config.programId).then().log().all()
				.extract().response();
	}

	@Then("User sucessfully receives status code {string} for valid program ID")
	public void user_sucessfully_receives_status_code_for_valid_program_id(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		logger.info("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request all Assignment data is successfull");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request unsuccessful");

		}
	}

	// Get request Invalid program ID - 404
	@Given("User Get request for programID")
	public void user_get_request_for_program_id() {
		this.uri = Config.GetProgramID_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and invalid programID");
	}

	@When("User set GET  request for invalid program ID from {string} and {string}")
	public void user_set_get_request_for_invalid_program_id_from_and(String string, String string2) throws IOException {
		logger.info("TestCase Name= " + string);

		ArrayList<String> programId = excelValue(string, string2);

		response = this.request.when().get(Config.GetProgramID_URL + "/" + randomestring()).then().log().all()
				.extract().response();
	}

	@Then("User receives status code {string} not found")
	public void user_receives_status_code_not_found(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		logger.info("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request all Assignment data is successfull");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request unsuccessful");
		}
	}
}
