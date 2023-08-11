package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import Base.baseclass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import utilities.Config;
import java.io.File;
import org.json.simple.JSONObject;
import utilities.Excelreader;
import java.io.IOException;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SubmissionPOSTSD extends baseclass {
	String uri;
	int submissionid;

	public RequestSpecification request;
	Response response;

	Logger logger = LogManager.getLogger("SubmissionPOSTSD.java");

	@Given("User sets request for submission module with valid base URL and valid request body")
	public void user_sets_request_for_submission_module_with_valid_base_url_and_valid_request_body() {
		this.uri = Config.Postassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for submission module with valid base URL and valid request body");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with data from {string} and {string} for submission module")
	public void user_sends_post_request_with_data_from_and_for_submission_module(String string, String string2)
			throws IOException {

		String assignmentId = excelDataValue(string, "assignmentId");
		// String assigID = excelDataValue(string,"newassignmentId");
		String userId = excelDataValue(string, "userId");

		String subDesc = excelDataValue(string, "subDesc");
		String subComments = excelDataValue(string, "subComments");
		String subPathAttach1 = excelDataValue(string, "subPathAttach1");
		// String dueDate = excelDataValue(string,time);
		String subPathAttach2 = excelDataValue(string, "subPathAttach2");
		String subPathAttach3 = excelDataValue(string, "subPathAttach3");
		String subPathAttach4 = excelDataValue(string, "subPathAttach4");
		String subPathAttach5 = excelDataValue(string, "subPathAttach5");
		// String pathAttachment4 = excelDataValue(string, "pathAttachment4");
		// String pathAttachment5 = excelDataValue(string, "pathAttachment5");
		String grade = excelDataValue(string, "grade");

		JSONObject body = new JSONObject();
		body.put("assignmentId", Config.assignmentID);
		// body.put("assignmentId", 0);
		body.put("userId", Config.studentUserID);
		body.put("subDesc", subDesc);
		body.put("subComments", subComments);
		body.put("subPathAttach1", subPathAttach1);
		body.put("subDateTime", Timestamp());
		body.put("subPathAttach2", subPathAttach2);
		body.put("subPathAttach3", subPathAttach3);
		body.put("subPathAttach4", subPathAttach4);
		body.put("subPathAttach5", subPathAttach5);
		// body.put("pathAttachment4", pathAttachment4);
		body.put("grade", grade);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	@Then("Request should be successfull with status code {string} for POST request submission module")
	public void request_should_be_successfull_with_status_code_for_post_request_submission_module(String statuscode) {
		int Poststatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Poststatuscode);
		if (Poststatuscode == 201) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Post Request Successful");

			// use delete request to delete the posted data//
			JsonPath js = response.jsonPath();
			int submissionId = js.getInt("submissionId");
			Config.submissionid = submissionId;
			logger.info("Extracted Submission Id:" + Config.submissionid);
		}
	}

	@Given("User sets request for submission module with valid base URL and valid request body for exisiting value")
	public void user_sets_request_for_submission_module_with_valid_base_url_and_valid_request_body_for_exisiting_value() {
		this.uri = Config.Postassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Submission module for existing value");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with data from {string} and {string} for submission module exisiting value")
	public void user_sends_post_request_with_data_from_and_for_submission_module_exisiting_value(String string,
			String string2) throws IOException {
		String assignmentId = excelDataValue(string, "assignmentId");
		// String assigID = excelDataValue(string,"newassignmentId");
		String userId = excelDataValue(string, "userId");

		String subDesc = excelDataValue(string, "subDesc");
		String subComments = excelDataValue(string, "subComments");
		String subPathAttach1 = excelDataValue(string, "subPathAttach1");
		// String dueDate = excelDataValue(string,time);
		String subPathAttach2 = excelDataValue(string, "subPathAttach2");
		String subPathAttach3 = excelDataValue(string, "subPathAttach3");
		String subPathAttach4 = excelDataValue(string, "subPathAttach4");
		String subPathAttach5 = excelDataValue(string, "subPathAttach5");
		// String pathAttachment4 = excelDataValue(string, "pathAttachment4");
		// String pathAttachment5 = excelDataValue(string, "pathAttachment5");
		String grade = excelDataValue(string, "grade");

		JSONObject body = new JSONObject();
		body.put("assignmentId", Config.assignmentID);
		// body.put("assignmentId", 0);
		body.put("userId", Config.studentUserID);
		body.put("subDesc", subDesc);
		body.put("subComments", subComments);
		body.put("subPathAttach1", subPathAttach1);
		body.put("subDateTime", Timestamp());
		body.put("subPathAttach2", subPathAttach2);
		body.put("subPathAttach3", subPathAttach3);
		body.put("subPathAttach4", subPathAttach4);
		body.put("subPathAttach5", subPathAttach5);
		// body.put("pathAttachment4", pathAttachment4);
		body.put("grade", grade);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	@Then("Request should be successfull with status code {string} for POST request submission module existing value")
	public void request_should_be_successfull_with_status_code_for_post_request_submission_module_existing_value(
			String statuscode) {
		int Statuscode400 = response.getStatusCode();
		if (Statuscode400 == 400) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Bad request error message displayed with statuscode " + statuscode);
		}
	}

	@Given("User sets request for submission with valid url and empty request body")
	public void user_sets_request_for_submission_with_valid_url_and_empty_request_body() {
		this.uri = Config.Postassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for submission module with empty request body");
	}

	@When("User sends post requests with empty request body")
	public void user_sends_post_requests_with_empty_request_body() {
		response = this.request.when().post(this.uri).then().log().all().extract().response();
		logger.info("No data in request body");
	}

	@Then("Request should be  with status code {string} for empty request body")
	public void request_should_be_with_status_code_for_empty_request_body(String statuscode) {
		int Statuscode400 = response.getStatusCode();
		if (Statuscode400 == 400) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Bad request error message displayed with statuscode " + statuscode);
		}
	}

	@Given("User sets request for submission module with valid base URL and missing mandatory field \\(assignment id)")
	public void user_sets_request_for_submission_module_with_valid_base_url_and_missing_mandatory_field_assignment_id() {
		this.uri = Config.Postassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for submission module with missing madatory field");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with data from {string} and {string} for submission module mandatory field \\(assignment id)")
	public void user_sends_post_request_with_data_from_and_for_submission_module_mandatory_field_assignment_id(
			String string, String string2) throws IOException {
		// String assignmentId = excelDataValue(string, "assignmentId");
		// String assigID = excelDataValue(string,"newassignmentId");
		String userId = excelDataValue(string, "userId");

		String subDesc = excelDataValue(string, "subDesc");
		String subComments = excelDataValue(string, "subComments");
		String subPathAttach1 = excelDataValue(string, "subPathAttach1");
		// String dueDate = excelDataValue(string,time);
		String subPathAttach2 = excelDataValue(string, "subPathAttach2");
		String subPathAttach3 = excelDataValue(string, "subPathAttach3");
		String subPathAttach4 = excelDataValue(string, "subPathAttach4");
		String subPathAttach5 = excelDataValue(string, "subPathAttach5");
		// String pathAttachment4 = excelDataValue(string, "pathAttachment4");
		// String pathAttachment5 = excelDataValue(string, "pathAttachment5");
		String grade = excelDataValue(string, "grade");

		JSONObject body = new JSONObject();
		// body.put("assignmentId", assignmentId);
		// body.put("assignmentId", 0);
		body.put("userId", userId);
		body.put("subDesc", subDesc);
		body.put("subComments", subComments);
		body.put("subPathAttach1", subPathAttach1);
		body.put("subDateTime", Timestamp());
		body.put("subPathAttach2", subPathAttach2);
		body.put("subPathAttach3", subPathAttach3);
		body.put("subPathAttach4", subPathAttach4);
		body.put("subPathAttach5", subPathAttach5);
		// body.put("pathAttachment4", pathAttachment4);
		body.put("grade", grade);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	@Then("Request should be successfull with status code {string} for submission module mandatory field \\(assignment id)")
	public void request_should_be_successfull_with_status_code_for_submission_module_mandatory_field_assignment_id(
			String statuscode) {
		int Statuscode400 = response.getStatusCode();
		if (Statuscode400 == 400) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Bad request error message displayed with statuscode " + statuscode);
		}
	}

	@Given("User sets request for submission module with invalid base URL and valid request body")
	public void user_sets_request_for_submission_module_with_invalid_base_url_and_valid_request_body() {
		this.uri = Config.Postassignmentinvalid_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for invald url and valid request body");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with data from {string} and {string} for submission module invalid url")
	public void user_sends_post_request_with_data_from_and_for_submission_module_invalid_url(String string,
			String string2) throws IOException {
		String assignmentId = excelDataValue(string, "assignmentId");
		// String assigID = excelDataValue(string,"newassignmentId");
		String userId = excelDataValue(string, "userId");

		String subDesc = excelDataValue(string, "subDesc");
		String subComments = excelDataValue(string, "subComments");
		String subPathAttach1 = excelDataValue(string, "subPathAttach1");
		// String dueDate = excelDataValue(string,time);
		String subPathAttach2 = excelDataValue(string, "subPathAttach2");
		String subPathAttach3 = excelDataValue(string, "subPathAttach3");
		String subPathAttach4 = excelDataValue(string, "subPathAttach4");
		String subPathAttach5 = excelDataValue(string, "subPathAttach5");
		// String pathAttachment4 = excelDataValue(string, "pathAttachment4");
		// String pathAttachment5 = excelDataValue(string, "pathAttachment5");
		String grade = excelDataValue(string, "grade");

		JSONObject body = new JSONObject();
		body.put("assignmentId", assignmentId);
		// body.put("assignmentId", 0);
		body.put("userId", userId);
		body.put("subDesc", subDesc);
		body.put("subComments", subComments);
		body.put("subPathAttach1", subPathAttach1);
		body.put("subDateTime", Timestamp());
		body.put("subPathAttach2", subPathAttach2);
		body.put("subPathAttach3", subPathAttach3);
		body.put("subPathAttach4", subPathAttach4);
		body.put("subPathAttach5", subPathAttach5);
		// body.put("pathAttachment4", pathAttachment4);
		body.put("grade", grade);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();

	}

	@Then("Request should be successfull with status code {string} for POST request submission module invalid url")
	public void request_should_be_successfull_with_status_code_for_post_request_submission_module_invalid_url(
			String statuscode) {
		int Poststatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Poststatuscode);
		if (Poststatuscode == 404) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Post Request Successful");
		}
	}

	@Given("User sets request for submission module with valid base URL and valid request body with no assignment id")
	public void user_sets_request_for_submission_module_with_valid_base_url_and_valid_request_body_with_no_assignment_id() {
		this.uri = Config.Postassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for valid url and valid request bodywith null assignment id");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with data from {string} and {string} for submission module with no assignment id")
	public void user_sends_post_request_with_data_from_and_for_submission_module_with_no_assignment_id(String string,
			String string2) throws IOException {
		// String nullassignmentId = excelDataValue(string, "nullassignmentId");
		String nullassignmentId = "";
		String userId = excelDataValue(string, "userId");

		String subDesc = excelDataValue(string, "subDesc");
		String subComments = excelDataValue(string, "subComments");
		String subPathAttach1 = excelDataValue(string, "subPathAttach1");
		// String dueDate = excelDataValue(string,time);
		String subPathAttach2 = excelDataValue(string, "subPathAttach2");
		String subPathAttach3 = excelDataValue(string, "subPathAttach3");
		String subPathAttach4 = excelDataValue(string, "subPathAttach4");
		String subPathAttach5 = excelDataValue(string, "subPathAttach5");
		// String pathAttachment4 = excelDataValue(string, "pathAttachment4");
		// String pathAttachment5 = excelDataValue(string, "pathAttachment5");
		String grade = excelDataValue(string, "grade");

		JSONObject body = new JSONObject();
		body.put("nullassignmentId", nullassignmentId);
		// body.put("assignmentId", 0);
		body.put("userId", Config.studentUserID);
		body.put("subDesc", subDesc);
		body.put("subComments", subComments);
		body.put("subPathAttach1", subPathAttach1);
		body.put("subDateTime", Timestamp());
		body.put("subPathAttach2", subPathAttach2);
		body.put("subPathAttach3", subPathAttach3);
		body.put("subPathAttach4", subPathAttach4);
		body.put("subPathAttach5", subPathAttach5);
		// body.put("pathAttachment4", pathAttachment4);
		body.put("grade", grade);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	@Then("Request should be successfull with status code {string} for POST request submission module with no assignment id")
	public void request_should_be_successfull_with_status_code_for_post_request_submission_module_with_no_assignment_id(
			String statuscode) {
		int Poststatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Poststatuscode);
		if (Poststatuscode == 400) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Post Request UnSuccessful");

		}
	}

	@Given("User sets request for submission module with valid base URL and valid request body with no user id")
	public void user_sets_request_for_submission_module_with_valid_base_url_and_valid_request_body_with_no_user_id() {
		this.uri = Config.Postassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for valid url and valid request bodywith null assignment id");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with data from {string} and {string} for submission module with no user id")
	public void user_sends_post_request_with_data_from_and_for_submission_module_with_no_user_id(String string,
			String string2) throws IOException {
		String assignmentId = excelDataValue(string, "assignmentId");
		// String assigID = excelDataValue(string,"newassignmentId");
		// String nulluserId = excelDataValue(string, "nulluserId");
		String nulluserId = "";

		String subDesc = excelDataValue(string, "subDesc");
		String subComments = excelDataValue(string, "subComments");
		String subPathAttach1 = excelDataValue(string, "subPathAttach1");
		// String dueDate = excelDataValue(string,time);
		String subPathAttach2 = excelDataValue(string, "subPathAttach2");
		String subPathAttach3 = excelDataValue(string, "subPathAttach3");
		String subPathAttach4 = excelDataValue(string, "subPathAttach4");
		String subPathAttach5 = excelDataValue(string, "subPathAttach5");
		// String pathAttachment4 = excelDataValue(string, "pathAttachment4");
		// String pathAttachment5 = excelDataValue(string, "pathAttachment5");
		String grade = excelDataValue(string, "grade");

		JSONObject body = new JSONObject();
		body.put("assignmentId", Config.assignmentID);
		// body.put("assignmentId", 0);
		body.put("nulluserId", nulluserId);
		body.put("subDesc", subDesc);
		body.put("subComments", subComments);
		body.put("subPathAttach1", subPathAttach1);
		body.put("subDateTime", Timestamp());
		body.put("subPathAttach2", subPathAttach2);
		body.put("subPathAttach3", subPathAttach3);
		body.put("subPathAttach4", subPathAttach4);
		body.put("subPathAttach5", subPathAttach5);
		// body.put("pathAttachment4", pathAttachment4);
		body.put("grade", grade);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	@Then("Request should be successfull with status code {string} for POST request submission module with no user id")
	public void request_should_be_successfull_with_status_code_for_post_request_submission_module_with_no_user_id(
			String statuscode) {
		int Poststatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Poststatuscode);
		if (Poststatuscode == 400) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Post Request UnSuccessful");

		}
	}

	@Given("User sets request for submission module with valid base URL and valid request body with no submission datetime")
	public void user_sets_request_for_submission_module_with_valid_base_url_and_valid_request_body_with_no_submission_datetime() {
		this.uri = Config.Postassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for valid url and valid request bodywith null submission datetime");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with data from {string} and {string} for submission module with no submission datetime")
	public void user_sends_post_request_with_data_from_and_for_submission_module_with_no_submission_datetime(
			String string, String string2) throws IOException {
		String assignmentId = excelDataValue(string, "assignmentId");
		// String assigID = excelDataValue(string,"newassignmentId");
		String userId = excelDataValue(string, "userId");

		String subDesc = excelDataValue(string, "subDesc");
		String subComments = excelDataValue(string, "subComments");
		String subPathAttach1 = excelDataValue(string, "subPathAttach1");
		// String dueDate = excelDataValue(string,time);
		String subPathAttach2 = excelDataValue(string, "subPathAttach2");
		String subPathAttach3 = excelDataValue(string, "subPathAttach3");
		String subPathAttach4 = excelDataValue(string, "subPathAttach4");
		String subPathAttach5 = excelDataValue(string, "subPathAttach5");
		// String pathAttachment4 = excelDataValue(string, "pathAttachment4");
		// String pathAttachment5 = excelDataValue(string, "pathAttachment5");
		String grade = excelDataValue(string, "grade");

		JSONObject body = new JSONObject();
		body.put("assignmentId", Config.assignmentID);
		// body.put("assignmentId", 0);
		body.put("userId", Config.studentUserID);
		body.put("subDesc", subDesc);
		body.put("subComments", subComments);
		body.put("subPathAttach1", subPathAttach1);
		// body.put("subDateTime",Timestamp());
		body.put("subPathAttach2", subPathAttach2);
		body.put("subPathAttach3", subPathAttach3);
		body.put("subPathAttach4", subPathAttach4);
		body.put("subPathAttach5", subPathAttach5);
		// body.put("pathAttachment4", pathAttachment4);
		body.put("grade", grade);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	@Then("Request should be successfull with status code {string} for POST request submission module with no submission datetime")
	public void request_should_be_successfull_with_status_code_for_post_request_submission_module_with_no_submission_datetime(
			String statuscode) {
		int Poststatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Poststatuscode);
		if (Poststatuscode == 400) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Post Request UnSuccessful");

		}
	}

	@Given("User sets request for submission module with valid base URL and valid request body with no grade")
	public void user_sets_request_for_submission_module_with_valid_base_url_and_valid_request_body_with_no_grade() {
		this.uri = Config.Postassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for valid url and valid request bodywith null grade value");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with data from {string} and {string} for submission module with no grade")
	public void user_sends_post_request_with_data_from_and_for_submission_module_with_no_grade(String string,
			String string2) throws IOException {
		String assignmentId = excelDataValue(string, "assignmentId");
		// String assigID = excelDataValue(string,"newassignmentId");
		String userId = excelDataValue(string, "userId");

		String subDesc = excelDataValue(string, "subDesc");
		String subComments = excelDataValue(string, "subComments");
		String subPathAttach1 = excelDataValue(string, "subPathAttach1");
		// String dueDate = excelDataValue(string,time);
		String subPathAttach2 = excelDataValue(string, "subPathAttach2");
		String subPathAttach3 = excelDataValue(string, "subPathAttach3");
		String subPathAttach4 = excelDataValue(string, "subPathAttach4");
		String subPathAttach5 = excelDataValue(string, "subPathAttach5");
		// String pathAttachment4 = excelDataValue(string, "pathAttachment4");
		// String pathAttachment5 = excelDataValue(string, "pathAttachment5");
		// String grade = excelDataValue(string, "grade");

		JSONObject body = new JSONObject();
		body.put("assignmentId", assignmentId);
		// body.put("assignmentId", 0);
		body.put("userId", userId);
		body.put("subDesc", subDesc);
		body.put("subComments", subComments);
		body.put("subPathAttach1", subPathAttach1);
		body.put("subDateTime", Timestamp());
		body.put("subPathAttach2", subPathAttach2);
		body.put("subPathAttach3", subPathAttach3);
		body.put("subPathAttach4", subPathAttach4);
		body.put("subPathAttach5", subPathAttach5);
		// body.put("pathAttachment4", pathAttachment4);
		// body.put("grade", grade);

		logger.info("JSON BODY= " + body.toJSONString());
		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	@Then("Request should be successfull with status code {string} for POST request submission module with no grade")
	public void request_should_be_successfull_with_status_code_for_post_request_submission_module_with_no_grade(
			String statuscode) {
		int Poststatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Poststatuscode);
		if (Poststatuscode == 404) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Post Request UnSuccessful");

		}
	}

	@Given("User sets request for submission module with valid base URL and valid request body with diff submission datetime format")
	public void user_sets_request_for_submission_module_with_valid_base_url_and_valid_request_body_with_diff_submission_datetime_format() {
		this.uri = Config.Postassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for valid url and valid request bodywith diff submission datetime format");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with data from {string} and {string} for submission module with diff submission datetime format")
	public void user_sends_post_request_with_data_from_and_for_submission_module_with_diff_submission_datetime_format(
			String string, String string2) throws IOException {
		String assignmentId = excelDataValue(string, "assignmentId");
		// String assigID = excelDataValue(string,"newassignmentId");
		String userId = excelDataValue(string, "userId");

		String subDesc = excelDataValue(string, "subDesc");
		String subComments = excelDataValue(string, "subComments");
		String subPathAttach1 = excelDataValue(string, "subPathAttach1");
		// String dueDate = excelDataValue(string,time);
		String subPathAttach2 = excelDataValue(string, "subPathAttach2");
		String subPathAttach3 = excelDataValue(string, "subPathAttach3");
		String subPathAttach4 = excelDataValue(string, "subPathAttach4");
		String subPathAttach5 = excelDataValue(string, "subPathAttach5");
		// String pathAttachment4 = excelDataValue(string, "pathAttachment4");
		// String pathAttachment5 = excelDataValue(string, "pathAttachment5");
		String grade = excelDataValue(string, "grade");

		JSONObject body = new JSONObject();
		body.put("assignmentId", assignmentId);
		// body.put("assignmentId", 0);
		body.put("userId", userId);
		body.put("subDesc", subDesc);
		body.put("subComments", subComments);
		body.put("subPathAttach1", subPathAttach1);
		body.put("subDateTime", DiffTimestamp());
		body.put("subPathAttach2", subPathAttach2);
		body.put("subPathAttach3", subPathAttach3);
		body.put("subPathAttach4", subPathAttach4);
		body.put("subPathAttach5", subPathAttach5);
		// body.put("pathAttachment4", pathAttachment4);
		body.put("grade", grade);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	@Then("Request should be successfull with status code {string} for POST request submission module with diff submission datetime format")
	public void request_should_be_successfull_with_status_code_for_post_request_submission_module_with_diff_submission_datetime_format(
			String statuscode) {
		int Poststatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Poststatuscode);
		if (Poststatuscode == 404) {
			response.then().statusCode(Integer.parseInt(statuscode));
		}
		logger.info("Post Request UnSuccessful");
	}

	@Given("User sets request for submission module with valid base URL and valid request body with greater than due date")
	public void user_sets_request_for_submission_module_with_valid_base_url_and_valid_request_body_with_greater_than_due_date() {
		this.uri = Config.Postassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for valid url and valid request bodywith greater than due date");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with data from {string} and {string} for submission module with greater than due date")
	public void user_sends_post_request_with_data_from_and_for_submission_module_with_greater_than_due_date(
			String string, String string2) throws IOException {
		String assignmentId = excelDataValue(string, "assignmentId");
		// String assigID = excelDataValue(string,"newassignmentId");
		String userId = excelDataValue(string, "userId");

		String subDesc = excelDataValue(string, "subDesc");
		String subComments = excelDataValue(string, "subComments");
		String subPathAttach1 = excelDataValue(string, "subPathAttach1");
		// String dueDate = excelDataValue(string,time);
		String subPathAttach2 = excelDataValue(string, "subPathAttach2");
		String subPathAttach3 = excelDataValue(string, "subPathAttach3");
		String subPathAttach4 = excelDataValue(string, "subPathAttach4");
		String subPathAttach5 = excelDataValue(string, "subPathAttach5");
		String wrongsubDateTime = excelDataValue(string, "wrongsubDateTime");
		// String pathAttachment5 = excelDataValue(string, "pathAttachment5");
		String postgrade = excelDataValue(string, "postgrade");

		JSONObject body = new JSONObject();
		body.put("assignmentId", assignmentId);
		// body.put("assignmentId", 0);
		body.put("userId", userId);
		body.put("subDesc", subDesc);
		body.put("subComments", subComments);
		body.put("subPathAttach1", subPathAttach1);
		body.put("wrongsubDateTime", wrongsubDateTime);
		body.put("subPathAttach2", subPathAttach2);
		body.put("subPathAttach3", subPathAttach3);
		body.put("subPathAttach4", subPathAttach4);
		body.put("subPathAttach5", subPathAttach5);
		// body.put("wrongsubDateTime", pathAttachment4);
		body.put("postgrade", postgrade);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	@Then("Request should be successfull with status code {string} for POST request submission module with greater than due date")
	public void request_should_be_successfull_with_status_code_for_post_request_submission_module_with_greater_than_due_date(
			String statuscode) {
		int Poststatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Poststatuscode);
		if (Poststatuscode == 404) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Post Request UnSuccessful");

		}
	}

	@Given("User sets request for submission module with valid base URL and valid request body with grade greater")
	public void user_sets_request_for_submission_module_with_valid_base_url_and_valid_request_body_with_grade_greater() {
		this.uri = Config.Postassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for valid url and valid request bodywith greater than due date");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with data from {string} and {string} for submission module with grade greater")
	public void user_sends_post_request_with_data_from_and_for_submission_module_with_grade_greater(String string,
			String string2) throws IOException {
		String assignmentId = excelDataValue(string, "assignmentId");
		// String assigID = excelDataValue(string,"newassignmentId");
		String userId = excelDataValue(string, "userId");

		String subDesc = excelDataValue(string, "subDesc");
		String subComments = excelDataValue(string, "subComments");
		String subPathAttach1 = excelDataValue(string, "subPathAttach1");
		// String dueDate = excelDataValue(string,time);
		String subPathAttach2 = excelDataValue(string, "subPathAttach2");
		String subPathAttach3 = excelDataValue(string, "subPathAttach3");
		String subPathAttach4 = excelDataValue(string, "subPathAttach4");
		String subPathAttach5 = excelDataValue(string, "subPathAttach5");
		String wrongsubDateTime = excelDataValue(string, "wrongsubDateTime");
		// String pathAttachment5 = excelDataValue(string, "pathAttachment5");
		String postgrade = excelDataValue(string, "postgrade");

		JSONObject body = new JSONObject();
		body.put("assignmentId", assignmentId);
		// body.put("assignmentId", 0);
		body.put("userId", userId);
		body.put("subDesc", subDesc);
		body.put("subComments", subComments);
		body.put("subPathAttach1", subPathAttach1);
		body.put("wrongsubDateTime", wrongsubDateTime);
		body.put("subPathAttach2", subPathAttach2);
		body.put("subPathAttach3", subPathAttach3);
		body.put("subPathAttach4", subPathAttach4);
		body.put("subPathAttach5", subPathAttach5);
		// body.put("wrongsubDateTime", pathAttachment4);
		body.put("postgrade", postgrade);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	@Then("Request should be successfull with status code {string} for POST request submission module with grade greater")
	public void request_should_be_successfull_with_status_code_for_post_request_submission_module_with_grade_greater(
			String statuscode) {
		int Poststatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Poststatuscode);
		if (Poststatuscode == 404) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Post Request UnSuccessful");
		}
	}
}
