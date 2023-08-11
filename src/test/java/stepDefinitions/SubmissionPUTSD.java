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


public class SubmissionPUTSD extends baseclass {
	String uri;
	int submissionid;
	public RequestSpecification request;
	Response response;
	
	Logger logger = LogManager.getLogger("Batch DELETESD.java");
	
		@Given("User sets request for submission module with valid submission id and valid request body")
		public void user_sets_request_for_submission_module_with_valid_submission_id_and_valid_request_body() {
			this.uri = Config.Putresubmitassgn_URL;
			this.request = RestAssured.given().header("Content-Type", "application/json");
			logger.info("given put request for valid submission id and request body");
		}
		@SuppressWarnings("unchecked")
		@When("User sends POST request with data from {string} and {string} with valid submission id")
		public void user_sends_post_request_with_data_from_and_with_valid_submission_id(String string,String string2) throws InvalidFormatException, IOException {
			//String submissionId = excelDataValue(string,"submissionId");
			//String assignmentId = excelDataValue(string, "assignmentId");
			//String userId = excelDataValue(string, "userId");

			String updatesubDesc = excelDataValue(string, "updatesubDesc");
			//String updatesubDesc ="re-submission";
			String UpdatesubComments = excelDataValue(string, "UpdatesubComments");
			//String UpdatesubComments = "re-submission completed";

			String subPathAttach1 = excelDataValue(string, "subPathAttach1");
			String subPathAttach2 = excelDataValue(string, "subPathAttach2");
			String subPathAttach3 = excelDataValue(string, "subPathAttach3");
			String subPathAttach4 = excelDataValue(string, "subPathAttach4");
			String subPathAttach5 = excelDataValue(string, "subPathAttach5");
			String grade = excelDataValue(string, "grade");

			JSONObject body = new JSONObject();
			//body.put("submissionId", submissionId);
			body.put("assignmentId", Config.assignmentID);
			body.put("userId", Config.adminUserID);
			body.put("subDesc", updatesubDesc);
			body.put("subComments", UpdatesubComments);
			body.put("subPathAttach1", subPathAttach1);
			body.put("subPathAttach2", subPathAttach2);
			body.put("subPathAttach3", subPathAttach3);
			body.put("subPathAttach4", subPathAttach4);
			body.put("subPathAttach5", subPathAttach5);
			body.put("subPathAttach5", subPathAttach5);
			body.put("subDateTime",Timestamp());
			body.put("updatedgradedBy", "U8252");
			body.put("gradedDateTime",Timestamp());
			
			body.put("grade", grade);

			logger.info("JSON BODY= " + body.toJSONString());

			response = this.request.body(body.toJSONString()).when().put(this.uri + "/" + Config.submissionid).then().log().all().extract().response();

		}
		@Then("Request should be successfull with status code {string} for PUT request valid submission id")
		public void request_should_be_successfull_with_status_code_for_put_request_valid_submission_id(String statuscode) {
			int Putstatuscode = response.getStatusCode();
			 logger.info("Poststatuscode : " +Putstatuscode); 
			 if (Putstatuscode == 200) { response.then().statusCode(Integer.parseInt(statuscode));
			  logger.info("Put Request Successful");
		}
			 }
		@Given("User sets request for submission module with invalid submission id and valid request body")
		public void user_sets_request_for_submission_module_with_invalid_submission_id_and_valid_request_body() {
			this.uri = Config.Putresubmitassgn_URL;
			this.request = RestAssured.given().header("Content-Type", "application/json");
			logger.info("given put request for valid submission id and request body");
		}

		@SuppressWarnings("unchecked")
		@When("User sends request with data from {string} and {string} with invalid submission id")
		public void user_sends_request_with_data_from_and_with_invalid_submission_id(String string, String string2) throws IOException {
			
			response = this.request
				.when()
				.get(Config.Putresubmitassgn_URL + "/" + randomestring())
				.then()
				.log().all().extract().response();
				}


		@Then("Request should be successfull with status code {string} for PUT request invalid submission id")
		public void request_should_be_successfull_with_status_code_for_put_request_invalid_submission_id(String statuscode) {
			int Putstatuscode = response.getStatusCode();
			 logger.info("Poststatuscode : " +Putstatuscode); 
			 if (Putstatuscode == 400) { response.then().statusCode(Integer.parseInt(statuscode));
			  logger.info("Put Request Successful");
		}
		}
		@Given("User sets request for submission module with valid submission id and missing madatory request body")
		public void user_sets_request_for_submission_module_with_valid_submission_id_and_missing_madatory_request_body() {
			this.uri = Config.Putresubmitassgn_URL;
			this.request = RestAssured.given().header("Content-Type", "application/json");
			logger.info("given put request for valid submission id and request body");
		}

