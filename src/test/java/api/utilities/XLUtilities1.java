package api.utilities;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtilities1 {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	public String path;

	public XLUtilities1(String path) {
		this.path = path;
	}
	
	
	//Get Multiple row and column
	@SuppressWarnings("unused")
	public Object[][] MultipleDataReadFromExcell(String sheetName) {

		Object[][] data = null;
		try (FileInputStream fi = new FileInputStream(path); XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet with name '" + sheetName + "' not found.");
			}

			int rows = sheet.getLastRowNum();
			int cells = sheet.getRow(0).getLastCellNum();

			data = new Object[rows][cells];

			for (int i = 1; i <= rows; i++) {
				XSSFRow row = sheet.getRow(i);
				for (int j = 0; j < cells; j++) {
					XSSFCell cell = row.getCell(j);

					CellType cellType = cell.getCellType();

					switch (cellType) {
					case STRING:
						data[i-1][j] = cell.getStringCellValue();
						break;

					case NUMERIC:
						if (cell == null) {
							data[i][j] = ""; // Default empty value
						} else {
							 double numericValue = cell.getNumericCellValue();
						        data[i-1][j] = (numericValue % 1 == 0) ? Integer.toString((int) numericValue) : Double.toString(numericValue);
						}
						break;
					case BOOLEAN:
						data[i-1][j] = cell.getBooleanCellValue();
						break;
					default:
						break;
					}

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	//get Single Column Data
	public Object[] SingleColumnDataReadFromExcel(String sheetName, int columnIndex) {
		Object[] data = null;
		try (FileInputStream fi = new FileInputStream(path); XSSFWorkbook workbook = new XSSFWorkbook(fi)) {

			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet with name '" + sheetName + "' not found.");
			}

			int rows = sheet.getLastRowNum();
			data = new Object[rows];

			for (int i = 1; i <= rows; i++) { // Start from row 1 to skip header
				XSSFRow row = sheet.getRow(i);
				if (row == null) {
					continue; // Skip empty rows
				}

				 XSSFCell cell = row.getCell(columnIndex); // Read data from the specified column index
		            if (cell != null) {
		                switch (cell.getCellType()) {
		                    case STRING:
		                        data[i - 1] = cell.getStringCellValue();
		                        break;
		                    case NUMERIC:
		                        // Convert numeric value to String or leave it as is based on the requirement
		                        data[i - 1] = (int) cell.getNumericCellValue();
		                        break;
		                    case BLANK:
		                        data[i - 1] = ""; // Handle blank cells
		                        break;
		                    default:
		                        throw new IllegalArgumentException("Unsupported cell type: " + cell.getCellType());
		                }
		            }
		        }
			} catch (IOException e ) {
			e.printStackTrace();
			}
		return data;
	}
}
