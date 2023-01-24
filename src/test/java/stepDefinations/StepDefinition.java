package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.location;
import resourses.APIResourses;
import resourses.TestData;
import resourses.utils;

public class StepDefinition extends utils {
	TestData p = new TestData();
	RequestSpecification res;
	ResponseSpecification rep;
	Response response;
	static String place_id;
	@Given("AddPlace payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	   		
		res = given().spec(requestSpec()).body(p.addPlacePayLoad(name, language, address));
		
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resourse, String httpMethod) {
		APIResourses ressAPI = APIResourses.valueOf(resourse); // value of method invokes the constructor which returns the value to pass
		 rep = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 System.out.println(ressAPI.getResourse());
		 if (httpMethod.equalsIgnoreCase("Post")) {
			 response = res.when().post(ressAPI.getResourse());
		 } else if (httpMethod.equalsIgnoreCase("Get")) {
			 response = res.when().get(ressAPI.getResourse());
		 }
		 
		
	    
	}
	@Then("the API call is successfull with status code {int}")
	public void the_api_call_is_successfull_with_status_code(Integer int1) {
	    
		assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	  
		assertEquals(getJsonPath(response,key),value);
		
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String method) throws IOException {
		
		// prepare request spec 
		// get API call
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpec()).queryParam("place_id", place_id);
		user_calls_with_post_http_request(method, "Get");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
		System.out.println("Test Done");
	}

	@Given("Delete place API")
	public void delete_place_api() throws IOException {
	    
		res = given().spec(requestSpec()).body(p.deletePlace(place_id));
	}
}
