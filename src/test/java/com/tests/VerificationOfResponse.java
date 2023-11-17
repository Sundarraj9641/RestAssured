package com.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;

//statically imported
import static io.restassured.RestAssured.*;

//for equalTo(), hasItems()
import static org.hamcrest.Matchers.*;


public class VerificationOfResponse {
	
	@BeforeTest
	public void baseUrl() {
		//base URL
		baseURI = "http://localhost:3000";
		
	}
	@Test
	public void staticallyImport() {
		
		//no need to use RestAssured.get()
		//directly use get()
		Response response = get("/Employees");
		
		//print the status code of the response
		System.out.println(response.statusCode());
	}
	
	@Test
	public void verifyStatusCode() {
		
		//verify status code should 200
		when().get("/Employees").then().statusCode(200);
		
	}
	
	@Test
	public void verifyBodyOfTheResponse() {

		//verify the body of the response
		//checking for id of index 1 is equal to 2
		when().
			get("/Employees").
		then().
			body("id[1]", equalTo(2));
		
		//verify the name in the response
		when().get("/Employees").then().body("name[0]", equalTo("Sundarraj")).log().all();
		
		//checking for names
		when().get("/Employees").then().body("name",hasItems("Sundarraj", "Karthik"));
		
		//checking for city
		when().get("/Employees").then().body("city",hasItems("Chennai", "Coimbatore", "Pune"));
	}

}
