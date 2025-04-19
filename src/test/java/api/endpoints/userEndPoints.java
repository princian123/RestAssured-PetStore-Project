package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// created for CRUD Operation
public class userEndPoints {
	
	//Post Method
	public static Response createUser(user payload)
	{
	Response response =
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routs.post_url);
	
	return response;
		
	}
	
	//get method
	public static Response readUser(String username)
	{
	Response response =
		given()
			.pathParam("username", username)
		.when()
			.get(Routs.get_url);
	
	return response;
		
	}
	
	// Update Method
	public static Response updateUser(String username, user payload)
	{
	Response response =
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(Routs.put_url);
	
	return response;
		
	}
	
	// Delete Method
		public static Response deleteUser(String username)
		{
		Response response =
			given()
				.pathParam("username", username)
			.when()
				.delete(Routs.delete_url);
		
		return response;
			
		}
}
