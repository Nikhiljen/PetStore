package api.endPoints;

import static io.restassured.RestAssured.given;

import api.payLoad.Store;
import api.utilities.propertiesFile;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class storeEndpoints {
	private static String base_Store_url =  propertiesFile.getProperty("base_Store_url");
	private static String base_order_url =  propertiesFile.getProperty("base_Store_order_url");
	
	
	public static Response getStoreInventory() {
		String path = "inventory";
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("inventoryPath", path)
				.when().get(base_Store_url+"/{inventoryPath}");
		return response;
	}
	
	public static Response placeOrder(Store Storepayload) {
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(Storepayload)
				.when().post(base_order_url);
		return response;
	}
	
	public static Response getPurchaseOrder(int order_Id) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("orderId", order_Id)
				.when().get(base_Store_url+"/{orderId}");
		return response;
	}
	
	public static Response deleteOrder(int order_Id) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("orderId", order_Id)
				.when().delete(base_Store_url+"/{orderId}");
		return response;
	}
}


