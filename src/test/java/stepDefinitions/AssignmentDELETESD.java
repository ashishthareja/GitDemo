package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import Base.baseclass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Config;

public class AssignmentDELETESD extends baseclass{
	
	Logger logger = LogManager.getLogger("SD_Asssignment Delete Method.java");
	String uri;
	public static String assigID;
	public RequestSpecification request;
	Response response;
	
	//<----User creates DELETE Request for the LMS API endpoint  and  invalid  Assignment Id--->
	@Given("User creates DELETE Request for the LMS API endpoint  and  valid Assignment Id")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_assignment_id() {
        this.uri = Config.Getallassignment_URL +"/" +Config.assignmentID ;
        this.request = RestAssured.given().header("Contesnt-Type", "application/json");
        response = this.request.get(this.uri);
        response.then().log().all();
               
       // String s = response.then().log().all().toString();
		//logger.info("The response is " + s);
	int statusCode = this.response.statusCode();
	Assert.assertEquals(200, statusCode);
	logger.info("Assignment ID is present "+Config.assignmentID);
		}
			

	@When("User set HTTPS Request for the LMS API endpoint")
	public void user_set_https_request_for_the_lms_api_endpoint() {
		response = this.request
				.when()
				.delete(this.uri)
				.then()
				.log().all().extract().response();
	}

	@Then("User receives {string} Ok status with message")
	public void user_receives_ok_status_with_message(String string) {
		int statusCode = this.response.statusCode();
		Assert.assertEquals(200, statusCode);
		logger.info("Assignment Deleted with Assignment ID : "+Config.assignmentID);
	}

	//<----User creates DELETE Request for the LMS API endpoint  and  invalid  Assignment Id--->
	@Given("User creates DELETE Request for the LMS API endpoint  and  invalid  Assignment Id")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_invalid_assignment_id() {
	     assigID = randomestring();
		 this.uri = Config.Getallassignment_URL + "/"+ assigID;
	        
	}

	@When("User sends HTTPS Request")
	public void user_sends_https_request() {
		this.request = RestAssured.given().header("Content-Type", "application/json");
        response = this.request.get(this.uri);
        response.then().log().all(); 
	}

	@Then("User receives {string} Not Found Status with message and boolean success details")
	public void user_receives_not_found_status_with_message_and_boolean_success_details(String string) {
		int statusCode = this.response.statusCode();
		Assert.assertEquals(404, statusCode);
		logger.info("Assignment not found with Id : "+ assigID);
	}

}
