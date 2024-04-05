package api.endpoints;
import static io.restassured.RestAssured.*;

import api.payload.Store;
import io.restassured.response.Response;

public class StoreEndPoints {
	
	public static Response createOrder(Store payload)
	{
		Response response = given()
				.contentType("application/json")
				 .accept("application/json")
				 .body(payload)
				 
			.when()
				   .post(Routes.store_post_url);
				return response;	 
	}

	public static Response getOrder(long orderId)
	{
		Response response = given()
				.pathParam("orderId", orderId)
			.when()
			    .get(Routes.store_get_url);
		return response;
	}
	
	public static Response deleteOrder(long orderId)
	{
		Response response = given()
				.pathParam("orderId", orderId)
			.when()
			    .delete(Routes.store_delete_url);
		return response;
	}
}
