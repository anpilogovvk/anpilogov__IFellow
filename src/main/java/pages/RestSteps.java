package pages;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestSteps {

    public Response getApi(String baseUri, int statusCode) {
        Response response = given()
                .baseUri(baseUri)
                .get()
                .then()
                .log().all()
                .assertThat()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    public Response postApi(String baseUri, String postUri, String body, int statusCode) {

        Response response = given()
                .header("Content-type", "application/json")
                .header("charset", "utf-8")
                .baseUri(baseUri)
                .body(body)
                .when()
                .post(postUri)
                .then()
                .statusCode(statusCode)
                .log().body()
                .extract().response();
        return response;
    }

}