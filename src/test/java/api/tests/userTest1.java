package api.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import api.endPoints.userEndpoints2;
import api.payLoad.User;
import api.utilities.Credentials;
import api.utilities.DataProviders1;
import api.utilities.SetUpData;
import io.restassured.response.Response;

public class userTest1 {
	SetUpData data;
	
	
	@BeforeMethod
	public void setup() {
	    data = new SetUpData();
	}
	@Test(priority=1, dataProvider = "newUserData", dataProviderClass= DataProviders1.class)
	public void test_creatUserusingDataProvider(String userID, String username, String fname, String lname, String email, String pwd, String MobileNumber) {
		
		// Initialize user data
	    User userPayload = data.setupUserData(userID, username, fname, lname, email, pwd, MobileNumber);
		Response response = userEndpoints2.createUser(userPayload);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=2, dataProvider = "UserName", dataProviderClass= DataProviders1.class, dependsOnMethods= {"test_creatUserusingDataProvider"})
	public void test_getUser(String userName) {
		Response response = userEndpoints2.readUser(userName);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority=3, dataProvider = "UserName", dataProviderClass= DataProviders1.class,dependsOnMethods= {"test_creatUserusingDataProvider"})
	public void test_deleteUser(String userName) {
		Response response = userEndpoints2.deleteUser(userName);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=4)
	public void test_loginWithValidUser() {
		Credentials credentials =  SetUpData.createTestCredentials();
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		Response response = userEndpoints2.loginWithValidUser(username, password);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=5,dependsOnMethods= {"test_loginWithValidUser"})
	public void test_logoutWithValidUser() {
		Response response = userEndpoints2.logoutFromForm();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
