package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class AssignmentPOSTSD extends baseclass {
	
	Logger logger = LogManager.getLogger("AssignmentPOSTSD.java");
	String uri;
	public RequestSpecification request;
	Response response;
	String time = Timestamp();
	String nextWeekTime = NextWeekTimestamp();
	String assignment_Name =  AssignmentName();
	public static int assignId;

	//<----User sets request for assignment module with valid base URL and valid request body---->
	@Given("User sets request for assignment module with valid base URL and valid request body")
	public void user_sets_request_for_assignment_module_with_valid_base_url_and_valid_request_body() {
		this.uri = Config.Postassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Batch module with valid base URL and valid data");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request for assignment with valid URL and data from {string}")
	public void user_sends_post_request_for_assignment_with_valid_url_and_data_from(String string) throws IOException {
		
		String assigDescription = excelDataValue(string, "assignmentDescription");
		String assigName = excelDataValue(string, "assignmentName");
		//batch id is int, need to convert string batch id to int
		int batchId = Integer.parseInt(excelDataValue(string, "batchID"));
		String comments = excelDataValue(string, "comments");
		String createdBy = excelDataValue(string, "createdBy");
		String graderId = excelDataValue(string, "graderId");
		String pathAttachment1 = excelDataValue(string, "pathAttachment1");
		String pathAttachment2 = excelDataValue(string, "pathAttachment2");
		String pathAttachment3 = excelDataValue(string, "pathAttachment3");
		String pathAttachment4 = excelDataValue(string, "pathAttachment4");
		String pathAttachment5 = excelDataValue(string, "pathAttachment5");
		 
		JSONObject body = new JSONObject();
		body.put("assignmentDescription", assigDescription);
		body.put("assignmentId", 0);
		body.put("assignmentName", assigName+randomestring());
		body.put("batchId", Config.batchId);
		body.put("comments", comments);
		body.put("createdBy", Config.adminUserID);
		body.put("dueDate", nextWeekTime);
		body.put("graderId", Config.adminUserID);
		body.put("pathAttachment1", pathAttachment1);
		body.put("pathAttachment2", pathAttachment2);
		body.put("pathAttachment3", pathAttachment3);
		body.put("pathAttachment4", pathAttachment4);
		body.put("pathAttachment5", pathAttachment5);

		logger.info("JSON BODY= " + body.toJSONString());
		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	@Then("Request should be successfull with status code {string} Post request valid URL")
	public void request_should_be_successfull_with_status_code_post_request_valid_url(String statuscode) {
		logger.info("Respone is "+ response.prettyPrint());
		 int Poststatuscode = response.getStatusCode();
		 logger.info("Poststatuscode : " +Poststatuscode); 
		 if (Poststatuscode == 201) { response.then().statusCode(Integer.parseInt(statuscode));
		  logger.info("Post Request Successful");
		  
		  //use delete request to delete the posted data// 
		  JsonPath js = response.jsonPath(); 
		 assignId = js.getInt("assignmentId");
		 Config.assignmentID = assignId;
		  logger.info("assignmentId: "+assignId); }		 

	}
	
	//<---User sends POST request for assignment with valid URL and existing data --->
	@Given("User set POST Request for the LMS API endpoint")
	public void user_set_post_request_for_the_lms_api_endpoint() {
		this.uri = Config.Postassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Batch module with valid base URL and valid data");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request for assignment with valid URL and existing data from {string}")
	public void user_sends_post_request_for_assignment_with_valid_url_and_existing_data_from(String string) throws IOException {
		
		String assigDescription = excelDataValue(string, "assignmentDescription");
		String assigName = excelDataValue(string, "assignmentName");
		String batchId = excelDataValue(string, "batchID");
		String comments = excelDataValue(string, "comments");
		String createdBy = excelDataValue(string, "createdBy");
		String graderId = excelDataValue(string, "graderId");
		String pathAttachment1 = excelDataValue(string, "pathAttachment1");
		String pathAttachment2 = excelDataValue(string, "pathAttachment2");
		String pathAttachment3 = excelDataValue(string, "pathAttachment3");
		String pathAttachment4 = excelDataValue(string, "pathAttachment4");
		String pathAttachment5 = excelDataValue(string, "pathAttachment5");
logger.info("Set Assign Id is: "+Config.assignmentID);
		JSONObject body = new JSONObject();
		body.put("assignmentDescription", assigDescription);
		body.put("assignmentId", Config.assignmentID);
		body.put("assignmentName", assigName);
		body.put("batchId", 0);
		body.put("comments", comments);
		body.put("createdBy", Config.adminUserID);
		body.put("dueDate", time);
		body.put("graderId", Config.adminUserID);
		body.put("pathAttachment1", pathAttachment1);
		body.put("pathAttachment2", pathAttachment2);
		body.put("pathAttachment3", pathAttachment3);
		body.put("pathAttachment4", pathAttachment4);
		body.put("pathAttachment5", pathAttachment5);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString())
				.when().post(this.uri)
				.then().log().all().extract().response();
	}
	
	@Then("User receives {string} Bad Request Status with message and boolean success details")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details(String statuscode) {
		int Statuscode400 = response.getStatusCode();
		if (Statuscode400 == 400) {
		response.then().statusCode(Integer.parseInt(statuscode));
		logger.info("Bad request error message displayed with statuscode " + statuscode);
	}
	
	else 
		logger.info("Statuscode received" + statuscode + ". Error to be reported");
	}
	
	//<---User sends POST request for assignment with valid URL and some data missing---->
	@Given("User creates POST Request for the LMS API endpoint")
	public void user_creates_post_request_for_the_lms_api_endpoint() {
		this.uri = Config.Postassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Batch module with valid base URL and valid data");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request for assignment with valid URL and some data missing from {string}")
	public void user_sends_post_request_for_assignment_with_valid_url_and_some_data_missing_from(String string) throws IOException {
		String assigDescription = excelDataValue(string, "assignmentDescription");
	    String assigID = excelDataValue(string,"excitingAssignmentID");
		String assigName = excelDataValue(string, "assignmentName");
		String batchId = excelDataValue(string, "batchID");
		String comments = excelDataValue(string, "comments");
		String createdBy = excelDataValue(string, "createdBy");
		String graderId = excelDataValue(string, "graderId");
		String pathAttachment1 = excelDataValue(string, "pathAttachment1");
		String pathAttachment2 = excelDataValue(string, "pathAttachment2");
		String pathAttachment3 = excelDataValue(string, "pathAttachment3");
		String pathAttachment4 = excelDataValue(string, "pathAttachment4");
		String pathAttachment5 = excelDataValue(string, "pathAttachment5");

		JSONObject body = new JSONObject();
		body.put("assignmentDescription", assigDescription);
		body.put("assignmentId",assigID );
		body.put("assignmentName", assigName);
		body.put("batchId", 0);
		body.put("comments", comments);
		body.put("createdBy", createdBy);
		body.put("dueDate", time);
		body.put("graderId", graderId);
		body.put("pathAttachment1", pathAttachment1);
		body.put("pathAttachment2", pathAttachment2);
		body.put("pathAttachment3", pathAttachment3);
		body.put("pathAttachment4", pathAttachment4);
		body.put("pathAttachment5", pathAttachment5);

		logger.info("JSON BODY= " + body.toJSONString());

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();

	}

	@Then("User receives {string} Bad Request Status")
	public void user_receives_bad_request_status(String statuscode) {
		int Statuscode400 = response.getStatusCode();
		if (Statuscode400 == 400) {
		response.then().statusCode(Integer.parseInt(statuscode));
		logger.info("Bad request error message displayed with statuscode " + statuscode);
	}
	
	else 
		logger.info("Statuscode received" + statuscode + ". Error to be reported");
	}
}
