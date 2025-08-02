package webdriver;

import org.openqa.selenium.By;
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
public class Topic_13_Button {
    //1. Setup
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    //2. Action/Exe
    @Test
    public void TC_01_(){
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("")).click();
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
