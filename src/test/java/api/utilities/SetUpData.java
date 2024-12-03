package api.utilities;


import api.payLoad.User;

public class SetUpData {
	static User userPayload;
	
	public User setupUserData(String userID, String username, String fname, String lname, String email, String pwd,
			String MobileNumber) {
		userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(username);
		userPayload.setFirstName(fname); 
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(MobileNumber);
		
		return userPayload;
	}
	
	public static Credentials createTestCredentials() {
		userPayload = new User();
	    userPayload.setUsername("test");
	    userPayload.setPassword("abc@123");
	    return new Credentials(userPayload.getUsername(), userPayload.getPassword());
	}
}
