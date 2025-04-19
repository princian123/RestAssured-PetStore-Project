package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndPoints;
import api.payload.user;
import api.utillities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority=1, dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String userID,String UserName,String FirstName,String LastName, String Email,String Password, String Phone )
	{
		user userPayload = new user();
		
		userPayload.setId((int) Double.parseDouble(userID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);
		
		Response response = userEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String UserName)
	{
		Response response=userEndPoints.deleteUser(UserName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
