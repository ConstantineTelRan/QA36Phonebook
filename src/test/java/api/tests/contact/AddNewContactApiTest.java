package api.tests.contact;

import api.enums.EndPoint;
import api.model.AddContactDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNewContactApiTest extends ApiBase {
    AddContactDto addContactDto;
    Response response;

    @Test
    public void addNewContactApiTest() {
        addContactDto = new AddContactDto();
        addContactDto.setFirstName(faker.name().firstName());
        addContactDto.setLastName(faker.name().lastName());
        addContactDto.setDescription(faker.lorem().sentence(4));

        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, addContactDto);

        Assert.assertEquals(response.jsonPath().getString("firstName"), addContactDto.getFirstName());
        Assert.assertEquals(response.jsonPath().getString("lastName"), addContactDto.getLastName());
        Assert.assertEquals(response.jsonPath().getString("description"), addContactDto.getDescription());

    }

    @Test
    public void addNewContactApiWithMapTest() {
        addContactDto = new AddContactDto();
        addContactDto.setFirstName(faker.name().firstName());
        addContactDto.setLastName(faker.name().lastName());
        addContactDto.setDescription(faker.lorem().sentence(4));

        Map<String, Object> expectedValue = new HashMap<>();
        expectedValue.put("firstName", addContactDto.getFirstName());
        expectedValue.put("lastName", addContactDto.getLastName());
        expectedValue.put("description", addContactDto.getDescription());

        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, addContactDto);

        Map<String, Object> actualValue = response.as(new TypeRef<Map<String, Object>>() {});
        actualValue.remove("id");

        Assert.assertEquals(actualValue, expectedValue);

    }

}
