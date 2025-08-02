package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
public class Topic_09_WebElement_Exercise {
    //1. Setup
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();

    }

    //2. Action/Exe
    @Test
    public void TC_01_Displayed(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Find elem Email
        //By.id("mail")
        //cssSelector
        WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));
        if(emailTextbox.isDisplayed()){
            System.out.println("Elem email Textbox is displayed");
            emailTextbox.sendKeys("test1@gmail.com");
        }else {
            System.out.println("Elem email Textbox is not displayed");
        }

        //Find elem Age (under 18)
        WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
        if(ageUnder18Radio.isDisplayed()){
            System.out.println("Elem age Under18 Radio is displayed");
            ageUnder18Radio.click();
        }else {
            System.out.println("Elem age Under18 Radio is not displayed");
        }

        //Find elem Education textare
        WebElement eduTextAre = driver.findElement(By.cssSelector("textarea#edu"));
        if(eduTextAre.isDisplayed()){
            System.out.println("Elem Education textare is displayed");
            eduTextAre.sendKeys("edu 123");
        }else {
            System.out.println("Elem Education textare is not displayed");
        }

        //Find elem is hided
        WebElement userNameLabel = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        if(userNameLabel.isDisplayed()){
            System.out.println("Elem userName 05 is displayed");
        }else {
            System.out.println("Elem userName 05 is not displayed");
        }

    }
    @Test
    public void TC_02_Enabled(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));
        if(emailTextbox.isEnabled()){
            System.out.println("Elem email Textbox is enabled");
            emailTextbox.sendKeys("test1@gmail.com");
        }else {
            System.out.println("Elem email Textbox is disabled");
        }

        //Find elem Age (under 18)
        WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
        if(ageUnder18Radio.isEnabled()){
            System.out.println("Elem age Under18 Radio is enabled");
            ageUnder18Radio.click();
        }else {
            System.out.println("Elem age Under18 Radio is disabled");
        }

        //Find elem is hided
        WebElement passwordTextbox = driver.findElement(By.cssSelector("input#disable_password"));
        if(passwordTextbox.isEnabled()){
            System.out.println("Elem password textbox is enabled");
            passwordTextbox.sendKeys("edu 123");
        }else {
            System.out.println("Elem password textbox is disabled");
        }

        WebElement job3Select = driver.findElement(By.cssSelector("select#job3"));
        if(job3Select.isEnabled()){
            System.out.println("Elem job3_select is enabled");
        }else {
            System.out.println("Elem job3_select is disabled");
        }


    }

    //kiểm tra 1 elem được chọn thành công
    @Test
    public void TC_03_Selected(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //verify ageUnder18 Radio is de-selected
        WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
        if(ageUnder18Radio.isDisplayed()){
            if(ageUnder18Radio.isSelected()){
                System.out.println("Elem age Under18 Radio is selected");
            }else {
                System.out.println("Elem age Under18 Radio is de-selected");
                ageUnder18Radio.click();
            }
        }else {
            System.out.println("Elem age Under18 Radio is not displayed");
        }

        //verify devCheckbox is de-selected
        WebElement devCheckbox = driver.findElement(By.cssSelector("input#development"));
        if(devCheckbox.isDisplayed()){
            if(devCheckbox.isSelected()){
                System.out.println("Elem devCheckbox is selected");
            }else {
                System.out.println("Elem devCheckbox is de-selected");
                devCheckbox.click();
            }
        }else {
            System.out.println("Elem devCheckbox is not displayed");
        }

        //verify ageUnder18, devCheckbox is selected
        if(ageUnder18Radio.isSelected()){
            System.out.println("Elem age Under18 Radio is selected");
        }else {
            System.out.println("Elem age Under18 Radio is de-selected");
        }

        if(devCheckbox.isSelected()){
            System.out.println("Elem devCheckbox is selected");
        }else {
            System.out.println("Elem devCheckbox is de-selected");
        }

    }


    @Test
    public void TC_04_MailChimp_Validate() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");
        //actions:
        driver.findElement(By.cssSelector("input#email")).sendKeys("ovu1024.fx@gmail.com");
        driver.findElement(By.cssSelector("input#email")).sendKeys(Keys.TAB);
        driver.findElement(By.cssSelector("input#new_username")).sendKeys(Keys.TAB);

        WebElement newPasswordTxt =   driver.findElement(By.cssSelector("input#new_password"));
        newPasswordTxt.sendKeys("123");
        newPasswordTxt.sendKeys(Keys.TAB);

        //verify password must not contain username
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //verify password only number pass
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        //8-char not-completed => ký tự số ko thể dùng Css
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());


        //verify password upper text pass
        newPasswordTxt.clear();
        Thread.sleep(3000);
        newPasswordTxt.sendKeys("OVU");
        newPasswordTxt.sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());

        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());
        //.....

        //verify password username-check failed
        newPasswordTxt.clear();
        newPasswordTxt.sendKeys("Aovu1024.fx");
        newPasswordTxt.sendKeys(Keys.TAB);
        //verify inline message:
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        //nếu verify inline message ko hiển thị nữa: using assertFalse
        //verify password check pass
        newPasswordTxt.clear();
        newPasswordTxt.sendKeys("Abc@1234");
        newPasswordTxt.sendKeys(Keys.TAB);
        //verify inline message:
        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
    }


    @Test
    public void TC_01_Login_Empty_Email_Password(){
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");

    }

    @Test
    public void TC_02_Login_Invalid_Email(){
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("123@123.123");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");

        driver.findElement(By.cssSelector("button#send2")).click();
        //advice-validate-email-email
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_Login_Invalid_Pass(){
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");

        driver.findElement(By.cssSelector("button#send2")).click();
        //advice-validate-email-email
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(),
                "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_Login_valid_username_pass() throws InterruptedException {
        driver.get("https://eco-dms-dev.finviet.com.vn/auth/login");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input#username")).sendKeys("oanhvu1");
        driver.findElement(By.cssSelector("input#password")).sendKeys("Abc@1234");
        driver.findElement(By.xpath("//button//span[text()='Đăng nhập']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(),"https://eco-dms-dev.finviet.com.vn/dashboard");
    }

    @AfterClass
    //3. Clean
    public void cleanBrowser(){
        //driver.quit();
    }
}
