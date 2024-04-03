package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	// method created for getting URL's from routes file
	public static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");
	      return routes;
	}

	public static  Response creatUser(User payload)
	{
		String post_url = getURL().getString("post_url");	
		Response response =given()
		 .contentType("application/json")
		 .accept("application/json")
		 .body(payload)
		.when()
		   .post(post_url);
		return response;
	}
	
	public static  Response getUser(String userName)
	{
		String get_url = getURL().getString("get_url");
		Response response =given()
		      .pathParams("username",userName)
		.when()
		   .get(get_url);
		return response;
	}
	
	public static  Response updateUser(String userName, User payload)
	{
		String update_url = getURL().getString("update_url");
		Response response =given()
		 .contentType("application/json")
		 .accept("application/json")
		 .pathParams("username",userName)
		 .body(payload)
		.when()
		   .put(update_url);
		return response;
	}
	
	public static  Response deleteUser(String userName)
	{
		String delete_url = getURL().getString("delete_url");
		Response response =given()
		      .pathParams("username",userName)
		.when()
		   .delete(delete_url);
		return response;
	}
}
