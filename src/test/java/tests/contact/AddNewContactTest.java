package tests.contact;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.AddContact;
import page.ContactPage;
import page.LoginPage;
import page.MenuPage;
import tests.BaseTest;

import java.util.Map;

public class AddNewContactTest extends BaseTest {
    Faker faker = new Faker();
    MenuPage menu;
    String email = "test@gmail.com";
    String password = "test@gmail.com";
    AddContact addContact;
    ContactPage contactPage;
    LoginPage loginPage;

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String about = faker.lorem().sentence(4);

    Map<String, String> contactInfo;


    @BeforeMethod
    public void precondition() {
        loginPage = new LoginPage(driver);
        loginPage.getAuth(email, password);
    }

    @Test
    public void addNewContactTest() {
        addContact = new AddContact(driver);
        menu = new MenuPage(driver);
        menu.getClickMenuLink(menu.getAddContacts());
        addContact.fillData(firstName, lastName, about);
        contactPage = new ContactPage(driver);
        contactInfo = contactPage.getContactInfo();

        Assert.assertEquals(contactPage.getName(), firstName);

        Assert.assertEquals(contactInfo.get("first name"), firstName);
        Assert.assertEquals(contactInfo.get("last name"), lastName);
        Assert.assertEquals(contactInfo.get("contact description"), about);
    }

}
