package PageObjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Utils.HighLight;

public class EnterpriseForm {
	private WebDriver driver;
	@FindBy(id="FirstName")private static WebElement firstName;
	@FindBy(id="LastName")private static  WebElement lastname;
	@FindBy(id="Email") private static WebElement Email;
	@FindBy(id="Phone")static  WebElement phone;
	@FindBy(id="Title") static WebElement title;
	@FindBy(id="Self_reported_employees_to_buy_for__c") static WebElement Learners;
	@FindBy(id="mktoCheckbox_109803_0") static WebElement checkBox;
	@FindBy(id="Country") static WebElement country;
	@FindBy(id="State") static WebElement state;
	@FindBy(id="What_the_lead_asked_for_on_the_website__c") static WebElement needDescription;
	@FindBy(xpath="//button[@type='submit' and @class='mktoButton']")static WebElement button;
	
	
	public EnterpriseForm(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setFirstName(String fname)
	{
		HighLight.highlightElement(driver, firstName);
		firstName.clear();
		firstName.sendKeys(fname);
		HighLight.normalizeElement(driver, firstName);
	}
	
	
	public void setLastName(String lname)
	{
		HighLight.highlightElement(driver, lastname);
		lastname.clear();
		lastname.sendKeys(lname);
		HighLight.normalizeElement(driver, lastname);
	}
	
	
	public void setEmail(String email)
	{
		HighLight.highlightElement(driver, Email);
		Email.clear();
		Email.sendKeys(email);
		HighLight.normalizeElement(driver, Email);
	}
	
	
	public void setPhone(String phoneNo)
	{
		HighLight.highlightElement(driver, phone);
		phone.clear();
		phone.sendKeys(phoneNo);
		HighLight.normalizeElement(driver, phone);
	}
	
	
	public void setJobTitle(String jobTitle)
	{
		HighLight.highlightElement(driver, title);
		title.clear();
		title.sendKeys(jobTitle);
		HighLight.normalizeElement(driver, title);
	}
	
	
	public void setExpectedLearners(String expectedLearners)
	{
		HighLight.highlightElement(driver, Learners);
		Select noOfLearners=new Select(Learners);
		noOfLearners.selectByVisibleText(expectedLearners);
		HighLight.normalizeElement(driver, Learners);
	}
	
	public void setIsGovtCheckBox(String isGovt)
	{

		if(isGovt.equalsIgnoreCase("yes") && !checkBox.isSelected())
		{
			checkBox.click();
		}
		if(isGovt.equalsIgnoreCase("no") && checkBox.isSelected())
		{
			checkBox.click();
		}
	}
	
	
	
	public void setCountry(String countryName)
	{
		HighLight.highlightElement(driver, country);
		
		Select countrySelect=new Select(country);
		countrySelect.selectByVisibleText(countryName);
		HighLight.normalizeElement(driver, country);
	}
	public void setState(String stateName)
	{
		state.sendKeys(stateName);
	}
	
	
	public void setNeed(String needs,String desc)
	{
		HighLight.highlightElement(driver, needDescription);

		Select needSelect=new Select(needDescription);
		needSelect.selectByVisibleText(needs);
		HighLight.normalizeElement(driver, needDescription);
		if(needs.equalsIgnoreCase("other"))
		{
		
			WebElement description=driver.findElement(By.id("rentalField5"));
			HighLight.highlightElement(driver, description);

			description.sendKeys(desc);
		}
	}
	
	
	public void submitForm()
	{
		button.click();
	}
//	public void switchBack()
//	{
//		if(driver.getTitle().equals("Thank You DS Corp | Coursera for Business")) {
//			driver.switchTo().window(windowHandle);
//		}
//	}
	
	
	public void isValidEmail()
	{
		boolean invalidmsg=false;
		try {
			
			WebElement invalidEmail=driver.findElement(By.id("ValidMsgEmail"));
			invalidmsg=invalidEmail.isDisplayed();
			System.out.println(invalidmsg);
		}
		catch(Exception e)
		{
			System.out.println("valid email");
		}
		Assert.assertFalse(invalidmsg);
	}
	
	
}
