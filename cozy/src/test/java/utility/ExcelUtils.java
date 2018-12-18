package utility;

import java.io.File;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.exec.util.StringUtils;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import main.Constants;
import executionEngine.DriverScript;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	 private static XSSFRow Row;
    public static XSSFWorkbook wb;
    public static XSSFSheet sheet;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static FileInputStream fis;

	// This method is to set the File path and to open the Excel file
	// Pass Excel Path and SheetName as Arguments to this method
	public static void setExcelFile(String Path,String SheetName) throws Exception {
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
	}

	// This method is to read the test data from the Excel cell
	// In this we are passing Arguments as Row Num, Col Num & Sheet Name
	@SuppressWarnings("static-access")
	public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			if(Cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
		        Cell.setCellType(Cell.CELL_TYPE_STRING);
		}
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}
	public static int getRegistrationsCount(int RowNum, int ColNum, String SheetName) throws Exception {
	
		try {
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			double CellData = Cell.getNumericCellValue();
			return (int) CellData;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static void setRegistrationsCount(int count) throws IOException
    {

        fis = new FileInputStream(new File(Constants.Path_TestData));
        wb = new XSSFWorkbook(fis);
       sheet = wb.getSheet(Constants.Sheet_TestCases);
        row = sheet.getRow(Constants.Col_CountRow);
        cell = row.getCell(Constants.Col_CountCol);
         String countstr = Integer.toString(count);
          cell.setCellValue(count);
          fis.close();
          FileOutputStream output_file =new FileOutputStream(new File(Constants.Path_TestData));  
          wb.write(output_file);
          output_file.close();
    }
	@SuppressWarnings("static-access")
	// This method is use to write value in the excel sheet
	// This method accepts four arguments (Result, Row Number, Column Number & Sheet
	// Name)
	public static void setCellData(String Result, int RowNum, int ColNum, String SheetName) throws Exception {
		try {
			fis = new FileInputStream(new File(Constants.Path_TestData));
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(SheetName);
			row = sheet.getRow(RowNum);
			Cell = row.getCell(ColNum, row.RETURN_BLANK_AS_NULL);

			if (Cell == null) {
				//FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData);
				//ExcelWBook.write(fileOut);
				Cell = row.createCell(ColNum);
				Cell.setCellValue(Result);
				Cell.setCellStyle(sheet.getColumnStyle(ColNum));
				//fileOut.close();
				//row = sheet.getRow(1);
		         //Cell = row.getCell(Constants.Col_TestStepResult);

		          //Cell.setCellValue(DriverScript.bResult);
		          //fis.close();
		          
				//System.out.println("inside IF BLOCK of setCellData");
			} else {
				Cell.setCellValue(Result);
				Cell.setCellStyle(sheet.getColumnStyle(ColNum));
				
				//ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestData));
				//FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData);
				//ExcelWBook.write(fileOut);
				//Cell.setCellValue(DriverScript.bResult);
				//fileOut.close();
				//System.out.println("inside IF BLOCK of setCellData");

			}
	          fis.close();

			FileOutputStream output_file =new FileOutputStream(new File(Constants.Path_TestData));
	          wb.write(output_file);
	          output_file.close();
//	        FileInputStream fis = new FileInputStream(Constants.Path_TestData);
//	         XSSFWorkbook wb = new XSSFWorkbook(fis);
//	        XSSFSheet sheet = wb.getSheet(Constants.Sheet_TestSteps);
//	         Row = sheet.getRow(1);
//	         Cell = Row.getCell(Constants.Col_TestStepResult);
//
//	          Cell.setCellValue(DriverScript.bResult);
			// Constant variables Test Data path and Test Data file name
			// fileOut.flush();

		} catch (Exception e) {
			e.printStackTrace();
			DriverScript.bResult = false;
		}
	}

	// This method is to get the row count used of the excel sheet
	public static int getRowCount(String SheetName) {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int number = ExcelWSheet.getLastRowNum() + 1;
		return number;
	}

	// This method is to get the Row number of the test case
	// This methods takes three arguments(Test Case name , Column Number & Sheet
	// name)
	public static int getRowContains(String sTestCaseName, int colNum, String SheetName) throws Exception {
		int i;
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int rowCount = ExcelUtils.getRowCount(SheetName);
		for (i = 0; i < rowCount; i++) {
			if (ExcelUtils.getCellData(i, colNum, SheetName).equalsIgnoreCase(sTestCaseName)) {
				break;
			}
		}
		return i;
	}
	// This method is to get the count of the test steps of test case
	// This method takes three arguments (Sheet name, Test Case Id & Test case row
	// number)
	public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception {
		for (int i = iTestCaseStart; i <= ExcelUtils.getRowCount(SheetName); i++) {
			if (!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName))) {
				int number = i;
				return number;
			}
		}
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int number = ExcelWSheet.getLastRowNum() + 1;
		return number;
	}
}