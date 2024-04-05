package api.endpoints;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.Store;
import io.restassured.response.Response;

public class StoreEndPointswithresourcerouts {
	
	
	// method created for getting URL's from routes file
		public static ResourceBundle getURL()
		{
			ResourceBundle routes = ResourceBundle.getBundle("routes");
		      return routes;
		}
	
	public static Response createOrder(Store payload)
	{
		String post_url = getURL().getString("store_post_url");	
		Response response = given()
				.contentType("application/json")
				 .accept("application/json")
				 .body(payload)
				 
			.when()
				   .post(post_url);
				return response;	 
	}

	public static Response getOrder(long orderId)
	{
		String get_url = getURL().getString("store_get_url");
		Response response = given()
				.pathParam("orderId", orderId)
			.when()
			    .get(get_url);
		return response;
	}
	
	public static Response deleteOrder(long orderId)
	{
		String delete_url = getURL().getString("store_delete_url");
		Response response = given()
				.pathParam("orderId", orderId)
			.when()
			    .delete(delete_url);
		return response;
	}
}
