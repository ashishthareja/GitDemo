package stepDefinitions;

import io.restassured.RestAssured;
import utilities.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.BatchResponse;

import org.json.simple.JSONObject;
import Base.baseclass;
import java.io.IOException;


public class BatchDeleteSD extends baseclass{
	String uri;
	public RequestSpecification request;
	Response response;
	BatchResponse batch;
	int batchId;
	
	Logger logger = LogManager.getLogger("Batch DELETESD.java");
	
	@Then("User Receives 200 status code for delete")
	public void verify_status_code_and_response_body() {
			
		response.then().statusCode(200);
			}
	
	@Then("User Receives 404 status code for delete")
	public void verify_404_status_code_and_response_body() {
		
		response.then().statusCode(404);
		
	}
	
	@SuppressWarnings("unchecked")
	@When("User creates new batch for this delete test and gets batch details from {string} and {string} and {string} and {string} and {string} and {string}")
	public void create_new_batch_get_batch_details(String sheetName, String bName, String bDesc, String bStatus, String bNoC, String bPgId) throws IOException {
		this.uri = Config.BASE_URL + "/" + Config.Batch_SUB_URL;
	    logger.info("Batch URL for delete existing Batch:" +this.uri);
	    this.request = RestAssured.given();
	    this.request.header("Content-Type", "application/json");
	    
		String batchName = excelDataValue(sheetName, bName);
		String batchDesc = excelDataValue(sheetName, bDesc);
		String batchStatus = excelDataValue(sheetName, bStatus);
		String batchNoC = excelDataValue(sheetName, bNoC);
		String batchPgId = excelDataValue(sheetName, bPgId);
		
		JSONObject body = new JSONObject(); 
		Random r = new Random();
		
		body.put("batchDescription", batchDesc); 
		body.put("batchName", batchName + r.nextInt(1000)); 
		body.put("batchNoOfClasses", batchNoC); 
		body.put("batchStatus", batchStatus); 
		body.put("programId", batchPgId); 
		
		logger.info(body.toString());
		
		this.request.body(body);
		Response resp = this.request.post(this.uri);
		resp.then().log().all();
		
		this.batch = new BatchResponse();
		JsonPath jsonPathEvaluator = resp.jsonPath();
		
		  JsonPath js = resp.jsonPath(); 
		//batchId = js.getInt("batchId");
		
//		this.batch.batchId = jsonPathEvaluator.get("batchId");
//		this.batch.batchDescription = jsonPathEvaluator.get("batchDescription");
//		this.batch.batchName = jsonPathEvaluator.get("batchName");
//		this.batch.batchNoOfClasses = jsonPathEvaluator.get("batchNoOfClasses");
//		this.batch.batchStatus = jsonPathEvaluator.get("batchStatus");
//		this.batch.programId = jsonPathEvaluator.get("programId");
//		this.batch.programName = jsonPathEvaluator.get("programName");   
	}
	
	@When("User sends HTTPS DELETE Request for same batch")
	public void delete_new_batch_get_batch_details() throws IOException {
		this.uri = Config.BASE_URL + "/" + Config.Batch_SUB_URL + "/" + Config.batchId;
	    logger.info("Batch URL for delete with valid batchID :"+this.uri);
	    this.request = RestAssured.given();
	    this.request.header("Content-Type", "application/json");
		this.response = this.request.delete(this.uri);
		this.response.then().log().all();
	   	}
	
	@When("User sends HTTPS DELETE Request for invalid batchid")
	public void delete_batch_with_invalid_id() throws IOException {
		this.uri = Config.BASE_URL + "/" + Config.Batch_SUB_URL + "/"+randomestring();
	    logger.info("Batch URL for invalid batchID : "+this.uri);
	    this.request = RestAssured.given();
	    this.request.header("Content-Type", "application/json");
		this.response = this.request.delete(this.uri);
		this.response.then().log().all();
	   	}
	
	
	
	
	
	
	
	
	
}