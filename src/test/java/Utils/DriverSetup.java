package Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import Constants.Constant;

public class DriverSetup {
	public static WebDriver driver;
	
	public static void createDriver(String browser)
	{
		browser.toLowerCase();
		switch(browser)
		{
		case "chrome":
			driver=new ChromeDriver();
			break;
		case "edge":
			driver=new EdgeDriver();
			break;
			
		}
		

        driver.get(Constant.baseUrl);
        driver.manage().window().maximize();
        //return driver;
		
	}
	public static WebDriver getDriver()
	{
		return driver;
	}

}

