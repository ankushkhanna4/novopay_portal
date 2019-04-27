package in.novopay.platform_ui.pages.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteDataToExcel {

	public void writeToParticularCell(String filePath, String fileName, String sheetName, int row, int col, String cellValue)
			throws IOException {

		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook MyWorkbook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			MyWorkbook = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			MyWorkbook = new HSSFWorkbook(inputStream);
		}
		Sheet sheet = MyWorkbook.getSheet(sheetName);
		Row r = sheet.getRow(row-1);
		if (r == null) {
			r = sheet.createRow(row-1);
		}
		Cell c = r.getCell(col-1);
		if (c == null) {
			c = r.createCell(col-1, Cell.CELL_TYPE_NUMERIC);
		}
		c.setCellValue(cellValue);

		inputStream.close();
		FileOutputStream outputStream = new FileOutputStream(file);
		MyWorkbook.write(outputStream);
		outputStream.close();
	}
}