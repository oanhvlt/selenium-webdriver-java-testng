package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

/*
Exe TCs
1. Setup
2. Action/Exe
3. Clean
 */
public class Topic_00_template {
    //1. Setup
    WebDriver driver;


    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //kich thước browser
        /**
         * 1280x768
         * 1366x768
         * 1920x1080
         * 2540x1440
         * //
         * HD
         * FHD
         * UHD
         */

        //driver.manage().window().setSize(new Dimension(1920, 768));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }

    //2. Action/Exe
    @Test
    public void TC_01_(){

    }
    @Test
    public void TC_02_(){

    }


    @AfterClass
    //3. Clean
    public void cleanBrowser(){
        driver.quit();
    }
}
