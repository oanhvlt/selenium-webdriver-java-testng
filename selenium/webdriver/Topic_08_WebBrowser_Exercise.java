package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*
Exe TCs
1. Setup
2. Action/Exe
3. Clean
 */
public class Topic_08_WebBrowser_Exercise {
    //1. Setup
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
    }

    //2. Action/Exe
    @Test
    public void TC_01_Url(){
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");

    }
    @Test
    public void TC_02_Title(){
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        Assert.assertEquals(driver.getTitle(),"Customer Login"); //title at tag <head>
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

    }

    @Test
    public void TC_03_Navigate(){
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_04_Page_Source(){
        driver.get("https://live.techpanda.org/");
        //action:
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        //verify login page chứa "Login or Create an Account"
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        //action:
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        //verify register page chứa "Create an Account"
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

    }

    @AfterClass
    //3. Clean
    public void cleanBrowser(){
        driver.quit();
    }
}
