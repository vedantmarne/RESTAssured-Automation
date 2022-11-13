package tests;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import io.restassured.http.ContentType;

public class SoapXMLRequest {

	@Test
	public void validateSoapXML() throws IOException {
		
		File file = new File("./SoapRequest/Add.xml");
		
		if(file.exists())
			System.out.println("File exists");
		
		FileInputStream fileInputStream = new FileInputStream(file);
		String requestBody = IOUtils.toString(fileInputStream,"UTF-8");
		
		baseURI = "https://ecs.syr.edu";
		
		given().
			contentType("text/xml").
			accept(ContentType.XML).
			body(requestBody).
		when().
			post("/faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx").
		then().
			statusCode(200).
			log().all();
	} 
}
