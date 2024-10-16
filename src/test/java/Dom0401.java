import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Dom0401 {
    /*
   Domaci:
Koristeci anotacije, ulogujte se na demoqa (https://demoqa.com/ -> Book Store Application) preko cookies-a
    */
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://demoqa.com/login");
    }

    @Test
    public void logViaCookies() {
        Cookie tCookie = new Cookie("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InBlcmlubG9nIiwicGFzc3dvcmQiOiJQZXJpY2EzNSEiLCJpYXQiOjE3MjgwNTQ4Nzd9.PhS--FPX_uUMEQX23mcxSwT3seIKIV184wDii0iuGcY");
        Cookie uidCookie = new Cookie("userID", "b282598c-8997-4e4b-b5ee-42b702af2cef");
        Cookie unCookie = new Cookie("userName", "perinlog");
        Cookie eCookie = new Cookie("expires", "2024-10-11T15%3A27%3A46.496Z");
        driver.manage().addCookie(unCookie);
        driver.manage().addCookie(tCookie);
        driver.manage().addCookie(uidCookie);
        driver.manage().addCookie(eCookie);
        driver.navigate().refresh();
        WebElement logOutButton = driver.findElement(By.id("submit"));
        Assert.assertTrue(logOutButton.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
