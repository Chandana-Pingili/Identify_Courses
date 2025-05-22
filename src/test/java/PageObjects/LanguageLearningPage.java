package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Utils.HighLight;

public class LanguageLearningPage {
	WebDriver driver;
	By showMoreLanguages=By.xpath("//*[@ aria-label='Show more Language options']");
	//static WebElement showMoreLanguages;
	By langauageLocator=By.xpath("//*[contains(@data-testid, 'language:')]/label");
	static List<WebElement> languagesList;
	By levelLocator=By.xpath("//*[contains(@data-testid, 'productDifficultyLevel')]/label");
	static List<WebElement> levelsList;
	
	public LanguageLearningPage(WebDriver driver) {
	this.driver=driver;
	}
	public void getCourseCountForEachLevelAndEachLanguage() throws InterruptedException
	{
		driver.findElement(showMoreLanguages).click();
		languagesList=driver.findElements(langauageLocator);
		
		levelsList=driver.findElements(levelLocator);
		System.out.println("No.of languages found = "+languagesList.size());
		String lang;
    	for(WebElement language:languagesList)
    	{
    		lang=language.getText().split("\\(")[0].trim();
    		for(WebElement level:levelsList)
    		{
    			System.out.println(lang+" "+level.getText());
    			
    		}
    		
    	}
	}
}
