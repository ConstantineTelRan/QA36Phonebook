package ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ui.page.LoginPage;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    String url = "http://phonebook.telran-edu.de:8080/user/login";
    String email = "test@gmail.com";
    String password = "test@gmail.com";

    @BeforeMethod(onlyForGroups = {"login_tests"})
    public void init() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
    }

    @BeforeMethod(onlyForGroups = {"all_tests"})
    public void initWithLogin() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.getAuth(email, password);
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