		@SuppressWarnings("unchecked")
		@When("User sends request with data from {string} and {string} with valid submission id and missing mandatory request body")
		public void user_sends_request_with_data_from_and_with_valid_submission_id_and_missing_mandatory_request_body(String string, String string2) throws IOException {
			String updatesubDesc = excelDataValue(string, "updatesubDesc");
//			String updatesubDesc ="re-submission";
			String UpdatesubComments = excelDataValue(string, "UpdatesubComments");
			String subPathAttach1 = excelDataValue(string, "subPathAttach1");
			// String dueDate = excelDataValue(string,time);
			String subPathAttach2 = excelDataValue(string, "subPathAttach2");
			String subPathAttach3 = excelDataValue(string, "subPathAttach3");
			String subPathAttach4 = excelDataValue(string, "subPathAttach4");
			String subPathAttach5 = excelDataValue(string, "subPathAttach5");
			//String pathAttachment4 = excelDataValue(string, "pathAttachment4");
			//String pathAttachment5 = excelDataValue(string, "pathAttachment5");
			String grade = excelDataValue(string, "grade");

			JSONObject body = new JSONObject();
			//body.put("submissionId", submissionId);
			body.put("updatesubDesc", updatesubDesc);
			body.put("UpdatesubComments", UpdatesubComments);
			body.put("subPathAttach1", subPathAttach1);
			body.put("subDateTime",Timestamp());
			body.put("subPathAttach2", subPathAttach2);
			body.put("subPathAttach3", subPathAttach3);
			body.put("subPathAttach4", subPathAttach4);
			body.put("subPathAttach5", subPathAttach5);
			//body.put("pathAttachment4", pathAttachment4);
			body.put("grade", grade);

			logger.info("JSON BODY= " + body.toJSONString());

			response = this.request.body(body.toJSONString()).when().put(this.uri + "/" + Config.submissionid).then().log().all().extract().response();

		}

		@Then("Request should be successfull with status code {string} for PUT request valid submission id and missing mandatory request body")
		public void request_should_be_successfull_with_status_code_for_put_request_valid_submission_id_and_missing_mandatory_request_body(String statuscode) {
			int Putstatuscode = response.getStatusCode();
			 logger.info("Poststatuscode : " +Putstatuscode); 
			 if (Putstatuscode == 400) { response.then().statusCode(Integer.parseInt(statuscode));
			  logger.info("Put Request Successful");
		}}
		@Given("User sets request for grade submission module with valid submission id and valid request body")
		public void user_sets_request_for_grade_submission_module_with_valid_submission_id_and_valid_request_body() {
			this.uri = Config.Putgradesubmit_URL;
			this.request = RestAssured.given().header("Content-Type", "application/json");
			logger.info("given put request for valid grade submission id and request body");
		}

