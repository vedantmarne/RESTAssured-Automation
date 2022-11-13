package tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

public class GetAndPostExamples {

	//@Test
	public void testGet() {
		
		baseURI="https://reqres.in/api";
		
		given().
			get("/users?page=2").
		then().
			statusCode(200).
			body("data[4].first_name",equalTo("George")).
			body("data.first_name",hasItems("George","Rachel"));
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPost() {
		
		JSONObject request = new JSONObject();
		
		request.put("name","rohit");
		request.put("job","cricketer");
		
		baseURI="https://reqres.in/api";
		
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).				
			log().all();
	}
}
