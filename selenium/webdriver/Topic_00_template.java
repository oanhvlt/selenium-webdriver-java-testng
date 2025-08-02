package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
