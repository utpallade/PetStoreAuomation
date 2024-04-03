package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.response.Response;

public class UserEndPoints {

	public static  Response creatUser(User payload)
	{
		Response response =given()
		 .contentType("application/json")
		 .accept("application/json")
		 .body(payload)
		.when()
		   .post(Routes.post_url);
		return response;
	}
	
	public static  Response getUser(String userName)
	{
		Response response =given()
		      .pathParams("username",userName)
		.when()
		   .get(Routes.get_url);
		return response;
	}
	
	public static  Response updateUser(String userName, User payload)
	{
		Response response =given()
		 .contentType("application/json")
		 .accept("application/json")
		 .pathParams("username",userName)
		 .body(payload)
		.when()
		   .put(Routes.update_url);
		return response;
	}
	
	public static  Response deleteUser(String userName)
	{
		Response response =given()
		      .pathParams("username",userName)
		.when()
		   .delete(Routes.delete_url);
		return response;
	}
}
