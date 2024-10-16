import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Dom02_sdLogIn_tc1 {
    public static void main(String[] args) {
        /*
        Domaci
Testirati bar 3 razlicita test case-a za logovanje na sledecem linku:
https://www.saucedemo.com
         */

        // ispravni kredencijali
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://www.saucedemo.com");

        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        String validUsername = "visual_user";
        String validPassword = "secret_sauce";

        String loginPageURL = driver.getCurrentUrl();

        usernameField.clear();
        usernameField.sendKeys(validUsername);

        passwordField.click();
        passwordField.sendKeys(validPassword);

        loginButton.click();

        String expectedURL = "https://www.saucedemo.com/inventory.html";

        Assert.assertNotEquals(driver.getCurrentUrl(), loginPageURL);

        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        WebElement logo = driver.findElement(By.className("app_logo"));
        String logoText = logo.getText();

        Assert.assertEquals(logoText,"Swag Labs");

    }
}
