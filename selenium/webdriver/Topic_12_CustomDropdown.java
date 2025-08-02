package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/*
Exe TCs
1. Setup
2. Action/Exe
3. Clean
 */
public class Topic_12_CustomDropdown {
    //1. Setup
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    //2. Action/Exe
    @Test
    public void TC_01_jQuery() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        //Speed menu
        //driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']"));
        String parentElem = "span#speed-button>span.ui-selectmenu-icon";
        String childElem = "ul#speed-menu>li>div";
        selectItemInCustomDropdown(parentElem,childElem,"Slower");
        Assert.assertEquals( driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");
//        selectItemInCustomDropdown(parentElem,childElem,"Slow");
//        selectItemInCustomDropdown(parentElem,childElem,"Medium");
//        selectItemInCustomDropdown(parentElem,childElem,"Fast");
//        selectItemInCustomDropdown(parentElem,childElem,"Faster");

        //Number menu
        String parentElemNumberMenu = "span#number-button>span.ui-selectmenu-icon";
        String childElemNumberMenu = "ul#number-menu>li>div";
        selectItemInCustomDropdown(parentElemNumberMenu,childElemNumberMenu,"3");
        Assert.assertEquals( driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "3");

        //Number menu
        String parentElemSalutationMenu = "span#salutation-button>span.ui-selectmenu-icon";
        String childElemSalutationMenu = "ul#salutation-menu>li>div";
        selectItemInCustomDropdown(parentElemSalutationMenu,childElemSalutationMenu,"Mr.");
        Assert.assertEquals( driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mr.");


    }



    @Test
    public void TC_02_Reactjs() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        String parentElem = "div.dropdown";
        String childElem = "div.dropdown>div.menu.transition>div.item>span"; //div.item>span
        selectItemInCustomDropdown(parentElem,childElem,"Elliot Fu");
        Assert.assertEquals( driver.findElement(By.cssSelector("div.dropdown>div.divider.text")).getText(), "Elliot Fu");
        selectItemInCustomDropdown(parentElem,childElem,"Christian");
        Assert.assertEquals( driver.findElement(By.cssSelector("div.dropdown>div.divider.text")).getText(), "Christian");
    }

    @Test
    public void TC_03_vuejs() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        String parentElem = "li.dropdown-toggle";
        String childElem = "ul.dropdown-menu>li>a"; //div.item>span
        selectItemInCustomDropdown(parentElem,childElem,"First Option");
        Assert.assertEquals( driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
        selectItemInCustomDropdown(parentElem,childElem,"Third Option");
        Assert.assertEquals( driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
    }

    @Test
    public void TC_04_editableDropdown() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        String parentElem = "input.search";
        String childElem = "div.item>span";
        enterItemInCustomDropdown(parentElem,childElem,"Alger");
        Assert.assertEquals( driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");
        enterItemInCustomDropdown(parentElem,childElem,"arme");
        Assert.assertEquals( driver.findElement(By.cssSelector("div.divider.text")).getText(), "Armenia");
    }


    private void selectItemInCustomDropdown(String parentElem, String childElem, String textItem) throws InterruptedException {
        //Thao tác với dropdown
        //1. Chờ cho tới khi có thao tác clickable
        ////span[@id='speed-button']/span[contains(@class, 'ui-selectmenu-icon')]
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentElem)));
        //2. click vào elem nào để nó xổ ra cái dropdown
        driver.findElement(By.cssSelector(parentElem)).click();
        Thread.sleep(3000);
        //3. Chờ cho tất cả các item được load ra (presence)
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childElem)));
        //4. Tìm cái item nào đúng vs mong đợi
        List<WebElement> allItems = driver.findElements(By.cssSelector(childElem));
        //5. items
        for (WebElement item: allItems){
            System.out.println(item.getText());
            if(item.getText().equals(textItem)){
                //click item
                item.click();
                break;
            }
        }
    }

    private void enterItemInCustomDropdown(String parentElem, String childElem, String textItem) throws InterruptedException {
        //1. Chờ cho tới khi có nhập được (visible
        //2. Sendkey vào dropdown
        WebElement dropdownTextbox;
        dropdownTextbox = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(parentElem)));
        dropdownTextbox.clear();
        dropdownTextbox.sendKeys(textItem);
        dropdownTextbox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        //3. Chờ cho tất cả các item được load ra (presence)
        //explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childElem)));
        //4. Tìm cái item nào đúng vs mong đợi
        //List<WebElement> allItems = driver.findElements(By.cssSelector(childElem));
        List<WebElement> allItems =  explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childElem)));
        //5. items
        for (WebElement item: allItems){
            System.out.println(item.getText());
            if(item.getText().equals(textItem)){
                //click item
                item.click();
                break;
            }
        }
    }

    @AfterClass
    //3. Clean
    public void cleanBrowser(){
        driver.quit();
    }
}
