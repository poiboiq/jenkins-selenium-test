package com.test;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
public class LoginTest {
    @Test
    public void test_login_with_incorrect_credentials() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.navigate().to("http://103.139.122.250:4000/login");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("qasim@malik.com");
            driver.findElement(By.id("password")).sendKeys("abcdefg");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            Thread.sleep(3000);
            String pageSource = driver.getPageSource();
            assert(pageSource.contains("Invalid") || pageSource.contains("incorrect") || pageSource.contains("error") || pageSource.contains("wrong"));
        } catch(Exception e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}
