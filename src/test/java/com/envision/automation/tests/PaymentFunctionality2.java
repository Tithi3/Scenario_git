package com.envision.automation.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class PaymentFunctionality2 {

    WebDriver driver;

    String url ="http://demo.guru99.com/payment-gateway/index.php";

    @Test
    public void paymentFunctionality() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\QA-Automation\\Java_selenium\\JavaLearning\\Scenarios\\src\\test\\resources\\binaries\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

       // step:2 Click on Generate Card Number

        WebElement linkCard = driver.findElement(By.xpath("//div[1]//a[contains(text(),'Generate Card')]"));
         linkCard.click();
        Thread.sleep(5000);

        //card

        String currentTab = driver.getWindowHandle();
        for(String tab: driver.getWindowHandles()){
            if (!tab.equals(currentTab)){
                driver.switchTo().window(tab);
                Thread.sleep(4000);
            }
        }



        WebElement cardNumber = driver.findElement(By.xpath("//div[@class='inner']//h4[1]"));
        String container = cardNumber.getText();
        String[] array =container.split(":-");
        System.out.println(array);

//
//                CharSequence cardnum = cardNumber.getText().substring(1,7);
//        System.out.println(cardnum);
//        Thread.sleep(3000);
//
//        WebElement cvv =driver.findElement(By.xpath("//div[@class='inner']//h4[2]"));
//        CharSequence cvvDetails = cvv.getText().substring(5,7);
//        System.out.println(cvvDetails);
//        Thread.sleep(3000);

        driver.close();
        driver.quit();


    }

//    public void clickOn(String elementHow){
//        String how = elementHow.split(":")[0];
//        String howValue = elementHow.split(":")[1];
//        WebElement elementToClick = findElementBy(how,howValue);
//        elementToClick.click();
    }
    ///a[contains(text(),'Generate Card')]/@href

