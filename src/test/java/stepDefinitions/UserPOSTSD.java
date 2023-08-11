package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Base.baseclass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Config;

public class UserPOSTSD extends baseclass {
	Logger logger = LogManager.getLogger("UserPOSTSD.java");
	String uri;
	public RequestSpecification request;
	Response response;
	String userId;

	Random rand = new Random();

	int num1 = rand.nextInt(900) + 100;
	int num2 = rand.nextInt(643) + 100;
	int num3 = rand.nextInt(9000) + 1000;
	String PhoneNumber = String.valueOf(num1) + String.valueOf(num2) + String.valueOf(num3);

	// <----User sets request for User module with valid base URL and valid request
	// body (Admin )---->
	@Given("User sets POST request for User module with valid base URL and valid request")
	public void user_sets_post_request_for_user_module_with_valid_base_url_and_valid_request() {
		this.uri = Config.PostUser_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Post request set for User module with valid base URL and valid data");
	}

	@SuppressWarnings("unchecked")
	@When("User POST request with data from {string}")
	public void user_post_request_with_data_from(String string) throws IOException {
		String usercomments = excelDataValue(string, "userComments");
		String EduPg = excelDataValue(string, "userEduPg");
		String EduUg = excelDataValue(string, "userEduUg");
		String FirstName = excelDataValue(string, "userFirstName");
		String LastName = excelDataValue(string, "userLastName");
		String LinkedinUrl = excelDataValue(string, "userLinkedinUrl");
		String Location = excelDataValue(string, "userLocation");
		String MiddleName = excelDataValue(string, "userMiddleName");
		String PhoneNumber = String.valueOf(num1) + String.valueOf(num2) + String.valueOf(num3);

		String roleId = excelDataValue(string, "roleId");
		String RoleStatus = excelDataValue(string, "userRoleStatus");
		String userTimeZone = excelDataValue(string, "userTimeZone");
		String VisaStatus = excelDataValue(string, "userVisaStatus");

		JSONObject body = new JSONObject();
		body.put("userComments", usercomments);
		body.put("userEduPg", EduPg);
		body.put("userEduUg", EduUg);
		body.put("userFirstName", FirstName + randomestring());
		body.put("userLastName", LastName);
		body.put("userLinkedinUrl", LinkedinUrl);
		body.put("userLocation", Location);
		body.put("userMiddleName", MiddleName);
		body.put("userPhoneNumber", PhoneNumber);

		JSONArray array = new JSONArray();
		JSONObject userRoleMapsArray = new JSONObject();
		userRoleMapsArray.put("roleId", roleId);
		userRoleMapsArray.put("userRoleStatus", RoleStatus);
		array.add(userRoleMapsArray);
		body.put("userRoleMaps", array);
		body.put("userTimeZone", userTimeZone);
		body.put("userVisaStatus", VisaStatus);
		logger.info("the request  body  " + body);

		logger.info("JSON BODY= " + body.toJSONString());
		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
		logger.info("response of the body is " + response);
	}

	@Then("Request is successful with status code {string} for POST request")
	public void request_is_successful_with_status_code_for_post_request(String statuscode) {
		int Poststatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Poststatuscode);
		if (Poststatuscode == 201) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Post Request Successful");

