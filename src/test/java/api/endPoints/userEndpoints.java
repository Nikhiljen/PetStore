package api.endPoints;

import static io.restassured.RestAssured.*;

import api.payLoad.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndpoints {
	
	//Create user end points return response 
	public static Response createUser(User PayLoad) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(PayLoad).when()
				.post(routes.user_post_url);

		return response;
	}
	
	//get user response using get requests
	public static Response readUser(String userName) {

		Response response = given().pathParam("username", userName).when()
				.get(routes.user_get_url);
		return response;
	}
	
	//update some changes in user using put requests
	
	public static Response updateUser(String userName, User PayLoad) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(PayLoad).pathParam("username", userName).when()
				.put(routes.user_put_url);

		return response;
	}
	
	//Delete user using delete request and user name
	public static Response deleteUser(String userName) {

		Response response = given().pathParam("username", userName).when()
				.delete(routes.user_delete_url);
		
		return response;
	}
	
	
}
