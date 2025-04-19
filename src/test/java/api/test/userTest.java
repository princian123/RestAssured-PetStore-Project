package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.common.returnsreceiver.qual.This;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints;
import api.payload.user;
import groovy.transform.stc.FirstParam.ThirdGenericType;
import io.restassured.response.Response;

public class userTest {
	Faker faker;
	user userPayload;
	public Logger logger;
	@BeforeClass
	public void setup()
	{
		faker = new Faker(); 
		userPayload = new user();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		//log
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostRequest()
	{
		logger.info("****************** creating user**********************");
		Response response = userEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******************user is created**********************");
	}
	
	@Test(priority=2)
	public void getUserByName()
	{
		logger.info("****************** retriving user**********************");
		Response response= userEndPoints.readUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******************user is retrived **********************");
	}
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("****************** Updating user**********************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=userEndPoints.updateUser(userPayload.getUsername(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//checking updated data
		Response responseAfterUpdate= userEndPoints.readUser(userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("******************user is Updated**********************");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("****************** deleting user**********************");
		Response response=userEndPoints.deleteUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("******************user id deleted**********************");
	}

}