			// use delete request to delete the posted data//
			JsonPath js = response.jsonPath();
			userId = js.get("userId");
			Config.adminUserID = userId;
			logger.info("Generated UserId is for Admin userID is " + userId);
		}
	}

	// Staff
	@Given("User sets request for User module with valid base URL and valid request body for staff")
	public void user_sets_request_for_user_module_with_valid_base_url_and_valid_request_body_for_staff() {
		this.uri = Config.PostUser_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("StaffId Post request set for User module with valid base URL and valid data");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with data from {string} for staff")
	public void user_sends_post_request_with_data_from_for_staff(String string) {
		String PhoneNumber = String.valueOf(num1) + String.valueOf(num2) + String.valueOf(num3);
		JSONObject body = new JSONObject();
		body.put("userComments", "Post Test");
		body.put("userEduPg", "M.E");
		body.put("userEduUg", "B.E");
		body.put("userFirstName", "BugBuster_" + randomestring());
		body.put("userLastName", "API");
		body.put("userLinkedinUrl", "staff@linkedin.com");
		body.put("userLocation", "Europe");
		body.put("userMiddleName", "Johny");
		body.put("userPhoneNumber", PhoneNumber);

		JSONArray array = new JSONArray();
		JSONObject userRoleMapsArray = new JSONObject();
		userRoleMapsArray.put("roleId", "R02");
		userRoleMapsArray.put("userRoleStatus", "Active");
		array.add(userRoleMapsArray);
		body.put("userRoleMaps", array);
		body.put("userTimeZone", "MST");
		body.put("userVisaStatus", "H4-EAD");
		logger.info("the request  body  " + body);

		logger.info("JSON BODY= " + body.toJSONString());
		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
		logger.info("response of the body is " + response);
	}

	@Then("Request should be successful with status code {string} for POST request for staff")
	public void request_should_be_successful_with_status_code_for_post_request_for_staff(String statuscode) {
		int Poststatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Poststatuscode);
		if (Poststatuscode == 201) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Post Request Successful");

			// use delete request to delete the posted data//
			JsonPath js = response.jsonPath();
			userId = js.get("userId");
			Config.staffUserID = userId;
			logger.info("Generated UserId is for Staff userID is " + userId);
		}
	}

	// Student
	@Given("User sets request for User module with valid base URL and valid request body for student")
	public void user_sets_request_for_user_module_with_valid_base_url_and_valid_request_body_for_student() {
		this.uri = Config.PostUser_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Student Id Post request set for User module with valid base URL and valid data");

	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with data from {string} for student")
	public void user_sends_post_request_with_data_from_for_student(String string) {
		String PhoneNumber = String.valueOf(num1) + String.valueOf(num2) + String.valueOf(num3);
		JSONObject body = new JSONObject();
		body.put("userComments", "Post Test2");
		body.put("userEduPg", "Ms");
		body.put("userEduUg", "BSc");
		body.put("userFirstName", "BugBuster_" + randomestring());
		body.put("userLastName", "RestAssured");
		body.put("userLinkedinUrl", "student@linkedin.com");
		body.put("userLocation", "US");
		body.put("userMiddleName", "");
		body.put("userPhoneNumber", PhoneNumber);

		JSONArray array = new JSONArray();
		JSONObject userRoleMapsArray = new JSONObject();
		userRoleMapsArray.put("roleId", "R03");
		userRoleMapsArray.put("userRoleStatus", "Active");
		array.add(userRoleMapsArray);
		body.put("userRoleMaps", array);
		body.put("userTimeZone", "CST");
		body.put("userVisaStatus", "GC-EAD");
		logger.info("the request  body  " + body);

		logger.info("JSON BODY= " + body.toJSONString());
		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
		logger.info("response of the body is " + response);
	}

	@Then("Request should be successful with status code {string} for POST request for student")
	public void request_should_be_successful_with_status_code_for_post_request_for_student(String statuscode) {
		int Poststatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Poststatuscode);
		if (Poststatuscode == 201) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Post Request Successful");

			// use delete request to delete the posted data//
			JsonPath js = response.jsonPath();
			userId = js.get("userId");
			Config.studentUserID = userId;
			logger.info("Generated UserId is for Student userID is " + userId);
		}

	}

	// user_post_04(negative Scenario)
	@Given("User sets request for User module with exisitng values")
	public void user_sets_request_for_user_module_with_exisitng_values() {
		this.uri = Config.PostUser_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for User module with valid base URL and valid data");

	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with existing phone number")
	public void user_sends_post_request_with_existing_phone_number() {
		JSONArray array = new JSONArray();

		JSONObject userRoleMapsArray = new JSONObject();
		userRoleMapsArray.put("roleId", "R03");
		userRoleMapsArray.put("userRoleStatus", "Active");
		array.add(userRoleMapsArray);

		JSONObject body = new JSONObject();
		body.put("userComments", "Test1");
		body.put("userEduPg", "MS");
		body.put("userEduUg", "B.Tech");
		body.put("userFirstName", "BugBusters" + randomestring());
		body.put("userLastName", "Hackathon7");
		body.put("userLinkedinUrl", "abc@linkedin.com");
		body.put("userLocation", "India");
		body.put("userMiddleName", "Put");
		body.put("userPhoneNumber", "1305065680");
		body.put("userRoleMaps", array);
		body.put("userTimeZone", "IST");
		body.put("userVisaStatus", "NA");

		logger.info("JSON BODY= " + body.toJSONString());
		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();

		logger.info("POST Request sent with valid request body with existing phone number");

	}

	@Then("User gets unsuccessful status code {string} for POST request")
	public void user_gets_unsuccessful_status_code_for_post_request(String statuscode) {
		int Poststatuscode = response.getStatusCode();
		logger.info("Poststatuscode : " + Poststatuscode);
		if (Poststatuscode == 400) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Post Request UnSuccessful");
		} else
			logger.info("Post Request successful with " + response.getStatusCode());

	}

	@Given("User sets POST request for User module with valid base URL and invalid request body")
	public void user_sets_post_request_for_user_module_with_valid_base_url_and_invalid_request_body() {
		this.uri = Config.PostUser_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for User module with valid base URL and valid data");
	}

	@SuppressWarnings({ "unchecked" })
	public void createPUTRequest1(String SheetName, String TestCaseName) throws IOException {

		logger.info("PUTTestCase SheetName= " + SheetName);
		logger.info("PUTTestCase Name= " + TestCaseName);
		ArrayList<String> userData = excelValue(SheetName, TestCaseName);

		String usercomments = userData.get(1);
		String EduPg = userData.get(2);
		String EduUg = userData.get(3);
		String FirstName = userData.get(4);
		String LastName = userData.get(5) + randomestring();
		String LinkedinUrl = userData.get(6);
		String Location = userData.get(7);
		String MiddleName = userData.get(8);
		String PhoneNumber = userData.get(9);
		String roleId = userData.get(10);
		String RoleStatus = userData.get(11);
		String userTimeZone = userData.get(12);
		String VisaStatus = userData.get(13);

		JSONArray array = new JSONArray();

		JSONObject userRoleMapsArray = new JSONObject();
		userRoleMapsArray.put("roleId", roleId);
		userRoleMapsArray.put("userRoleStatus", RoleStatus);
		array.add(userRoleMapsArray);

		JSONObject body = new JSONObject();
		body.put("userComments", usercomments);
		body.put("userEduPg", EduPg);
		body.put("userEduUg", EduUg);
		body.put("userFirstName", FirstName + randomestring());
		body.put("userLastName", LastName);
		body.put("userLinkedinUrl", LinkedinUrl);
		body.put("userLocation", Location);
		body.put("userMiddleName", MiddleName);
		body.put("userPhoneNumber", PhoneNumber);
		body.put("userRoleMaps", array);
		body.put("userTimeZone", userTimeZone);
		body.put("userVisaStatus", VisaStatus);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	@When("User sends POST request with missing data fields from the {string} and {string}")
	public void user_sends_post_request_with_missing_data_fields_from_the_and(String SheetName, String TestCaseName)
			throws IOException {
		logger.info("POST Request sent with valid request body with missing fields");
		createPUTRequest1(SheetName, TestCaseName);
	}

	@Then("Request should be unsuccessful with status code {string} for POST request")
	public void request_should_be_unsuccessful_with_status_code_for_post_request(String statuscode) {
		int Statuscode400 = response.getStatusCode();
		if (Statuscode400 == 400) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Bad request error message displayed with statuscode " + statuscode);
		}

	}
}
