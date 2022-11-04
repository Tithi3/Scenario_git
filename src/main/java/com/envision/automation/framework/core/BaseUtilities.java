package com.envision.automation.framework.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseUtilities {

   public String url ="https://opensource-demo.orangehrmlive.com/";

    public  static void takeScrnshot(WebDriver driver, String filePath) throws IOException {
        TakesScreenshot scrshot = ((TakesScreenshot) driver);
        File picfile = scrshot.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(picfile,new File(".//screenshot/screen.png"));
    }
    }


