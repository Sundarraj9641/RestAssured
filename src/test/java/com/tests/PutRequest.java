package com.tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class PutRequest {

	@Test
	public void putRequest() {
		
		// Better to use Map to preserve the order of the json body
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", "Kamal");
		map.put("role", "Senior Analyst");
		map.put("city", "Trichy");
		
		JSONObject request = new JSONObject(map);
		
		
		baseURI = "http://localhost:3000";
		
		given().
			contentType(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put("/Employees/8").	//id = 8
		then().
			statusCode(200);
	}
}
