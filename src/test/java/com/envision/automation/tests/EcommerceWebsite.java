package com.envision.automation.tests;
import com.envision.automation.framework.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
public class EcommerceWebsite extends BasePage {
    @Test
    public static void loginFunctionalityEcommerce() throws InterruptedException {
        WebDriver driver;
        String url = "http://automationpractice.com/index.php";

        System.setProperty("webdriver.chrome.driver","C:\\QA-Automation\\Java_selenium\\JavaLearning\\" +
                "Scenarios\\src\\test\\resources\\binaries\\chromedriver.exe");
        driver = new ChromeDriver();
        Integer waitTime = 500;

        driver.manage().window().maximize();
        Thread.sleep(waitTime);
        driver.get(url);

        //1.  Open link http://automationpractice.com/index.php
        WebElement singInElement = driver.findElement(By.xpath("//a[@class='login']"));
        singInElement.click();

        //2. Login to the website using email : "hey@abc.com" and password: "Testing@1234"
        WebElement emailElement = driver.findElement(By.xpath("//input[@id='email']"));
        emailElement.sendKeys("hey@abc.com");

        WebElement passwordElement = driver.findElement(By.xpath("//input[@id='passwd']"));
        passwordElement.sendKeys("Testing@1234");

        WebElement clickButton =driver.findElement(By.xpath("//button[@id='SubmitLogin']"));
        clickButton.click();


        //3. Click on sub menu 'T-shirts'.
        WebElement subMenu =driver.findElement(By.xpath("//li[3]//a[text()='T-shirts']"));
        subMenu.click();

        Thread.sleep(waitTime);

        //4. Click on Result Image : "Faded Short Sleeve T-shirts"
        WebElement imageLink = driver.findElement(By.xpath("//a[@class='product_img_link']//img[@class='replace-2x img-responsive']"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click()", imageLink);

        //5.Click Add to cart
        WebElement addToCart = driver.findElement(By.xpath("//p[@id='add_to_cart']/button/span"));
        addToCart.click();
        Thread.sleep(10000);

       // 6. Verify that the product is added to cart by verifying the message "Product successfully added to your shopping cart

        String expectedMessage = "Product successfully added to your shopping cart";
        WebElement confirmMessage = driver.findElement(By.xpath("//i[@class='icon-ok']"));  //  //div[@id='layer_cart']/div[1]/div[1]/h2
//                "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2/i"));
        String  messageConfirmed = confirmMessage.getText();
        System.out.println(messageConfirmed);
        Assert.assertTrue(confirmMessage.isDisplayed());


        //7.  Click on Proceed to checkout
       // WebElement checkOut = driver.findElement(By.xpath("//div[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")); ////div[@id='layer_cart']/div[1]/div[2]/div[4]/a/span
        WebElement checkOut =driver.findElement(By.xpath("//div[@class='button-container']/a/span"));
        Actions a = new Actions(driver);
        a.moveToElement(checkOut).click();
        Thread.sleep(waitTime);

        //8. On the shopping cart summary screen, fetch the total price.

//        Thread.sleep(waitTime);
//        WebElement cartScreenTotalPrice = driver.findElement(By.xpath("//table[@id='cart_summary']/tfoot/tr[7]/td[2]/span"));
//        String amountCart = cartScreenTotalPrice.getText().replaceAll("[^0-9]","");
//        Integer a1 = Integer.parseInt(amountCart)/100;
//        Integer b1 = 20;
//        Assert.assertEquals(a1,b1,"Price is high");
//        System.out.println("Cart screen Price::" +amountCart);

       //9. Verify the price doesn't exceed total of 20 dollars.


        //10. click Proceed to Checkout-summary
        WebElement cartSummaryCheckoutFirst = driver.findElement(By.xpath("//div[@id='layer_cart']/div/div/div[4]/a/span"));
        a.moveToElement(cartSummaryCheckoutFirst).click();
        Thread.sleep(10000);

        driver.findElement(By.xpath("//div[@id='layer_cart']//a[@title='Proceed to checkout']")).click();

        //number 1.
        driver.findElement(By.xpath("//div[@id='center_column']/p[2]/a[1]/span")).click();

        driver.findElement(By.xpath("//div[@id='center_column']/form/p/button")).click();
        Thread.sleep(5000);


        //11. Select terms of service (You can do it by simply clicking on the text - terms of service)
        WebElement selectCheckTerm = driver.findElement(By.xpath("//div[@class='checker']/span"));  //alternate: //p[@class='checkbox']/div/span and //div//input[@id='cgv']
//        JavascriptExecutor js2 = (JavascriptExecutor) driver;
//        js2.executeScript("arguments[0].scrollIntoView();", selectCheckTerm);
        a.moveToElement(selectCheckTerm).click();
        boolean isSelected = selectCheckTerm.isSelected();

      //performing click operation if element is not checked
        if(isSelected == false) {
            selectCheckTerm.click();
        }
        Thread.sleep(10000);

        //12. Fetch the delivery fees, and verify that delivery fees is not more than 5 dollars
//        WebElement fetchpriceDelivery = driver.findElement(By.xpath("//td[@class='delivery_option_price']/div"));
//        String[] arrDelivery ={"$2.00"};
//        String amountFetched = arr[0];
//        String amountDelivery = amountNumber.substring(0,4);
//        System.out.println("Total :" +amountDelivery);
//        String priceExpect = "$5";
//      //  Assert.assertEquals(arrDelivery,priceExpect); //true
//        Thread.sleep(waitTime);

        //13. Proceed to checkout
        WebElement finalCheckout = driver.findElement(By.xpath("//form[@id='form']/p/button"));  // //form[@id='form']/p/button   //button[@class='button btn btn-default standard-checkout button-medium']/span
        finalCheckout.click();
        Thread.sleep(waitTime);

        //14. Select Pay by bank wire
        WebElement testDropDown = driver.findElement(By.xpath("//div[@id='HOOK_PAYMENT']//a[@class='bankwire']"));
        a.moveToElement(testDropDown).click();
        Thread.sleep(waitTime);

        //15. Click on "I Confirm my order"
        WebElement confirmationOrderPage = driver.findElement(By.xpath("//div[@id='HOOK_PAYMENT']/div[1]/div/p/a[@class='bankwire']"));
        a.moveToElement(confirmationOrderPage).click();
        System.out.println(confirmationOrderPage.getText());
        Thread.sleep(waitTime);

       // 16. Verify successful order confirmation by validating the message "Your order on My Store is complete."
//        WebElement msgPrint = driver.findElement(By.xpath ("//div[@id='center_column']/div[@class='box']/p/strong"));
//        Assert.assertTrue (msgPrint.isDisplayed ());
//        Thread.sleep(waitTime);

        //17. Fetch the wire details: the total amount, name of account owner, name of bank
        //For Price fetch, try following
        WebElement totalPaidAmount = driver.findElement(By.xpath("//div//span[@class='price']/strong"));
        System.out.println("Amount: " + totalPaidAmount.getText());



//        WebElement nameOfOwner = driver.findElement
//                (By.xpath(" //div[@class='box']/text()[contains(.,'Name')]//following-sibling::strong[1]"));
//        String[] arr2= {" ","Pradeep Macharla"};
//        String name =arr2[2];
//        String nameOwner = name.substring(0,15);
//        System.out.println("Name of Owner is ::" +nameOwner);
//
//        WebElement bankName = driver.findElement
//                (By.xpath("//div[@class='box']/text()[contains(.,'Bank')]//following-sibling::strong[1]"));
//        String[] arr3 = {" ","RTP"};
//        String bank =arr3[2];
//        String nameOfBank = bank.substring(0,2);
//        System.out.println("Name of bank is ::" +nameOfBank);
        driver.close();
    }
}
