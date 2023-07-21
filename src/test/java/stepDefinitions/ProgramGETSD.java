package stepDefinitions;

import io.restassured.RestAssured;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Config;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProgramGETSD {
	String uri;
	public RequestSpecification request;
	Response response;

@Given("User sets request for Program module with valid base URL")
public void user_sets_request_for_program_module_with_valid_base_url() {
    this.uri = Config.Getallprogram_URL;
    this.request = RestAssured.given().header("Content-type","application/json");
}

@When("User sends GET request")
public void user_sends_get_request() {
	response = this.request.get(this.uri);	
	response.then().log().all();
}

@Then("Request should be successfull with status code {string} for GET All programs")
public void request_should_be_successfull_with_status_code_for_get_all_programs(String statuscode) {
	//Statuscode Validation
			int GetAllstatuscode = response.getStatusCode();
			if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			//Header Validation
			response.then().assertThat().header("Connection", "keep-alive");
}

}
}