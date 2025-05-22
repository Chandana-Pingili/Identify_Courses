package Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet sheet;

    // Load an existing Excel file
    public static void loadExcelFile(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    // Save the Excel file
    public static void saveExcelFile(String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
    }

    // Get data from a specific cell, handling empty cells
    public static String getCellData(int sheetIndex, int rowIndex, int cellIndex) {
        sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            return "";
        }
        Cell cell = row.getCell(cellIndex);
        
        if (cell == null) {
            return "";
        }

        if (cell.getCellType() == CellType.NUMERIC) {
        	return  String.valueOf((long) cell.getNumericCellValue());
        }
        return cell.getStringCellValue();
        }

    // Set data in a specific cell
    public static void setCellData(int sheetIndex, int rowIndex, int cellIndex, String data) {
        sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            cell = row.createCell(cellIndex);
        }
        cell.setCellValue(data);
    }

    // Create a new sheet
    public static void createSheet(String sheetName) {
        workbook.createSheet(sheetName);
    }

    // Create a new row
    public static void createRow(int sheetIndex, int rowIndex) {
        sheet = workbook.getSheetAt(sheetIndex);
        sheet.createRow(rowIndex);
    }

    // Create a new cell
    public static void createCell(int sheetIndex, int rowIndex, int cellIndex) {
        sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        row.createCell(cellIndex);
    }

    // Get the number of rows in a sheet
    public static int getNumberOfRows(int sheetIndex) {
        sheet = workbook.getSheetAt(sheetIndex);
        return sheet.getPhysicalNumberOfRows();
    }

    // Get the number of columns in a sheet
    public static int getNumberOfColumns(int sheetIndex, int rowIndex) {
        sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            return 0;
        }
        return row.getPhysicalNumberOfCells();
    }
}

