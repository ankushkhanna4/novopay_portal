package in.novopay.platform_ui.pages.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {

	public String readExcel(String filePath, String fileName, String sheetName, int rowNum, int col) throws IOException {

		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);

		Workbook Workbook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			Workbook = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			Workbook = new HSSFWorkbook(inputStream);
		}
		Sheet sheet = Workbook.getSheet(sheetName);

		Row row = sheet.getRow(rowNum-1);
		return (row.getCell(col-1).getStringCellValue());

	}
}