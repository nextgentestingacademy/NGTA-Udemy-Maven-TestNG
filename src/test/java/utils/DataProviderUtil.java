package utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {

	@DataProvider(name = "loginData")
	public Object[][] getLoginData(){
		return new Object[][] {
			{"john","demo"}
		};
	}
	
	@DataProvider(name = "invalidLoginData")
	public Object[][] getInvalidLoginData(){
		return new Object[][] {
			{"john1","demo2132"}
		};
	}
}
