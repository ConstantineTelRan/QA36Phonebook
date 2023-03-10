package ui.tests.login;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.page.LoginPage;
import ui.page.MenuPage;
import ui.tests.BaseTest;

public class LoginTest extends BaseTest {

    Faker faker = new Faker();

    LoginPage loginPage;
    MenuPage menu;

    String email = "test@gmail.com";
    String password = "test@gmail.com";

    String wrongEmail = faker.internet().emailAddress();
    String wrongPassword = faker.internet().password();

    String errorMessage = "Please check your activation or Login + Password combination";
    String errorPasswordMessage = "Password is required.";

    @Test(groups = {"login_tests"})
    public void loginTest() {
        loginPage = new LoginPage(driver);
        loginPage.getAuth(email, password);
        menu = new MenuPage(driver);
        Assert.assertTrue(menu.waitElementVisible(menu.getAccountButton()));
    }

    @Test(groups = {"login_tests"})
    public void loginTestWithWrongEmailAndPassword() {
        loginPage = new LoginPage(driver);
        loginPage.getAuth(wrongEmail, wrongPassword);

        Assert.assertTrue(loginPage.waitElementVisible(loginPage.getErrorMsg()));
        Assert.assertEquals(loginPage.getErrorMsgText(), errorMessage,
                "The actual text of error message does not matches the expected text");
    }

    @Test(groups = {"login_tests"})
    public void loginTestWithoutPassword() {
        loginPage = new LoginPage(driver);
        loginPage.getAuth(email, "");
        loginPage.appHeaderClick();
        Assert.assertTrue(loginPage.waitElementVisible(loginPage.getPasswordErrorMsg()));
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), errorPasswordMessage,
                "The actual text of error message does not matches the expected text");
    }

}
