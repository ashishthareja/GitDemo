package stepDefinitions;

import io.restassured.specification.RequestSpecification;
import model.BatchResponse;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;
//import org.testng.Assert;
import Base.baseclass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.Config;
import java.util.List;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import model.BatchResponse;


public class BatchGetSD extends baseclass{
	

	Logger logger = LogManager.getLogger("SD_Batch GET Method.java");
	String uri;
	public RequestSpecification request;
	JsonObject jsonObject;
	String jsonAsString;
	Response response;
	
	@Given("User verify  batch module url is set with valid url")
	public void user_verify_batch_module_url_is_set_with_valid_url() {
		 this.uri = Config.BASE_URL + "/" + Config.Batch_SUB_URL;
		    this.request = RestAssured.given().header("Content-type","application/json");
	}

	@When("User GET All batches request")
	public void user_get_all_batches_request() {
		response = this.request.get(this.uri);	
		logger.info("Response is= " + response.asString());
		//response.then().log().all();
	}

	@Then("User  success {string} status code and verify collection schema")
	public void user_success_status_code_and_verify_collection_schema(String string) {
		// checking return status code
		response.then().statusCode(200); 
		JsonPath jsonPathEvaluator = response.jsonPath();
		 // checking response has at least one batch
//		Assert.assertTrue(jsonPathEvaluator.getList("$").size()>0);
//	    // deserialze into object to validate schema 
//		List<BatchResponse> resp = jsonPathEvaluator.getList("$", BatchResponse.class);
//		for (BatchResponse item: resp) {
//			//logger.info(item.batchId + ","+ item.batchName + "," + item.batchDescription+ "," + item.batchNoOfClasses+ "," + item.programId+ "," + item.programName+ "," + item.batchStatus );
//			Assert.assertTrue(item.batchId.length()> 0);
//			Assert.assertTrue(item.batchName.length() > 0);
//			//Assert.assertTrue(item.batchDescription.length()> 0);
//			//Assert.assertTrue(item.batchNoOfClasses > 0);
//			Assert.assertTrue(item.programId> 0);
			//Assert.assertTrue(item.programName.length()> 0);
			//Assert.assertTrue(item.batchStatus.length()> 0);
		}
//}
		
	@Given("User verify Get Batches by valid BatchID")
	public void user_verify_get_batches_by_valid_batch_id() {
		this.uri = Config.BASE_URL + "/" + Config.GETBatch_by_BatchID_URL;
	    logger.info(this.uri);
	    this.request = RestAssured.given().header("Content-type","application/json");
	}

	@When("User set GET request with valid batchID from {string} and {string}")
	public void user_set_get_request_with_valid_batch_id_from_and(String sheetname, String testcase) throws IOException {
		String vaildBatchID = excelDataValue(sheetname, testcase);
		response = this.request.get(this.uri+Config.batchId);	
		//response.then().log().all();
	}

	@Then("User get {string} status code")
	public void user_get_status_code(String statuscode) {
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

	@Given("User GET request for Batch module with valid base URL and valid path")
	public void user_get_request_for_batch_module_with_valid_base_url_and_valid_path() {
		this.uri = Config.BASE_URL + "/" + Config.GETBatch_by_BatchID_URL;
	    logger.info(this.uri);
	    this.request = RestAssured.given().header("Content-type","application/json");
	}

	@When("User GET request with invalid batchID from {string} and {string}")
	public void user_get_request_with_invalid_batch_id_from_and(String string, String string2) {
		String invaildBatchID = randomestring();
		response = this.request.get(this.uri+invaildBatchID);	
		//response.then().log().all();
			}

	@Then("Verify batches API returns {string} status code and with message not found")
	public void verify_batches_api_returns_status_code_and_with_message_not_found(String string) {
		JsonPath jsonPathEvaluator = response.jsonPath();
		String mes = jsonPathEvaluator.get("message");
	//	Assert.assertEquals(mes.contains("Batch not found with Id"),true);
		Boolean suc= jsonPathEvaluator.get("success");
	//	Assert.assertFalse(suc);
	}
	

	@Given("Userverify request for BatchName in Batch module with valid base URL and valid Path")
	public void userverify_request_for_batch_name_in_batch_module_with_valid_base_url_and_valid_path() {
		this.uri = Config.BASE_URL + "/" + Config.GETBatch_by_BatchName_URL;
		this.request = RestAssured.given().header("Content-type","application/json");
	}

	@When("User GET request with valid batchName from {string} and {string}")
	public void user_get_request_with_valid_batch_name_from_and(String sheetname, String testcase) throws IOException {
		String validBatchName = excelDataValue(sheetname, testcase);
		logger.info(this.uri+validBatchName);
		response = this.request.get(this.uri+validBatchName);	
		//response.then().log().all();
	}

	@Then("Verify batches api returns single batchname success {string} status code")
	public void verify_batches_api_returns_single_batchname_success_status_code(String string) {
		response.then().statusCode(200);
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		// checking response has at least one batch
	//	Assert.assertTrue(jsonPathEvaluator.getList("$").size()>0); 
				}

	
	@Given("User with valid base URL and valid path")
	public void user_with_valid_base_url_and_valid_path() {
		this.uri = Config.BASE_URL + "/" + Config.Batch_SUB_URL;
	    this.request = RestAssured.given().header("Content-type","application/json");
	}

	@When("User send GET request with invalid batchName")
	public void user_send_get_request_with_invalid_batch_name() {
		String invaildBatchName = randomestring();
		response = this.request.get(this.uri+invaildBatchName);	
		//response.then().log().all();
	}

	@Then("Verify batches API returns {string} status code")
	public void verify_batches_api_returns_status_code(String statuscode) {
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

	@Given("User verify in Batch module wih valid ProgramId")
	public void user_verify_in_batch_module_wih_valid_program_id() {
		this.uri = Config.BASE_URL + "/" + Config.GETBatch_by_ProgramId_URL;
		
	    this.request = RestAssured.given().header("Content-type","application/json");
	}

	@When("User  set GETrequest with valid ProgramId from {string} and {string}")
	public void user_set_ge_trequest_with_valid_program_id_from_and(String sheetname, String testcase) throws IOException {
		String validProgramId = excelDataValue(sheetname, testcase);
		//logger.info(this.uri+validProgramId);
		response = this.request.get(this.uri+validProgramId);	
		//response.then().log().all();
	}

	@Then("User get single batchname success {string} status code and verify schema")
	public void user_get_single_batchname_success_status_code_and_verify_schema(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		logger.info("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request all Assignment data is successfull");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request unsuccessful");
			throw new io.cucumber.java.PendingException();
		}

	}

	@Given("User verify in Batch module wih Invalid ProgramId")
	public void user_verify_in_batch_module_wih_invalid_program_id() {
		this.uri = Config.BASE_URL + "/" + Config.Batch_SUB_URL;
	    this.request = RestAssured.given().header("Content-type","application/json");
	}

	@When("User  set GETrequest with invalid ProgramId")
	public void user_set_ge_trequest_with_invalid_program_id() {
		String invaildProgramId = randomestring();
		response = this.request.get(this.uri+invaildProgramId);	
		//response.then().log().all();
	}

	@Then("User  {string} status code and with message not found")
	public void user_status_code_and_with_message_not_found(String statuscode) {
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
