package utilities;
import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//Data provider through excel file
	
	@DataProvider(name ="LoginData")
	public String[][] testData() throws IOException {
		
		String path = "..\\testData\\LoginData.xlsx";
		String sheet ="sheet1";//LoginTest
		ExcelUtility exlutil = new ExcelUtility(path, sheet);
		
		int totalrows=exlutil.getRowCount();
				int totalcols= exlutil.getColumnCount();
				
				String loginData[][] = new String[totalrows][totalcols];

				for(int i=0; i<=totalrows; i++) {
					for(int j=0; j<totalcols; j++) {
						loginData[i][j] = exlutil.getCellData(i, j);
					}
				}
				
				return loginData;
	}
	
	
	/*
	@DataProvider(name = "LoginData")
    public Object[][] getLoginData() throws IOException {
    ExcelUtils excel = new ExcelUtils("path/to/data.xlsx", "Sheet1");
    int rowCount = excel.getRowCount();
    int colCount = excel.getColumnCount();
    Object[][] data = new Object[rowCount - 1][colCount];
    for (int i = 1; i < rowCount; i++) {
        for (int j = 0; j < colCount; j++) {
            data[i - 1][j] = excel.getCellData(i, j);
        }
    }
    return data;
}	 
	 
	 */
	
	//Dataprovider through dataprovider	
	@DataProvider(name ="DPloginData")
	public Object[][] testdata() {
	return	new Object[][] { {"invaliddata@gmail.com", "welcome.123", "Valid"}, 
							{"text@gmail.com", "xyz.12345", "Valid"}, 
							{"", "", "Invalid"},
							{"text@gmail.com", "xyz.12345", "Invalid"}, 
							{"texwewt@gmail.com", "xyz.12345", "Valid"},
		
	};
	}
		
}
