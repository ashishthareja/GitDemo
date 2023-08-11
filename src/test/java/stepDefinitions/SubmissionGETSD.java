package stepDefinitions;

import io.restassured.RestAssured;

import Base.baseclass;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.JsonObject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Config;
import java.io.File;
import utilities.Excelreader;
import java.io.IOException;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SubmissionGETSD extends baseclass {
	String uri;
	JsonObject jsonObject;
	String jsonAsString;
	public RequestSpecification request;
	Response response;
	
	static Logger logger = LogManager.getLogger("BaseClass.java");
	
	@Given("User send GET Request for the LMS API endpoint with valid submission Assignment ID")
	public void user_send_get_request_for_the_lms_api_endpoint_with_valid_submission_assignment_id() {
		this.uri = Config.Getassignment_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");//
		logger.info("Get Request for all assignments");
	}

	@When("User create GET request with valid submission AssignmentID from {string} and {string}")
	public void user_create_get_request_with_valid_submission_assignment_id_from_and(String string, String string2) throws IOException {
		logger.info("TestCase Name= " + string);
		response = this.request.when().get(Config.Getassignment_URL + "/" + Config.assignmentID).then().log().all()
				.extract().response();
	}

	@Then("User receives {string} OK Status for valid submission assignment id")
	public void user_receives_ok_status_for_valid_submission_assignment_id(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		logger.info("Response Status is= " + GetAllstatuscode);
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request all Assignment data is successfull");
		} else if (GetAllstatuscode == 404) {
			logger.info("Get Request unsuccessful");

		}}
		@Given("User send GET Request for the LMS API endpoint with valid submission Student ID")
		public void user_send_get_request_for_the_lms_api_endpoint_with_valid_submission_student_id() {
			this.uri = Config.Getsubstudentid_URL;
			this.request = RestAssured.given().header("Content-Type", "application/json");//
			logger.info("Get Request for valid assignment student id");
		}

		@When("User create GET request with valid submission StudentID from {string} and {string}")
		public void user_create_get_request_with_valid_submission_student_id_from_and(String string, String string2) throws IOException {
			logger.info("TestCase Name= " + string);

			String assignmentId = excelDataValue(string, string2);

			response = this.request.when().get(Config.Getsubstudentid_URL + "/" + assignmentId).then().log().all()
					.extract().response();
		}

		@Then("User receives {string} OK Status for valid submission student id")
		public void user_receives_ok_status_for_valid_submission_student_id(String statuscode) {
			int GetAllstatuscode = response.getStatusCode();
			logger.info("Response Status is= " + GetAllstatuscode);
			if (GetAllstatuscode == 200) {
				response.then().statusCode(Integer.parseInt(statuscode));
				response.then().assertThat().header("Connection", "keep-alive");
				logger.info("Get Request all student id data is successfull");
			} else if (GetAllstatuscode == 404) {
				logger.info("Get Request unsuccessful");
		}}
			@Given("User send GET Request for the LMS API endpoint with valid submission Batch ID")
			public void user_send_get_request_for_the_lms_api_endpoint_with_valid_submission_batch_id() {
				this.uri = Config.Getsubbatchid_URL;
				this.request = RestAssured.given().header("Content-Type", "application/json");//
				logger.info("Get Request for assignment batch id");
			}

			@When("User create GET request with valid submission Batchid from {string} and {string}")
			public void user_create_get_request_with_valid_submission_batchid_from_and(String string, String string2) throws IOException {
				logger.info("TestCase Name= " + string);

				String studenttId = excelDataValue(string, string2);

				response = this.request.when().get(Config.Getsubbatchid_URL + "/" + studenttId).then().log().all()
						.extract().response();
			}

			@Then("User receives {string} OK Status for valid submission batch id")
			public void user_receives_ok_status_for_valid_submission_batch_id(String statuscode) {
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
			@Given("User send GET Request for the LMS API endpoint with valid submission user ID")
			public void user_send_get_request_for_the_lms_api_endpoint_with_valid_submission_user_id() {
				this.uri = Config.Getsubbyuserid_URL;
				this.request = RestAssured.given().header("Content-Type", "application/json");//
				logger.info("Get Request valid assignment user id");
			}

			@When("User create GET request with valid submission userid from {string} and {string}")
			public void user_create_get_request_with_valid_submission_userid_from_and(String string, String string2) throws IOException {
				logger.info("TestCase Name= " + string);

				String assignmentId = excelDataValue(string, string2);

				response = this.request.when().get(Config.Getsubbyuserid_URL + "/" + assignmentId).then().log().all()
						.extract().response();
			}

			@Then("User receives {string} OK Status for valid submission user id")
			public void user_receives_ok_status_for_valid_submission_user_id(String statuscode) {
				int GetAllstatuscode = response.getStatusCode();
				logger.info("Response Status is= " + GetAllstatuscode);
				if (GetAllstatuscode == 200) {
					response.then().statusCode(Integer.parseInt(statuscode));
					response.then().assertThat().header("Connection", "keep-alive");
					logger.info("Get Request all Assignment data is successfull");
				} else if (GetAllstatuscode == 404) {
					logger.info("Get Request unsuccessful");
			}}
				@Given("User send GET Request for the LMS API endpoint with valid submission by student Batch ID")
				public void user_send_get_request_for_the_lms_api_endpoint_with_valid_submission_by_student_batch_id() {
					this.uri = Config.Getsubbybatchid_URL;
					this.request = RestAssured.given().header("Content-Type", "application/json");//
					logger.info("Get Request valid submission by batch id");
				}

				@When("User create GET request with valid submission by student Batchid from {string} and {string}")
				public void user_create_get_request_with_valid_submission_by_student_batchid_from_and(String string, String string2) throws IOException {
					logger.info("TestCase Name= " + string);

					String assignmentId = excelDataValue(string, string2);

					response = this.request.when().get(Config.Getsubbybatchid_URL + "/" + assignmentId).then().log().all()
							.extract().response();
				}

				@Then("User receives {string} OK Status for valid submission by student batch id")
				public void user_receives_ok_status_for_valid_submission_by_student_batch_id(String statuscode) {
					int GetAllstatuscode = response.getStatusCode();
					logger.info("Response Status is= " + GetAllstatuscode);
					if (GetAllstatuscode == 200) {
						response.then().statusCode(Integer.parseInt(statuscode));
						response.then().assertThat().header("Connection", "keep-alive");
						logger.info("Get Request all Assignment data is successfull");
					} else if (GetAllstatuscode == 404) {
						logger.info("Get Request unsuccessful");
				}}
	


	@Given("User creates GET Request for the LMS API endpoint")
	public void user_creates_get_request_for_the_lms_api_endpoint() {
		 this.uri = Config.Getallsubmission_URL;
		    this.request = RestAssured.given().header("Content-type","application/json");
		    logger.info("request for get all programs valid scenario");
	}

	@When("User sends HTTPS requests")
	public void user_sends_https_requests() {
		response = this.request.get(this.uri);	
		response.then().log().all();
		logger.info("when get all programs");
	}
	@Then("Request should be successfull with status code {string} for GET All submission")
	public void request_should_be_successfull_with_status_code_for_get_all_submission(String statuscode) {
		//status code validation
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 200) {
		response.then().statusCode(Integer.parseInt(statuscode));
		//Header Validation
		response.then().assertThat().header("Connection", "keep-alive");
		//Json Schema Validation
			//	response.then().assertThat()
				//.body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/resources/JsonSchemas/getallsubmissionschema.json")));
		logger.info("get all submission success");}}
		
		@Given("User creates GET Request for the invalid LMS API endpoint")
		public void user_creates_get_request_for_the_invalid_lms_api_endpoint() {
			 this.uri = Config.Getallsubmissioninvalid_URL;
			    this.request = RestAssured.given().header("Content-type","application/json");
			    logger.info("request for get all programs invalid scenario");
		}
		@When("User sends HTTPS requests invalid url")
		public void user_sends_https_requests_invalid_url() {
			response = this.request.get(this.uri);	
			response.then().log().all();
			logger.info("when get all programs");
		}
		@Then("Request should be successfull with status code {string} for GET All submission invalid url")
		public void request_should_be_successfull_with_status_code_for_get_all_submission_invalid_url(String statuscode) {
			int GetAllstatuscode = response.getStatusCode();
			if (GetAllstatuscode == 400) {
			response.then().statusCode(Integer.parseInt(statuscode));
			//Header Validation
			response.then().assertThat().header("Connection", "keep-alive");
			//Json Schema Validation
					response.then().assertThat()
					.body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/resources/JsonSchemas/getallsubmissionschema.json")));
			logger.info("get all submission success");
		}}

