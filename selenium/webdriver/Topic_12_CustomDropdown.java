package webdriver;

import org.openqa.selenium.*;
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

    @Test
    public void TC_05_mutipleDropdown() throws InterruptedException {
        driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");


    }
    public void selectMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem) throws InterruptedException {
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        // 1: click vào cái dropdown cho nó xổ hết tất cả các giá trị ra
        driver.findElement(By.xpath(parentXpath)).click();

        // 2: chờ cho tất cả các giá trị trong dropdown được load ra thành công
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

        // Duyệt qa hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
        for (WebElement childElement : allItems) {
            // "January", "April", "July"
            for (String item : expectedValueItem) {
                if (childElement.getText().equals(item)) {
                    // 3: scroll đến item cần chọn (nếu như item cần chọn có thể nhìn thấy thì ko cần scroll)
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
                    Thread.sleep(1000);

                    // 4: click vào item cần chọn
                    childElement.click();
                    Thread.sleep(1000);

                    List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
                    System.out.println("Item selected = " + itemSelected.size());
                    if (expectedValueItem.length == itemSelected.size()) {
                        break;
                    }
                }
            }
        }
    }

    public boolean isItemSelected(String[] months) {
        List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
        int numberItemSelected = itemSelected.size();

        String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
        System.out.println("Text da chon = " + allItemSelectedText);

        if (numberItemSelected <= 3 && numberItemSelected > 0) {
            boolean status = true;
            for (String item : months) {
                if (!allItemSelectedText.contains(item)) {
                    status = false;
                    return status;
                }
            }
            return status;
        } else if (numberItemSelected >= 12) {
            return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
        } else if (numberItemSelected > 3 && numberItemSelected < 12) {
            return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
        } else {
            return false;
        }
    }

    private void selectItemInCustomDropdown(String parentElem, String childElem, String textItem) throws InterruptedException {
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
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
                //trước khi click thì scroll đến elem
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                Thread.sleep(1000);
                //đợi cho đến khi elem clickable
                explicitWait.until(ExpectedConditions.elementToBeClickable(item));
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
