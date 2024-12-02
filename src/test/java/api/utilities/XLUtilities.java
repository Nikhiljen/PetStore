package api.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class XLUtilities {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	public XLUtilities(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) {
		int rowCount = 0;
		try (FileInputStream fi = new FileInputStream(path); XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet with name '" + sheetName + "' not found.");
			}
			rowCount = sheet.getLastRowNum();
			System.out.println(rowCount);
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + path);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error reading Excel file: " + path);
			e.printStackTrace();
		}

		return rowCount;
	}

	// get cell count

	public int getCellCount(String sheetName, int rownum) {
		int cellcount = 0;
		try (FileInputStream fi = new FileInputStream(path); XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet with name '" + sheetName + "' not found.");
			}
			row = sheet.getRow(rownum);
			cellcount = row.getLastCellNum();
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + path);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error reading Excel file: " + path);
			e.printStackTrace();
		}

		return cellcount;
	}

	public String getCellData(String sheetName, int rowNum, int cellNum) {
	    String data = "";
	   
	    	try {
				fi = new FileInputStream(path);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				workbook = new XSSFWorkbook(fi);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	sheet = workbook.getSheet(sheetName);
	    	row = sheet.getRow(rowNum);
	    	cell = row.getCell(cellNum);
	    	
	    	DataFormatter formatter = new DataFormatter();
	    	try {
	    		data = formatter.formatCellValue(cell);
	    	}catch(Exception e)
	    	{
	    		data = "";
	    	}
	    	try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				fi.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return data;
	    }
}