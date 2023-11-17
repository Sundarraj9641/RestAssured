package com.tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import java.util.*;
import static io.restassured.RestAssured.*;
public class PatchRequest {

	@Test
	public void patchRequest() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", "Jack");
		map.put("role", "System Engineer");
		map.put("city", "Mumbai");
		
		JSONObject request = new JSONObject(map);
		
		baseURI="http://localhost:3000";
		
		given()
			.contentType(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.patch("/Employees/8")
		.then()
			.statusCode(200);
		
	}
}