		@SuppressWarnings("unchecked")
		@When("User sends PUT request with data from {string} and {string} with valid submission id for grade update")
		public void user_sends_put_request_with_data_from_and_with_valid_submission_id_for_grade_update(String string, String string2) throws IOException {
			String submissionId = excelDataValue(string,"submissionId");
			String assignmentId = excelDataValue(string, "assignmentId");
			//String assignId = excelDataValue(string,"assignId");
			// String assigID = excelDataValue(string,"newassignmentId");
			String userId = excelDataValue(string, "userId");

			String updatesubDesc = excelDataValue(string, "updatesubDesc");
			String UpdatesubComments = excelDataValue(string, "UpdatesubComments");
			String subPathAttach1 = excelDataValue(string, "subPathAttach1");
			// String dueDate = excelDataValue(string,time);
			String subPathAttach2 = excelDataValue(string, "subPathAttach2");
			String subPathAttach3 = excelDataValue(string, "subPathAttach3");
			String subPathAttach4 = excelDataValue(string, "subPathAttach4");
			String subPathAttach5 = excelDataValue(string, "subPathAttach5");
			//String pathAttachment4 = excelDataValue(string, "pathAttachment4");
			//String pathAttachment5 = excelDataValue(string, "pathAttachment5");
			String updatedgradedBy = excelDataValue(string, "updatedgradedBy");
			String updatedgrade = excelDataValue(string, "updatedgrade");

			JSONObject body = new JSONObject();
			//body.put("submissionId", submissionId);
			body.put("assignmentId", assignmentId);
			//body.put("assignmentId", 0);
			body.put("userId", userId);
			body.put("updatesubDesc", updatesubDesc);
			body.put("UpdatesubComments", UpdatesubComments);
			body.put("subPathAttach1", subPathAttach1);
			body.put("subDateTime",Timestamp());
			body.put("subPathAttach2", subPathAttach2);
			body.put("subPathAttach3", subPathAttach3);
			body.put("subPathAttach4", subPathAttach4);
			body.put("subPathAttach5", subPathAttach5);
			//body.put("pathAttachment4", pathAttachment4);
			body.put("updatedgradedBy", updatedgradedBy);
			body.put("updatedgrade", updatedgrade);

			logger.info("JSON BODY= " + body.toJSONString());

			response = this.request.body(body.toJSONString()).when().put(this.uri + "/" + Config.submissionid).then().log().all().extract().response();

		}

		@Then("Request should be successfull with status code {string} for grade submission")
		public void request_should_be_successfull_with_status_code_for_grade_submission(String statuscode) {
			int Putstatuscode = response.getStatusCode();
			 logger.info("Poststatuscode : " +Putstatuscode); 
			 if (Putstatuscode == 200) { response.then().statusCode(Integer.parseInt(statuscode));
			  logger.info("Put Request Successful");
		}}
		@Given("User sets request for grade submission module with invalid submission id and valid request body")
		public void user_sets_request_for_grade_submission_module_with_invalid_submission_id_and_valid_request_body() {
			this.uri = Config.Putgradesubmit_URL;
			this.request = RestAssured.given().header("Content-Type", "application/json");
			logger.info("given put request for valid submission id and request body");
		}

		@SuppressWarnings("unchecked")
		@When("User sends PUT request with data from {string} and {string} with invalid submission id for grade update")
		public void user_sends_put_request_with_data_from_and_with_invalid_submission_id_for_grade_update(String string, String string2) throws IOException {
			
			response = this.request
					.when()
					.get(Config.Putgradesubmit_URL + "/" + randomestring())
					.then()
					.log().all().extract().response();
					}
			//response = this.request.body(body.toJSONString()).when().put(this.uri + "/" + randomestring()).then().log().all().extract().response();

		

		@Then("Request should be successfull with status code {string} for grade submission invalid id")
		public void request_should_be_successfull_with_status_code_for_grade_submission_invalid_id(String statuscode) {
			int Putstatuscode = response.getStatusCode();
			 logger.info("Poststatuscode : " +Putstatuscode); 
			 if (Putstatuscode == 400) { response.then().statusCode(Integer.parseInt(statuscode));
			  logger.info("Put Request Successful");
		}}

		@Given("User sets request for grade submission module with valid submission id and missing madatory request body")
		public void user_sets_request_for_grade_submission_module_with_valid_submission_id_and_missing_madatory_request_body() {
			this.uri = Config.Putgradesubmit_URL;
			this.request = RestAssured.given().header("Content-Type", "application/json");
			logger.info("given put request for valid submission id and request body");
		}

