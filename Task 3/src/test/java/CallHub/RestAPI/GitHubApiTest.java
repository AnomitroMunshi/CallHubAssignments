package CallHub.RestAPI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CallHub.RestAPI.utils.ResuableMethods;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class GitHubApiTest {
	Properties prop;
	FileInputStream fis;
	public static Logger log=LogManager.getLogger(GitHubApiTest.class.getName());
	
	@BeforeMethod
	public void getData() {
		try {
			prop = new Properties();
			log.info("Reading property file.........");
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\CallHub\\RestAPI\\resources\\properties.properties");
			prop.load(ip);
			log.info("Property file loaded successfully!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void getRepoNames() {
		
		log.info("Host name->"+prop.getProperty("host"));
		
		RestAssured.baseURI=prop.getProperty("host");
		log.info("Getting host");
		Response res=given().auth().preemptive().basic(prop.getProperty("username"), prop.getProperty("password"))
				.header(prop.getProperty("Key"),prop.getProperty("Value"))
		.expect().statusCode(200).when().get("/user/repos").then().extract().response();
		
		log.info("changing raw format to JSON....");
		JsonPath js=ResuableMethods.rawToJSON(res);
		log.info(js.prettify());
		log.info("Storing all names in a list");
		List<String> names=js.get("name");
		log.info("Printing the names of the repos");
		System.out.println("Name of the repos:");
		for(String s:names) {
			System.out.println(s);
			log.info(s);
		}
		
	}
	
}
