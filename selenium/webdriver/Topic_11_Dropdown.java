package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class Topic_11_Dropdown {
    //1. Setup
    WebDriver driver;
    Actions action;
    Select select;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        //nhận driver là tham số, initail tại đây (BeforeClass):
        //- action
//        action = new Actions(driver);
//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        jsExecutor = (JavascriptExecutor) driver;
        //-
    }

    //2. Action/Exe
    @Test
    public void TC_01_Facebook_signup(){
        driver.get("https://www.facebook.com/reg/");
        select = new Select(driver.findElement(By.cssSelector("select#day")));
        //chọn 1 item
        select.selectByVisibleText("25");
        //chọn xong verify đã chọn thành cộng hay chưa?
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "25");
        //verify có multiple select
        Assert.assertFalse(select.isMultiple());

        //verify tổng số lượng item trong dropdown
        Assert.assertEquals(select.getOptions().size(), 31);

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
