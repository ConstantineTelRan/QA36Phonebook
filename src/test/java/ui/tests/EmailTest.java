package ui.tests;

import api.enums.EndPoint;
import api.model.AddContactDto;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.page.EmailPage;
import ui.page.LoginPage;

public class EmailTest extends BaseTest {
    Faker faker = new Faker();
    int id;
    Response response;
    AddContactDto addContactDto;

    LoginPage loginPage;
    String email = faker.internet().emailAddress();
    String contactUrl = "http://phonebook.telran-edu.de:8080/contacts/";
    EmailPage emailPage;

    @BeforeMethod(onlyForGroups = {"all_tests"})
    public void precondition() {
        addContactDto = new AddContactDto();
        addContactDto.setFirstName(faker.name().firstName());
        addContactDto.setLastName(faker.name().lastName());
        addContactDto.setDescription(faker.lorem().sentence(4));

        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, addContactDto);
        id = response.jsonPath().getInt("id");
    }

    @Test(groups = {"all_tests"})
    public void addEmail() {
        getPage(contactUrl + id);
        emailPage = new EmailPage(driver);
        emailPage.clickToEmailTabl();
        emailPage.clickToAddEmail();
    }

}
