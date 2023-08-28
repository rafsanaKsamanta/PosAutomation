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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AdminConfig {
    private static WindowsDriver pos = null;
    public static String getDate(){
        LocalDate date = LocalDate.now();
        return date.toString();
    }
    @BeforeClass
    public static void setUp() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:\\Program Files\\WindowsApps\\CF9BD7D4-0F9A-4730-8950-05D032861D46_2.0.104.0_x64__0vy7f2qngagxj\\POS.exe");
            capabilities.setCapability("platformName","Windows");
            capabilities.setCapability("deviceName", "WindowsPC");
            pos = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
            pos.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 1, testName = "TC_01" , description = "Open app on desktop and login")
    public void loginPos() {
        pos.findElementByName("User ID").sendKeys("Admin");
        pos.findElementByName("Password").sendKeys("Admin");
//        pos.findElementByName("Go Offline").click(); //for offline operations
        pos.findElementByClassName("Button").click();




    }


    @Test(priority = 2 , testName = "TC_02",description = "Url,account,branch config")
    public void configUrl(){

        pos.findElementByName("App Url").sendKeys("https://devprincebazar.ibos.io/"); // for devprince
//        pos.findElementByName("App Url").sendKeys("https://areez.ibos.io/"); // for devareez

        pos.findElementByName("Check").click();

        pos.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //account selection
        pos.findElementByClassName("ComboBox").click();
        pos.findElementByName("Prince Super Shop Limited").click();
        pos.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        //branch selection
        pos.findElementByClassName("ComboBox").click();
        pos.findElementByName("Prince Bazar BD").click();
    }

    @Test(priority = 3 , testName = "TC_03",description = "office config")

    public void officeConfig(){

        pos.findElementByClassName("ComboBox").click();
        pos.findElementByName("Prince Super Shop Limited").click();   //office	Prince Super Shop Limited

//        pos.findElementByName("Prince Supershop Limited (Warehouse)").click();   //office	Prince Supershop Limited (Warehouse)




    }




}


