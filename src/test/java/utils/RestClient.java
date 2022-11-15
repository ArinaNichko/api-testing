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
        return   given()
                .queryParam("id", propertiesReader("id"))
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
                .post(propertiesReader("pathForCreate"))
                .then();
    }

    public static ValidatableResponse putProductRequest(Product product) {
        return RestAssured.given()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .put(propertiesReader("pathForUpdate"))
                .then();
    }

    public static ValidatableResponse deleteProductRequest(Product product) {
        return  RestAssured.given()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .delete(propertiesReader("pathForDelete"))
                .then();
    }
}
