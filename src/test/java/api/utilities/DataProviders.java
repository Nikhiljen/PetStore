package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "newUserData")
	public String[][] getAllData() {
	    String[][] apiData = null;

	    try {
	        String path = System.getProperty("user.dir") + "//testData//UserData2.xlsx";
	        XLUtilities xl = new XLUtilities(path);

	        int rowNum = xl.getRowCount("UserSheet"); // Total rows, including header
	        int colNum = xl.getCellCount("UserSheet", 0); // Columns in the header row
	        
	        // Exclude header row
	        apiData = new String[rowNum - 1][colNum];
	        
	        for (int i = 1; i < rowNum; i++) { // Start from row 1 (data rows)
	            for (int j = 0; j < colNum; j++) {
	                apiData[i - 1][j] = xl.getCellData("UserSheet", i, j); // Exclude header
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Failed to load data from Excel for 'newUserData'.");
	    }
	    return apiData;
	}

	@DataProvider(name = "UserName")
	public Object[] getUsername() {
	    Object[] usernameData = null;
	    try {
	        String path = System.getProperty("user.dir") + "//testData//UserData2.xlsx";
	        XLUtilities xl = new XLUtilities(path);

	        int rowNum = xl.getRowCount("UserSheet"); // Total rows, including header
	        
	        // Exclude header row
	        usernameData = new Object[rowNum - 1];
	        
	        for (int i = 1; i < rowNum; i++) { // Start from row 1 (data rows)
	            usernameData[i - 1] = xl.getCellData("UserSheet", i, 1); // Column index for "Username"
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Failed to load data from Excel for 'UserName'.");
	    }
	    return usernameData;
	}
	
	

}
