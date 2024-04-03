package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.Dataproviders;
import io.restassured.response.Response;

public class DDTests {

	
	@Test(priority = 1, dataProvider="Data", dataProviderClass= Dataproviders.class)
	public void testPostUser(String userID, String userName,String fname, String lname, String useremail, String psw, String pho)
	{
		User userPayload =new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(psw);
		userPayload.setPhone(pho);
		
		
		Response respons = UserEndPoints.creatUser(userPayload);	
		respons.then().log().all();
		
		Assert.assertEquals(respons.getStatusCode(), 200);
		Assert.assertEquals(respons.contentType(), "application/json");
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass= Dataproviders.class)
	public void testDeleteUserbyName(String userName)
	{
		
		Response response = UserEndPoints.deleteUser(userName);
        response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	} 
}
