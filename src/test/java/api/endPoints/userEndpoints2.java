package api.endPoints;

import static io.restassured.RestAssured.*;

import api.payLoad.User;
import api.utilities.propertiesFile;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndpoints2 {
	// Create user end point - POST request
	public static Response createUser(User payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(propertiesFile.getProperty("user_post_url"));
		return response;
	}

	// Read user end point - GET request
	public static Response readUser(String userName) {
		Response response = given().pathParam("username", userName).when()
				.get(propertiesFile.getProperty("user_get_url"));
		return response;
	}

	// Update user end point - PUT request
	public static Response updateUser(String userName, User payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
				.pathParam("username", userName).when().put(propertiesFile.getProperty("user_update_url"));
		return response;
	}

	// Delete user end point - DELETE request
	public static Response deleteUser(String userName) {
		Response response = given().pathParam("username", userName).when()
				.delete(propertiesFile.getProperty("user_delete_url"));
		return response;
	}
	public static Response loginWithValidUser(String userName, String password) {
		String baseUrl = propertiesFile.getProperty("user_logine_url");
	    String loginPath = "login";
		Response response = given()
				.pathParam("LoginPath", loginPath)
				.queryParam("username", userName) // Pass username as query parameter
		        .queryParam("password", password) // Pass password as query parameter.when()
				.get(baseUrl+"/{LoginPath}");
		return response;
	}
}
