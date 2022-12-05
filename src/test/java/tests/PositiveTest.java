package tests;

import configurations.BaseTest;
import io.restassured.http.Method;
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


public class PositiveTest extends BaseTest {

    @Test
    public void getProduct() {

        Response response1 = restClient.sendRequestWithParams(Method.GET, getProductPath, Map.of("id", "2"));

        assertThat(response1.statusCode(), Matchers.equalTo(OK.getValue()));
        assertThat(response1.as(Product.class), equalTo(readJSONFile(responsesTemplatePath + getProductResponse)));
    }

    @Test
    public void createProduct() {

        Product product = readJSONFile(requestsTemplatePath + createProductRequest);
        Response response1 = restClient.sendRequestWithBody(Method.POST, createProductPath, product);

        assertThat(response1.statusCode(), Matchers.equalTo(CREATED.getValue()));
        assertThat(response1.asString(), equalTo(JSOnFileToString(responsesTemplatePath + createProductResponse)));
    }

    @Test
    public void updateProduct() {

        Product product = readJSONFile(requestsTemplatePath + updateProductRequest);
        Response response1 = restClient.sendRequestWithBody(Method.PUT, updateProductPath, product);

        assertThat(response1.statusCode(), Matchers.equalTo(OK.getValue()));
        assertThat(response1.asString(), equalTo(JSOnFileToString(responsesTemplatePath + updateProductResponse)));
    }

    @Test
    public void deleteProduct() {
        Product product = readJSONFile(requestsTemplatePath + deleteProductRequest);
        Response response1 = restClient.sendRequestWithBody(Method.DELETE, deleteProductPath, product);

        assertThat(response1.statusCode(), Matchers.equalTo(OK.getValue()));
        assertThat(response1.asString(), equalTo(JSOnFileToString(responsesTemplatePath + deleteProductResponse)));
    }
}