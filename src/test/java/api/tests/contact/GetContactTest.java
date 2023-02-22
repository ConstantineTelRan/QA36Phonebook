package api.tests.contact;

import api.enums.EndPoint;
import api.enums.ErrorMessage;
import api.model.AddContactDto;
import api.tests.ApiBase;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class GetContactTest extends ApiBase {
    AddContactDto addContactDto;
    Response response;
    int id;
    int wrongId;
    @BeforeMethod(onlyForGroups = {"positive"})
    public void precondition() {
        addContactDto = new AddContactDto();
        addContactDto.setFirstName(faker.name().firstName());
        addContactDto.setLastName(faker.name().lastName());
        addContactDto.setDescription(faker.lorem().sentence(4));

        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, addContactDto);
        id = response.jsonPath().getInt("id");
    }

    @AfterMethod(onlyForGroups = {"positive"})
    public void afterMethod() {
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);
    }

    @Test(groups = {"positive"})
    public void getContactByIdTest() {
        response = doGetRequestWithParam(EndPoint.GET_CONTACT_BY_ID, 200, id);

        Assert.assertEquals(response.jsonPath().getInt("id"), id);
        Assert.assertEquals(response.jsonPath().getString("firstName"), addContactDto.getFirstName());
        Assert.assertEquals(response.jsonPath().getString("lastName"), addContactDto.getLastName());
        Assert.assertEquals(response.jsonPath().getString("description"), addContactDto.getDescription());
    }

    @Test
    public void getContactByWrongId() {
        wrongId = getWrongId();
        response = doGetRequestWithParam(EndPoint.GET_CONTACT_BY_ID, 500, wrongId);

        Assert.assertEquals(response.jsonPath().getString("message"),
                ErrorMessage.CONTACT_ERROR_MESSAGE.getValue());
    }

    @Test(groups = {"positive"})
    public void getLIstOfContacts() {
        response = doGetRequest(EndPoint.GET_LIST_OF_CONTACTS, 200);
        List<Map<String, Object>> contacts = response.as(new TypeRef<List<Map<String, Object>>>() {});
        Assert.assertTrue(contacts.size() > 0);
    }
}
