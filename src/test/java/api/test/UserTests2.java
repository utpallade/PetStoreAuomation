package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;

public class UserTests2 {
	
	Faker faker;
	User userPayload;
	
	
	
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
		
		
	}

	@Test(priority = 1)
	public void testPostUser()
	{
		Response respons = UserEndPoints2.creatUser(userPayload);	
		respons.then().log().all();
		
		Assert.assertEquals(respons.getStatusCode(), 200);
		Assert.assertEquals(respons.contentType(), "application/json");
	}
	
	@Test(priority=2)
	public void testGetUser()
	{
		Response response = UserEndPoints2.getUser(this.userPayload.getUsername());
        response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.contentType(), "application/json");
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		//update data using payload
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		Response respons = UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);	
		respons.then().log().body();
		
		Assert.assertEquals(respons.getStatusCode(), 200);
		Assert.assertEquals(respons.contentType(), "application/json");
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
