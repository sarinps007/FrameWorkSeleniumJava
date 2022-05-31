package com.payroll.pageobjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.payroll.actiondriver.Action;
import com.payroll.baseclass.baseclass;

public class HomePage extends baseclass {
	
	@FindBy(xpath="//a[normalize-space()='Dashboard']")
	WebElement Dashboard;
	
	@FindBy(xpath="//a[normalize-space()='Company']")
	WebElement Company;
	
	@FindBy(xpath="//a[normalize-space()='Clients']")
	WebElement client;
	
	@FindBy(xpath="//a[normalize-space()='Workers']")
	WebElement Workersclick;
	
	@FindBy(xpath="//a[normalize-space()='Deduction']")
	WebElement Deduction;
	
	@FindBy(xpath="//img[@alt='logo']")
	WebElement Logo;
	
	//constructor
	public HomePage()
	{
		PageFactory.initElements(driver.get(),this);
	}
	
	public boolean verifyHomePgLogo()
	{
		Action action = new Action();
		return action.isDisplayed(driver.get(), Logo);
	}
	
	public Clients ClientTab(String Uname ,String Pwd)
	{
		Action action = new Action();
		action.click(driver.get(), client);
		return new Clients();
	}
	
	public Workers WorkerTb()
	{
		Action action = new Action();
		action.click(driver.get(), Workersclick);
		return new Workers();
	}
	
	
}
