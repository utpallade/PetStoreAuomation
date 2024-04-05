package api.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import io.restassured.response.Response;



public class StoreTestslog {
	
	
	Faker faker;
	Store StorePayload;
	public Logger logger;
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
		
		//logs
				logger = LogManager.getLogger(this.getClass());
				logger.debug("This is a debug message");
		        logger.info("This is an info message");
		        logger.warn("This is a warning message");
		        logger.error("This is an error message");
		        logger.fatal("This is a fatal message");}
	
	@Test(priority =1)
	public void testPostStore()
	{
		logger.info("------ Creating Order------");
		Response response= StoreEndPoints.createOrder(StorePayload);
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
		 logger.info("------Order Created------");
	}
	
	@Test(priority =2)
	public void testgetStore()
	{
		logger.info("------Get Order Details------");
		Response response= StoreEndPoints.getOrder(this.StorePayload.getId());
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
		 logger.info("------Order Details displayed------");
	}

	@Test(priority =3)
	public void testdeletStore()
	{
		logger.info("------Delete Order Details------");
		Response response= StoreEndPoints.deleteOrder(this.StorePayload.getId());
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(), 200);
		 logger.info("------Order Details deleted------");
	}
}
