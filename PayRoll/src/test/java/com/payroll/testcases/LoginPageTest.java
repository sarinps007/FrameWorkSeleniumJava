package com.payroll.testcases;
import static org.testng.Assert.assertEquals;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.payroll.actiondriver.Action;
import com.payroll.baseclass.baseclass;
import com.payroll.pageobjects.LoginPage;
import com.payroll.utility.Log;
public class LoginPageTest extends baseclass{
	Action action = new Action();
	
	  @Parameters("browser")
	  @BeforeMethod (groups = {"Smoke"})
	  public void setup(String browser) {
		  launchApp(browser); 
		  }
	 	
	@Test(groups = {"Smoke"})
	public void LoginTest() throws InterruptedException
	{
		Log.startTestCase("Login to Payroll");
		LoginPage loginPg = new LoginPage();
		Log.info("Going to enter username and password");
		loginPg.login(prop.getProperty("Username"),prop.getProperty("Password"));
		String expUrl="https://www.qabible.in/payrollapp/site/index";
		//should remove
		Thread.sleep(9000);
		String actURL = driver.get().getCurrentUrl();
		Assert.assertEquals(actURL, expUrl);
		Log.endTestCase("Login Successfull");
		
	}
	
	@Test(dataProvider="getlogin")
	public void loginInvalid(String Username,String Password)
	{
		Log.startTestCase("Login to Payroll");
		LoginPage loginPg = new LoginPage();
		Log.info("Going to enter username and password");
		loginPg.login(Username,Password);
		String expmsg="Incorrect username or password.";
		//should remove
		String actmsg = loginPg.errormsg();
		Assert.assertEquals(actmsg,expmsg);
		Log.endTestCase("Login not Successfull");
	}
	
	@AfterMethod(groups = {"Smoke"})
	public void tearDown() {
		driver.get().quit();
	}
	
	@DataProvider
	public Object[][] getlogin()
		{
		Object[][] data = new Object[2][2];
		    data[0][0]="carolwrong";
		    data[0][1]="1q2w3e4r";
		    data[1][0]="carol";
		    data[1][1]="1q2w3e4rwrong";
		    return data;
		    
		} 	
	
     
}
