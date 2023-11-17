package com.tests;

import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class ParsingXMLResponse {

	@Test
	public void xmlResponse_Aproach_1() {

		given()
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
		.statusCode(200)
		.body("TravelerinformationResponse.page", equalTo("1"));
	}

	@Test
	public void xmlResponse_Aproach_2() {

		Response res = given()
					   .when()
					   		.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		//verify for status code is equal to 200
		Assert.assertEquals(res.getStatusCode(), 200);
		
		//get page no from xml using xmlpath
		//then convert into string
		//store that into string variable
		String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
		
		//verify page no
		Assert.assertEquals(pageNo, "1");
					   
		//get name from xml at the index of 2 using xmlpath
		//then convert into string
		//store that into string variable
		String travelerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[2].name").toString();
		
		//verify the name
		Assert.assertEquals(travelerName, "vano");
	}
	
	@Test
	public void xmlResponse_Aproach_3() {

		Response res = given()
					   .when()
					   		.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		//convert the response into string using asSting()
		//JSON path -- toString()
		//XML path -- asString()
		XmlPath xml = new XmlPath(res.asString());
		
		
		//get the list af travelers form the xml and store that in List<String>
		List<String> travellers = xml.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		
		
		//checking for a perticular Traveler
		for(String traveller: travellers) {
			
			if(traveller.equals("karen")) {
				System.out.println("Traveller found");
				break;
			}
		
		}
		
	} 

}
