package api.tests;

import api.enums.EndPoint;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Random;

public class ApiBase {
    final String API_KEY = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6InRlc3RAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MjEwNjk3ODI5Nn0.GM1wsoRV2QoAsD6wKmIk7N49DDpuCejK4BC9H9YItJvesH5vft8HO2uqTPnGQJwJ5oXKS2OILqP1yoanMnIMkA";
    final String BASE_URI = "http://phonebook.telran-edu.de:8080";

    protected Faker faker = new Faker();

    RequestSpecification spec =  new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .addHeader("Access-Token", API_KEY)
            .build();

    public Response doPostRequest(EndPoint endPoint, int statusCode, Object body) {
        Response response = RestAssured.given()
                .spec(spec)
                .body(body)
                .when()
                .log().all()
                .post(endPoint.getValue())
                .then()
                .log().all()
                .extract().response();
        response.then().assertThat().statusCode(statusCode);
        return response;
    }

    public Response doDeleteRequest(EndPoint endPoint, int statusCode, int id) {
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam("id", id)
                .log().all()
                .delete(endPoint.getValue())
                .then()
                .log().all()
                .extract().response();
        response.then().assertThat().statusCode(statusCode);
        return response;
    }

    public Response doGetRequest(EndPoint endPoint, int statusCode) {
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .log().all()
                .get(endPoint.getValue())
                .then()
                .log().all()
                .extract().response();
        response.then().assertThat().statusCode(statusCode);
        return response;
    }

    public Response doGetRequestWithParam(EndPoint endPoint, int statusCode, int id) {
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam("id", id)
                .log().all()
                .get(endPoint.getValue())
                .then()
                .log().all()
                .extract().response();
        response.then().assertThat().statusCode(statusCode);
        return response;
    }

    public Response doPutRequest(EndPoint endPoint, int statusCode, Object body) {
        Response response = RestAssured.given()
                .spec(spec)
                .body(body)
                .when()
                .log().all()
                .put(endPoint.getValue())
                .then()
                .log().all()
                .extract().response();
        response.then().assertThat().statusCode(statusCode);
        return response;
    }

    public int getWrongId() {
        Random rnd = new Random();
        int wrongId = 100000 + rnd.nextInt(900000);
        return wrongId;
    }

}
