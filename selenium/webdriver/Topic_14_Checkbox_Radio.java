package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

/*
Exe TCs
1. Setup
2. Action/Exe
3. Clean
 */
public class Topic_14_Checkbox_Radio {
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

        //driver.manage().window().maximize();
        //driver.manage().window().setSize(new Dimension(1920, 1080));

    }

    //isSelected

    //2. Action/Exe
    @Test
    public void TC_01_Checkbox(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        ////label[text()='Leather trim']/preceding-sibling::span/input
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isEnabled());
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isSelected());

        //find checkbox , croll thêm 1 đoạn trên browser 300 pixel
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,300)");

        //click checkbox 3
        String checkbox3Locator = "//label[text()='Luggage compartment cover']/preceding-sibling::span/input";

        WebElement checkbox3 = driver.findElement(By.xpath(checkbox3Locator));
        if(!checkbox3.isSelected()){
            checkbox3.click();
        }
        Assert.assertTrue(checkbox3.isSelected());
        if(checkbox3.isSelected()){
            checkbox3.click();
        }
        Assert.assertFalse(checkbox3.isSelected());

    }
    @Test
    public void TC_02_Radio(){

    }

    @Test
    public void TC_03_Multiple(){
        driver.get("https://automationfc.github.io/multiple-fields/");

        //select all checkboxed
        //get all checkboxed of section has label: "Have you ever had (Please check all that apply)"
        //label[text()=' Have you ever had (Please check all that apply) ']/following-sibling::div//input
        String checkboxLocators = "//label[text()=' Have you ever had (Please check all that apply) ']/following-sibling::div//input";
        List<WebElement> checkboxes = driver.findElements(By.xpath(checkboxLocators));
        //check random 5 items
        for(int i = 0; i < 5; i++){
            int number = new Random().nextInt(checkboxes.size() - 1);
            System.out.println(number);
            if(!checkboxes.get(number).isSelected()){
                checkboxes.get(number).click();
            }
        }
        //click all checkboxes
        for(WebElement checkbox : checkboxes){
           if(!checkbox.isSelected()){
               checkbox.click();
           }
        }
        //verify all checkbox selected
        for(WebElement checkbox : checkboxes){
            Assert.assertTrue(checkbox.isSelected());
        }

        //Deselect all checkboxes
        for(WebElement checkbox : checkboxes){
            if(checkbox.isSelected()){
                checkbox.click();
            }
        }
        //Verify all checkboxex deselected
        for(WebElement checkbox : checkboxes){
            Assert.assertFalse(checkbox.isSelected());
        }

        //Select 1 in all + verify
        driver.findElement(By.cssSelector("input[value='Venereal Disease']")).click();
        Assert.assertTrue( driver.findElement(By.cssSelector("input[value='Venereal Disease']")).isSelected());

        for(WebElement checkbox : checkboxes){
            if(checkbox.getAttribute("value").equals("Bleeding Disorders") && !checkbox.isSelected()){
                checkbox.click();
            }
        }
        Assert.assertTrue( driver.findElement(By.cssSelector("input[value='Bleeding Disorders']")).isSelected());


    }


    @AfterClass
    //3. Clean
    public void cleanBrowser(){

        //driver.quit();
    }
}
