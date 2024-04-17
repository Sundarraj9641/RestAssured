- Rest Assured enables you to test REST APIs using java libraries and integrates well with Maven.
- Rest Assured is implemented in Groovy
- It used BDD Style for testing
- It has very efficient matching techniques, so asserting your expected results is also pretty straight forward.
- Rest Assured has methods to fetch data from almost every part of the request and response no matter how complex the JSON structures are.

**CREATE JAVA PROJECT ON ECLIPSE:**

Step1 – Open eclipse

Step 2 – Create Maven Project

Step 3 – Add Rest Assured and TestNG dependencies in POM.xml file

Step 4 – Add TestNG plugin on eclipse

**ADDED DEPENDENCIES:**

- **Rest Assured**
- **TestNG**

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**GIVEN()**

Content Type

Set Cookies

Add Auth

Add Param

Set Headers info etc……

**WHEN()**

Get

Post

Put

Delete

**THEN()**

Validate Status Code

Extract Response

Extract Headers

Extract response Body

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**GET REQUEST:**

RestAssured.**get**("<http://localhost:3000/Employees>");

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**RESPONSE:**

**Response** response=RestAssured.get("<http://localhost:3000/Employees>");

//get status code

**response.statusCode();**

//get the time taken for the response

**response.getTime();**

//get the body of the response as pretty string

**response.getBody().asPrettyString();**

//get the body of the response as string

**response.getBody().asString();**

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**STATICALLY IMPORT METHODS:**

In order to use REST assured effectively it's recommended to statically import methods from the following classes:

io.restassured.RestAssured.\*

io.restassured.matcher.RestAssuredMatchers.\*

org.hamcrest.Matchers.\*

**import static io.restassured.RestAssured.\*;**

- If you use this, no need to use RestAssured.get()
- You can directly use get()

Response response = get("<http://localhost:3000/Employees>");

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**BASE URL:**

//base URL

**baseURI** = "<http://localhost:3000>";

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**VERIFY STATUS CODE:**

//verify status code should 200

when().get("/Employees").**then().statusCode(200);**

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**VERIFY BODY OF THE JSON RESPONSE: (using Json path)**

**#1 without using Response class**

import static org.hamcrest.Matchers.\*;

baseURI = "<http://localhost:3000>";

//verify the body of the response

//checking for id of index 1 is equal to 2

when().get("/Employees").then().**body("id\[1\]", equalTo(2));**

//verify the name in the response

when ().get("/Employees").then().**body("name\[0\]", equalTo**("Sundarraj"**));**

**#2 using Response class**

//json-path dependency is added for this

Response **res** = given()

.contentType("application.json")

.when()

.get("<http://localhost:3000/Employees>");

**Assert.assertEquals(res.getStatusCode(), 200);**

Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");

//get the name at the index 0 from the response

//get() gives the object data type

//so convert the object into string using -- toString()

**String name = res.jsonPath().get("name\[1\]").toString();**

//verify the name

**Assert.assertEquals(name, "Karthik");**

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**VERIFY BODY OF THE XML: (using XML path)**

**#1 without using Response Class**

given()

.when()

.get("<http://restapi.adequateshop.com/api/Traveler?page=1>")

.then()

.statusCode(200)

**.body("TravelerinformationResponse.page", equalTo("1"));**

**#2 using Response class**

Response **res** = given()

.when()

.get("<http://restapi.adequateshop.com/api/Traveler?page=1>");

//verify for status code is equal to 200

Assert.assertEquals(res.getStatusCode(), 200);

//get page no from xml using xmlpath

//then convert into string

//store that into string variable

**String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();**

//verify page no

**Assert.assertEquals(pageNo, "1");**

//get name from xml at the index of 2 using xmlpath

//then convert into string

//store that into string variable

**String travelerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation\[2\].name").toString();**

//verify the name

**Assert.assertEquals(travelerName, "vano");**

**#3 using Xml Path**

Response **res** = given()

.when()

.get("<http://restapi.adequateshop.com/api/Traveler?page=1>");

//convert the response into string using asSting()

//JSON path -- toString()

//XML path -- asString()

**XmlPath xml = new XmlPath(res.asString());**

//get the list af travelers form the xml and store that in List&lt;String&gt;

**List&lt;String&gt; travellers = xml.getList("TravelerinformationResponse.travelers.Travelerinformation.name");**

//checking for a perticular Traveler

