package stepDefinitions;

import Base.baseclass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Config;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;

public class UserPUTSD extends baseclass {
	String uri;
	public RequestSpecification request;
	Response response;
	String time = Timestamp();
	Logger logger = LogManager.getLogger("User PUT step defination.java");

	// update using UserID
	@SuppressWarnings("unchecked")
	@Given("User creates PUT Request by valid User ID for the LMS API endpoint")
	public void user_creates_put_request_by_valid_user_id_for_the_lms_api_endpoint() {
		JSONObject body = new JSONObject();

		body.put("userComments", "Update user");
		body.put("userEduPg", "MSc");
		body.put("userEduUg", "BSc");
		body.put("userFirstName", "BigB_0045");
		body.put("userId", "U8332");
		body.put("userLastName", "API");
		body.put("userMiddleName", "Hackathon");
		body.put("userLinkedinUrl", "efg@linkedin.com");
		body.put("userLocation", "India");
		body.put("userPhoneNumber", "8764568765");
		body.put("userTimeZone", "IST");
		body.put("userVisaStatus", "H4-EAD");

		logger.info(body);

		this.uri = Config.PutUser_UserId_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
		logger.info("Put Request user module with valid base valid UserID");
	}

	@When("User sends PUT request with data from valid payload")
	public void user_sends_put_request_with_data_from_valid_payload() {
		logger.info(this.uri + "/" + "U8285");
		response = this.request.when().put(this.uri + "/" + "U8285");// 
	}

	@Then("User gets successful statuscode {string}")
	public void user_gets_successful_statuscode(String string) {
		String s = response.then().log().all().toString();
		logger.info(s);
		int statusCode = this.response.statusCode();
		Assert.assertEquals(200, statusCode);
	}

	@Given("User creates PUT Request with the valid Base URL and valid request body")
	public void user_creates_put_request_with_the_valid_base_url_and_valid_request_body() {
		this.uri = Config.PutUser_UserId_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for User module update with valid base URL and valid User ID");
	}

