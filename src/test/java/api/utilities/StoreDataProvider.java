package api.utilities;

import org.testng.annotations.DataProvider;

public class StoreDataProvider {
	@DataProvider(name = "StoreData")
	public Object[][] getAllData() {
	    Object[][] apiData = null;
	    
	    try {
	        String path = System.getProperty("user.dir") + "//testData//UserData2.xlsx";
	        XLUtilities1 xl = new XLUtilities1(path);
	        
	     // Exclude header row
	        apiData = xl.MultipleDataReadFromExcell("StoreData"); // Fetch data
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Failed to load data from Excel for 'newUserData'.");
	    }
	    return apiData;
}
	@DataProvider(name = "OrderID")
	public Object[] getOrderID() {
	    Object[] orderIDdata = null;
	    try {
	        String path = System.getProperty("user.dir") + "//testData//UserData2.xlsx";
	        XLUtilities1 xl = new XLUtilities1(path);

	        // Column index for the Id column (0 for first column)
	        int columnIndex = 0;
	        
	        orderIDdata = xl.SingleColumnDataReadFromExcel("OrderId", columnIndex); // Fetch single column data
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Failed to load data from Excel for 'orderId'.");
	    }
	    return orderIDdata;
	}
}
