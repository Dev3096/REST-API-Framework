package resourses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class utils {
	public static RequestSpecification req; // this variable will be shared across the class (will not be null for each test case) - values would be added
	public RequestSpecification requestSpec() throws IOException 
	
	{
		if (req == null) { // for first test 
			
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt")); //RequestLoggingFilter.logRequestTo(log) requires a PrintStream object. 
																				//PrintStream object requires a file to log all the data (logging.txt)
		 req = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl")).
				addQueryParam("key","qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log)).
				addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
		 return req;
		}
		return req; // else logs keep on adding serially and returned (check logging.txt file)
	}
	
	public static String getGlobalValues(String key) throws IOException {
		Properties prop = new Properties();
		// reading the global.properties file
		FileInputStream fis = new FileInputStream("D:\\Eclips workspace\\APIFramework\\src\\test\\java\\resourses\\global.properties");
		prop.load(fis); // loading fis in prop to scan the properties file
		return prop.getProperty(key);
		
	}
	
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
		
	}

}
