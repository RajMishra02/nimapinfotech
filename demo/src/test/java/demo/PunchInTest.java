package demo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PunchInTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://testffc.nimapinfotech.com/auth/login"); 
        driver.manage().window().maximize();

        // Log in
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin123");
        driver.findElement(By.id("loginButton")).click();
    }

    @Test
    public void testPunchIn() {
        driver.findElement(By.id("punchInButton")).click(); // Adjust locator for PunchIn

        WebElement toastMessage = driver.findElement(By.id("toastMessage")); // Adjust locator
        Assert.assertEquals(toastMessage.getText(), "PunchIn successful", "Toast message validation failed.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
