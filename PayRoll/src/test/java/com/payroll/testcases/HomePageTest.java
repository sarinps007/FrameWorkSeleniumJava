package com.payroll.testcases;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.payroll.baseclass.baseclass;
import com.payroll.pageobjects.HomePage;
import com.payroll.pageobjects.LoginPage;
import com.payroll.utility.Log;

public class HomePageTest extends baseclass {
	
	/*
	 * @BeforeMethod public void setup() { launchApp(); }
	 */
	@Parameters("browser")
	  @BeforeMethod (groups = {"Smoke"})
	  public void setup(String browser) {
		  launchApp(browser); 
		  }
	@Test
	public void verifyHomePage() throws InterruptedException
	{
		LoginPage loginPg = new LoginPage();
		Log.startTestCase("VERIFY LOGO");
		loginPg.login(prop.getProperty("Username"),prop.getProperty("Password"));
		HomePage Hp = new HomePage(); 
		Thread.sleep(5000);
		boolean result=Hp.verifyHomePgLogo();
		Assert.assertTrue(result);
		Log.startTestCase("VERIFY LOGO SUCCESSFUL");
	}
	@AfterMethod
	public void tearDown() {
		driver.get().quit();
	}
	
}

