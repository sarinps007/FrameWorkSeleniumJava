package com.payroll.baseclass;

import com.payroll.actiondriver.Action;
import com.payroll.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

    public class baseclass {
    	//Introducing Thread for parallel test execution
    	
    	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    	
	public static Properties prop;

	@BeforeSuite(groups = { "Smoke" })
	public void BeforeSuiteLog() {
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");
		//Iam writing this to show u github actions
	}
	
	public static WebDriver getDriver() {
		// Get Driver from threadLocalmap
		return driver.get();
	}
	
	// loadConfig method is to load the configuration
	@BeforeTest(groups = { "Smoke" })
	public void loadConfig() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configurations\\config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void launchApp(String browserName) {

		// String browserName = prop.getProperty("Broswer");
		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver.set( new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver.set(new InternetExplorerDriver() );
		}
		// Maximize the screen
		getDriver().manage().window().maximize();
		Action action = new Action();
		// Implicit TimeOut
		action.implicitWait(getDriver(), 10);
		// PageLoad TimeOuts
		action.pageLoadTimeOut(	getDriver(), 30);
		getDriver().get(prop.getProperty("url"));

	}
	@AfterTest(groups = { "Smoke" })
	public void End() {
		driver.get().quit();
	}

	@AfterSuite(groups = { "Smoke" })
	public void aftersuite() {
		ExtentManager.endReport();

	}

}
