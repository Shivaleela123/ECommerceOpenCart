package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextDataReader {

	  public static Object[][] getTestData(String filePath, String testCaseName) {
	        List<Object[]> dataList = new ArrayList<>();
	        
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                // Split by comma
	                String[] tokens = line.split(",");

	                // If row belongs to the target test case, store the remaining parameters
	                if (tokens.length > 0 && tokens[0].equalsIgnoreCase(testCaseName)) {
	                    Object[] params = new Object[tokens.length - 1];
	                    System.arraycopy(tokens, 1, params, 0, tokens.length - 1);
	                    dataList.add(params);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Convert List to 2D Object Array
	        Object[][] dataArray = new Object[dataList.size()][];
	        return dataList.toArray(dataArray);
	           
	  }
	  }
