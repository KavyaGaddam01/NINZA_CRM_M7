package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		//Create java represenation object of physical file
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP\\Documents\\NinzaCRM_M30.xlsx");

		//Open Excel in read mode
		Workbook wb = WorkbookFactory.create(fis);
		
		//get control of sheet
		Sheet sh = wb.getSheet("Campaign");
		
		//get control of row
		Row r = sh.getRow(1);
		
		//get control of cell
		Cell c = r.getCell(2);
		
		//read the data
		String campaignName = c.getStringCellValue();
		System.out.println(campaignName);
		
		//Close the workbook
		wb.close();
	}

}
