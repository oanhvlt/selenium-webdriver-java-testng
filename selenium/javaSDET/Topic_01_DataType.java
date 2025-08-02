package javaSDET;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.*;

public class Topic_01_DataType {
    //kiểu nguyên thuỷ
    //initial value nằm trong dấu ''
    public char cName = 'a';
    private char cAddress;

    public void clickToElem(){
        cAddress = 'c';
    }

    char cZip = 'z';
    byte bNumber = -120;
    short sNumber = 1200;
    int iNumber = 350000;
    long lNumber = 242141222;
    //
    float fNumber = 1.33f;
    double dNumber = 3.3131;
    boolean bValue = true;

    //tham chiếu: initial value nằm trong dấu ""
    //string, array, map, object
    // interface: Iterable -> Collection -> List/Set/Queue, Queue -> Deque, Set -> SortedSet -> NavigableSet
    // Class: ArrayList, LinkedList, Vector -> Stack, PriorityQueue, ArrayQueue, MashSet, LinkedHashSet, TreeSet
    // inherit:
    // List -> ArrayList/ LinkedList/ Vector
    // Queue -> PriorityQueue, Deque -> ArrayQueue/ LinkedList
    // Set -> MashSet, LinkedHashSet
    // NavigableSet -> TreeSet

    String fullName = "Automation";
    //class type
    FirefoxDriver fDriver = new FirefoxDriver();
    Actions actions = new Actions(fDriver);
    Topic_01_DataType topic01 = new Topic_01_DataType();

    //interface type
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    //array
    String[] studentName = {"SV1", "SV2"};
    Integer[] studentPhone = {43434343,3422333,424242333};

    //list/set/queue
    List<String> studentAddr = new ArrayList<String>(); //ArrayList, LinkedList, Vector -> Stack
    List<String> studentCity = new LinkedList<String>();

    //map
    Map<String, Integer> zip = new HashMap<String, Integer>();

    //object : ép kiểu
    Object name = "";
    Object phone = 2421421;
    Object isTrue = true;



}
