package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP\\Documents\\NinzaCRM_M7.xlsx");
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sh = wb.getSheet("Practice");
        for(int i=1;i<=sh.getLastRowNum();i++) {
        	String BRAND = sh.getRow(i).getCell(0).getStringCellValue();
        	String PRODUCT = sh.getRow(i).getCell(1).getStringCellValue();
        	System.out.println(BRAND+"-->"+PRODUCT);
        }
        wb.close();
	}

}
