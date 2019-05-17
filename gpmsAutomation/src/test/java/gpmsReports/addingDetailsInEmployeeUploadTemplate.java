package gpmsReports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
		
		//inputStream.close();
		FileOutputStream outputStream=new FileOutputStream(filePath+fileName);
		employeeUploadTemplate.write(outputStream);
		//outputStream.close();
		
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
		
		//inputStream.close();
		FileOutputStream outputStream=new FileOutputStream(filePath+fileName);
		employeeUploadTemplate.write(outputStream);
		//outputStream.close();
	}


	public static void addingEntitlementPerm(String filePath, String fileName, String sheetName, String  empNo , Object[] entitlementDetails) throws IOException {
		
		//Object[] entitlement1Details= {entitlement1, "", ThreadLocalRandom.current().nextInt(100, 300)};
		ArrayList<String> headers=new ArrayList<String>();
		
		File file=new File(filePath+fileName);
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook employeeUploadTemplate = new XSSFWorkbook(inputStream);
		
		Sheet required=employeeUploadTemplate.getSheet(sheetName);
		Row header=required.getRow(0);
		Row bindingsRow=required.getRow(1);
		int headerLenght=header.getLastCellNum();
		int rowCountinRequiredSheet=required.getLastRowNum();
		int rowNoOfEmp=0;
		ArrayList<Integer> coloumnsNos=new ArrayList<Integer>();
		for (int x=3; x<headerLenght; x++) {
			Cell cell=header.getCell(x);
			String cellValue=cell.getStringCellValue();
			headers.add(cellValue);
			coloumnsNos.add(x);
			if(headers.size()>1 && headers.get(headers.size()-1)==headers.get(headers.size()-2)) {
				headers.remove(headers.size()-1);
				coloumnsNos.remove(coloumnsNos.size()-1);
			}
		}
		
		
		for(int x=0; x<=rowCountinRequiredSheet; x++) {
			Row row=required.getRow(x);
			Cell cell=row.getCell(0);
			if(cell!=null && cell.getStringCellValue().contentEquals(empNo)) { 	rowNoOfEmp=x; break;}
   	 	}
		
		if(rowNoOfEmp==0) {	System.out.println("Failed "+empNo+" not found in sheet to add ETypes");	}
		Row empRow=required.getRow(rowNoOfEmp);
		Cell empNo1=empRow.getCell(0);
		Boolean isEntitlementEntered=false;
		int isEntitlementDuplicate=0;
		
		for(int x=0; x<headers.size(); x++) {
			if(headers.get(x).substring(0, headers.get(x).length()-2).contentEquals(String.valueOf(entitlementDetails[0]))) {
				if(empRow.getCell(coloumnsNos.get(x))==null || empRow.getCell(coloumnsNos.get(x)).getCellType()==CellType.BLANK) {
					Cell x1=empRow.createCell(coloumnsNos.get(x));
	   		   	 	x1.setCellValue(String.valueOf(entitlementDetails[0]));
	   		   	 	
	   		   	 	Cell x2=empRow.createCell(coloumnsNos.get(x)+1);
	   		   	 	x2.setCellValue(String.valueOf(entitlementDetails[1]));
	   		   	 	
	   		   	 	Cell x3=empRow.createCell(coloumnsNos.get(x)+2);
	   		   	 	x3.setCellValue((Integer) entitlementDetails[2]);
	   		   	 	
	   		   	 	isEntitlementEntered=true;
	   		   	 	break;
				}else {
					isEntitlementDuplicate++;
					
				}
			}
		}
		
		
		if(isEntitlementEntered==false) {
				
				Cell z1=header.createCell(headerLenght);
		   	 	z1.setCellValue(String.valueOf(entitlementDetails[0])+" "+isEntitlementDuplicate);
		   	 	
		   	 	Cell z2=header.createCell(headerLenght+1);
		   	 	z2.setCellValue(String.valueOf(entitlementDetails[0])+" "+isEntitlementDuplicate);
		   	 	
		   	 	Cell z3=header.createCell(headerLenght+2);
		   	 	z3.setCellValue(String.valueOf(entitlementDetails[0])+" "+isEntitlementDuplicate);
		   	 	
		   	 	Cell y1=bindingsRow.createCell(headerLenght);
		   	 	y1.setCellValue("ENP!Element Type");
		   	 	
		   	 	Cell y2=bindingsRow.createCell(headerLenght+1);
		   	 	y2.setCellValue("ENP!Effective To");
		   	 	
		   	 	Cell y3=bindingsRow.createCell(headerLenght+2);
		   	 	y3.setCellValue("ENP!Amount");
		   	 	
		   	 	Cell x1=empRow.createCell(headerLenght);
		   	 	x1.setCellValue(String.valueOf(entitlementDetails[0]));
		   	 	
		   	 	Cell x2=empRow.createCell(headerLenght+1);
		   	 	x2.setCellValue(String.valueOf(entitlementDetails[1]));
		   	 	
		   	 	Cell x3=empRow.createCell(headerLenght+2);
		   	 	x3.setCellValue((Integer) entitlementDetails[2]);
		}
		
		//inputStream.close();
		FileOutputStream outputStream=new FileOutputStream(filePath+fileName);
		employeeUploadTemplate.write(outputStream);
		//outputStream.close();
	}
	
	
	public static void addingEntitlementTemp(String filePath, String fileName, String sheetName, String  empNo , Object[] entitlementDetails) throws IOException {
		
		//Object[] entitlement1Details= {entitlement1, ThreadLocalRandom.current().nextInt(100, 300)};
				ArrayList<String> headers=new ArrayList<String>();
				
				File file=new File(filePath+fileName);
				FileInputStream inputStream = new FileInputStream(file);
				
				XSSFWorkbook employeeUploadTemplate = new XSSFWorkbook(inputStream);
				
				Sheet required=employeeUploadTemplate.getSheet(sheetName);
				Row header=required.getRow(0);
				Row bindingsRow=required.getRow(1);
				int headerLenght=header.getLastCellNum();
				int rowCountinRequiredSheet=required.getLastRowNum();
				int rowNoOfEmp=0;
				ArrayList<Integer> coloumnsNos=new ArrayList<Integer>();
				for (int x=3; x<headerLenght; x++) {
					Cell cell=header.getCell(x);
					String cellValue=cell.getStringCellValue();
					headers.add(cellValue);
					coloumnsNos.add(x);
					if(headers.size()>1 && headers.get(headers.size()-1)==headers.get(headers.size()-2)) {
						headers.remove(headers.size()-1);
						coloumnsNos.remove(coloumnsNos.size()-1);
					}
				}
				
				
				for(int x=0; x<=rowCountinRequiredSheet; x++) {
					Row row=required.getRow(x);
					Cell cell=row.getCell(0);
					if(cell!=null && cell.getStringCellValue().contentEquals(empNo)) { 	rowNoOfEmp=x; break;}
		   	 	}
				
				if(rowNoOfEmp==0) {	System.out.println("Failed "+empNo+" not found in sheet to add ETypes");	}
				Row empRow=required.getRow(rowNoOfEmp);
				Cell empNo1=empRow.getCell(0);
				Boolean isEntitlementEntered=false;
				int isEntitlementDuplicate=0;
				
				for(int x=0; x<headers.size(); x++) {
					if(headers.get(x).substring(0, headers.get(x).length()-2).contentEquals(String.valueOf(entitlementDetails[0]))) {
						if(empRow.getCell(coloumnsNos.get(x))==null || empRow.getCell(coloumnsNos.get(x)).getCellType()==CellType.BLANK) {
							Cell x1=empRow.createCell(coloumnsNos.get(x));
			   		   	 	x1.setCellValue(String.valueOf(entitlementDetails[0]));
			   		   	 	
			   		   	 	Cell x2=empRow.createCell(coloumnsNos.get(x)+1);
			   		   	 	x2.setCellValue((Integer) entitlementDetails[1]);
			   		   	 	
			   		   	 	isEntitlementEntered=true;
			   		   	 	break;
						}else {
							isEntitlementDuplicate++;
							
						}
					}
				}
				
				
				if(isEntitlementEntered==false) {
						
						Cell z1=header.createCell(headerLenght);
				   	 	z1.setCellValue(String.valueOf(entitlementDetails[0])+" "+isEntitlementDuplicate);
				   	 	
				   	 	Cell z2=header.createCell(headerLenght+1);
				   	 	z2.setCellValue(String.valueOf(entitlementDetails[0])+" "+isEntitlementDuplicate);
				   	 	
				   	 	Cell y1=bindingsRow.createCell(headerLenght);
				   	 	y1.setCellValue("ENT!Element Type");
				   	 	
				   	 	Cell y2=bindingsRow.createCell(headerLenght+1);
				   	 	y2.setCellValue("ENT!Amount");
				   	 	
				   	 	Cell x1=empRow.createCell(headerLenght);
				   	 	x1.setCellValue(String.valueOf(entitlementDetails[0]));
				   	 	
				   	 	Cell x2=empRow.createCell(headerLenght+1);
				   	 	x2.setCellValue((Integer) entitlementDetails[1]);
				   	 	
				}
				
				//inputStream.close();
				FileOutputStream outputStream=new FileOutputStream(filePath+fileName);
				employeeUploadTemplate.write(outputStream);
				//outputStream.close();
		
	}
	

	
	public static void addingEntitlementUnit(String filePath, String fileName, String sheetName, String  empNo , Object[] entitlementDetails) throws IOException {
		
		//Object[] entitlement1Details= {entitlement1, "", ThreadLocalRandom.current().nextInt(100, 300)};
		ArrayList<String> headers=new ArrayList<String>();
		
		File file=new File(filePath+fileName);
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook employeeUploadTemplate = new XSSFWorkbook(inputStream);
		
		Sheet required=employeeUploadTemplate.getSheet(sheetName);
		Row header=required.getRow(0);
		Row bindingsRow=required.getRow(1);
		int headerLenght=header.getLastCellNum();
		int rowCountinRequiredSheet=required.getLastRowNum();
		int rowNoOfEmp=0;
		ArrayList<Integer> coloumnsNos=new ArrayList<Integer>();
		
		for (int x=3; x<headerLenght; x++) {
			Cell cell=header.getCell(x);
			String cellValue=cell.getStringCellValue();
			headers.add(cellValue);
			coloumnsNos.add(x);
			if(headers.size()>1 && headers.get(headers.size()-1)==headers.get(headers.size()-2)) {
				headers.remove(headers.size()-1);
				coloumnsNos.remove(coloumnsNos.size()-1);
			}
		}
		
		
		for(int x=0; x<=rowCountinRequiredSheet; x++) {
			Row row=required.getRow(x);
			Cell cell=row.getCell(0);
			if(cell!=null && cell.getStringCellValue().contentEquals(empNo)) { 	rowNoOfEmp=x; break;}
   	 	}
		
		if(rowNoOfEmp==0) {	
			System.out.println("Failed "+empNo+" not found in sheet to add ETypes");	
			Assert.fail("Failed "+empNo+" not found in sheet to add ETypes");}
		Row empRow=required.getRow(rowNoOfEmp);
		Cell empNo1=empRow.getCell(0);
		Boolean isEntitlementEntered=false;
		int isEntitlementDuplicate=0;
		
		for(int x=0; x<headers.size(); x++) {
			if(headers.get(x).substring(0, headers.get(x).length()-2).contentEquals(String.valueOf(entitlementDetails[0]))) {
				if(empRow.getCell(coloumnsNos.get(x))==null || empRow.getCell(coloumnsNos.get(x)).getCellType()==CellType.BLANK) {
					Cell x1=empRow.createCell(coloumnsNos.get(x));
	   		   	 	x1.setCellValue(String.valueOf(entitlementDetails[0]));
	   		   	 	
	   		   	 	Cell x2=empRow.createCell(coloumnsNos.get(x)+1);
	   		   	 	x2.setCellValue(String.valueOf(entitlementDetails[1]));
	   		   	 	
	   		   	 	Cell x3=empRow.createCell(coloumnsNos.get(x)+2);
	   		   	 	x3.setCellValue((Integer) entitlementDetails[2]);
	   		   	 	
	   		   	 	isEntitlementEntered=true;
	   		   	 	break;
				}else {
					isEntitlementDuplicate++;
					
				}
			}
		}
		
		
		if(isEntitlementEntered==false) {
				
				Cell z1=header.createCell(headerLenght);
		   	 	z1.setCellValue(String.valueOf(entitlementDetails[0])+" "+isEntitlementDuplicate);
		   	 	
		   	 	Cell z2=header.createCell(headerLenght+1);
		   	 	z2.setCellValue(String.valueOf(entitlementDetails[0])+" "+isEntitlementDuplicate);
		   	 	
		   	 	Cell z3=header.createCell(headerLenght+2);
		   	 	z3.setCellValue(String.valueOf(entitlementDetails[0])+" "+isEntitlementDuplicate);
		   	 	
		   	 	Cell y1=bindingsRow.createCell(headerLenght);
		   	 	y1.setCellValue("ENU!Element Type");
		   	 	
		   	 	Cell y2=bindingsRow.createCell(headerLenght+1);
		   	 	y2.setCellValue("ENU!#Units");
		   	 	
		   	 	Cell y3=bindingsRow.createCell(headerLenght+2);
		   	 	y3.setCellValue("ENU!Rate Override");
		   	 	
		   	 	Cell x1=empRow.createCell(headerLenght);
		   	 	x1.setCellValue(String.valueOf(entitlementDetails[0]));
		   	 	
		   	 	Cell x2=empRow.createCell(headerLenght+1);
		   	 	x2.setCellValue(String.valueOf(entitlementDetails[1]));
		   	 	
		   	 	Cell x3=empRow.createCell(headerLenght+2);
		   	 	x3.setCellValue((Integer) entitlementDetails[2]);
		}
		
		//inputStream.close();
		FileOutputStream outputStream=new FileOutputStream(filePath+fileName);
		employeeUploadTemplate.write(outputStream);
		//outputStream.close();
	}
	
	
	public static void addingETypes(String filePath, String fileName, String sheetName, String  empNo , Object[] elementTypeDetails) throws IOException {
		
		//Object[] entitlement1Details= {entitlement1, "", ThreadLocalRandom.current().nextInt(100, 300)};
		ArrayList<String> headers=new ArrayList<String>();
		ArrayList<String> bindings=new ArrayList<String>();
		if(sheetName=="Perm Entitlements") 		{	bindings.add("ENP!Element Type");	bindings.add("ENP!Effective To");	bindings.add("ENP!Amount");			}
		if(sheetName=="Temp Entitlements") 		{	bindings.add("ENT!Element Type");										bindings.add("ENT!Amount");			}
		if(sheetName=="Unit Entitlements") 		{	bindings.add("ENU!Element Type");	bindings.add("ENU!#Units");			bindings.add("ENU!Rate Override");	}
		if(sheetName=="Perm Notional Amounts") 	{	bindings.add("NAP!Element Type");	bindings.add("NAP!End Period");		bindings.add("NAP!Amount");			}
		if(sheetName=="Temp Notional Amounts") 	{	bindings.add("NAT!Element Type");										bindings.add("NAT!Amount");			}
		if(sheetName=="Perm Paydeds") 			{	bindings.add("PDP!Element Type");	bindings.add("PDP!Effective To");	bindings.add("PDP!Amount");			}
		if(sheetName=="Temp Paydeds") 			{	bindings.add("PDT!Element Type");										bindings.add("PDT!Amount");			}
		if(sheetName=="Unit Pays") 				{	bindings.add("UP!Element Type");	bindings.add("UP!#Units");			bindings.add("UP!Rate Override");	}
		
		int elementTypeDetailsLenght=elementTypeDetails.length;
		
		File file=new File(filePath+fileName);
		FileInputStream inputStream = new FileInputStream(file);
		
		XSSFWorkbook employeeUploadTemplate = new XSSFWorkbook(inputStream);
		
		Sheet requiredSheet=employeeUploadTemplate.getSheet(sheetName);
		Row header=requiredSheet.getRow(0);
		Row bindingsRow=requiredSheet.getRow(1);
		int headerLenght=header.getLastCellNum();
		int rowCountinRequiredSheet=requiredSheet.getLastRowNum();
		int rowNoOfEmp=0;
		ArrayList<Integer> coloumnsNos=new ArrayList<Integer>();
		for (int x=3; x<headerLenght; x++) {
			Cell cell=header.getCell(x);
			String cellValue=cell.getStringCellValue();
			headers.add(cellValue);
			coloumnsNos.add(x);
			if(headers.size()>1 && headers.get(headers.size()-1)==headers.get(headers.size()-2)) {
				headers.remove(headers.size()-1);
				coloumnsNos.remove(coloumnsNos.size()-1);
			}
		}
		
		
		for(int x=0; x<=rowCountinRequiredSheet; x++) {
			Row row=requiredSheet.getRow(x);
			Cell cell=row.getCell(0);
			if(cell!=null && cell.getStringCellValue().contentEquals(empNo)) { 	rowNoOfEmp=x; break;}
   	 	}
		
		if(rowNoOfEmp==0) {	System.out.println("Failed "+empNo+" not found in sheet to add ETypes");	
				Assert.fail("Failed "+empNo+" not found in sheet to add ETypes");
				}
		
		Row empRow=requiredSheet.getRow(rowNoOfEmp);
		Cell empNo1=empRow.getCell(0);
		Boolean isETypeEntered=false;
		int isETypeDuplicate=0;
		
		for(int x=0; x<headers.size(); x++) {
			if(headers.get(x).substring(0, headers.get(x).length()-2).contentEquals(String.valueOf(elementTypeDetails[0]))) {
				if(empRow.getCell(coloumnsNos.get(x))==null || empRow.getCell(coloumnsNos.get(x)).getCellType()==CellType.BLANK) {
					if(elementTypeDetailsLenght<=2) {
						Cell x1=empRow.createCell(coloumnsNos.get(x));
		   		   	 	x1.setCellValue(String.valueOf(elementTypeDetails[0]));
		   		   	 	
			   		   	Cell x2=empRow.createCell(coloumnsNos.get(x)+1);
		   		   	 	x2.setCellValue((Integer) elementTypeDetails[1]);
					}else {
						Cell x1=empRow.createCell(coloumnsNos.get(x));
		   		   	 	x1.setCellValue(String.valueOf(elementTypeDetails[0]));
		   		   	 	
		   		   	 	Cell x2=empRow.createCell(coloumnsNos.get(x)+1);
		   		   	 	x2.setCellValue(String.valueOf(elementTypeDetails[1]));
		   		   	 	
		   		   	 	Cell x3=empRow.createCell(coloumnsNos.get(x)+2);
			   		   	x3.setCellValue((Integer) elementTypeDetails[2]);
					}
					isETypeEntered=true;
	   		   	 	break;
				}else {
					isETypeDuplicate++;
					
				}
			}
		}
		
		
		if(isETypeEntered==false) {
			
			if(elementTypeDetailsLenght<=2) {
				Cell z1=header.createCell(headerLenght);
		   	 	z1.setCellValue(String.valueOf(elementTypeDetails[0])+" "+isETypeDuplicate);
		   	 	
		   	 	Cell z2=header.createCell(headerLenght+1);
		   	 	z2.setCellValue(String.valueOf(elementTypeDetails[0])+" "+isETypeDuplicate);
		   	 	
		   	 	Cell y1=bindingsRow.createCell(headerLenght);
		   	 	y1.setCellValue(bindings.get(0));
		   	 	
		   	 	Cell y2=bindingsRow.createCell(headerLenght+1);
		   	 	y2.setCellValue(bindings.get(1));
		   	 	
		   	 	Cell x1=empRow.createCell(headerLenght);
		   	 	x1.setCellValue(String.valueOf(elementTypeDetails[0]));
		   	 	
		   	 	Cell x2=empRow.createCell(headerLenght+1);
		   	 	x2.setCellValue((Integer) elementTypeDetails[1]);
		   	 	
		   	 	
			}else {
				Cell z1=header.createCell(headerLenght);
		   	 	z1.setCellValue(String.valueOf(elementTypeDetails[0])+" "+isETypeDuplicate);
		   	 	
		   	 	Cell z2=header.createCell(headerLenght+1);
		   	 	z2.setCellValue(String.valueOf(elementTypeDetails[0])+" "+isETypeDuplicate);
		   	 	
		   	 	Cell z3=header.createCell(headerLenght+2);
		   	 	z3.setCellValue(String.valueOf(elementTypeDetails[0])+" "+isETypeDuplicate);
		   	 	
		   	 	Cell y1=bindingsRow.createCell(headerLenght);
		   	 	y1.setCellValue(bindings.get(0));
		   	 	
		   	 	Cell y2=bindingsRow.createCell(headerLenght+1);
		   	 	y2.setCellValue(bindings.get(1));
		   	 	
		   	 	Cell y3=bindingsRow.createCell(headerLenght+2);
		   	 	y3.setCellValue(bindings.get(2));
		   	 	
		   	 	Cell x1=empRow.createCell(headerLenght);
		   	 	x1.setCellValue(String.valueOf(elementTypeDetails[0]));
		   	 	
		   	 	Cell x2=empRow.createCell(headerLenght+1);
		   	 	x2.setCellValue(String.valueOf(elementTypeDetails[1]));
		   	 		
		   	 	Cell x3=empRow.createCell(headerLenght+2);
		   	 	x3.setCellValue((Integer) elementTypeDetails[2]);
			}
		}
		
		//inputStream.close();
		FileOutputStream outputStream=new FileOutputStream(filePath+fileName);
		employeeUploadTemplate.write(outputStream);
		//outputStream.close();
	}
	
	
}
