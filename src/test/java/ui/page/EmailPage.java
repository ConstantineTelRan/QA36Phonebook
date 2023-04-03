package ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailPage extends BasePage{


    public EmailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@id=\"btn-add-phone\"]")
    private WebElement addEmail;

    @FindBy(xpath = "//a[@id=\"ngb-nav-2\"]")
    private WebElement emailTab;

    public void clickToAddEmail() {
        addEmail.click();
    }

    public void clickToEmailTabl() {
        emailTab.click();
    }

}
