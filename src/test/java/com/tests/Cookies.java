package com.tests;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.*;

public class Cookies {

	@Test
	public void getKeyOfCookies() {
		
		Response res = given().when().get("https://www.google.com/");
		
		//get the cookies from the response res and store this into Map
		Map <String,String> coo = res.getCookies();
		
		//print all the key from the cookies
		System.out.println("Cookies Values: " + coo.keySet());
		
	}
	
	@Test
	public void getValuesOfCookies() {
		
		Response res = given().when().get("https://www.google.com/");
		
		//get the cookies from the response res and store this into Map
		Map <String,String> coo = res.getCookies();
		
		
		//get the key of the cookies
		for(String c : coo.keySet()) {
			
			//store value of the cookie as a string using key
			String CoockiesValue = res.getCookie(c);
			
			//print keys and values
			System.out.println(c + "         " + CoockiesValue);
		}
		
	}
}
