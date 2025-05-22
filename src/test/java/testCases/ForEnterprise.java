package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.EnterpriseForm;
import PageObjects.HomePage;
import Utils.DriverSetup;
import Utils.ExcelUtils;
import com.aventstack.extentreports.ExtentTest;

public class ForEnterprise {
    
    private static WebDriver driver;
    private static EnterpriseForm form;
    private static HomePage home;
    private static ExtentTest test;

    

    @BeforeClass
    public void setUp() {
        driver = DriverSetup.getDriver();
        driver.navigate().back();
        home = new HomePage(driver);
        form = new EnterpriseForm(driver);
    }

    @Test
    public void clickOnForEnterprise() throws InterruptedException {
        home.clickForEnterprise();
    }

    @Test(dataProvider = "FormData")
    public void fillFormAndSubmit(String fname, String lname, String email, String phoneNo, String jobTitle, String expectedLearners, String isGovt, String country, String state, String needs, String desc) throws InterruptedException {
    	form.setFirstName(fname);
    	form.setLastName(lname);
    	form.setEmail(email);
    	form.setPhone(phoneNo);
    	form.setJobTitle(jobTitle);
    	form.setExpectedLearners(expectedLearners);
    	form.setIsGovtCheckBox(isGovt);
    	form.setCountry(country);
    	form.setNeed(needs, desc);
    	form.submitForm();
    	form.isValidEmail();
       // form.fillForm(fname, lname, email, phoneNo, jobTitle, expectedLearners, isGovt, country, state, needs, desc);
    }

    @DataProvider(name = "FormData")
    public String[][] getData() throws IOException {
        ExcelUtils.loadExcelFile("src/test/resources/EnterpriseForm.xlsx");
        int noOfCols = ExcelUtils.getNumberOfColumns(0,1);
        String[][] data = new String[2][noOfCols + 1];
        for (int i = 1; i <=2; i++) {
            for (int j = 0; j <= noOfCols; j++) {
                data[i - 1][j] = ExcelUtils.getCellData(0, i, j);
                System.out.print(data[i-1][j]+"--");
            }
            System.out.println("\nhi\n");
        }
        return data;
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
