package com.envision.automation.framework.core;
import com.envision.automation.framework.utils.ConfigfLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;
public class BasePage {
    WebDriver baseDriver;
    public WebDriver launchBrowser(String browserType){
        if(browserType.equalsIgnoreCase("Chrome")){
            System.setProperty("webdriver.chrome.driver", ConfigfLoader.getChromeDriverPath());
            this.baseDriver=new ChromeDriver();
        }else if (browserType.equalsIgnoreCase("Edge")){
            System.setProperty("webdriver.edge.driver", ConfigfLoader.getEdgeDriverPath());
            this.baseDriver = new EdgeDriver();
        }else if(browserType.equalsIgnoreCase("Firefox")){
            System.setProperty("webdriver.gecko.driver", ConfigfLoader.getFirefoxDriverPath());
            this.baseDriver = new FirefoxDriver();
        }else{
            throw new UnsupportedOperationException("Browser Type [" +browserType+ "] is not supported");
        }
        this.baseDriver.manage().timeouts().implicitlyWait(ConfigfLoader.getWaitTime(), TimeUnit.SECONDS);
        this.baseDriver.manage().window().maximize();
        return this.baseDriver;
    }
}
