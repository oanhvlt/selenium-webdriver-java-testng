package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
    //WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser(){
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    //2. Action/Exe

    @Test
    public void TC_01_() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        By loginButton = By.cssSelector("button.fhs-btn-login");
        //nếu elem mà enabled thì trả ve true, disabled thì trả ve false
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());
        //get value của attr "background-color"
        String loginBackgroundColor = driver.findElement(loginButton).getCssValue("background-color");
        System.out.println(loginBackgroundColor);
        //convert to color type
        Color loginColor = Color.fromString(loginBackgroundColor);
        //compare with #f8f6f0 or #000000
        Assert.assertEquals( loginColor.asHex().toUpperCase(), "#000000");

        //Input user, pass, check button enabled
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("ovu@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("12345678");
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());
        loginBackgroundColor = driver.findElement(loginButton).getCssValue("background-color");
        System.out.println(loginBackgroundColor);
        //compare with #c92127
        Assert.assertEquals( Color.fromString(loginBackgroundColor).asHex().toUpperCase(), "#C92127");


        driver.findElement(loginButton).click();
        //3. chờ cho tới khi message được load ra (presence)
        Thread.sleep(3000);
        //Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs-popup-msg fhs-login-msg']")).getText(), "Số điện thoại/Email hoặc Mật khẩu sai!");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.fhs-login-msg")).getText(), "Số điện thoại/Email hoặc Mật khẩu sai!");


    }
    @Test
    public void TC_02_(){

    }


    @AfterClass
    //3. Clean
    public void cleanBrowser(){

        //driver.quit();
    }
}