	@When("User sends PUT request with with valid User data from {string} and {string}")
	public void user_sends_put_request_with_with_valid_user_data_from_and(String SheetName, String TestCaseName)
			throws IOException {

		logger.info("PUT Request sent with valid request body");
		createPUTRequest(SheetName, TestCaseName);
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void createPUTRequest(String SheetName, String TestCaseName) throws IOException {

		logger.info("PUTTestCase SheetName= " + SheetName);
		logger.info("PUTTestCase Name= " + TestCaseName);
		ArrayList<String> userData = excelValue(SheetName, TestCaseName);

		String usercomments = userData.get(1);
		String EduPg = userData.get(2);
		String EduUg = userData.get(3);
		String FirstName = userData.get(4);
		String LastName = userData.get(5);
		String LinkedinUrl = userData.get(6);
		String Location = userData.get(7);
		String MiddleName = userData.get(8);
		String PhoneNumber = userData.get(9);
		String roleId = userData.get(10);
		String RoleStatus = userData.get(11);
		String userTimeZone = userData.get(12);
		String VisaStatus = userData.get(13);
		String userID = userData.get(14);
		// String userID = Config.userId;

		JSONObject body = new JSONObject();
		body.put("userComments", usercomments);
		body.put("userEduPg", EduPg);
		body.put("userEduUg", EduUg);
		body.put("userFirstName", FirstName);
		body.put("userId", userID);
		body.put("userLastName", LastName);
		body.put("userMiddleName", MiddleName);
		body.put("userLinkedinUrl", LinkedinUrl);
		body.put("userLocation", Location);
		body.put("userPhoneNumber", PhoneNumber);
		body.put("userTimeZone", userTimeZone);
		body.put("userVisaStatus", VisaStatus);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().put(this.uri + "/" + userID).then().log().all()
				.extract().response();

	}

	@Then("Request should be successful with status code {string}")
	public void request_should_be_successful_with_status_code(String statusCode) {
		int Putstatuscode = response.getStatusCode();
		if (Putstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			logger.info("PUT Request Successful");
		} else
			logger.info("PUT Request unsuccessful with status code " + statusCode);

	}

	@Given("User creates PUT Request with the valid Base URL and invalid UserId")
	public void user_creates_put_request_with_the_valid_base_url_and_invalid_user_id() {
		this.uri = Config.PutUser_UserId_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for User module update with valid base URL and invalid User ID");
	}

	@When("User sends PUT request with with invalid User data from {string} and {string}")
	public void user_sends_put_request_with_with_invalid_user_data_from_and(String SheetName, String TestCaseName)
			throws IOException {
		logger.info("PUT Request sent with valid request body");
		createPUTRequest(SheetName, TestCaseName);
	}

	@Then("Request should be unsuccessful with status code {string}")
	public void request_should_be_unsuccessful_with_status_code(String statusCode) {
		int Putstatuscode = response.getStatusCode();
		if (Putstatuscode == 404) {
			response.then().statusCode(Integer.parseInt(statusCode));
			logger.info("PUT Request Successful");
		} else
			logger.info("PUT Request unsuccessful with status code " + statusCode);
	}

	@When("User sends PUT request with with valid User data and missing data fields from {string} and {string}")
	public void user_sends_put_request_with_with_valid_user_data_and_missing_data_fields_from_and(String SheetName,
			String TestCaseName) throws IOException {
		logger.info("PUT Request sent with missing fields in request body");
		createPUTRequest(SheetName, TestCaseName);
	}

	@Then("Request should be unsuccessful with status code with {string}")
	public void request_should_be_unsuccessful_with_status_code_with(String statusCode) {
		int Putstatuscode = response.getStatusCode();
		if (Putstatuscode == 400) {
			response.then().statusCode(Integer.parseInt(statusCode));
			logger.info("PUT Request with missing fields");
		} else
			logger.info("PUT Request unsuccessful with status code " + statusCode);
	}

	// Update using Rolestatus

	@Given("User creates PUT Request with the valid Base URL and valid request body for RoleStatus update")
	public void user_creates_put_request_with_the_valid_base_url_and_valid_request_body_for_role_status_update() {
		this.uri = Config.PutUser_Rolestatus_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for User module update with valid base URL and invalid User ID");
	}

	@When("User sends PUT request with with valid User data from {string} and {string} for Rolestatus update")
	public void user_sends_put_request_with_with_valid_user_data_from_and_for_rolestatus_update(String SheetName,
			String TestCaseName) throws IOException {
		logger.info("PUT Request sent with valid request body");
		roleStatusPUTRequest(SheetName, TestCaseName);
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void roleStatusPUTRequest(String SheetName, String TestCaseName) throws IOException {

		logger.info("PUTTestCase SheetName= " + SheetName);
		logger.info("PUTTestCase Name= " + TestCaseName);
		ArrayList<String> userData = excelValue(SheetName, TestCaseName);

		String usercomments = userData.get(1);
		String EduPg = userData.get(2);
		String EduUg = userData.get(3);
		String FirstName = userData.get(4);
		String LastName = userData.get(5);
		String LinkedinUrl = userData.get(6);
		String Location = userData.get(7);
		String MiddleName = userData.get(8);
		String PhoneNumber = userData.get(9);
		String roleId = userData.get(10);
		String RoleStatus = userData.get(11);
		String userTimeZone = userData.get(12);
		String VisaStatus = userData.get(13);
		String userID = userData.get(14);

		JSONObject body = new JSONObject();

		body.put("roleId", roleId);
		body.put("userRoleStatus", RoleStatus);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().put(this.uri + "/" + userID).then().log().all()
				.extract().response();

	}

	@When("User sends PUT request with invalid User data from {string} and {string} for Rolestatus update")
	public void user_sends_put_request_with_invalid_user_data_from_and_for_rolestatus_update(String SheetName,
			String TestCaseName) throws IOException {
		logger.info("PUT Request sent with valid request body");
		roleStatusPUTRequest(SheetName, TestCaseName);
	}

	@When("User sends PUT request with valid User data from {string} and {string} for Rolestatus update")
	public void user_sends_put_request_with_valid_user_data_from_and_for_rolestatus_update(String SheetName,
			String TestCaseName) throws IOException {
		logger.info("PUT Request sent with missing fields request body");
		roleMissingPUTRequest(SheetName, TestCaseName);
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void roleMissingPUTRequest(String SheetName, String TestCaseName) throws IOException {

		logger.info("PUTTestCase SheetName= " + SheetName);
		logger.info("PUTTestCase Name= " + TestCaseName);
		ArrayList<String> userData = excelValue(SheetName, TestCaseName);

		String usercomments = userData.get(1);
		String EduPg = userData.get(2);
		String EduUg = userData.get(3);
		String FirstName = userData.get(4);
		String LastName = userData.get(5);
		String LinkedinUrl = userData.get(6);
		String Location = userData.get(7);
		String MiddleName = userData.get(8);
		String PhoneNumber = userData.get(9);
		String roleId = userData.get(10);
		String RoleStatus = userData.get(11);
		String userTimeZone = userData.get(12);
		String VisaStatus = userData.get(13);
		String userID = userData.get(14);

		JSONObject body = new JSONObject();

		// body.put("roleId",roleId);
		body.put("userRoleStatus", RoleStatus);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().put(this.uri + "/" + userID).then().log().all()
				.extract().response();

	}

	// update using Program/Batch ID
	@Given("User creates PUT Request with the valid Base URL and valid request body with program\\/batchID")
	public void user_creates_put_request_with_the_valid_base_url_and_valid_request_body_with_program_batch_id() {
		this.uri = Config.PutUser_Batch_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for User module update with program and Batch ID");
	}

	@When("User sends PUT request with valid User data from {string} and {string} for program and batch")
	public void user_sends_put_request_with_valid_user_data_from_and_for_program_and_batch(String SheetName,
			String TestCaseName) throws IOException {
		logger.info("PUT Request sent with missing fields request body");
		ProgBatchPUTRequest(SheetName, TestCaseName);
	}

	@SuppressWarnings("unchecked")
	public void ProgBatchPUTRequest(String SheetName, String TestCaseName) throws IOException {

		logger.info("PUTTestCase SheetName= " + SheetName);
		logger.info("PUTTestCase Name= " + TestCaseName);
		ArrayList<String> userData = excelValue(SheetName, TestCaseName);
		String program_id = userData.get(1);
		String roleid = userData.get(2);
		String userid = userData.get(3);
		String batchid = userData.get(4);
		String progBatchStatus = userData.get(5);
		JSONObject body = new JSONObject();
		body.put("programId", program_id);
		body.put("roleId", roleid);
		body.put("userId", userid);
		JSONArray array = new JSONArray();
		JSONObject userRoleProgBatchArray = new JSONObject();
		userRoleProgBatchArray.put("batchId", batchid);
		userRoleProgBatchArray.put("userRoleProgramBatchStatus", progBatchStatus);
		array.add(userRoleProgBatchArray);
		body.put("userRoleProgramBatches", array);

		logger.info("JSON BODY= " + body.toJSONString());
		response = this.request.body(body.toJSONString()).when().put(this.uri + "/" + userid).then().log().all()
				.extract().response();
	}

	// missing fields program/batch update
	@When("User sends PUT request with valid User data from {string} and {string} with missing fields")
	public void user_sends_put_request_with_valid_user_data_from_and_with_missing_fields(String SheetName,
			String TestCaseName) throws IOException {
		logger.info("PUT Request sent with missing fields request body");
		MissingProgBatchPUTRequest(SheetName, TestCaseName);
	}

	@SuppressWarnings("unchecked")
	public void MissingProgBatchPUTRequest(String SheetName, String TestCaseName) throws IOException {

		logger.info("PUTTestCase SheetName= " + SheetName);
		logger.info("PUTTestCase Name= " + TestCaseName);
		ArrayList<String> userData = excelValue(SheetName, TestCaseName);
		String program_id = userData.get(1);
		String roleid = userData.get(2);
		String userid = userData.get(3);
		String batchid = userData.get(4);
		String progBatchStatus = userData.get(5);
		JSONObject body = new JSONObject();
		body.put("programId", program_id);

		// missing fields
		// body.put("roleId",roleid);
		// body.put("userId", userid);
		JSONArray array = new JSONArray();
		JSONObject userRoleProgBatchArray = new JSONObject();
		userRoleProgBatchArray.put("batchId", batchid);
		userRoleProgBatchArray.put("userRoleProgramBatchStatus", progBatchStatus);
		array.add(userRoleProgBatchArray);
		body.put("userRoleProgramBatches", array);

		logger.info("JSON BODY= " + body.toJSONString());
		response = this.request.body(body.toJSONString()).when().put(this.uri + "/" + userid).then().log().all()
				.extract().response();
	}
}
