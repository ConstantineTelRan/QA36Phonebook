package ui.page;

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

    @FindBy(xpath = "//ul[@class='navbar-nav mr-auto']//li[2]")
    private WebElement addContacts;

    @FindBy(xpath = "//select[@id='langSelect']")
    private WebElement selectLang;

    @FindBy(xpath = "//button[@routerlink='/account']")
    private WebElement accountButton;

    public WebElement getAccountButton() {
        return accountButton;
    }

    public void getClickMenuLink(WebElement element) {
        element.click();
    }

    public WebElement getLogOutButton() {
        return logOutButton;
    }

    public WebElement getContacts() {
        return contacts;
    }

    public WebElement getSelectLang() {
        return selectLang;
    }

    public WebElement getAddContacts() {
        return addContacts;
    }
}
