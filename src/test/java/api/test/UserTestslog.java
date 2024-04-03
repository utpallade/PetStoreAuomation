package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import api.endpoints.UserEndPoints;
import api.payload.User;

public class UserTestslog {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	
	@BeforeClass
	public void setdata()
	{
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");
	}

	@Test(priority = 1)
	public void testPostUser()
	{
		logger.info("------ Creating User------");
		Response respons = UserEndPoints.creatUser(userPayload);	
		respons.then().log().all();
		
		Assert.assertEquals(respons.getStatusCode(), 200);
		Assert.assertEquals(respons.contentType(), "application/json");
		logger.info("------User Created------");
	}
	
	@Test(priority=2)
	public void testGetUser()
	{
		logger.info("------ Getting User------");
		Response response = UserEndPoints.getUser(this.userPayload.getUsername());
        response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.contentType(), "application/json");
		logger.info("------User displayed------");	
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		logger.info("------ updating User------");
		//update data using payload
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		Response respons = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);	
		respons.then().log().body();
		
		Assert.assertEquals(respons.getStatusCode(), 200);
		Assert.assertEquals(respons.contentType(), "application/json");
		logger.info("------ updated User------");	
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		logger.info("------ Deleting User------");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("------ Deleted User------");
	}
}
