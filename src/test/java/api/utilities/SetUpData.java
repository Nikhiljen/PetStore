package api.utilities;


import api.payLoad.Store;
import api.payLoad.User;

public class SetUpData {
	static User userPayload;
	static Store storePayLoad;
	
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
	
	public Store setupStoreData(String id,String petId,String quantity,String shipDate,String status, boolean complete) {
		storePayLoad = new Store();
		storePayLoad.setId(Integer.parseInt(id));
		storePayLoad.setPetId(Integer.parseInt(petId));
		storePayLoad.setQuantity(Integer.parseInt(quantity));
		storePayLoad.setShipDate(shipDate);
		storePayLoad.setStatus(status);
		storePayLoad.setComplete(complete);
		
		return storePayLoad;
	}
}
