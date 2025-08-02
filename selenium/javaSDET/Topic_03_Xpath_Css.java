package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_Xpath_Css {
    //1. Setup
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        //driver.get("https://live.techpanda.org/");
        //driver.get("https://live.techpanda.org/index.php/customer/account/create/");
        //
        //driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    //2. Action/Exe
    @Test
    public void TC_Example(){
        //use attribute: @attr
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.xpath("//li[@class='success-msg']//span")).click();

        //use function: funcName()
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        //driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']")).click();

        //text tương đối: lấy 1 phần => contains()
        //driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Compu')]")).click();

    }
    //by id
    @Test
    public void Register_01_Empty_Data(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //action
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //verify
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
    }

    @Test
    public void Register_02_Invalid_Email(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //action
        driver.findElement(By.id("txtEmail")).sendKeys("abc");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
    }

    @Test
    public void Register_03_Incorrect_Confirm_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //action
        driver.findElement(By.id("txtEmail")).sendKeys("abc@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("abcd@gmail.com");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void Register_04_Invalid_Password(){

    }

    @Test
    public void Register_04_Incorrect_Confirm_Password() {

    }

    @Test
    public void Register_04_Invalid_Phone_Number(){

    }


    @AfterClass
    //3. Clean
    public void cleanBrowser(){
        driver.quit();
    }
}
