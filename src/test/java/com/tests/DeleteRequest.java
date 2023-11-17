package com.tests;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
public class DeleteRequest {
	
	@Test
	public void deleteRequest() {
		
		baseURI="http://localhost:3000";
		
		when()
			.delete("/Employees/8")		//Delete the id 8
		.then()
			.statusCode(200);
		
	}
}
