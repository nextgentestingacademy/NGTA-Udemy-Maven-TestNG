package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryLogic implements IRetryAnalyzer {
	int count = 0;
	int maxRetryCount = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxRetryCount) {
			count++;
			return true;
		}
		return false;
	}
}
