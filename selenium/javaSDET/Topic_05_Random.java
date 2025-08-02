package javaSDET;

import org.testng.annotations.Test;

import java.util.Random;

public class Topic_05_Random {
    String prefixEmail = "ovu";
    String postfixEmail = "@gmail.com";
    String fulEmail = prefixEmail + postfixEmail;

    @Test
    public void testEmail(){
        Random rand = new Random();
        //local
        String fulName = prefixEmail + rand.nextInt(99999) + postfixEmail;
        System.out.println(fulName);
    }

    public String randomEmail(){
        Random rand = new Random();
        //local
        String fulEmail = prefixEmail + rand.nextInt(99999) + postfixEmail;
        return fulEmail;
    }

}
