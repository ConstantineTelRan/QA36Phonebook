package ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="defaultRegisterFormEmail")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@name ='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@routerlink='/user/registration']")
    private WebElement registerLink;

    @FindBy(id = "error-message")
    private WebElement errorMsg;

    @FindBy(id="email-error-required")
    private WebElement emailErrorMsg;

    @FindBy(id="password-error-required")
    private WebElement passwordErrorMsg;

    @FindBy(xpath = "//h3[@data-value=\"Content Writing - May 2021\"]")
    private WebElement header;

    @FindBy(xpath = "//div[@class=\"card-body\"]//h2")
    WebElement appHeader;

    public void getAuth(String email, String password) {
        waitElementVisible(emailInput);
        type(emailInput, email);
        type(passwordInput, password);
        loginButton.click();
    }

    public WebElement getErrorMsg() {
        return errorMsg;
    }

    public WebElement getPasswordErrorMsg() {
        return passwordErrorMsg;
    }

    public String getErrorMsgText() {
        return errorMsg.getText();
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMsg.getText();
    }

    public void appHeaderClick() {
        appHeader.click();
    }
}
