package api.tests.email;

import api.enums.EndPoint;
import api.model.AddContactDto;
import api.model.EmailDto;
import api.tests.ApiBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetEmailTest extends ApiBase {
    AddContactDto addContactDto;
    EmailDto emailDto;
    Response response;
    Response responseForEmail;
    int contactId;
    int emailId;

    @BeforeMethod
    public void precondition() {
        addContactDto = new AddContactDto();
        addContactDto.setFirstName(faker.name().firstName());
        addContactDto.setLastName(faker.name().lastName());
        addContactDto.setDescription(faker.lorem().sentence(4));

        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, addContactDto);
        contactId = response.jsonPath().getInt("id");

        emailDto = new EmailDto();
        emailDto.setEmail(faker.internet().emailAddress());
        emailDto.setContactId(contactId);
        doPostRequest(EndPoint.ADD_NEW_EMAIL, 201, emailDto);

        responseForEmail = doGetRequestWithParam(EndPoint.GET_LIST_OF_EMAILS, 200, contactId);
        emailId = responseForEmail.jsonPath().getInt("[0].id");
    }

    @AfterMethod
    public void afterMethod() {
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, contactId);
    }

    @Test
    public void getEmailByEmailId() {
        response = doGetRequestWithParam(EndPoint.GET_EMAIL_BY_EMAIL_ID, 200, emailId);

        Assert.assertEquals(response.jsonPath().getInt("id"), emailId);
        Assert.assertEquals(response.jsonPath().getString("email"), emailDto.getEmail());
        Assert.assertEquals(response.jsonPath().getInt("contactId"), contactId);
    }
}
