package api.utillities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	 @DataProvider(name = "Data")
	    public String[][] getAllData() throws Exception {
	        String path = System.getProperty("user.dir")+"//testdata//userdata.xlsx";
	        XLUtility excel = new XLUtility(path);
	        String sheetName = "Sheet1";

	        int rowCount = excel.getRowCount(sheetName);
	        int colCount = excel.getCellCount(sheetName, 0);

	        String[][] apidata = new String[rowCount][colCount];

	        for (int i = 1; i <= rowCount; i++) {
	            for (int j = 0; j < colCount; j++) {
	            	apidata[i-1][j] = excel.getCellData(sheetName, i, j);  // Skip header
	            }
	        }
	        return apidata;         
	    }
	 
	 @DataProvider(name = "UserNames")
	 public String[] getuserName() throws Exception {
		 String path = System.getProperty("user.dir")+"//testdata//userdata.xlsx";	 
		 XLUtility excel = new XLUtility(path);	 
		 String sheetName = "Sheet1";
	
	     int rowCount = excel.getRowCount(sheetName);
	     String[] apidata = new String[rowCount];
	     for (int i = 1; i <= rowCount; i++) {
	    	 apidata[i-1] = excel.getCellData(sheetName, i ,1 ).trim();
	     }
		  return apidata;
	     
		 }

}
