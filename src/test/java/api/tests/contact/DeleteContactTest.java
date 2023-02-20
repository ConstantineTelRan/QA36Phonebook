package api.tests.contact;

import api.enums.EndPoint;
import api.enums.ErrorMessage;
import api.model.AddContactDto;
import api.tests.ApiBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTest extends ApiBase {
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

    @Test(groups = {"positive"})
    public void deleteContactTest() {
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);
    }

    @Test
    public void deleteContactWithWrongId() {
        wrongId = getWrongId();
        response = doDeleteRequest(EndPoint.DELETE_CONTACT, 500, wrongId);

        Assert.assertEquals(response.jsonPath().getString("message"),
                ErrorMessage.CONTACT_ERROR_MESSAGE.getValue());
    }

}
