package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// created for CRUD Operation
public class userEndPoints2 {
	
	//created for getting URL from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	
	//Post Method
	public static Response createUser(user payload)
	{
		
		String post_url=getURL().getString("post_url");
		
	Response response =
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
	
	return response;
		
	}
	
	//get method
	public static Response readUser(String username)
	{
		String get_url=getURL().getString("get_url");
		
	Response response =
		given()
			.pathParam("username", username)
		.when()
			.get(get_url);
	
	return response;
		
	}
	
	// Update Method
	public static Response updateUser(String username, user payload)
	{
		String put_url=getURL().getString("put_url");
		
	Response response =
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(put_url);
	
	return response;
		
	}
	
	// Delete Method
		public static Response deleteUser(String username)
		{
			String delete_url=getURL().getString("delete_url");
			
		Response response =
			given()
				.pathParam("username", username)
			.when()
				.delete(delete_url);
		
		return response;
			
		}
}
