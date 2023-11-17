package com.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.util.*;

public class GetIdFromResponse {

	@Test
	public void getIdFromResponse() {
		
		int id;
		baseURI = "http://localhost:3000";
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("name", "Vijay");
		hm.put("role", "Manager");
		hm.put("city", "Chennai");
		
		id=given()
			.contentType("application/json")
			.body(hm)
		.when()
			.post("/Employees")
			.jsonPath().getInt("id");
		
		System.out.println("ID of this response is: "+id);
		
	}
}
