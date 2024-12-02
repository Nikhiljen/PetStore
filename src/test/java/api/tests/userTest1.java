package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;


import api.endPoints.userEndpoints2;
import api.payLoad.User;

import api.utilities.DataProviders1;
import io.restassured.response.Response;

public class userTest1 {
	User userPayload;
	
	public void setupUserData(String userID, String username, String fname, String lname, String email, String pwd,
			String MobileNumber) {
		userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(username);
		userPayload.setFirstName(fname); 
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(MobileNumber);
	}
	
	@Test(priority=1, dataProvider = "newUserData", dataProviderClass= DataProviders1.class)
	public void test_creatUserusingDataProvider(String userID, String username, String fname, String lname, String email, String pwd, String MobileNumber) {
		
		// Initialize user data
	    setupUserData(userID, username, fname, lname, email, pwd, MobileNumber);
		Response response = userEndpoints2.createUser(userPayload);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=2, dataProvider = "UserName", dataProviderClass= DataProviders1.class)
	public void test_getUser(String userName) {
		Response response = userEndpoints2.readUser(userName);
		response.then().statusCode(200);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority=3, dataProvider = "UserName", dataProviderClass= DataProviders1.class)
	public void test_deleteUser(String userName) {
		Response response = userEndpoints2.deleteUser(userName);
		response.then().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
