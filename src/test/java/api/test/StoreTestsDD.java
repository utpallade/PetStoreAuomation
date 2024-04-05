package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import api.utilities.DataprovidersForStore;
import io.restassured.response.Response;



@SuppressWarnings("unused")
public class StoreTestsDD {
	
	
		
	
	
	
	@Test(priority = 1, dataProvider="Data", dataProviderClass= DataprovidersForStore.class)
	public void testPostStore(String id,String petId,String quantity,String shipDate,String  status,String complete)
	{
		Store StorePayload = new Store();
		StorePayload.setId(Long.parseLong(id));
		StorePayload.setPetId(Integer.parseInt(petId));
		StorePayload.setQuantity(Integer.parseInt(quantity));
		StorePayload.setShipDate(shipDate);
		StorePayload.setComplete(Boolean.parseBoolean(complete));
		StorePayload.setStatus(status);
		
		Response response= StoreEndPoints.createOrder(StorePayload);
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority =2, dataProvider="ID", dataProviderClass= DataprovidersForStore.class)
	public void testgetStore(String storeId)
	{
		Response response= StoreEndPoints.getOrder(Long.parseLong(storeId));
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority =3, dataProvider="ID", dataProviderClass= DataprovidersForStore.class)
	public void testdeletStore(String storeId)
	{
		Response response= StoreEndPoints.deleteOrder(Long.parseLong(storeId));
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
	}
}
