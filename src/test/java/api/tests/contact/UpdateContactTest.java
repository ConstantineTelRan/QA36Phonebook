package api.tests.contact;

import api.enums.EndPoint;
import api.model.AddContactDto;
import api.model.UpdateContactDto;
import api.tests.ApiBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateContactTest extends ApiBase {
    AddContactDto addContactDto;
    UpdateContactDto updateContactDto;
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
    public void updateContactTest() {
        updateContactDto = new UpdateContactDto();
        updateContactDto.setId(id);
        updateContactDto.setFirstName(faker.name().firstName());
        updateContactDto.setLastName(faker.name().lastName());
        updateContactDto.setDescription(faker.lorem().sentence(4));

        doPutRequest(EndPoint.UPDATE_CONTACT, 200, updateContactDto);

        response = doGetRequestWithParam(EndPoint.GET_CONTACT_BY_ID, 200, id);

        Assert.assertEquals(response.jsonPath().getInt("id"), id);
        Assert.assertEquals(response.jsonPath().getString("firstName"), updateContactDto.getFirstName());
        Assert.assertEquals(response.jsonPath().getString("lastName"), updateContactDto.getLastName());
        Assert.assertEquals(response.jsonPath().getString("description"), updateContactDto.getDescription());
    }

}
