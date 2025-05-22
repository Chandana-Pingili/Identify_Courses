package testCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.annotations.Parameters;

import PageObjects.HomePage;
import PageObjects.WebDevelopmentCoursePage;
import Utils.DriverSetup;

@Test
public class WebDevelopment {
    private static WebDriver driver;
    private String baseUrl = "https://www.coursera.org/";
    private static HomePage home;
    private WebDevelopmentCoursePage course;
    
    
    @BeforeTest
   @Parameters("browser")
    public void getDriver(String browser) throws IOException {
        DriverSetup.createDriver(browser);
    }
    @BeforeClass
    public void setUp() throws IOException {
        driver = DriverSetup.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        home = new HomePage(driver);
        course = new WebDevelopmentCoursePage(driver);
    }
    @Test(priority=1)
    public void enterText() throws InterruptedException {
        home.searchCourse("Web Development");
        Thread.sleep(2000);
    }
    @Test(dependsOnMethods  ={"enterText"})
    public void filterCourses() throws InterruptedException {
        course.clickEnglish();
        course.clickBeginner();
        Thread.sleep(3000);
    }
    @Test(dependsOnMethods  ={"enterText"})
    public void printCourseDetails() {
        course.printDetails();
    }

 

    @AfterClass
    public void closeDriver() {
    }
}
