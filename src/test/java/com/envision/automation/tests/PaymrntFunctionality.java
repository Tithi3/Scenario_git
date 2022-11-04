package com.envision.automation.tests;



import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class PaymrntFunctionality {

        @Test
        public void validPayment() throws InterruptedException {


            System.setProperty("webdriver.chrome.driver","C:\\QA-Automation\\Java_selenium\\JavaLearning\\" +
                    "Scenarios\\src\\test\\resources\\binaries\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            Integer waitTime = 500;

            driver.manage().window().maximize();

            Thread.sleep(waitTime);
            driver.get("http://demo.guru99.com/payment-gateway/index.php");

            WebElement cardNumberLink = driver.findElement(By.xpath("//a[@href='cardnumber.php']"));
            cardNumberLink.click();

            // storing card details
            String cardNumber = "";

            String cvv = "";

            String exp = "";
            String expiryMonth = "";
            String expiryYear = "";

            String creditLimit = "";

            String currentTab = driver.getWindowHandle();
            for (String tab : driver.getWindowHandles()) {
                if (!tab.equals(currentTab)) {

                    //switching newly window
                    driver.switchTo().window(tab);

                    Thread.sleep(waitTime);
                    // for card number
                    WebElement elementCardNumber = driver.findElement(By.xpath("//div[@class='inner']//h4[1]"));
                    String cardNumberContainer = elementCardNumber.getText();


                    String[] cardNumberArray = cardNumberContainer.split(":-");
                    cardNumber = cardNumberArray[1];
                    System.out.println("cardNumber: " + cardNumber);

                    // for cvv
                    WebElement cvvElement = driver.findElement(By.xpath("//div[@class='inner']//h4[2]"));
                    String cvvStored = cvvElement.getText();


                    String[] cvvArray = cvvStored.split(":-");
                    cvv = cvvArray[1];
                    System.out.println("cvv: " + cvv);

                    // for expiry
                    WebElement expElement = driver.findElement(By.xpath("//div[@class='inner']//h4[3]"));

                    String expStored = expElement.getText();
                    String[] expArray = expStored.split(":-");
                    exp = expArray[1];

                    String[] expMonthYearArray = exp.split("/");
                    expiryMonth = expMonthYearArray[0];
                    expiryYear = expMonthYearArray[1];

                    System.out.println("exp: " + exp + "");
                    System.out.println("expMonth: " + expiryMonth);
                    System.out.println("expYear: " + expiryYear);

                    // for credit limit
                    WebElement creditLimitCurrencyElement = driver.findElement(By.xpath("//div[@class='inner']//h4[4]"));

                    String creditLimitWithCurrencyStored = creditLimitCurrencyElement.getText();
                    String[] creditLimitCurrencyArray = creditLimitWithCurrencyStored.split(" ");
                    String creditLimitCurrency = creditLimitCurrencyArray[2];
                    creditLimit = creditLimitCurrency.substring(1,4);
                    System.out.println("creditLimit: " +creditLimit);


                    Thread.sleep(waitTime);
                    driver.close();

                    // now switching to currenttab
                    driver.switchTo().window(currentTab);
                }
            }

            // for cart
            WebElement cart = driver.findElement(By.xpath("//a[text()='Cart']"));
            cart.click();

            Thread.sleep(waitTime);
            Select quantity = new Select(driver.findElement(By.name("quantity")));
            quantity.selectByValue("4");

            // for price
            Thread.sleep(waitTime);
            WebElement priceStoredd = driver.findElement(By.xpath("//div[@class='6u 12u$(small)']//h3"));
            String[] arrayOfPrice = priceStoredd.getText().split(" ");
            String priceWithCurrency = arrayOfPrice[1];
            String price = priceWithCurrency.substring(1,3);
            System.out.println("Price is: " + price);

            // for Buy Now
            Thread.sleep(waitTime);

            WebElement buyNow = driver.findElement(By.xpath("//input[@type='submit']"));
            buyNow.click();

            // information to be filled- card number
            Thread.sleep(waitTime);
            WebElement fillingCardInfo = driver.findElement(By.id("card_nmuber"));

            //using sendkeys
            fillingCardInfo.sendKeys(cardNumber);
            Thread.sleep(waitTime);

            // information to be filled- expiry month

            Select fillingExpiryMonth = new Select(driver.findElement(By.id("month")));


            Integer fillExpiryMonthIntegr = Integer.parseInt(expiryMonth.trim());
            fillingExpiryMonth.selectByValue(fillExpiryMonthIntegr.toString());

            //information to be filled- expiry year
            Select fillExpiryYear = new Select(driver.findElement(By.id("year")));
            fillExpiryYear.selectByVisibleText(expiryYear);
            Thread.sleep(waitTime);

            //information to be filled- expiry cvv

            WebElement fillingCvvInfo = driver.findElement(By.id("cvv_code"));
            fillingCvvInfo.sendKeys(cvv);

            //for purchase amount
            Thread.sleep(waitTime);
            WebElement purchaseAmountContainer = driver.findElement(By.xpath("//input[@type='submit']"));

            String purchaseWithCurrencyAndPay = purchaseAmountContainer.getAttribute("value");
            String[] purchaseAmountArray = purchaseWithCurrencyAndPay.split(" ");
            String purchasingCurrency = purchaseAmountArray[1];
            String purchaseAmount = purchasingCurrency.substring(1,3);
            System.out.println("Purchase Amount is : " + purchaseAmount);

            Integer totalPurchaseAmount = 4 * Integer.parseInt(price);
            assertTrue("Verify the total purchase amount",totalPurchaseAmount==Integer.parseInt(purchaseAmount));



            //for pay now
            Thread.sleep(waitTime);
            WebElement payNow = driver.findElement(By.xpath("//input[@type='submit']"));
            payNow.click();

            //for successful payment
            Thread.sleep(waitTime);
            WebElement successfulPayment = driver.findElement(By.xpath("//div[@class='inner']//h2"));
            System.out.println("paymentSuccessful: " + successfulPayment.getText());
            assertTrue("Verifying that payment is successful", successfulPayment.getText().equalsIgnoreCase("Payment Successfull!"));


            Thread.sleep(waitTime);
            WebElement orderIdStored = driver.findElement(By.xpath("//td[2]/h3/strong"));
            String orderId = orderIdStored.getText();
            System.out.println("Order Id is : " + orderId);

            // for credit limit link to check
            Thread.sleep(waitTime);
            WebElement creditLimitLink = driver.findElement(By.xpath("//a[text()='Check Credit Card Limit']"));
            creditLimitLink.click();

            Thread.sleep(waitTime*2);

            // to remove pop up ad by clicking anywhere
            Actions actions = new Actions(driver);
            actions.moveToElement(creditLimitLink, 0, 0);
            actions.click().build().perform();

            // to check credit limit of that card
            Thread.sleep((waitTime*2));
            WebElement creditLimitCheckCard = driver.findElement(By.id("card_nmuber"));
            creditLimitCheckCard.sendKeys(cardNumber);


            Thread.sleep(waitTime);
            WebElement creditLimitSubmit = driver.findElement(By.xpath("//input[@type='submit']"));
            creditLimitSubmit.click();

            //showing rest balance
            Thread.sleep(waitTime);
            WebElement remainingCreditLimit= driver.findElement(By.xpath("//h4/span"));
            Integer remainingBalance = Integer.parseInt(remainingCreditLimit.getText());
            assertTrue("Verifying remaining credit Balance ", ((Integer.parseInt(creditLimit))-totalPurchaseAmount)==remainingBalance);
            Thread.sleep(waitTime);


            WebElement purchasedOrderId = driver.findElement(By.xpath("//td[6]/b/font"));
            assertTrue("Verify Order Id", orderId.equalsIgnoreCase(purchasedOrderId.getText()));

            Thread.sleep(waitTime);

            driver.close();
            driver.quit();
}
    }


