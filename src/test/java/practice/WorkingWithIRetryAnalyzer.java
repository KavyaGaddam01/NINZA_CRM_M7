package practice;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class WorkingWithIRetryAnalyzer {   //practice

	@Test(retryAnalyzer = genericutility.IRetryAnalyzerImplementation.class)
	public void test() {
		Assert.assertEquals("hdfc", "hfdc");
	}
	
}
