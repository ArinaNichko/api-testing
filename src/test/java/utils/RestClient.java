package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.Product;

import static io.restassured.RestAssured.given;
import static utils.BuildPathHelper.propertiesReader;

public class RestClient {
    public static String baseURL = propertiesReader("templateRequest");

    public static ValidatableResponse getProductRequest() {
        return RestAssured.given().
                queryParam("id", propertiesReader("id"))
                .baseUri(baseURL)
                .when()
                .get(propertiesReader("pathForGetReadOne"))
                .then();
    }

    public static ValidatableResponse postProductRequest(Product product) {
        return given()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post(propertiesReader("pathForUpdate"))
                .then();
    }

    public static ValidatableResponse putProductRequest(Product product) {
        return RestAssured.given()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .put(propertiesReader("pathForCreate"))
                .then();
    }

    public static ValidatableResponse deleteProductRequest(Product product) {
        return (ValidatableResponse) RestAssured.given()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .delete(propertiesReader("pathForDelete"));
    }
}
