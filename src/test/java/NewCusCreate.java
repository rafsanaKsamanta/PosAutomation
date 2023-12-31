import io.appium.java_client.windows.WindowsDriver;
import javafx.scene.layout.Priority;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NewCusCreate {
    private static WindowsDriver pos = null;
    public static String getDate(){
        LocalDate date = LocalDate.now();
        return date.toString();
    }
    @BeforeClass
    public static void setUp() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:\\Program Files\\WindowsApps\\CF9BD7D4-0F9A-4730-8950-05D032861D46_2.0.101.0_x64__4rafj02apad6w\\POS.exe");
            capabilities.setCapability("platformName","Windows");
            capabilities.setCapability("deviceName", "WindowsPC");
            pos = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
            pos.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 1 , testName = "TC_01" , description = "Open app on desktop and login")
    public void loginPos() {
        pos.findElementByName("User ID").sendKeys("01558102053");
        pos.findElementByName("Password").sendKeys("123456");
//        pos.findElementByName("Go Offline").click(); //for offline operations
        pos.findElementByClassName("Button").click();


    }


    @Test(priority = 2 , testName = "TC_02",description = "Add items to sales screen")
    public void addItem(){

        WebElement barcodeInput = pos.findElement(By.name("Scan or enter Barcode (Alt+B) ........"));

        // List of barcode numbers to scan
        List<String> barcodeNumbers = new ArrayList<>();

        barcodeNumbers.add("842251185262");

        // ... add more barcode numbers

        // Simulate scanning each barcode
        for (String barcodeNumber : barcodeNumbers) {
            // Enter the barcode number
            barcodeInput.sendKeys(barcodeNumber);

            // Trigger scanning action (e.g., press Enter)
            barcodeInput.sendKeys(Keys.ENTER);

            // Pause to simulate the time between scans (if needed)
            try {
                Thread.sleep(1000); // Adjust the sleep time as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test(priority =3 , testName = "TC_03",description = "item qty change")
    public void qtyChange(){
        WebElement pressf8 = pos.findElementByName("1.0");
        pressf8.sendKeys(Keys.F8);
        pressf8.sendKeys("5");

        WebElement pressEnter2=pos.findElementByName("1.0");
        pressEnter2.sendKeys(Keys.ENTER);

    }

    @Test(priority =4 , testName = "TC_04",description = "customer create alt+c")
    public void newCus(){

//        pos.findElement(By.className("Image")).sendKeys(Keys.chord(Keys.ALT, "c"));

        pos.findElementByClassName("Image").sendKeys(Keys.chord(Keys.ALT,"c"));

        // Locate the element you want to interact with
        WebElement element = pos.findElement(By.className("Image"));

        // Initialize the Actions class
        Actions actions = new Actions(pos);
        actions.keyDown(Keys.ALT).sendKeys("c").keyUp(Keys.ALT).build().perform();

        pos.findElementByName("Customer Name :").sendKeys("Zeba");
        pos.findElementByName("Mobile No :").sendKeys("01558102046");
        pos.findElementByName("Save").click();
        pos.findElementByName("OK").click();



    }



//    @Test(priority = 4 , testName = "TC_04",description = "Add customer to sales screen")
//    public void addCustomer(){
//
//        pos.findElementByName("Enter Customer No (Alt+M)....").sendKeys("01522222221");
//        WebElement pressEnter=pos.findElementByName("Enter Customer No (Alt+M)....");
//        pressEnter.sendKeys(Keys.ENTER);
//    }

    @Test(priority = 5,testName = "TC_05",description = "Focus on cash field")
    public void cash(){

        WebElement pressEnter=pos.findElementByName("Scan or enter Barcode (Alt+B) ........");
        pressEnter.sendKeys(Keys.ENTER);

        WebElement CashFocus1=pos.findElementByName("0");
        CashFocus1.sendKeys(Keys.ENTER);

    }


    @Test(priority = 6,testName = "TC_06",description = "Sales, invoice creation and back to new sale screen")
    public void sale(){

        WebElement CashFocus1=pos.findElementByName("0");
        CashFocus1.sendKeys(Keys.ENTER);

        pos.findElementByName("OK").click(); //ok button click and back to sales screen

    }



}


