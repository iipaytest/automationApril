package reusableMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelReadMethods {
	
	public static void read() throws IOException{
		
		File file =    new File("C://Users//sribur19//Desktop//Automation Test Cases.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook excelFile = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheetName=excelFile.getSheet("GPMS Reggression");
		int rowCount=sheetName.getLastRowNum()-sheetName.getFirstRowNum();
		System.out.println(rowCount);
		for(int i=3;i<=6; i++) {
			Row row=sheetName.getRow(i);
			int rowLenght=row.getLastCellNum();
			for(int j=0; j<=rowLenght; j++) {
				System.out.println(row.getCell(j).getStringCellValue()+"|| ");
			}
			
		}System.out.println("--");
		
		
	}
	
	
	
}
