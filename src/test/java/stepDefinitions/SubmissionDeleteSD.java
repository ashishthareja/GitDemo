package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import Base.baseclass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.Config;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SubmissionDeleteSD extends baseclass {
	String uri;
	int submissionid;
	// String assignmentId;
	// String userId;
	// String submissionId;
	public RequestSpecification request;
	Response response;

	static Logger logger = LogManager.getLogger("SubmissionDeleteSD.java");

	@Given("User send GET Request for the LMS API endpoint with valid submission submissionId")
	public void user_send_get_request_for_the_lms_api_endpoint_with_valid_submission_submission_id() {
		this.uri = Config.Delete_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Delete request for valid submission id");
	}

	@When("User create GET request with valid  submissionId from {string} and {string}")
	public void user_create_get_request_with_valid_submission_id_from_and(String string, String string2) {
		logger.info("Config.Delete_URL + \"/\" + Config.submissionid");
		response = this.request.when().delete(Config.Delete_URL + "/" + Config.submissionid).then().log().all()
				.extract().response();

	}

	@Then("User receives {string} OK Status for valid submission submissionId")
	public void user_receives_ok_status_for_valid_submission_submission_id(String statuscode) {
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

	@Given("User send GET Request for the LMS API endpoint with invalid submissionId")
	public void user_send_get_request_for_the_lms_api_endpoint_with_invalid_submission_id() {
		this.uri = Config.Deleteinvalid_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Delete request for invalid submission id");
	}

	@When("User create GET request with invalid  submissionId from {string} and {string}")
	public void user_create_get_request_with_invalid_submission_id_from_and(String string, String string2) {
		// String getSubmissionIdvalue = SubmissionPOSTSD.getSubmissionIdvalue();
		// logger.info("GETREQUESTPRINTProgramIDvalue= " + getSubmissionIdvalue);
		logger.info("Config.Delete_URL");
		response = this.request.when().delete(Config.Delete_URL + "/" + randomestring()).then().log().all().extract()
				.response();
	}

	@Then("User receives {string} Status for invalid submission submissionId")
	public void user_receives_ok_status_for_invalid_submission_submission_id(String statuscode) {
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

	@Given("User send GET Request for the LMS API endpoint with empty submissionId")
	public void user_send_get_request_for_the_lms_api_endpoint_with_empty_submission_id() {
		this.uri = Config.Delete_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Delete request for empty submission id");
	}

	@When("User create GET request with empty  submissionId from {string} and {string}")
	public void user_create_get_request_with_empty_submission_id_from_and(String string, String string2) {

		response = this.request.when().delete(Config.Delete_URL + "/").then().log().all().extract().response();
	}

	@Then("User receives {string} OK Status for empty submission submissionId")
	public void user_receives_ok_status_for_empty_submission_submission_id(String statuscode) {
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

	@Given("User send GET Request for the LMS API endpoint with invalid url and valid submissionId")
	public void user_send_get_request_for_the_lms_api_endpoint_with_invalid_url_and_valid_submission_id() {
		this.uri = Config.Delete_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Delete request for invalid url and valid submission id");
	}

	@When("User create GET request with invalid url and valid submissionId from {string} and {string}")
	public void user_create_get_request_with_invalid_url_and_valid_submission_id_from_and(String string,
			String string2) {

		logger.info("SubmissionIdvalue= " + Config.submissionid);
		response = this.request.when().delete(Config.Delete_URL + "/" + Config.submissionid).then().log().all()
				.extract().response();
	}

	@Then("User receives {string} Status for invalid url valid submissionId")
	public void user_receives_status_for_invalid_url_valid_submission_id(String statuscode) {
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
}