for(String traveller: travellers) {

if(traveller.equals("karen")) {

System.out.println("Traveller found");

break;

}

}

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**LOG:**

//for equalTo(), hasItems()

import static org.hamcrest.Matchers.\*;

when ().

get("/Employees").

then().

body("name\[0\]", equalTo("Sundarraj")).

**log().all();**

- log().all()
- log().body()
- log().header()
- log().cookies()

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**HAS ITEMS:**

//checking for names

when ().

get("/Employees").

then().

**body("name",hasItems("Sundarraj", "Karthik"));**

//checking for city

when ().get("/Employees").then().**body("city",hasItems("Chennai", "Coimbatore", "Pune"));**

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**POST REQUEST:**

**Using HashMap:-----**

**HashMap&lt;String, String&gt; hm = new HashMap&lt;String, String&gt;();**

**hm.put("name", "DineshKumar");**

hm.put("role", "tester");

hm.put("city", "Mumbai");

given()

.contentType("application/json")

**.body(hm)**

.when()

.post("/Employees")

.then()

.statusCode(201)

.log().all();

**Using Map:----**

“JSON.Simple” dependency is added in pom.xml to get the json format

Like {

"id": 1,

"name": "Sundarraj",

"role": "Tester",

"city": "Chennai"

}

&lt;!--<https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple> --&gt;

&lt;dependency&gt;

&lt;groupId&gt;com.googlecode.json-simple&lt;/groupId&gt;

&lt;artifactId&gt;json-simple&lt;/artifactId&gt;

&lt;version&gt;1.1.1&lt;/version&gt;

&lt;/dependency&gt;

// JSON.simple dependency is added

// &lt;!-- <https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple> --&gt;

// &lt;dependency&gt;

// &lt;groupId&gt;com.googlecode.json-simple&lt;/groupId&gt;

// &lt;artifactId&gt;json-simple&lt;/artifactId&gt;

// &lt;version&gt;1.1.1&lt;/version&gt;

// &lt;/dependency&gt;

**Map&lt;String, Object&gt; map = new HashMap&lt;String, Object&gt;();**

**map.put("name", "Manoj");**

map.put("role", "Consultant");

map.put("city", "Chennai");

System.out.println(map);

//JSON Object to get the JSON format

//{"name":"Manoj","role":"Consultant","city":"Chennai"}

JSONObject postrequest = new JSONObject(map);

System.out.println(postrequest);

given().

contentType(ContentType.JSON). //Specify the content type

**body(postrequest.toJSONString()).** //post body

when().

post("/Employees"). //path

then().

statusCode(201); //verify status code

**Using org.Json:----**

Add org.json dependency in pom.xml file

&lt;!-- <https://mvnrepository.com/artifact/org.json/json> --&gt;

&lt;dependency&gt;

&lt;groupId&gt;org.json&lt;/groupId&gt;

&lt;artifactId&gt;json&lt;/artifactId&gt;

&lt;version&gt;20231013&lt;/version&gt;

&lt;/dependency&gt;

**JSONObject request = new JSONObject();**

**request.put("name", "DineshKumar");**

request.put("role", "tester");

request.put("city", "Mumbai");

given().

contentType(ContentType.JSON). //Specify the content type

**body(request.toJSONString()).** //post body

when().

post("/Employees"). //path

then().

statusCode(201); //verify status code

**Using POJO (plain old Java object) class:---**

package com.tests;

**public class Pojo_PostRequest {**

**String name;**

**String role;**

**String city;**

**public String getName() {**

**return name;**

**}**

**public void setName(String name) {**

**this.name = name;**

**}**

public String getRole() {

return role;

}

public void setRole(String role) {

this.role = role;

}

public String getCity() {

return city;

}

public void setCity(String city) {

this.city = city;

}

}

//object of the pojo class

**Pojo_PostRequest ppr = new Pojo_PostRequest();**

**ppr.setName("Robert");**

ppr.setRole("Tester");

ppr.setCity("Bangalore");

given().

contentType("application/json").

**body(ppr).**

when().

post("/Employees").

then().

statusCode(201)

.log().all();

**Using External File:----**

import java.io.\*;

**File file = new File("C:\\\\Users\\\\SUMAHALI\\\\REST Assured\\\\RESTAssuredTesting\\\\src\\\\test\\\\resources\\\\JSON File\\\\ExternalFile.json");**

given().

contentType("application/json").

body(file).

when().

post("/Employees").

then().

statusCode(201)

.log().all();

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**PUT REQUEST:**