		@SuppressWarnings("unchecked")
		@When("User sends PUT request with data from {string} and {string} with valid submission id and missing mandatory request body for grade update")
		public void user_sends_put_request_with_data_from_and_with_valid_submission_id_and_missing_mandatory_request_body_for_grade_update(String string, String string2) throws IOException {
			//String submissionId = excelDataValue(string,"submissionId");
			String updatesubDesc = excelDataValue(string, "updatesubDesc");
			//String updatesubDesc ="re-submission";
			String UpdatesubComments = excelDataValue(string, "UpdatesubComments");
			String subPathAttach1 = excelDataValue(string, "subPathAttach1");
			// String dueDate = excelDataValue(string,time);
			String subPathAttach2 = excelDataValue(string, "subPathAttach2");
			String subPathAttach3 = excelDataValue(string, "subPathAttach3");
			String subPathAttach4 = excelDataValue(string, "subPathAttach4");
			String subPathAttach5 = excelDataValue(string, "subPathAttach5");
			//String pathAttachment4 = excelDataValue(string, "pathAttachment4");
			//String pathAttachment5 = excelDataValue(string, "pathAttachment5");
			String updatedgrade = excelDataValue(string, "updatedgrade");

			JSONObject body = new JSONObject();
			//body.put("submissionId", submissionId);
			body.put("updatesubDesc", updatesubDesc);
			body.put("UpdatesubComments", UpdatesubComments);
			body.put("subPathAttach1", subPathAttach1);
			body.put("subDateTime",Timestamp());
			body.put("subPathAttach2", subPathAttach2);
			body.put("subPathAttach3", subPathAttach3);
			body.put("subPathAttach4", subPathAttach4);
			body.put("subPathAttach5", subPathAttach5);
			//body.put("pathAttachment4", pathAttachment4);
			body.put("updatedgrade", updatedgrade);

			logger.info("JSON BODY= " + body.toJSONString());

			response = this.request.body(body.toJSONString()).when().put(this.uri + "/" + Config.submissionid).then().log().all().extract().response();

		}

		@Then("Request should be successfull with status code {string} for PUT request grade submission id")
		public void request_should_be_successfull_with_status_code_for_put_request_grade_submission_id(String statuscode) {
			int Putstatuscode = response.getStatusCode();
			 logger.info("Poststatuscode : " +Putstatuscode); 
			 if (Putstatuscode == 400) { response.then().statusCode(Integer.parseInt(statuscode));
			  logger.info("Put Request Successful");
		}}
		@Given("Validate User sets request for grade submission module with valid submission id and valid request body")
		public void validate_user_sets_request_for_grade_submission_module_with_valid_submission_id_and_valid_request_body() {
			this.uri = Config.Putgradesubmit_URL;
			this.request = RestAssured.given().header("Content-Type", "application/json");
			logger.info("given put request for valid submission id and request body");

		}

		@SuppressWarnings("unchecked")
		@When("Validate User sends PUT request with data from {string} and {string} with valid submission id for grade update")
		public void validate_user_sends_put_request_with_data_from_and_with_valid_submission_id_for_grade_update(String string, String string2) throws IOException {
//		//	String submissionId = excelDataValue(string,"submissionId");
//			String assignmentId = excelDataValue(string, "assignmentId");
//			String userId = excelDataValue(string, "userId");

			String updatesubDesc = excelDataValue(string, "updatesubDesc");
			String UpdatesubComments = excelDataValue(string, "UpdatesubComments");
			String subPathAttach1 = excelDataValue(string, "subPathAttach1");
			String subPathAttach2 = excelDataValue(string, "subPathAttach2");
			String subPathAttach3 = excelDataValue(string, "subPathAttach3");
			String subPathAttach4 = excelDataValue(string, "subPathAttach4");
			String subPathAttach5 = excelDataValue(string, "subPathAttach5");
			String grade = excelDataValue(string, "grade");

			JSONObject body = new JSONObject();
			//body.put("submissionId", submissionId);
			body.put("assignmentId", Config.assignmentID);
			body.put("userId", Config.adminUserID);
			body.put("subDesc", updatesubDesc);
			body.put("subComments", UpdatesubComments);
			body.put("subPathAttach1", subPathAttach1);
			body.put("subPathAttach2", subPathAttach2);
			body.put("subPathAttach3", subPathAttach3);
			body.put("subPathAttach4", subPathAttach4);
			body.put("subPathAttach5", subPathAttach5);
			body.put("subPathAttach5", subPathAttach5);
			body.put("subDateTime",Timestamp());
			body.put("gradedBy", "U7900");
			body.put("gradedDateTime",Timestamp());
			
			body.put("grade", grade);
			logger.info("JSON BODY= " + body.toJSONString());

			response = this.request.body(body.toJSONString()).when().put(this.uri + "/" + Config.submissionid).then().log().all().extract().response();


		}

		@Then("Validate Request should be successfull with status code {string} for grade submission")
		public void validate_request_should_be_successfull_with_status_code_for_grade_submission(String statuscode) {
			int Putstatuscode = response.getStatusCode();
			 logger.info("Poststatuscode : " +Putstatuscode); 
			 if (Putstatuscode == 200) { response.then().statusCode(Integer.parseInt(statuscode));
			  logger.info("Put Request Successful");
		}}}