package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public FileInputStream file;

	public XSSFRow row;
	public XSSFCell cell;
	String path;

	public ExcelUtility(String excelPath, String sheetName) throws IOException {
		workbook = new XSSFWorkbook(new FileInputStream(excelPath));
		sheet = workbook.getSheet(sheetName);
	}

	public int getRowCount() {
		return sheet.getLastRowNum();

	}

	public int getColumnCount() {
		return sheet.getRow(0).getLastCellNum();

	}

	public String getCellData(int rowNum, int colNum) {
		return new DataFormatter().formatCellValue(sheet.getRow(rowNum).getCell(colNum));
	}
	
	public void setCellData() {
		
	}

}
