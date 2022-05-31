package com.payroll.pageobjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.payroll.actiondriver.Action;
import com.payroll.baseclass.baseclass;
//POM using PageObject Factory
public class LoginPage extends baseclass  {
	Action action = new Action();
	
	@FindBy(xpath="//input[@id='loginform-username']")
	WebElement Username;
	
	@FindBy(xpath="//input[@id='loginform-password']")
	WebElement password;
	
	@FindBy(xpath="//button[@name='login-button']")
	WebElement Submit;
	
	@FindBy(xpath="//p[normalize-space()='Incorrect username or password.']")
	WebElement Errormsg;
	
	
	//constructor
	public LoginPage()
	{
		PageFactory.initElements(driver.get(),this);
	}
	
	public HomePage login(String Uname ,String Pwd)
	{
		action.type(Username, Uname);
		action.type(password, Pwd);
		action.click(driver.get(), Submit);
		return new HomePage();
	}
	
	public String errormsg() {
		String msg= action.takeText(driver.get(),Errormsg);
		return msg;
	}
	

}
