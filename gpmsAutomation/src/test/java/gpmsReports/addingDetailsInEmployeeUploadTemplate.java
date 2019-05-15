package gpmsReports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

public class addingDetailsInEmployeeUploadTemplate {

	public static ArrayList<ArrayList<String>> addingEmployeesDuplicateInMasterData(String filePath, String fileName, int noOfEmployeesToAdd) throws IOException {
		
		String requiredSheetName="Master Data";
		Sheet requiredSheet=null;
		String[] sheetName=null;
		List<Sheet> sheets=new ArrayList<Sheet>();
		//String[] newEmpNos=null;
		ArrayList<ArrayList<String>> newEmpDetails=new ArrayList<ArrayList<String>>();
		ArrayList<String> newEmpNos=new ArrayList<String>();
		ArrayList<String> newEmpSurnames=new ArrayList<String>();
		ArrayList<String> newEmpForenames=new ArrayList<String>();
		
		File file=new File(filePath+fileName);
		FileInputStream inputStream = new FileInputStream(file);
		
		//read employee upload template and check for required sheet existence
		XSSFWorkbook employeeUploadTemplate = new XSSFWorkbook(inputStream);
		int count=employeeUploadTemplate.getNumberOfSheets();
		sheetName=new String[count];
		for(int x=0; x<count; x++) {	
			sheetName[x]=employeeUploadTemplate.getSheetAt(x).getSheetName();
			sheets.add(employeeUploadTemplate.getSheetAt(x));
			if(sheetName[x].contains(requiredSheetName)) {
				requiredSheet=employeeUploadTemplate.getSheetAt(x);
				break;
			}
		}if(requiredSheet==null) {
			System.out.println("Failed: "+requiredSheetName+" Sheet not found in "+fileName);
			Assert.fail("Failed: "+requiredSheetName+" Sheet not found in "+fileName);
		}
		
		//get the data from last employee in that master data file
		int rowCount=requiredSheet.getLastRowNum();
		for(int x=0; x<rowCount; x++) {
			Row row=requiredSheet.getRow(x);
			Cell cell=row.getCell(0);
			if(cell==null || cell.getCellType()==CellType.BLANK) { 	rowCount=x+1; break;}
		}
		Row lastEmployeerow=requiredSheet.getRow(rowCount);
		
		//copies same last employee data and creates 3 new employees with same data
		for(int x=1; x<=noOfEmployeesToAdd; x++) {
			Row newEmployeeRow=requiredSheet.createRow(rowCount+x);
			for(int y=0; y<lastEmployeerow.getLastCellNum(); y++) {
				
				if(y==0) {
					Cell newCell=newEmployeeRow.createCell(y);
					Cell oldCell=lastEmployeerow.getCell(y);
					if(oldCell==null) {	newCell=null; break;}
					CellStyle newCellStyle=employeeUploadTemplate.createCellStyle();
					newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
					CellType newCell1=oldCell.getCellType();
					switch (newCell1) {
		            case STRING:
		                newCell.setCellValue(oldCell.getStringCellValue()+"New"+x);
		                newEmpNos.add(oldCell.getStringCellValue()+"New"+x);
		                break;
					}
				}else if(y==2||y==3){
					Cell newCell=newEmployeeRow.createCell(y);
					Cell oldCell=lastEmployeerow.getCell(y);
					if(oldCell==null) {	newCell=null; break;}
					CellStyle newCellStyle=employeeUploadTemplate.createCellStyle();
					newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
					CellType newCell1=oldCell.getCellType();
					newCell.setCellValue(oldCell.getRichStringCellValue());
					if(y==2) {	newEmpSurnames.add(oldCell.getStringCellValue());	}
					if(y==3) {	newEmpForenames.add(oldCell.getStringCellValue());	}
				}
				else {
					
					Cell newCell=newEmployeeRow.createCell(y);
					Cell oldCell=lastEmployeerow.getCell(y);
					
					if(oldCell==null) {	newCell=null; break;}
					CellStyle newCellStyle=employeeUploadTemplate.createCellStyle();
					newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
					CellType newCell1=oldCell.getCellType();
					
					//newCell.setCellType(oldCell.getCellType());
					 switch (newCell1) {
			            case BLANK:
			                newCell.setCellValue(oldCell.getStringCellValue());
			                break;
			            case BOOLEAN:
			                newCell.setCellValue(oldCell.getBooleanCellValue());
			                break;
			            case ERROR:
			                newCell.setCellErrorValue(oldCell.getErrorCellValue());
			                break;
			            case FORMULA:
			                newCell.setCellFormula(oldCell.getCellFormula());
			                break;
			            case NUMERIC:
			                newCell.setCellValue(oldCell.getNumericCellValue());
			                break;
			            case STRING:
			                newCell.setCellValue(oldCell.getRichStringCellValue());
			                break;
			        }
				}
				
				
			}
		}
		
		inputStream.close();
		FileOutputStream outputStream=new FileOutputStream(filePath+fileName);
		employeeUploadTemplate.write(outputStream);
		outputStream.close();
		
		newEmpDetails.add(newEmpNos);
		newEmpDetails.add(newEmpSurnames);
		newEmpDetails.add(newEmpForenames);
		
		return newEmpDetails;
		
	}


	public static void addingEmployeesInOtherSheets(String filePath, String fileName, String sheetName, ArrayList<ArrayList<String>> newEmpDetails, int noOfEmployeesToAdd) throws IOException {
		ArrayList<String> empNos=newEmpDetails.get(0);
		ArrayList<String> empSur=newEmpDetails.get(1);
		ArrayList<String> empFore=newEmpDetails.get(2);
	
		File file=new File(filePath+fileName);
		FileInputStream inputStream = new FileInputStream(file);
		
		//read employee upload template and read required sheet
		XSSFWorkbook employeeUploadTemplate = new XSSFWorkbook(inputStream);
		Sheet permEntitlement=employeeUploadTemplate.getSheet(sheetName);
		
		for(int x=0; x<noOfEmployeesToAdd; x++) {
			int rowCount=permEntitlement.getLastRowNum();
			Row newEmployeeRow=permEntitlement.createRow(rowCount+1);
			
			Cell newCellEmpNos=newEmployeeRow.createCell(0);
			newCellEmpNos.setCellValue(empNos.get(x));
			
			Cell newCellEmpSur=newEmployeeRow.createCell(1);
			newCellEmpSur.setCellValue(empSur.get(x));
			
			Cell newCellEmpFore=newEmployeeRow.createCell(2);
			newCellEmpFore.setCellValue(empFore.get(x));
		}
		
		inputStream.close();
		FileOutputStream outputStream=new FileOutputStream(filePath+fileName);
		employeeUploadTemplate.write(outputStream);
		outputStream.close();
	}

}
