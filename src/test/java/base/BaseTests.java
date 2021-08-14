package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.HomePage;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;


//    @Parameters("browser")

    @BeforeTest

    public void setUp() {

        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("chrome")) {

            System.out.println("chrome");

            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("http://www.expedia.com");
            driver.manage().window().maximize();

            homePage = new HomePage(driver);

        } else if (browser.equalsIgnoreCase("firefox")) {

            System.out.println("firefox");

            System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
            driver = new FirefoxDriver();
            driver.get("http://www.expedia.com");
            driver.manage().window().maximize();

            homePage = new HomePage(driver);

        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
