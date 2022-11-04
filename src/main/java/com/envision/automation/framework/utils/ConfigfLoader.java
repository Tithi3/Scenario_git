package com.envision.automation.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigfLoader {
    static Properties configurations;

    private static String browserType;
    private static String chromeDriverPath;
    private static String firefoxDriverPath;
    private static String edgeDriverPath;

    private static int waitTime;

    public static int getWaitTime() {
        return waitTime;
    }

    public static Properties getConfigurations() {
        return configurations;
    }

    public static String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public static String getFirefoxDriverPath() {
        return firefoxDriverPath;
    }

    public static String getEdgeDriverPath() {
        return edgeDriverPath;
    }

    public static String getBrowserType(){
        return browserType;
    }
    public static void loadConfigurations() throws IOException {
        //step 2:locate file
        configurations = new Properties();
        String path = System.getProperty("user.dir")+ "\\src\\main\\resources\\configs\\config.properties";
        System.out.println(path);
        File propertyFile =  new File(path);

        configurations.load(new FileInputStream(propertyFile));

        browserType =getPropertyValue("browserType");
        chromeDriverPath =getPropertyValue("chromeDriverPath");
        firefoxDriverPath =getPropertyValue("firefoxDriverPath");
        edgeDriverPath =getPropertyValue("edgeDriverPath");
        waitTime = Integer.parseInt(getPropertyValue("waitTime"));

    }

    public static String getPropertyValue(String propertName){
        String value = configurations.getProperty(propertName);
        return value;
    }

    public static void main(String[] args) throws IOException {
        ConfigfLoader configloader = new ConfigfLoader();
        configloader.loadConfigurations();
        System.out.print("property value is :"+ configloader.getPropertyValue("browserType"));
    }
}
