package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.junit.Assert;

import Base.baseclass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Config;

public class AssigmentPUTSD extends baseclass {

	Logger logger = LogManager.getLogger("AssigmentPUTSD.java");
	String uri;
	public RequestSpecification request;
	Response response;

	// <=========Scenario: Verify put request forLMS API endpoint and Valid
	// Assignment Id=======>
	@SuppressWarnings("unchecked")
	@Given("User set PUT Request for the LMS API endpoint and Valid Assignment Id")
	public void user_set_put_request_for_the_lms_api_endpoint_and_valid_assignment_id() {

		JSONObject body = new JSONObject();
		body.put("assignmentDescription", "testing Login page");
		body.put("assignmentId", "3336");
		body.put("assignmentName", "Jun23-APICollectors_SDET_97");
		body.put("batchId", "6674");
		body.put("comments", "Creating by Me");
		body.put("createdBy", "U8153");
		body.put("dueDate", "2023-08-20T21:00:09.853+00:00");
		body.put("graderId", "U8153");
		body.put("pathAttachment1", "345-654-3246");
		body.put("pathAttachment2", "file1.json1");
		body.put("pathAttachment5", "file2.json");
		body.put("pathAttachment4", "file3.json");

		logger.info(body);

		this.uri = Config.Putassignment_URL;

		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
		logger.info("Put Request user module with valid base valid UserID");
		logger.info("Request = " + this.request);
	}

	@When("User sends HTTPS Request and request Body")
	public void user_sends_https_request_and_request_body() {
		// logger.info(this.uri+ "/" +"3336");
		// response = this.request.when().put(this.uri + Config.Valid_Assignment);
		response = this.request.when().put(this.uri + Config.assignmentID);
		logger.info("Config Assignmnet ID is " + Config.assignmentID);
		// response = this.request.when().put(this.uri);
		logger.info("Respone is " + response.prettyPrint());
		logger.info("Request set for Batch module with valid base URL and valid data");
	}

	@Then("User receives {string} OK Status with updated value in response body")
	public void user_receives_ok_status_with_updated_value_in_response_body(String string) {
		String s = response.then().log().all().toString();
		logger.info("Response= " + s);
		int statusCode = this.response.statusCode();
		Assert.assertEquals(200, statusCode);
		logger.info("Put resquest with with missing fields is unsuccessfull");
	}

	// <------- Request for the LMS API end point and invalid Assignment ID"----->
	@SuppressWarnings("unchecked")
	@Given("User creates PUT Request for the LMS API endpoint and invalid Assignment ID")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_invalid_assignment_id() {
		JSONObject body = new JSONObject();
		body.put("assignmentDescription", "testing Login page");
		body.put("assignmentId", "3197");
		body.put("assignmentName", "Organizers_001");
		body.put("batchId", "6407");
		body.put("comments", "null");
		body.put("createdBy", "U7821");
		body.put("dueDate", "2023-07-29T22:00:04.964+00:00");
		body.put("graderId", "U7821");

		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
	}

	@When("User sends HTTPS Request and fields")
	public void user_sends_https_request_and_fields() {
		this.uri = Config.Putassignment_URL + randomestring();
		response = this.request.when().put(this.uri);
		logger.info("Resposse inavlid assignment" + response);
	}

	@Then("User receives {string} Not Found Status")
	public void user_receives_not_found_status(String string) {
		int statusCode = this.response.statusCode();
		Assert.assertEquals(404, statusCode);
		logger.info("Put resquest with with missing fields is unsuccessfull");
	}

	// <------User sends HTTPS Request with missing mandatory fields---->
	@SuppressWarnings("unchecked")
	@Given("User PUT Request with Valid Assignment Id")
	public void user_put_request_with_valid_assignment_id() {

		JSONObject body = new JSONObject();
		body.put("assignmentDescription", "testing Login page");
		body.put("assignmentId", "3197");
		body.put("batchId", "6407");
		body.put("comments", "null");
		body.put("createdBy", "U7821");
		body.put("dueDate", "2023-07-29T22:00:04.964+00:00");
		body.put("graderId", "U7821");

		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Batch module with valid base URL and valid data");
	}

	@When("User sends HTTPS Request with missing mandatory fields")
	public void user_sends_https_request_with_missing_mandatory_fields() {
		//this.uri = Config.Putassignment_URL + Config.Valid_Assignment;
		this.uri = Config.Putassignment_URL + Config.assignmentID;
		response = this.request.when().put(this.uri);
	}

	@Then("User  {string} Bad Request Status")
	public void user_bad_request_status(String string) {
		int statusCode = this.response.statusCode();
		Assert.assertEquals(400, statusCode);
		logger.info("Put resquest with with missing fields is unsuccessfull");
	}
}
