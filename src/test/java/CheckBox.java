import io.appium.java_client.windows.WindowsDriver;
import javafx.scene.layout.Priority;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckBox {
    private static WindowsDriver pos = null;
    public static String getDate(){
        LocalDate date = LocalDate.now();
        return date.toString();
    }
    @BeforeClass
    public static void setUp() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:\\Program Files\\WindowsApps\\CF9BD7D4-0F9A-4730-8950-05D032861D46_2.0.73.0_x64__4rafj02apad6w\\POS.exe");
            capabilities.setCapability("platformName","Windows");
            capabilities.setCapability("deviceName", "WindowsPC");
            pos = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
            pos.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //    @AfterMethod
//    public void cleanApp(){
//        pos.quit();
//        setUp();
//    }
//    @AfterSuite
//    public void tearDown(){
//        pos.quit();
//    }
    @Test(priority = 1 , testName = "TC_01" , description = "Open app on desktop and login")
    public void loginPos() {
        pos.findElementByName("User ID").sendKeys("01558102056");
        pos.findElementByName("Password").sendKeys("123456");
//        pos.findElementByName("Go Offline").click(); //for offline operations
        pos.findElementByClassName("Button").click();


    }




    @Test(priority = 2,testName = "TC_07",description = "Go to invoice page")
    public void invoicePage(){
        pos.findElementByName("Invoice Page").click();



    }

    @Test(priority = 3,testName = "TC_09",description = "Online to Offline")
    public void checkboxOffline(){
        pos.findElementByClassName("CheckBox").click();
    }

    @Test(priority = 4,testName = "TC_10",description = "Offline to Online")
    public void checkboxOnline(){
        pos.findElementByClassName("CheckBox").click();
    }

    @Test(priority = 5,testName = "TC_11",description = "Online to Offline")
    public void checkboxSync(){

        pos.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        pos.findElementByAccessibilityId("synchronous_assynchronous_CheckBox").click();

    }

    @Test(priority = 6,testName = "TC_12",description = "Offline to Online")
    public void checkboxNotsync(){
        pos.findElementByAccessibilityId("synchronous_assynchronous_CheckBox").click();
    }


}


