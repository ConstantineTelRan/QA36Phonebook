package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPage extends BasePage {

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='collapse navbar-collapse']//div//button[2]")
    private WebElement logOutButton;

    @FindBy(xpath = "//li//a[@href='/']")
    private WebElement contacts;

    @FindBy(xpath = "//select[@id='langSelect']")
    private WebElement selectLang;

    @FindBy(xpath = "//button[@routerlink='/account']")
    private WebElement accountButton;

    public WebElement getAccountButton() {
        return accountButton;
    }
}
