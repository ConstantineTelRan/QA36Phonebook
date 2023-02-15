package ui.tests.contact;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.page.AddContact;
import ui.page.ContactPage;
import ui.page.MenuPage;
import ui.tests.BaseTest;

import java.util.Map;

public class AddNewContactTest extends BaseTest {
    Faker faker = new Faker();
    MenuPage menu;
    AddContact addContact;
    ContactPage contactPage;
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String about = faker.lorem().sentence(4);

    Map<String, String> contactInfo;


    @Test(groups = {"all_tests"})
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
