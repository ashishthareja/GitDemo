package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;

import Base.baseclass;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Config;

public class UserAllGETSD extends baseclass{
	
	Logger logger = LogManager.getLogger("SD_Asssignment POST Method.java");

	String uri;
	public RequestSpecification request;
	JsonObject jsonObject;
	String jsonAsString;
	Response response;

//@User_Get_01
	// Scenario: Check if user able to retrieve all users with valid LMS API
	@Given("User creates GET Request for the LMS API All User endpoint")
	public void user_creates_get_request_for_the_lms_api_all_user_endpoint() {
		this.uri = Config.GetallUsers_URL;
		logger.info("URL= " + uri);
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}

	@When("User sends Https Request")
	public void user_sends_https_request() {
		  response = this.request.get(this.uri);
			logger.info("Response is= " + response.asString());
			response.then().log().all();
	}

	@Then("User recieves {string} OK Status")
	public void user_recieves_ok_status(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		logger.info("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("User Get Request for all users is successful");
		/*	//Json Schema Validation
			response.then().assertThat()
			.body(JsonSchemaValidator.matchesJsonSchema(new File("GetallUsers.json")));*/ 
			//response.then().assertThat()
		//	.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("GetallUsers.json"));
			
		} else if (GetAllstatuscode == 404) {
			logger.info("User Get Request unsuccessful");
			}
	}
	
	//@User_Get_02
	// Scenario: Verify user able to retrieve a record with valid UserID
	@Given("User send GET Request for the LMS API endpoint with valid User ID")
	public void user_send_get_request_for_the_lms_api_endpoint_with_valid_user_id() {
		this.uri = Config.GetallUsers_URL;
		logger.info("URL= " + uri);
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}

	@When("User GET request with valid UserID from {string} and {string}")
	public void user_get_request_with_valid_user_id_from_and(String string, String string2) throws IOException {
		logger.info("TestCase Name= " + string);
		String userId = excelDataValue(string, string2);
		logger.info(" Valid UserID " + "userIdGet");
		logger.info("Test URL" +(Config.GetallUsers_URL + "/" + Config.userID));

		response = this.request.when().get(Config.GetallUsers_URL + "/" + Config.userID).then().log().all()
				.extract().response();
	}

	@Then("User gets {string} Status code in response body.")
	public void user_gets_status_code_in_response_body(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		logger.info("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request for valid userId is successful");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request for valid userId is unsuccessful");
		  }
	}
	
	//@User_Get_03  
	//negative Scenario: Check if user able to retrieve a user with invalid User ID
	@Given("User creates GET Request for the LMS API endpoint with invalid User ID")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_user_id() {
		this.uri = Config.GetallUsers_URL;
		logger.info("URL= " + uri);
		this.request = RestAssured.given().header("Content-Type", "application/json");

	}

	@When("User create GET request with invalid UserID from {string} and {string}")
	public void user_create_get_request_with_invalid_user_id_from_and(String string, String string2) throws IOException {
		logger.info("TestCase Name= " + string);

		String invalidUserId = excelDataValue(string, string2);
		response = this.request.when().get(Config.GetallUsers_URL + "/" + invalidUserId).then().log().all()
				.extract().response();
	}

	@Then("User receives {string} Not Found Status with message")
	public void user_receives_not_found_status_with_message(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		logger.info("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request for invalid userId is successful");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request for invalid userId is unsuccessful");
				  }
	}

	//@User_Get_04
    //Scenario: Check if user able to retrieve staff with valid LMS API
	@Given("User creates GET Request for the LMS API All Staff endpoint")
	public void user_creates_get_request_for_the_lms_api_all_staff_endpoint() {
		this.uri = Config.GetUsersallstaff_URL;
		logger.info("URL= " + uri);
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}

	@When("User sends HTTPS Request to get all staff")
	public void user_sends_https_request_to_get_all_staff() {
		response = this.request.get(this.uri);
		logger.info("Response is= " + response.asString());
		response.then().log().all(); 
	}

	@Then("User recieve {string} OK Status with response body.")
	public void user_recieve_ok_status_with_response_body(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		logger.info("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request for invalid userId is successful");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request for invalid userId is unsuccessful");
					  } 
	}

	// @User_Get_05
    // Scenario: Check if user able to retrieve a user with roles valid LMS API
	@Given("User creates GET Request for the LMS API User Roles endpoint")
	public void user_creates_get_request_for_the_lms_api_user_roles_endpoint() {
		this.uri = Config.GetUsersallroles_URL;
		logger.info("URL= " + uri);
		this.request = RestAssured.given().header("Content-Type", "application/json"); 
	}

	@When("User sends HTTPS Requestto get roles")
	public void user_sends_https_requestto_get_roles() {
		response = this.request.get(this.uri);
		logger.info("Response is= " + response.asString());
		response.then().log().all();
	}

	@Then("User receives {string} Status code with response body.")
	public void user_receives_status_code_with_response_body(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		logger.info("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode)).log().all();
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request for invalid userId is successful");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request for invalid userId is unsuccessful");
			throw new io.cucumber.java.PendingException();
		  } 
	}

	//@User_Get_06 (new) 
    //Negative Scenario: Check if user able to retrieve all users with invalid LMS API
	@Given("User creates GET Request for the LMS API All User endpoint with invalid endpoint")
	public void user_creates_get_request_for_the_lms_api_all_user_endpoint_with_invalid_endpoint() {
		this.uri = Config.GetallUsersinvalid_URL;
		logger.info("URL= " + uri);
		this.request = RestAssured.given().header("Content-Type", "application/json");   
	}

	@When("User sends get Https Request")
	public void user_sends_get_https_request() {
		response = this.request.get(this.uri);
		logger.info("Response is= " + response.asString());
		response.then().log().all(); 
	}

	@Then("User recieves {string} status code with not found error message")
	public void user_recieves_status_code_with_not_found_error_message(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		logger.info("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode)).log().all();
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request for invalid userId is successful");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request for invalid userId is unsuccessful");
					  }
	}

	//@User_Get_07 (new)
			//negative Scenario: Check if user able to retrieve a user with roles invalid LMS API
	@Given("User creates GET Request for the LMS API User Roles with invalid endpoint")
	public void user_creates_get_request_for_the_lms_api_user_roles_with_invalid_endpoint() {
		this.uri = Config.GetUserwithrolesinvalid_URL;
		logger.info("URL= " + uri);
		this.request = RestAssured.given().header("Content-Type", "application/json");  
	}

	@When("User sends HTTPS Request to get roles using invalid endpont")
	public void user_sends_https_request_to_get_roles_using_invalid_endpont() {
		response = this.request.get(this.uri);
		logger.info("Response is= " + response.asString());
		response.then().log().all();
	}

	@Then("User receives {string} Status code with not found message in response body.")
	public void user_receives_status_code_with_not_found_message_in_response_body(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		logger.info("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode)).log().all();
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request for invalid userId is successful");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request for invalid userId is unsuccessful");
					  }
	}
}
