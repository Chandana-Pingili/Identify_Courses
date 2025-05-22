package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.HighLight;

public class WebDevelopmentCoursePage {
	static WebDriver driver;
	@FindBy(xpath="//*[@id='cds-react-aria-:R4darconaj6kqikta:']")
	static WebElement englishCheckBox;
	@FindBy(xpath="//*[@id='cds-react-aria-:R4darcp7aj6kqikta:']")
	static WebElement beginnerCheckBox;
	
	public WebDevelopmentCoursePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickEnglish() throws InterruptedException
	{
		HighLight.highlightElement(driver, englishCheckBox);
		englishCheckBox.click();
		Thread.sleep(3000);
	}
	public void clickBeginner() throws InterruptedException
	{
		HighLight.highlightElement(driver, beginnerCheckBox);

		beginnerCheckBox.click();
		Thread.sleep(3000);
	}
	public void printDetails()
	{
		for(int i=1;i<=2;i++) {
			WebElement cont =  driver.findElement(By.xpath("//*[@id=\"searchResults\"]/div[1]/div/ul/li["+i+"]/div/div/div/div/div//a/h3"));
			HighLight.highlightElement(driver, cont);

			WebElement rating = driver.findElement(By.xpath("//*[@id=\"searchResults\"]/div[1]/div/ul/li["+i+"]//span[@class='css-6ecy9b']"));
			HighLight.highlightElement(driver, rating);

			WebElement Hours = driver.findElement(By.xpath("//*[@id=\"searchResults\"]/div[1]/div/ul/li["+i+"]//div[@class='cds-CommonCard-metadata']"));
			HighLight.highlightElement(driver, Hours);

			System.out.println("Name of the Course: "+cont.getText());
			System.out.println("Rating of the course: "+rating.getText());
			String[] s = Hours.getText().split(" . ");
			String time = s[2]+" - "+s[3];
			System.out.println("Duration of the course: "+ time);
			System.out.println();
		}
	}
	
}
