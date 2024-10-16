import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class Dom03_amazon {
    public static void main(String[] args) {
        /*
        Zadatak za domaci:
https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2
Testirati dodavanje knjige u korpu i da li se knjiga obrise kada obrisete kolacice

Ne treba vam nalog da biste dodali i brisali iz korpe

Brisanjem kolacica koristite ovu liniju koda

driver.manage().deleteAllCookies();
driver.navigate().refresh();
         */
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.navigate().to("https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2");

        WebElement cartAmountBeforeAdding = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartAmountBeforeAdding.getText(), "0");

        WebElement cart = driver.findElement(By.id("nav-cart"));
        cart.click();

        WebElement emptyCart = driver.findElement(By.id("sc-empty-cart"));
        Assert.assertTrue(emptyCart.isDisplayed());

        driver.navigate().back();

        WebElement book = driver.findElement(By.id("productTitle"));
        String bookTitleToBeAdded = book.getText();

        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();

        WebElement successfulMessage = driver.findElement(By.id("NATC_SMART_WAGON_CONF_MSG_SUCCESS"));
        Assert.assertTrue(successfulMessage.isDisplayed());

        WebElement atcText = driver.findElement(By.cssSelector(".a-size-medium-plus.a-color-base.sw-atc-text.a-text-bold"));
        String addtcText = atcText.getText();
        Assert.assertEquals(addtcText, "Added to Cart");

        WebElement cartAmountAfterAdding = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartAmountAfterAdding.getText(), "1");

        cart = driver.findElement(By.id("nav-cart"));
        cart.click();

        WebElement bookInCart = driver.findElement(By.className("a-truncate-cut"));
        String bookTitleInCart = bookInCart.getText();

        Assert.assertEquals(bookTitleToBeAdded, bookTitleInCart);

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        WebElement cartAmountAfterRemoving = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartAmountAfterRemoving.getText(), "0");

        emptyCart = driver.findElement(By.id("sc-empty-cart"));
        Assert.assertTrue(emptyCart.isDisplayed());
    }
}
