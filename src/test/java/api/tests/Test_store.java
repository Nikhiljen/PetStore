package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import api.endPoints.storeEndpoints;
import api.payLoad.Store;
import api.utilities.SetUpData;
import api.utilities.StoreDataProvider;
import io.restassured.response.Response;

public class Test_store {
	SetUpData data;
	
	
	@BeforeMethod
	public void setup() {
	    data = new SetUpData();
	}
	@Test
	public void test_getStroreInventory() {
		Response response = storeEndpoints.getStoreInventory();
		Assert.assertEquals(response.getStatusCode(), 200, "Inventory not found or invalid status code returned.");
	}
	
	@Test(dataProvider = "StoreData", dataProviderClass= StoreDataProvider.class)
	public void test_placeOrder(String Id, String PetId, String quantity, String shipDate, String status, boolean complete ) {
		Store storePayLoad = data.setupStoreData(Id, PetId, quantity, shipDate, status, complete);
		Response response = storeEndpoints.placeOrder(storePayLoad);
		String responseBody = response.getBody().asString();
		System.out.println("Response Body: " + responseBody);
		Assert.assertEquals(response.getStatusCode(), 200, "Order not placed or invalid status code returned.");
	}
	
	@Test(dataProvider = "OrderID", dataProviderClass= StoreDataProvider.class)
	public void test_purchaseOrderHistory(int orderId ) {
		Response response = storeEndpoints.getPurchaseOrder(orderId);
		Assert.assertEquals(response.getStatusCode(), 200, "Order not found or invalid status code returned.");
	}
	
	@Test(dataProvider = "OrderID", dataProviderClass= StoreDataProvider.class)
	public void test_deletePurchaseOrder(int orderId) {
		Response response = storeEndpoints.deleteOrder(orderId);
		Assert.assertEquals(response.getStatusCode(), 200, "Order not found or invalid status code returned.");
	}
}
