package com.envision.automation.tests;

import com.envision.automation.framework.core.BasePage;
import com.envision.automation.framework.utils.ConfigfLoader;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class LaunchBrowserTests {

    @BeforeSuite
    public void loadConfigurations() throws IOException {
        ConfigfLoader.loadConfigurations();
    }

    @Test
    public void testLaunchingOfBrowser() throws IOException, InterruptedException {

        ConfigfLoader.loadConfigurations();

        BasePage basepage = new BasePage();
        basepage.launchBrowser(ConfigfLoader.getBrowserType());
        Thread.sleep(5000);
    }
}