@Given("User send GET Request for the LMS API endpoint for submission invalid AssignmentID")
public void user_send_get_request_for_the_lms_api_endpoint_for_submission_invalid_assignment_id() {
	this.uri = Config.Getassignment_URL;
	this.request = RestAssured.given().header("Content-Type", "application/json");//
	logger.info("Get Request invalid assignment id");
}

@When("User create GET request for invalid AssignmentID from {string} and {string}")
public void user_create_get_request_for_invalid_assignment_id_from_and(String string, String string2) throws IOException {
	logger.info("TestCase Name= " + string);
	
	response = this.request
		.when()
		.get(Config.Getassignment_URL + "/" + randomestring())
		.then()
		.log().all().extract().response();
		}


@Then("User {string} Not Found Status for invalid assignment id")
public void user_not_found_status_for_invalid_assignment_id(String statuscode) {
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
@Given("User send GET Request for the LMS API endpoint for submission null AssignmentID")
public void user_send_get_request_for_the_lms_api_endpoint_for_submission_null_assignment_id() {
	this.uri = Config.Getassignment_URL;
	this.request = RestAssured.given().header("Content-Type", "application/json");//
	logger.info("Get Request for null assignment id");
}

@When("User create GET request for null AssignmentID from {string} and {string}")
public void user_create_get_request_for_null_assignment_id_from_and(String string, String string2) {
logger.info("TestCase Name= " + string);
	
	response = this.request
		.when()
		.get(Config.Getassignment_URL + "/")
		.then()
		.log().all().extract().response();
		}


@Then("User {string} Bad Request Status for null assignment id")
public void user_bad_request_status_for_null_assignment_id(String statuscode) {
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

@Given("User send GET Request for the LMS API endpoint for submission invalid StudentID")
public void user_send_get_request_for_the_lms_api_endpoint_for_submission_invalid_student_id() {
	this.uri = Config.Getsubstudentid_URL;
	this.request = RestAssured.given().header("Content-Type", "application/json");
	logger.info("Get Request invalid student id");
}

@When("User create GET request for invalid StudentID from {string} and {string}")
public void user_create_get_request_for_invalid_student_id_from_and(String string, String string2) {
logger.info("TestCase Name= " + string);
	
	response = this.request
		.when()
		.get(Config.Getsubstudentid_URL + "/" + randomestring())
		.then()
		.log().all().extract().response();
		}


@Then("User {string} Not Found Status for invalid student id")
public void user_not_found_status_for_invalid_student_id(String statuscode) {
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
@Given("User send GET Request for the LMS API endpoint for submission null StudentID")
public void user_send_get_request_for_the_lms_api_endpoint_for_submission_null_student_id() {
	this.uri = Config.Getsubstudentid_URL;
	this.request = RestAssured.given().header("Content-Type", "application/json");
	logger.info("Get Request null student id");
}

@When("User create GET request for null StudentID from {string} and {string}")
public void user_create_get_request_for_null_student_id_from_and(String string, String string2) {
logger.info("TestCase Name= " + string);
	
	response = this.request
		.when()
		.get(Config.Getsubstudentid_URL + "/" )
		.then()
		.log().all().extract().response();
		}


@Then("User {string} Not Found Status for null student id")
public void user_not_found_status_for_null_student_id(String statuscode) {
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

@Given("User creates GET Request for the LMS API endpoint with invalid submission BatchId")
public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_submission_batch_id() {
	this.uri = Config.Getsubstudentid_URL;
	this.request = RestAssured.given().header("Content-Type", "application/json");
	logger.info("Get Request invalid submission batch id");
}

@When("User create GET request with invalid submission batch ID from {string} and {string}")
public void user_create_get_request_with_invalid_submission_batch_id_from_and(String string, String string2) {
logger.info("TestCase Name= " + string);
	
	response = this.request
		.when()
		.get(Config.Getsubstudentid_URL + "/" )
		.then()
		.log().all().extract().response();
		}


@Then("User get status {string} for invalid submission batch id")
public void user_get_status_for_invalid_submission_batch_id(String statuscode) {
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
@Given("User creates GET Request for the LMS API endpoint with null submission BatchId")
public void user_creates_get_request_for_the_lms_api_endpoint_with_null_submission_batch_id() {
	this.uri = Config.Getsubstudentid_URL;
	this.request = RestAssured.given().header("Content-Type", "application/json");
	logger.info("Get Request null submission batch id");
}

@When("User create GET request with null submission batch ID from {string} and {string}")
public void user_create_get_request_with_null_submission_batch_id_from_and(String string, String string2) {
logger.info("TestCase Name= " + string);
	
	response = this.request
		.when()
		.get(Config.Getsubstudentid_URL + "/" )
		.then()
		.log().all().extract().response();
		}


@Then("User get status {string} for null submission batch id")
public void user_get_status_for_null_submission_batch_id(String statuscode) {
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
@Given("User creates GET Request for the LMS API endpoint with invalid get submission by BatchId")
public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_get_submission_by_batch_id() {
	this.uri = Config.Getsubbybatchid_URL;
	this.request = RestAssured.given().header("Content-Type", "application/json");
	logger.info("Get Request invalid batch id");
}

@When("User create GET request with invalid get submission by batch ID from {string} and {string}")
public void user_create_get_request_with_invalid_get_submission_by_batch_id_from_and(String string, String string2) {
logger.info("TestCase Name= " + string);
	
	response = this.request
		.when()
		.get(Config.Getsubbybatchid_URL + "/" + randomestring())
		.then()
		.log().all().extract().response();
		}


@Then("User get status {string} bad request for invalid get submission by batch id")
public void user_get_status_bad_request_for_invalid_get_submission_by_batch_id(String statuscode) {
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
@Given("User creates GET Request for the LMS API endpoint with null submission by BatchId")
public void user_creates_get_request_for_the_lms_api_endpoint_with_null_submission_by_batch_id() {
	this.uri = Config.Getsubbybatchid_URL;
	this.request = RestAssured.given().header("Content-Type", "application/json");
	logger.info("Get Request null submission by batch id");
}

@When("User create GET request with null submission by batch ID from {string} and {string}")
public void user_create_get_request_with_null_submission_by_batch_id_from_and(String string, String string2) {
logger.info("TestCase Name= " + string);
	
	response = this.request
		.when()
		.get(Config.Getsubbybatchid_URL + "/" )
		.then()
		.log().all().extract().response();
		
}

@Then("User get status {string} bad requesr for null submission by batch id")
public void user_get_status_bad_requesr_for_null_submission_by_batch_id(String statuscode) {
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
@Given("User creates GET Request for the LMS API endpoint with invalid get submission by userID")
public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_get_submission_by_user_id() {
	this.uri = Config.Getsubbyuserid_URL;
	this.request = RestAssured.given().header("Content-Type", "application/json");
	logger.info("Get Request invalid userid");
}

@When("User create GET request with invalid get submission by userID from {string} and {string}")
public void user_create_get_request_with_invalid_get_submission_by_user_id_from_and(String string, String string2) {
logger.info("TestCase Name= " + string);
	
	response = this.request
		.when()
		.get(Config.Getsubbyuserid_URL + "/" + randomestring())
		.then()
		.log().all().extract().response();
		}


@Then("User get status {string} bad request for invalid get submission by userID")
public void user_get_status_bad_request_for_invalid_get_submission_by_user_id(String statuscode) {
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

@Given("User creates GET Request for the LMS API endpoint with null submission by UserID")
public void user_creates_get_request_for_the_lms_api_endpoint_with_null_submission_by_user_id() {
	this.uri = Config.Getsubbyuserid_URL;
	this.request = RestAssured.given().header("Content-Type", "application/json");
	logger.info("Get Request null submission user id");
}
@When("User create GET request with null submission by  UserID from {string} and {string}")
public void user_create_get_request_with_null_submission_by_user_id_from_and(String string, String string2) {
logger.info("TestCase Name= " + string);
	
	response = this.request
		.when()
		.get(Config.Getsubbyuserid_URL + "/" )
		.then()
		.log().all().extract().response();
		}


@Then("User get status {string} bad requesr for null submission by  userid")
public void user_get_status_bad_requesr_for_null_submission_by_userid(String statuscode) {
	int GetAllstatuscode = response.getStatusCode();
	logger.info("Response Status is= " + GetAllstatuscode);
	if (GetAllstatuscode == 200) {
		response.then().statusCode(Integer.parseInt(statuscode));
		response.then().assertThat().header("Connection", "keep-alive");
		logger.info("Get Request all Assignment data is successfull");
	} else if (GetAllstatuscode == 404) {
		logger.info("Get Request unsuccessful");
	}
}}



