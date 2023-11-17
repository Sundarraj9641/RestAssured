package com.tests;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.response.Response;

public class Headers {
	
	@Test
	public void singleheader() {
		
		Response res = given().when().get("https://www.google.com/");
		
		//get the value of header (Content-Type) and store it in String
		String headerValue = res.getHeader("Content-Type");
		
		//print the header value
		System.out.println("Value of Content-Type Header is: " + headerValue);
		
	}
	
	@Test
	public void headersWithNameAndValue() {
		
		Response res = given().when().get("https://www.google.com/");
		
		//get all the headers
		io.restassured.http.Headers headersValue = res.getHeaders();
		
		for(Header hd :headersValue) {
			
			System.out.println(hd.getName() + "      " + hd.getValue());
		}
		
	}

}
