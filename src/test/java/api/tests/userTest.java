package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

//import api.endPoints.userEndpoints;
import api.endPoints.userEndpoints2;
import api.payLoad.User;
import io.restassured.response.Response;

public class userTest {
	Faker faker;
	User userPayload;

	@BeforeClass
	public void setupData() {
		faker = new Faker();

		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}

	@Test(priority = 1)
	public void test_createUser() {
		Response response = userEndpoints2.createUser(userPayload);
		response.then().log().all();

		// Validate response status code
		Assert.assertEquals(response.getStatusCode(), 200, "Failed to create user.");
	}

	@Test(priority = 2, dependsOnMethods = { "test_createUser" })
	public void test_getUser() {
		Response response = userEndpoints2.readUser(userPayload.getUsername());
		response.then().log().body().statusCode(200);

		// Validate response data
		Assert.assertEquals(response.getStatusCode(), 200, "Failed to fetch user.");
		Assert.assertEquals(response.jsonPath().getString("username"), userPayload.getUsername(),
				"Incorrect username.");
	}

	@Test(priority = 3, dependsOnMethods = { "test_createUser" })
	public void test_updateUser() {
		// Change some details for the user
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		// Send update request
		Response response = userEndpoints2.updateUser(userPayload.getUsername(), userPayload);
		response.then().log().body().statusCode(200);

		// Validate response status code
		Assert.assertEquals(response.getStatusCode(), 200, "Failed to update user.");

		// Fetch and validate updated data
		Response updatedResponse = userEndpoints2.readUser(userPayload.getUsername());
		updatedResponse.then().log().body().statusCode(200);
		Assert.assertEquals(updatedResponse.jsonPath().getString("firstName"), userPayload.getFirstName(),
				"First name not updated.");
		Assert.assertEquals(updatedResponse.jsonPath().getString("lastName"), userPayload.getLastName(),
				"Last name not updated.");
	}

	@Test(priority = 4, dependsOnMethods = { "test_createUser" })
	public void test_deleteUser() {
		Response response = userEndpoints2.deleteUser(userPayload.getUsername());
		response.then().statusCode(200);

		// Validate response status code
		Assert.assertEquals(response.getStatusCode(), 200, "Failed to delete user.");

		// Validate user no longer exists
		Response getResponse = userEndpoints2.readUser(userPayload.getUsername());
		Assert.assertEquals(getResponse.getStatusCode(), 404, "User was not deleted successfully.");
	}
}
