package demo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddCustomerTest {
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

    @Test(dataProvider = "customerData")
    public void testAddCustomer(String name, String email) {
        driver.findElement(By.id("addCustomerMenu")).click(); // Adjust locator for Add Customer

        driver.findElement(By.id("customerName")).sendKeys(name);
        driver.findElement(By.id("customerEmail")).sendKeys(email);
        driver.findElement(By.id("saveButton")).click();

        // Validate customer addition
        WebElement customer = driver.findElement(By.xpath("//td[contains(text(),'" + name + "')]"));
        Assert.assertTrue(customer.isDisplayed(), "Customer addition validation failed.");
    }

    @DataProvider(name = "customerData")
    public Object[][] getCustomerData() {
        return new Object[][] {
            {"John Doe", "john.doe@example.com"},
            {"Jane Smith", "jane.smith@example.com"}
        };
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

