package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class IfElse {

	String s=null;
	@Test
	public void WorkingWithHardAssert() {
			
		System.out.println("Start");
		Assert.assertNull(s);  //Hard Assert
		System.out.println("End");
	}
	
	@Test
	public void WorkingWithSoftAssert() {
		System.out.println("Start");
		SoftAssert soft=new SoftAssert();    //Soft Assert
		soft.assertNotNull(s);
		System.out.println("End");
		
	    soft.assertAll(); //must be last line of code	
	}
}


