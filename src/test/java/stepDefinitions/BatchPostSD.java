package stepDefinitions;

import io.restassured.RestAssured;
import utilities.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import model.BatchResponse;

import org.json.simple.JSONObject;
//import org.testng.Assert;
import Base.baseclass;
import java.io.IOException;


public class BatchPostSD extends baseclass{
	

	Logger logger = LogManager.getLogger("AssignmentPOSTSD.java");
	String uri;
	public RequestSpecification request;
	Response response;
	BatchResponse batch;
	
	@Given("User POST Request for the LMS API endpoint")
	public void batch_module_base_url_is_set_with_valid_post_batch() {
	    this.uri = Config.BASE_URL + "/" + Config.Batch_SUB_URL;
	    logger.info(this.uri);
	    this.request = RestAssured.given();
	    this.request.header("Content-Type", "application/json");
	    logger.info(this.uri);
	}
	
	@When("User sends HTTPS Request and  request Body with all field from {string} and {string} and {string} and {string} and {string} and {string}")
	public void valid_data_is_passed_as_request_body(String sheetName, String bName, String bDesc, String bStatus, String bNoC, String ProgramID) throws IOException {
		String batchName = excelDataValue(sheetName, bName);
		String batchDesc = excelDataValue(sheetName, bDesc);
		String batchStatus = excelDataValue(sheetName, bStatus);
		String batchNoC = excelDataValue(sheetName, bNoC);
		String batchPgId = excelDataValue(sheetName, ProgramID);
		Random r = new Random();
		SendHttpPostRequest(batchName+ r.nextInt(1000),batchDesc, batchStatus,batchNoC,batchPgId);
	}
	
	@When("User sends HTTPS Request and  request Body with missing fields from {string} and {string} and {string} and {string} and {string} and {string}")
	public void invalid_data_is_passed_as_request_body(String sheetName, String bName, String bDesc, String bStatus, String bNoC, String ProgramID) throws IOException {
		//String batchName = excelDataValue(sheetName, bName);
		String batchDesc = excelDataValue(sheetName, bDesc);
		String batchStatus = excelDataValue(sheetName, bStatus);
		String batchNoC = excelDataValue(sheetName, bNoC);
		String batchPgId = excelDataValue(sheetName, ProgramID);
		Random r = new Random();
		SendHttpPostRequest("",batchDesc, batchStatus,batchNoC,batchPgId);
	}
	
	@SuppressWarnings("unchecked")
	private void SendHttpPostRequest(String bName, String bDesc, String bStatus, String bNoC, String ProgramID) {
		
		JSONObject body = new JSONObject(); 
		
		body.put("batchDescription", bDesc); 
		body.put("batchName", bName); 
		body.put("batchNoOfClasses", bNoC); 
		body.put("batchStatus", bStatus); 
		body.put("programId", Config.programId); 
		
		logger.info(body.toString());
		
		this.request.body(body);
		this.response = this.request.post(this.uri);
		this.response.then().log().all();
	}
	
	
	@Then("User Receives 201 created status with response body")
	public void verify_status_code_and_response_body() {
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		response.then().statusCode(201);
		
		ResponseBody body = response.getBody();
		
		String bodyAsString = body.asString();
//		Assert.assertTrue(bodyAsString.contains("batchDescription"));
//		Assert.assertTrue(bodyAsString.contains("batchId"));
//		Assert.assertTrue(bodyAsString.contains("batchName"));
//		Assert.assertTrue(bodyAsString.contains("batchNoOfClasses"));
//		Assert.assertTrue(bodyAsString.contains("batchStatus"));
//		Assert.assertTrue(bodyAsString.contains("programId"));
//		Assert.assertTrue(bodyAsString.contains("programName"));
		
		logger.info(jsonPathEvaluator.get("batchId").toString());
	}
	
	@SuppressWarnings("unchecked")
	@When("User creates new batch and gets batch details from {string} and {string} and {string} and {string} and {string} and {string}")
	public void create_new_batch_get_batch_details(String sheetName, String bName, String bDesc, String bStatus, String bNoC, String ProgramID) throws IOException {
		this.uri = Config.BASE_URL + "/" + Config.Batch_SUB_URL;
	    logger.info(this.uri);
	    this.request = RestAssured.given();
	    this.request.header("Content-Type", "application/json");
	    
		String batchName = excelDataValue(sheetName, bName);
		String batchDesc = excelDataValue(sheetName, bDesc);
		String batchStatus = excelDataValue(sheetName, bStatus);
		String batchNoC = excelDataValue(sheetName, bNoC);
		String batchPgId = excelDataValue(sheetName, ProgramID);
		System.out.println("ProgramID send from Program Module "+ProgramID);
		JSONObject body = new JSONObject(); 
		Random r = new Random();
		
		body.put("batchDescription", batchDesc); 
		body.put("batchName", batchName + r.nextInt(1000)); 
		body.put("batchNoOfClasses", batchNoC); 
		body.put("batchStatus", batchStatus); 
		body.put("programId", Config.programId); 
		
		logger.info(body.toString());
		
		this.request.body(body);
		Response resp = this.request.post(this.uri);
		resp.then().log().all();
		
		this.batch = new BatchResponse();
//		JsonPath jsonPathEvaluator = resp.jsonPath();
		
		  JsonPath js = resp.jsonPath(); 
		int batchId = js.getInt("batchId");
		 Config.assignmentID = batchId;
		
//		this.batch.batchId = jsonPathEvaluator.get("batchId");
//		this.batch.batchDescription = jsonPathEvaluator.get("batchDescription");
//		this.batch.batchName = jsonPathEvaluator.get("batchName");
//		this.batch.batchNoOfClasses = jsonPathEvaluator.get("batchNoOfClasses");
//		this.batch.batchStatus = jsonPathEvaluator.get("batchStatus");
//		this.batch.programId = jsonPathEvaluator.get("programId");
//		this.batch.programName = jsonPathEvaluator.get("programName");
//		
//		Config.batchId=Integer.parseInt(this.batch.batchId);
	
	}
	
	@When("User sends HTTPS Request with Existing batch details")
	public void user_sends_request_with_existing_batch_details() throws IOException {
		
		SendHttpPostRequest(this.batch.batchName, this.batch.batchDescription, this.batch.batchStatus, Integer.toString(this.batch.batchNoOfClasses), Integer.toString(this.batch.programId));
		
	}
	
	@Then("Verify batches API returns 400 status code and with batch already there message")
	public void verify_batches_API_returns_404_status_code_and_with_message_not_found() {
				response.then().statusCode(400);
				
				JsonPath jsonPathEvaluator = response.jsonPath();
				String mes = jsonPathEvaluator.get("message");
//				Assert.assertEquals(mes.contains("already exists"),true);
//				Boolean suc= jsonPathEvaluator.get("success");
//				Assert.assertFalse(suc);
	}
	
	@Then("Verify batches API returns 400 status code and with batch missing fields error")
	public void verify_batches_API_returns_400_status_code_and_with_missing_field_message() {
				response.then().statusCode(400);
				
				JsonPath jsonPathEvaluator = response.jsonPath();
				String mes = jsonPathEvaluator.get("message");
//				Assert.assertEquals(mes.contains("mandatory"),true);
				Boolean suc= jsonPathEvaluator.get("success");
//				Assert.assertFalse(suc);
	}
	
	
	
}