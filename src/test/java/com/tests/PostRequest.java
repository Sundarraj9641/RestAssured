package com.tests;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.*;
import java.io.*;


public class PostRequest {

	@BeforeTest
	public void baseUrl() {
		baseURI = "http://localhost:3000";
	}

	@Test(priority=1 ,enabled=false)
	public void PostRequestUsingMap() {

		//		JSON.simple dependency is added
		//		<!-- https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple -->
		//			<dependency>
		//			    <groupId>com.googlecode.json-simple</groupId>
		//			    <artifactId>json-simple</artifactId>
		//			    <version>1.1.1</version>
		//			</dependency>


		Map<String, Object> map = new HashMap<String, Object>();

		map.put("name", "Manoj");
		map.put("role", "Consultant");
		map.put("city", "Chennai");

		System.out.println(map);

		//JSON Object to get the JSON format
		//{"name":"Manoj","role":"Consultant","city":"Chennai"}
		JSONObject postrequest = new JSONObject(map);
		System.out.println(postrequest);

		given().
		contentType(ContentType.JSON).		//Specify the content type
			body(postrequest.toJSONString()).	//post body
		when().
			post("/Employees").					//path
		then().
			statusCode(201);					//verify status code
	}

	@SuppressWarnings("unchecked")
	@Test(priority=2 ,enabled=false)
	public void withoutUsingmap() {

		JSONObject request = new JSONObject();
		request.put("name", "DineshKumar");
		request.put("role", "tester");
		request.put("city", "Mumbai");

		given().
			contentType(ContentType.JSON).		//Specify the content type
			body(request.toJSONString()).	    //post body
		when().
			post("/Employees").					//path
		then().
			statusCode(201);					//verify status code
	}

	@Test(priority=3 ,enabled=false)
	public void withoutJsonObject() {

		HashMap<String, String> hm = new HashMap<String, String>();

		hm.put("name", "DineshKumar");
		hm.put("role", "tester");
		hm.put("city", "Mumbai");

		given()
			.contentType("application/json")
			.body(hm)
		.when()
			.post("/Employees")
		.then()
			.statusCode(201)
			.log().all();


	}

	@Test(priority=4 ,enabled=false)
	public void usingPojoClass() {

		//object of the pojo class
		Pojo_PostRequest ppr = new Pojo_PostRequest();

		ppr.setName("Robert");
		ppr.setRole("Tester");
		ppr.setCity("Bangalore");

		given().
			contentType("application/json").
			body(ppr).
		when().
			post("/Employees").
		then().
			statusCode(201)
			.log().all();
	}

	@Test(priority=4 ,enabled=true)
	public void usingExternalFile() throws FileNotFoundException {

		File file = new File("C:\\Users\\SUMAHALI\\REST Assured\\RESTAssuredTesting\\src\\test\\resources\\JSON File\\ExternalFile.json");

		//FileReader fr = new FileReader(file);

		//JSONTokener jtk = new JSONTokener(fr);

		//JSONObject data = new JSONObject(jtk);

		given().
			contentType("application/json").
			body(file).
		when().
			post("/Employees").
		then().
			statusCode(201)
			.log().all();

	}

}
