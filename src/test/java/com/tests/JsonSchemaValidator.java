package com.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

//JsonSchemaValidator
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
public class JsonSchemaValidator {

	@Test
	public void jsonSchemaValidator() {
		
		baseURI="http://localhost:3000";
		//Schema.json -- file name(present in target >> classpath folder)
		when()
			.get("/Employees")
		.then()
			.assertThat().body(matchesJsonSchemaInClasspath("Schema.json"))		
			.statusCode(200);
	}
}
