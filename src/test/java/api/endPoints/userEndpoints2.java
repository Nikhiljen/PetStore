package api.endPoints;

import static io.restassured.RestAssured.*;

import api.payLoad.User;
import api.utilities.propertiesFile;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndpoints2 {
	
	private static String base_User_url =  propertiesFile.getProperty("base_User_url");
	// Create user end point - POST request
	
	public static Response createUser(User payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(base_User_url);
		return response;
	}

	// Read user end point - GET request
	public static Response readUser(String userName) {
		Response response = given().pathParam("username", userName).when()
				.get(base_User_url+"/{username}");
		return response;
	}

	// Update user end point - PUT request
	public static Response updateUser(String userName, User payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
				.pathParam("username", userName).when().put(base_User_url+"/{username}");
		return response;
	}

	// Delete user end point - DELETE request
	public static Response deleteUser(String userName) {
		Response response = given().pathParam("username", userName).when()
				.delete(base_User_url+"/{username}");
		return response;
	}
	public static Response loginWithValidUser(String userName, String password) {
	    String loginPath = "login";
		Response response = given()
				.pathParam("LoginPath", loginPath)
				.queryParam("username", userName) // Pass username as query parameter
		        .queryParam("password", password) // Pass password as query parameter.when()
				.get(base_User_url+"/{LoginPath}");
		return response;
	}
	
	public static Response logoutFromForm() {
		String logoutPath = "logout";
		Response response = given()
				.pathParam("LogoutPath", logoutPath)
				.get(base_User_url+"/{LogoutPath}");
		return response;
	}
}
