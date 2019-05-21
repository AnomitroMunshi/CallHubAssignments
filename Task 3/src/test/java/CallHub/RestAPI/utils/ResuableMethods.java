package CallHub.RestAPI.utils;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import io.restassured.response.Response;


import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;


public class ResuableMethods {
	
	public  static XmlPath rawToXML(Response r) {
		
		String respon=r.asString();
		XmlPath x=new XmlPath(respon);
		return x;
	}
	
public static JsonPath rawToJSON(Response r) {
		
		String respon=r.asString();
		JsonPath jspath=new JsonPath(respon);
		return jspath;
	}




}
