package com.envision.automation.tests;

import com.envision.automation.framework.core.BaseUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class siteOrangeHRMSuccessful extends BaseUtilities {

    WebDriver driver;
    @Test
    public void PositiveLogin() throws InterruptedException {

            System.setProperty("webdriver.chrome.driver","C:\\QA-Automation\\Java_selenium\\JavaLearning\\" +
                    "Scenarios\\src\\test\\resources\\binaries\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get(url);
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("username")).sendKeys(Keys.TAB);
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Keys.TAB);
        Thread.sleep(15000);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"))
                .isDisplayed(), "pic was displayed");
        Thread.sleep(15000);

        driver.close();
        driver.quit();
    }


    @Test
    public class  siteOrangeHRMUnsuccessful extends BaseUtilities{

        public void negativeLogin() throws InterruptedException {

            System.setProperty("webdriver.chrome.driver","C:\\QA-Automation\\Java_selenium\\JavaLearning\\" +
                    "Scenarios\\src\\test\\resources\\binaries\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get(url);
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);

            driver.findElement(By.name("username")).sendKeys("Admin");
            driver.findElement(By.name("username")).sendKeys(Keys.TAB);
            Thread.sleep(2000);

            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin12");
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Keys.TAB);
            Thread.sleep(2000);

            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(2000);
            Thread.sleep(5000);

            driver.close();
            driver.quit();
        }
    }
    }

