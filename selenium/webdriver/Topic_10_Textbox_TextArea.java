package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

/*
Exe TCs
1. Setup
2. Action/Exe
3. Clean
 */
public class Topic_10_Textbox_TextArea {
    //1. Setup
    WebDriver driver;
    Random rand;
    String firstName, lastName, emailAddress, password, fullName;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        rand = new Random();
        firstName = "Alice";
        lastName = "Vu";
        emailAddress = "ovu" + rand.nextInt() + "gmail.com";
        password = "123456";
    }

    //2. Action/Exe
    @Test
    public void TC_01_DemoTextAre(){
        driver.get("https://automationfc.github.io/basic-form/");
        String strEdu = "Automation testing\nwith\nSelenium Java";
        driver.findElement(By.cssSelector("textarea#edu")).sendKeys(strEdu);
    }
    @Test
    public void TC_02_Demo2_Register(){
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("button#send2")).click();

        //Register page
        driver.findElement(By.cssSelector("button#firstname")).sendKeys("");
        driver.findElement(By.cssSelector("button#middlename")).sendKeys("");
        driver.findElement(By.cssSelector("button#lastname")).sendKeys("");
        driver.findElement(By.cssSelector("button#email_address")).sendKeys("ovu1024.fx@gmail.com");
        driver.findElement(By.cssSelector("button#password")).sendKeys("");
        driver.findElement(By.cssSelector("button#confirmation")).sendKeys("");
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        //driver.findElement(By.xpath("button#proceed-button")).click();
        driver.findElement(By.xpath("//button[@id='proceed-button']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");

        //get content "Contact Information"
        String contactInfoText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        // contactInfoText.contains("") => return boolean result
        Assert.assertTrue(contactInfoText.contains("") && contactInfoText.contains(""));


    }
//
//    //handle alert
//    @Test
//    public void TC_02_Demo3_Login() throws InterruptedException {
//        driver.get("https://live.techpanda.org/");
//        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']/a")).click();
//        driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log In']")).click();
//        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/login/");
//        //login page
//        driver.findElement(By.cssSelector("input#email")).sendKeys("ovu1024.fx@gmail.com");
//        driver.findElement(By.cssSelector("input#pass")).sendKeys("Abc@1234");
//        driver.findElement(By.xpath("//button[@title='Login']")).click();
//        Thread.sleep(3000);
//    }




    @AfterClass
    //3. Clean
    public void cleanBrowser(){

        //driver.quit();
    }
}
