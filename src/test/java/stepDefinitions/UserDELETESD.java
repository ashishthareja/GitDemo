package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import Base.baseclass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
//import io.restassured.config.Config;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Config;

public class UserDELETESD extends baseclass {
	Logger logger = LogManager.getLogger("UserDELETESD.java");
	String uri;
	public RequestSpecification request;
	Response response;

	public static JSONObject body;
	int duserId = 8912;

	@Given("User creates DELETE Request for the LMS API endpoint  and  valid user ID")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_user_id() {
		this.uri = Config.DeleteUser_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for user module with valid base URL and valid data");
	}

	@When("User sends Delete HTTPS Request")
	public void user_sends_delete_https_request() {
		response = this.request.delete(this.uri + "/" + Config.studentUserID);
		logger.info("Url" + (this.uri + "/" + Config.studentUserID));
		logger.info("Response is= " + response.asString());
		response.then().log().all();
	}

	@Then("User receives {string} Ok status with message for Delete request with valid userId")
	public void user_receives_ok_status_with_message_for_delete_request_with_valid_user_id(String statuscode) {
		int Delstatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Delstatuscode);
		if (Delstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Post Request Successful");
		} else if (Delstatuscode == 404) {
			logger.info("User Delete Request unsuccesful");
		}
	}

	@Given("User creates DELETE Request for the LMS API endpoint  and  invalid \\{user ID}")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_invalid() {
		this.uri = Config.DeleteUser_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for user module with valid base URL and valid data");
	}

	@When("User sends Delete HTTPS Request with invalid userID")
	public void user_sends_delete_https_request_with_invalid_user_id() {
		response = this.request.delete(this.uri + "/" + duserId);
		logger.info("Url" + (this.uri + "/" + duserId));
		logger.info("Response is= " + response.asString());
		response.then().log().all();
	}

	@Then("User gets {string} Not Found Status with message")
	public void user_gets_not_found_status_with_message(String statuscode) {
		int Delstatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Delstatuscode);
		if (Delstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Post Request Successful");
		} else if (Delstatuscode == 404) {
			logger.info("UserId is invalid " + duserId);
			logger.info("User Delete Request unsuccesful");
		}
	}
}
