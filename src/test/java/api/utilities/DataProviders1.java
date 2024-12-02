package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders1 {
	@DataProvider(name = "newUserData")
	public Object[][] getAllData() {
	    Object[][] apiData = null;
	    
	    try {
	        String path = System.getProperty("user.dir") + "//testData//UserData2.xlsx";
	        XLUtilities1 xl = new XLUtilities1(path);
	        
	     // Exclude header row
	        apiData = xl.MultipleDataReadFromExcell("Sheet1"); // Fetch data
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Failed to load data from Excel for 'newUserData'.");
	    }
	    return apiData;
}
	@DataProvider(name = "UserName")
	public Object[] getUserNames() {
	    Object[] usernameData = null;
	    try {
	        String path = System.getProperty("user.dir") + "//testData//UserData2.xlsx";
	        XLUtilities1 xl = new XLUtilities1(path);

	        // Column index for the User name column (0 for first column)
	        int columnIndex = 1;
	        
	        usernameData = xl.SingleColumnDataReadFromExcel("Sheet1", columnIndex); // Fetch single column data
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Failed to load data from Excel for 'UserName'.");
	    }
	    return usernameData;
	}
}
