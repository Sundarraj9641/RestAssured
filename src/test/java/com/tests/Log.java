package com.tests;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class Log {
	@Test
	public void logall() {
		
		// url -- https://reqres.in/api/users?page=2
		
		given().
		when().
			get("https://reqres.in/api/users?page=2").
		then()
			.log().all();
		
		System.out.println("------------------------------------------------------------------");
	}
	
	@Test
	public void logbody() {
		
		// url -- https://reqres.in/api/users?page=2
		
		given().
		when().
			get("https://reqres.in/api/users?page=2").
		then()
			.log().body();
		
		System.out.println("------------------------------------------------------------------");
	}
	
	@Test
	public void logheader() {
		
		// url -- https://reqres.in/api/users?page=2
		
		given().
		when().
			get("https://reqres.in/api/users?page=2").
		then()
			.log().headers();
		
		System.out.println("------------------------------------------------------------------");
	}
	
	@Test
	public void logcookies() {
		
		// url -- https://reqres.in/api/users?page=2
		
		given().
		when().
			get("https://reqres.in/api/users?page=2").
		then()
			.log().cookies();
		
		System.out.println("------------------------------------------------------------------");
	}

}
