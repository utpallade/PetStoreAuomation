package api.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import io.restassured.response.Response;



public class StoreTests {
	
	
	Faker faker;
	Store StorePayload;
	
	@BeforeClass
	public void setdata()
	{
		faker = new Faker();
		StorePayload = new Store();
		//String dob = faker.date().birthday().toString();
		//System.out.println(dob);
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		
		StorePayload.setId(faker.number().numberBetween(0, 10));
		
		StorePayload.setShipDate(now.format(formatter));
		StorePayload.setStatus("placed");
		StorePayload.setComplete(false);
		//System.out.println("  "+StorePayload.getId()+"  "+StorePayload.getPetId()+"  "+StorePayload.getQuantity()+"  "+StorePayload.getShipDate()+"  "+StorePayload.getStatus()+"  "+StorePayload.isComplete());
	}
	
	@Test(priority =1)
	public void testPostStore()
	{
		Response response= StoreEndPoints.createOrder(StorePayload);
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority =2)
	public void testgetStore()
	{
		Response response= StoreEndPoints.getOrder(this.StorePayload.getId());
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority =3)
	public void testdeletStore()
	{
		Response response= StoreEndPoints.deleteOrder(this.StorePayload.getId());
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
	}
}
