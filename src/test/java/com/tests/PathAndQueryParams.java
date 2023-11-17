package com.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class PathAndQueryParams {
	
	@Test
	public void pathAndQueryParm() {
		
		// url -- https://reqres.in/api/users?page=2
		
		given().
			pathParam("data", "users").		//data--variable, user -- value in the url
			queryParam("page", "2").		//page, id -- parameter
			queryParam("id", "10").			// 2, 10 -- value
		when().
			get("https://reqres.in/api/{data}").
		then()
			.statusCode(200)
			.log().all();
	}

}
