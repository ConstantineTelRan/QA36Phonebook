package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddContact extends BasePage{
    public AddContact(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='form-name']")
    WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='form-lastName']")
    WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='form-about']")
    WebElement aboutInput;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    WebElement saveButton;

    public void fillData(String firstName, String lastName, String about) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        aboutInput.clear();
        aboutInput.sendKeys(about);
        saveButton.click();
    }

}
