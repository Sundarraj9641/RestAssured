package com.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequest {
	
	@Test
	public void getRequest() {
		//get request
		Response response=RestAssured.get("http://localhost:3000/Employees");
		
		//get status code
		System.out.println(response.statusCode());
		
		//get the time taken for the response
		System.out.println(response.getTime());
		
		//get the body of the response as pretty string
		System.out.println(response.getBody().asPrettyString());
		
		//get the body of the response as pretty string
		System.out.println(response.getBody().asString());
		
		
	}

}
