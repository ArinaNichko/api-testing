package tests;

import configurations.BaseTest;
import io.restassured.response.Response;
import models.Product;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Map;

import static enums.ResponseCodes.CREATED;
import static enums.ResponseCodes.OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.JSONFileReader.JSOnFileToString;
import static utils.JSONFileReader.readJSONFile;
import static utils.PropertiesHelper.getProperty;
import static clients.RestClient.postProductRequest;
import static clients.RestClient.putProductRequest;
import static clients.RestClient.deleteProductRequest;

public class PositiveTest extends BaseTest {

    @Test
    public void getProduct() {

        Response response1 = restClient.performGetRequest(getProperty("pathForGetReadOne"), Map.of("id", "2"));

        assertThat(response1.statusCode(), Matchers.equalTo(OK.getValue()));
        assertThat(response1.as(Product.class), equalTo(readJSONFile(getProperty("pathTemplateResponses") + getProperty("pathGetResponse"))));
    }


    @Test
    public void createProduct() {
        Product product = readJSONFile(getProperty("pathTemplateRequests") + getProperty("pathPostRequest"));

        String responsePost = postProductRequest(product)
                .assertThat()
                .statusCode(CREATED.getValue())
                .extract().response().asString();
        assertThat(responsePost, equalTo(JSOnFileToString(getProperty("pathTemplateResponses") + getProperty("pathPostResponse"))));
    }


    @Test
    public void updateProduct() {

        Product product = readJSONFile(getProperty("pathTemplateRequests") + getProperty("pathPutRequest"));
        String responsePut = putProductRequest(product)
                .assertThat()
                .statusCode(OK.getValue())
                .extract().response().asString();
        assertThat(responsePut, equalTo(JSOnFileToString(getProperty("pathTemplateResponses") + getProperty("pathPutResponse"))));
    }

    @Test
    public void deleteProduct() {
        Product product = readJSONFile(getProperty("pathTemplateRequests") + getProperty("pathDeleteRequest"));
        String responseDelete = deleteProductRequest(product)
                .assertThat()
                .statusCode(OK.getValue())
                .extract().response().asString();
        assertThat(responseDelete, equalTo(JSOnFileToString(getProperty("pathTemplateResponses") + getProperty("pathDeleteResponse"))));
    }
}