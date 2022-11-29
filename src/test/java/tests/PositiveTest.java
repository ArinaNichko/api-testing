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
import static utils.PropertiesHelper.getProperty;


public class PositiveTest extends BaseTest {

    @Test
    public void getProduct() {

        Response response1 = restClient.sendRequestWithParams(Method.GET, getProperty("pathForGetReadOne"), Map.of("id", "2"));

        assertThat(response1.statusCode(), Matchers.equalTo(OK.getValue()));
        assertThat(response1.as(Product.class), equalTo(readJSONFile(getProperty("pathTemplateResponses") + getProperty("pathGetResponse"))));
    }

    @Test
    public void createProduct() {

        Product product = readJSONFile(getProperty("pathTemplateRequests") + getProperty("pathPostRequest"));
        Response response1 = restClient.sendRequestWithBody(Method.POST, getProperty("pathForCreate"), product);

        assertThat(response1.statusCode(), Matchers.equalTo(CREATED.getValue()));
        assertThat(response1.asString(), equalTo(JSOnFileToString(getProperty("pathTemplateResponses") + getProperty("pathPostResponse"))));
    }

    @Test
    public void updateProduct() {

        Product product = readJSONFile(getProperty("pathTemplateRequests") + getProperty("pathPutRequest"));
        Response response1 = restClient.sendRequestWithBody(Method.PUT, getProperty("pathForUpdate"), product);

        assertThat(response1.statusCode(), Matchers.equalTo(OK.getValue()));
        assertThat(response1.asString(), equalTo(JSOnFileToString(getProperty("pathTemplateResponses") + getProperty("pathPutResponse"))));
    }

    @Test
    public void deleteProduct() {
        Product product = readJSONFile(getProperty("pathTemplateRequests") + getProperty("pathDeleteRequest"));
        Response response1 = restClient.sendRequestWithBody(Method.DELETE, getProperty("pathForDelete"), product);

        assertThat(response1.statusCode(), Matchers.equalTo(OK.getValue()));
        assertThat(response1.asString(), equalTo(JSOnFileToString(getProperty("pathTemplateResponses") + getProperty("pathDeleteResponse"))));
    }
}