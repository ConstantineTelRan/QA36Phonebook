package ui.tests;

import api.tests.ApiBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ui.page.LoginPage;

import java.time.Duration;

public class BaseTest extends ApiBase {
    protected WebDriver driver;
    String url = "http://phonebook.telran-edu.de:8080/user/login";
    String email = "test@gmail.com";
    String password = "test@gmail.com";

    @BeforeMethod(onlyForGroups = {"login_tests"})
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
    }

    @BeforeMethod(onlyForGroups = {"all_tests"})
    public void initWithLogin() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.getAuth(email, password);
    }

    public void getPage(String url) {
        driver.get(url);
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
