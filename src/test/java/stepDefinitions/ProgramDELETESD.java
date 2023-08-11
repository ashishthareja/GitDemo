package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import Base.baseclass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Config;

public class ProgramDELETESD extends baseclass {
	String uri;
	public RequestSpecification request;
	Response response;
	Logger logger = LogManager.getLogger("ProgramDELETESD.java");

	// Delete request for valid program name 200
	@Given("User create delete request for program module with valid endpoint and valid program ID")
	public void user_create_delete_request_for_program_module_with_valid_endpoint_and_valid_program_id() {
		{
			this.uri = Config.DELETEProgramID_URL;
			this.request = RestAssured.given().header("Content-Type", "application/json");
			logger.info("Request set for Program module with valid base URL and valid data");

		}
	}

	@When("User sends DELETE Http request from valid program ID")
	public void user_sends_delete_http_request_from_valid_program_id() {

		String GetProgramIDvalue = Config.programId;
		logger.info("GETREQUESTPRINTProgramIDvalue= " + GetProgramIDvalue);
		response = this.request.when().delete(Config.DELETEProgramID_URL + "/" + GetProgramIDvalue).then().log().all()
				.extract().response();

	}

	@Then("User sucessfully receives status code {string} with valid reponse body for delete request")
	public void user_sucessfully_receives_status_code_with_valid_reponse_body_for_delete_request(String statuscode) {

		logger.info(response.asPrettyString());
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

	// delete program ID 404
	@Given("User able to delete request for program module with valid endpoint and valid program ID")
	public void user_able_to_delete_request_for_program_module_with_valid_endpoint_and_valid_program_id() {
		this.uri = Config.DELETEProgramID_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid data");

	}

	@When("User sends DELETE http request data from valid program ID")
	public void user_sends_delete_http_request_data_from_valid_program_id() {

		String GetProgramIDvalue = Config.programId;

		logger.info("GETREQUESTPRINTProgramIDvalue= " + GetProgramIDvalue);
		response = this.request.when().delete(Config.DELETEProgramID_URL + "/" + GetProgramIDvalue).then().log().all()
				.extract().response();
	}

	@Then("User sucessfully receives status code {string} with valid reponse body for delete")
	public void user_sucessfully_receives_status_code_with_valid_reponse_body_for_delete(String statusCode) {
		int Poststatuscode = response.getStatusCode();
		if (Poststatuscode == 400) {
			response.then().statusCode(Integer.parseInt(statusCode));
			logger.info("Delete Request should recive 400 bad request");
		} else
			logger.info("Delete Request unsuccessful with status code " + statusCode);

	}

	// Delete by program name
	@Given("User create delete request for program module with valid endpoint and valid program name")
	public void user_create_delete_request_for_program_module_with_valid_endpoint_and_valid_program_name() {
		this.uri = Config.DELETEProgramName_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid data");
	}

	@When("User sends DELETE Http request data from valid program ID for from {string} and {string}")
	public void user_sends_delete_http_request_data_from_valid_program_id_for_from_and(String SheetName,
			String TestCaseName) throws InvalidFormatException, IOException {
		logger.info("POSTTestCase Name= " + SheetName);
		logger.info("POSTTestCase Name= " + TestCaseName);

		ArrayList<String> programName = excelValue(SheetName, TestCaseName);
		response = this.request.when().delete(Config.DELETEProgramName_URL + "/" + programName.get(1)).then().log()
				.all().extract().response();
	}

	@Then("User sucessfully receives status code {string} with valid reponse for delete request")
	public void user_sucessfully_receives_status_code_with_valid_reponse_for_delete_request(String statuscode) {
		logger.info(response.asPrettyString());
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

	// Delete invalid program name
	@Given("User create delete request for program module with valid endpoint and invalid program name")
	public void user_create_delete_request_for_program_module_with_valid_endpoint_and_invalid_program_name() {
		this.uri = Config.DELETEProgramName_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid data");

	}

	@When("User sends DELETE Http request from {string} and {string}")
	public void user_sends_delete_http_request_from_and(String SheetName, String TestCaseName) throws IOException {

		logger.info("POSTTestCase Name= " + SheetName);
		logger.info("POSTTestCase Name= " + TestCaseName);

		ArrayList<String> programName = excelValue(SheetName, TestCaseName);
		response = this.request.when().delete(Config.DELETEProgramName_URL + "/" + programName.get(1)).then().log()
				.all().extract().response();

	}

	@Then("User should receives status code {string} Not found request")
	public void user_should_receives_status_code_not_found_request(String statusCode) {

		int Deletestatuscode = response.getStatusCode();
		if (Deletestatuscode == 404) {
			response.then().statusCode(Integer.parseInt(statusCode));
			logger.info("Delete Request should recive 404 Not found");
		} else
			logger.info("delete Request unsuccessful with status code " + statusCode);

	}

}
