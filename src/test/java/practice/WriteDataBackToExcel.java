package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class WriteDataBackToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
	
		//Create java representation object of physical file
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP\\Documents\\NinzaCRM_M7.xlsx");

		//Open Excel in read mode
		Workbook wb = WorkbookFactory.create(fis);
		
		//get the control of sheet
		Sheet sh = wb.getSheet("Campaign");
		
		//get the control of the row
		Row r = sh.getRow(4);
		
		//create a cell
		Cell c = r.createCell(5);
		
		//Set the cell type and pass the value
		c.setCellType(CellType.STRING);
		c.setCellValue("KAVYA");
		
		//Open the excel in write mode
		FileOutputStream fos=new FileOutputStream("C:\\Users\\QSP\\Documents\\NinzaCRM_M7.xlsx");
		
		//save the data
		wb.write(fos);
		
		//close the excel sheet
		wb.close();
	}

}
