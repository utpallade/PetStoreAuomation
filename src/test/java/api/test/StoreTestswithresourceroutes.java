package api.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPointswithresourcerouts;
import api.payload.Store;
import io.restassured.response.Response;



public class StoreTestswithresourceroutes {
	
	
	Faker faker;
	Store StorePayload;
	
	@BeforeClass
	public void setdata()
	{
		faker = new Faker();
		StorePayload = new Store();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        StorePayload.setId(faker.number().numberBetween(0, 10));
		StorePayload.setShipDate(now.format(formatter));
		StorePayload.setStatus("placed");
		StorePayload.setComplete(true);
		}
	
	@Test(priority =1)
	public void testPostStore()
	{
		Response response= StoreEndPointswithresourcerouts.createOrder(StorePayload);
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority =2)
	public void testgetStore()
	{
		Response response= StoreEndPointswithresourcerouts.getOrder(this.StorePayload.getId());
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority =3)
	public void testdeletStore()
	{
		Response response= StoreEndPointswithresourcerouts.deleteOrder(this.StorePayload.getId());
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
	}
}