**Map&lt;String, Object&gt; map = new HashMap&lt;String, Object&gt;();**

**map.put("name", "Kamal");**

map.put("role", "Senior Analyst");

map.put("city", "Trichy");

**JSONObject request = new JSONObject(map);**

baseURI = "<http://localhost:3000>";

given().

contentType(ContentType.JSON).

**body(request.toJSONString()).**

when().

put("/Employees/8"). //id = 8

then().

statusCode(200);

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**PATCH REQUEST:**

**Map&lt;String, Object&gt; map = new HashMap&lt;String, Object&gt;();**

**map.put("name", "Jack");**

map.put("role", "System Engineer");

map.put("city", "Mumbai");

**JSONObject request = new JSONObject(map);**

baseURI="<http://localhost:3000>";

given()

.contentType(ContentType.JSON)

**.body(request.toJSONString())**

.when()

.patch("/Employees/8")

.then()

.statusCode(200);

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**DELETE REQUEST:**

baseURI="<http://localhost:3000>";

when()

**.delete("/Employees/8")** //Delete the id 8

.then()

.statusCode(200);

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**GET ID FROM RESPONSE:**

**int id;**

baseURI = "<http://localhost:3000>";

HashMap&lt;String, Object&gt; hm = new HashMap&lt;String, Object&gt;();

hm.put("name", "Vijay");

hm.put("role", "Manager");

hm.put("city", "Chennai");

**id**\=given()

.contentType("application/json")

.body(hm)

.when()

.post("/Employees")

**.jsonPath().getInt("id");**

System.out.println(id);

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**JSON SCHEMA VALIDATOR:**

Step 1 – Create JSON Schema

\>> copy the json from fake api.

\>>convert it into json schema in online

\>>use json to json schema converter online

Step 2 – Add JSON Schema file in target >> classpath folder

Step 3 – Add maven dependency for JSON Schema Validator

Step 4 – Create new function to validate json response against schema

&lt;!-- <https://mvnrepository.com/artifact/io.rest-assured/json-schema-validator> --&gt;

&lt;dependency&gt;

&lt;groupId&gt;io.rest-assured&lt;/groupId&gt;

&lt;artifactId&gt;json-schema-validator&lt;/artifactId&gt;

&lt;version&gt;5.3.2&lt;/version&gt;

&lt;/dependency&gt;

//JsonSchemaValidator

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

baseURI="<http://localhost:3000>";

//Schema.json -- file name(present in target >> classpath folder)

when()

.get("/Employees")

.then()

**.assertThat().body(matchesJsonSchemaInClasspath("Schema.json"))**

.statusCode(200);

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**PATH AND QUERY PARAMS:**

// url -- <https://reqres.in/api/users?page=2>

given().

**pathParam("data", "users").** //data--variable, user -- value in the url

**queryParam("page", "2").** //page, id -- parameter

queryParam("id", "10"). // 2, 10 -- value

when().

get("<https://reqres.in/api/{data}>").

then()

.statusCode(200)

.log().all();

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**COOCKIES:**

**Get Keys of all Cookies:----**

Response res = given().when().get("<https://www.google.com/>");

//get the cookies from the response res and store this into Map

**Map &lt;String,String&gt; coo = res.getCookies();**

//print all the key from the cookies

System.out.println("Cookies Values: " + coo.keySet());

**Get key and value of the Cookies:----**

Response res = given().when().get("<https://www.google.com/>");

//get the cookies from the response res and store this into Map

Map &lt;String,String&gt; coo = res.getCookies();

//get the key of the cookies

for(String c : coo.keySet()) {

//store value of the cookie as a string using key

String CoockiesValue = res.getCookie(c);

//print keys and values

System.out.println(c + " " + CoockiesValue);

\----------------------------------------------------------------------------------------------------------------------------------------------------------------

**HEADER:**

**Header:----**

Response res = given().when().get("<https://www.google.com/>");

//get the value of header (Content-Type) and store it in String

**String headerValue = res.getHeader("Content-Type");**

//print the header value

System.out.println("Value of Content-Type Header is: " + headerValue);

**Headers:----**

Response res = given().when().get("<https://www.google.com/>");

//get all the headers

**io.restassured.http.Headers headersValue = res.getHeaders();**

for(Header hd :headersValue) {

System.out.println(hd.getName() + " " + hd.getValue());

}

\----------------------------------------------------------------------------------------------------------------------------------------------------------------
