package com.tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

import static io.restassured.RestAssured.*;

public class AssertionOfJsonResponse {
	
	@Test
	public void responseAssertion_Approach_1() {
		
		given()
			.contentType("application.json")
		.when()
			.get("http://localhost:3000/Employees")
		.then()
			.statusCode(200)
			.body("name[0]",equalTo("Sundarraj"));
		
	}
	
	@Test
	public void responseAssertion_Approach_2() {
		
		//json-path dependency is added for this
		
		Response res = given()
			.contentType("application.json")
		.when()
			.get("http://localhost:3000/Employees");

		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		//get the name at the index 0 from the response
		//get() gives the object data type
		//so convert the object into string using -- toString()
		String name = res.jsonPath().get("name[1]").toString();
		
		//verify the name
		Assert.assertEquals(name, "Karthik");
	}
	

}
