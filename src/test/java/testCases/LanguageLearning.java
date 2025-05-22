package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LanguageLearningPage;
import Utils.DriverSetup;

public class LanguageLearning {
	WebDriver driver;
	HomePage home;
	LanguageLearningPage languageLearning;
	
	@BeforeClass
    public void setUp() {
        driver = DriverSetup.getDriver();
        home = new HomePage(driver);
        languageLearning=new LanguageLearningPage(driver);
    }
	
	@Test
	public void SearchLanguageLearning() throws InterruptedException
	{ 
		home.searchCourse("Language Learning");
		Thread.sleep(2000);
	}
	@Test(dependsOnMethods = {"SearchLanguageLearning"})
	public void printNoOfCourses() throws InterruptedException
	{
		languageLearning.getCourseCountForEachLevelAndEachLanguage();
	}

}
