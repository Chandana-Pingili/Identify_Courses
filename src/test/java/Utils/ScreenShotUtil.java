package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Base64;

public class ScreenShotUtil {

    public static String captureScreenshotAsBase64(WebDriver driver) {
        if (driver instanceof TakesScreenshot) {
            return Base64.getEncoder().encodeToString(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        }
        return null;
    }

    public static File captureScreenshotAsFile(WebDriver driver, String directory) throws IOException {
        if (driver instanceof TakesScreenshot) {
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = "screenshot_" + timestamp + ".png";
            File destinationFile = new File(directory + File.separator + fileName);
            FileUtils.copyFile(sourceFile, destinationFile);
            return destinationFile;
        }
        return null;
    }
}
