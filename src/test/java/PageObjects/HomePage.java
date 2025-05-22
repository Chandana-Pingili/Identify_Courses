package PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Utils.HighLight;

public class HomePage {
	
	private static WebDriver driver;
	@FindBy(id="search-autocomplete-input")
	static WebElement searchBox;
	
	@FindBy(linkText="For Enterprise") 
	static WebElement ForEnterpriseLink;
	
	public HomePage(WebDriver driver) {
		HomePage.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//js.executeScript("arguments[0].setAttribute='';",searchBox);
	public void searchCourse(String courseName) throws InterruptedException
	{
		HighLight.highlightElement(driver, searchBox);
		Actions actions=new Actions(driver);
		actions.click(searchBox)
        .keyDown(Keys.CONTROL)
        .sendKeys("a") // Send "a" while Control is pressed
        .keyUp(Keys.CONTROL)
        .sendKeys(Keys.BACK_SPACE)
        .perform();
		searchBox.sendKeys(courseName);
		searchBox.sendKeys(Keys.ENTER);
		String Title="Top "+courseName+" Courses - Learn "+courseName +" Online";
		Assert.assertEquals(driver.getTitle(), Title);
		HighLight.normalizeElement(driver, searchBox);
	}
	
	public void clickForEnterprise()
	{
		HighLight.highlightElement(driver, ForEnterpriseLink);
		
		ForEnterpriseLink.click();
		String expectedTitle="Custom Employee Development Programs | Coursera for Business";
		Assert.assertEquals(driver.getTitle(),expectedTitle);
		HighLight.normalizeElement(driver, ForEnterpriseLink);
	}
}
