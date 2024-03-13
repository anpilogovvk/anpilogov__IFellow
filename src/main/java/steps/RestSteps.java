package steps;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestSteps {

    public Response getApi(String baseUri, int statusCode) {
        return given()
                .baseUri(baseUri)
                .get()
                .then()
                .log().all()
                .assertThat()
                .statusCode(statusCode)
                .extract().response();
    }

    public Response postApi(String baseUri, String postUri, String body, int statusCode) {

        return given()
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
    }
}