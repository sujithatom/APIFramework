package stepDefinitions;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBulid;
import resources.Utils;

public class StepDefinition extends Utils {
	RequestSpecification request;
	ResponseSpecification responsespec;
	Response response;
	TestDataBulid data=new TestDataBulid();
	
	@Given("Add place payLoad with {string} {string} {string}")
	public void add_place_payLoad_with(String name, String language, String address) throws IOException {
	   
	    
	     request=given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource,String method) {
	    
		// Write code here that turns the phrase above into concrete actions
		
		APIResources resourceAPI=APIResources.valueOf(resource);//valueof() will call the constructor
		System.out.println(resourceAPI.getResource());
		responsespec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		response= request.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))  
			response= request.when().get(resourceAPI.getResource());	
	}

	@Then("The API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
		
		assertEquals(getJsonPath(response,keyValue),expectedValue);
	}

	@Then("verify palce_Id created maps to {string} using {string}")
	public void verify_palce_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		String place_id=getJsonPath(response,"place_id");
		request=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String actualName=getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
	}


}