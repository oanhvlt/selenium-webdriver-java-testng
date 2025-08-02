/*
* Locator in selenium: 8 loại
* */


package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/*
Exe TCs
1. Setup
2. Action/Exe
3. Clean
 */
public class Topic_02_Selenium_Locator {
    //1. Setup
    WebDriver driver;
    String fullName = "Selenium locator";

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        //driver.get("https://demo.nopcommerce.com/register");
        //driver.get("https://demo.nopcommerce.com/");
    }

    //2. Action/Exe
    //XPath: tagname[@attribute='value']
    //css:   tagname[@attribute='value']
    /**
     * Ex:
     * WebElement emailTextbox = driver.findElement(By.id(""));
     * emailTextbox.clear();
     * emailTextbox.sendKeys("");
     * emailTextbox.isDisplayed();
     *
     * driver.findElement(By.cssSelector("")).click();
     * driver.findElement(By.cssSelector("")).getText();
     *
     * //Tìm nhiều elem giống nhau
     * List<WebElement> textboxes = driver.findElements(By.cssSelector(""));
     */
    /*
    Some error message:
    - InvalidSelectorException: Compound class names not permitted
     */
    @Test
    public void TC_01_ID() throws InterruptedException {
        driver.findElement(By.id("small-searchterms")).sendKeys("Macbook");
        Thread.sleep(3000);

        driver.findElement(By.id("FirstName")).sendKeys("Automation");
        Thread.sleep(3000);


//        try {
//            Thread.sleep(3000);
//        }catch (InterruptedException e){
//            throw new RuntimeException(e);
//        }

    }

    @Test
    public void TC_02_Class() throws InterruptedException {
        driver.findElement(By.className("register-next-step-button")).click();
        Thread.sleep(3000);
    }
    @Test
    public void TC_03_Name() throws InterruptedException{
        driver.findElement(By.name("Company")).sendKeys("ABC");
        Thread.sleep(3000);
    }
    @Test
    public void TC_04_LinkText() {
        driver.findElement(By.linkText("Log in")).click();
    }
    @Test
    //lấy hết toàn bộ text hoặc 1 phần
    public void TC_05_PartialLinkText() {
        driver.findElement(By.partialLinkText("Digital")).click();
    }
    @Test
    //Tìm tất cả elem giống nhau (thẻ component giống nhau: vd: all textbox/ all button)
    public void TC_06_Tagname() {
        driver.findElements(By.tagName("button"));
        driver.findElements(By.tagName("input"));
        driver.findElements(By.tagName("label"));
    }
    @Test
    public void TC_07_Css() {
        //combine id
        driver.findElement(By.cssSelector("input[id='FirstName']")).sendKeys("ABC");
        //Viết tắt: # (~id=)
        driver.findElement(By.cssSelector("#FirstName")).sendKeys("Vu");
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Oanh");

        //combine class
        //driver.findElement(By.cssSelector("button.register-next-step-button")).click();
        driver.findElement(By.cssSelector("button[class='button-1 register-next-step-button']")).click();
        //combine select
        //driver.findElement(By.cssSelector("select[name='123']"));
        //combine linktext
        //driver.findElement(By.cssSelector("a[href='/login?returnUrl=%2F']")).click();
        //combine partiallinktext
         driver.findElement(By.cssSelector("a[href*='login?']")).click();
        //combine tagname
        driver.findElement(By.cssSelector("input"));
        driver.findElement(By.cssSelector("a"));

        //AND / OR / NOT
        //and : input[id='email'][name='login[username]']
        driver.findElement(By.cssSelector("input[id='email'][name='login[username]']"));
        //or : input[id='email'],[id='pass']
        driver.findElement(By.cssSelector("input[id='email'],[id='pass']"));
        //not : input:not([id='email'])
        driver.findElement(By.cssSelector("input:not([id='email'])"));
        driver.findElement(By.cssSelector("input:not(#email)"));

        //contains: *=,
        driver.findElement(By.cssSelector("input[placeholder*='entire store']"));
        //starts-with: ^=,
        driver.findElement(By.cssSelector("input[placeholder^='Search ']"));
        //end-with: $=,
        driver.findElement(By.cssSelector("input[placeholder$='here...']"));

        //lấy hết node em : ~tagName (following-sibling in XPath)
        //~div : a[title='Xperia']~div>h2~div
        //Lấy 1 node gần kề: +tagName

        //nth-of-type(n), nth-of-child(n)  (index trong XPath)


        //Không dùng được
        /*
        - text()
        - Không đi ngược node
         */

    }
    /*
    * Relative xpath
    * cú pháp: //tagname[@attribute='value']
    * attribute: id, class, name, describeBy
    * */
    @Test
    public void TC_08_XPath() {
        driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys("123");
        driver.findElement(By.xpath("//input[@id='Company']"));

        driver.findElement(By.xpath("//button[@class='button-1 register-next-step-button']"));
        driver.findElement(By.xpath("//button[contains(@class,'register-next-step-button')]"));

        driver.findElement(By.xpath("//select[@name='customerCurrency']"));

        //link text
        driver.findElement(By.xpath("//a[text()='Log in']"));
        //partial link text
        driver.findElement(By.xpath("//a[contains(text(),'Wish')]"));
        //tagname
        driver.findElement(By.xpath("//a"));
        driver.findElement(By.xpath("//button"));

    }

    //xpath: contains / concat / starts-with
    @Test
    public void TC_XPath_Example1() {
       // driver.findElement(By.xpath("//input[@id='small-searchterms']"));
//        driver.findElement(By.xpath(" //a[contains(text(),'Wish')]"));
//        driver.findElement(By.xpath(" //a[contains(text(),'Wish')]"));
        //driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']")).click();
        //driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Compu')]")).click();

        //Xpath technical 05: nested text (text lồng nhau)
        driver.get("https://automationfc.github.io/basic-form/");
        ////h5[contains(.,'18 years old')]
        //1 phần và cùng 1 hàng
        driver.findElement(By.xpath("//h5[contains(text(),'Michael Jackson')]")).getText();
        ////1 phần và KHÔNG cùng 1 hàng
        driver.findElement(By.xpath("//h5[contains(.,'18 years old')]")).getText();
        driver.findElement(By.xpath("//h5[contains(string(),'18 years old')]")).getText();

        //chuỗi có ký tự đặc biệt
        //span[text()=concat('Hello "John", What', "'s happened?")]
        String helloStr = driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What', \"'s happened?\")]")).getText();
        System.out.println(helloStr);

        // starts-with: tagName[starts-with(@attr,'abc')],
        driver.findElement(By.xpath("//input[starts-with(@placeholder,'Search')]"));
    }

    //Xpath 06 : AND / OR, NOT
    @Test
    public void TC_XPath_Example2() {
        driver.get("https://automationfc.github.io/basic-form/");
        //Xpath 06 : AND / OR
        //input[@type='email' and @id='email']

        //NOT
        //div[not(@style='display:none;')]/div[@class='raDiv']
    }

    //Xpath 07 : inside parent
    @Test
    public void TC_XPath_Example3() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        //ol[@id='selectable']/li[10]
        //ol[@id='selectable']/li[position() = 10]
        driver.findElement(By.xpath("//ol[@id='selectable']/li[10]")).getText();
        //or
        driver.findElement(By.xpath("//ol[@id='selectable']/li[position() = 10]")).getText();
        //elem cuối cùng
        //ol[@id='selectable']/li[last()]
        driver.findElement(By.xpath("//ol[@id='selectable']/li[last()]")).getText();
        //elem kề cuối
        //ol[@id='selectable']/li[last() - 1]

        //Đếm số lượng elem: count(//ol[@id='selectable']/li)

        //đối tượng giống nhau nhưng khác nhánh
        driver.get("https://live.techpanda.org/index.php/mobile.html");

        driver.findElement(By.xpath("(//button[@title='Add to Cart'])[1]")).getText();
        driver.findElement(By.xpath("(//button[@title='Add to Cart'])[2]")).getText();

    }

    //Xpath 08 : TC_Example4_XPath_AXES
    @Test
    public void TC_XPath_Example4_AXES() {
        driver.get("https://live.techpanda.org/index.php/mobile.html");
        //Đi lên: parent(cha) / ancestor(tổ tiên)
        //Đi qua ngang hàng
        //-- node anh: preceding-sibling
        //-- node em: following-sibling

        //Đi xuống: descendant(con/cháu/chắt))/ child (con)

        ////a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button")).getText();

        //Đi lên 1 lần nhiều node:
        ////a[text()='Samsung Galaxy']/ancestor::div[@class='category-products']
        //preceding-sibling:
        ////a[@title='Samsung Galaxy']/following-sibling::div[@class='product-info']/child::div[@class='actions']/preceding-sibling::div[@class='ratings']
        ////a[@title='Samsung Galaxy']/following-sibling::div[@class='product-info']/child::div[@class='actions']/preceding-sibling::h2
        ////a[@title='Samsung Galaxy']/following-sibling::div/div[@class='actions']/preceding-sibling::h2

        //Tìm hết tất cả hậu duệ: *
        ////a[@title='Samsung Galaxy']/following-sibling::div/descendant::*
        //or
        ////a[@title='Samsung Galaxy']/following-sibling::div//*

        ////a[@title='Samsung Galaxy']/following-sibling::div/descendant::a[@class='link-wishlist']

        //Tìm tất cả con:
        ////a[@title='Samsung Galaxy']/following-sibling::div/child::*
        //or:
        ////a[@title='Samsung Galaxy']/following-sibling::div//*

        //Áp dụng lấy các gía trị động
        driver.get("https://demo.guru99.com/access.php?uid=mngr628804%20&%20pass=madydEn%20&%20email=ovu1024.fx@gmail.com");
        ////td[text()='User ID :']/following-sibling::td
        driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();

    }

    //XPath: Func
    @Test
    public void TC_XPath_Func() {
        //string()
        //contains()
        //text()
    }

    @Test
    public void TC_09_Relative_Locator() {
        //above
        //below
        //toRightOf
        //toLeftOf
        WebElement firstName = driver.findElement(By.id("FirstName"));
        WebElement text = driver.findElement(RelativeLocator.with(By.tagName("label"))
                .above(firstName));


    }


    @AfterClass
    //3. Clean
    public void cleanBrowser(){
        driver.quit();
    }

    //function

}
