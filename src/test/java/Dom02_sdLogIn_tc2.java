import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Dom02_sdLogIn_tc2 {
    public static void main(String[] args) {
        /*
        Domaci
Testirati bar 3 razlicita test case-a za logovanje na sledecem linku:
https://www.saucedemo.com
         */

        // pogresan username
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://www.saucedemo.com/");

        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        String invalidUsername = "visul_user";
        String validPassword = "secret_sauce";

        String loginPageURL = driver.getCurrentUrl();

        usernameField.clear();
        usernameField.sendKeys(invalidUsername);

        passwordField.click();
        passwordField.sendKeys(validPassword);

        loginButton.click();

        String expectedURL = "https://www.saucedemo.com/";

        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        WebElement dataTest = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String errorM = dataTest.getText();

        Assert.assertEquals(errorM,"Epic sadface: Username and password do not match any user in this service");
    }
}
