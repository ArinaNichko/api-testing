package tests;

import model.Product;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.BuildPathHepler.*;
import static utils.JSONFileReader.JSOnFileGetMessage;
import static utils.JSONFileReader.readJSONFile;
import static utils.RestClient.*;

public class PositiveTest {


    @Test
    void getProduct() {

        Product response = getProductRequest().
                assertThat().
                statusCode(SUCCESS_CODE)
                .extract().response().as(Product.class);
        assertThat(response, equalTo(readJSONFile(propertiesReader("pathTemplateResponses") + propertiesReader("pathGetResponse"))));
    }

    @Test
    public void createProduct() {
        Product product = readJSONFile(propertiesReader("pathTemplateRequests") + propertiesReader("pathPostRequest"));
        Product responsePost = postProductRequest(product).
                assertThat().
                statusCode(SUCCESS_CODE)
                .extract().response().path(dataMessage);
        assertThat(responsePost, equalTo(JSOnFileGetMessage(propertiesReader("pathTemplateResponses") + propertiesReader("pathPostResponse"))));

    }


    @Test
    void updateProduct() {

        Product product = readJSONFile(propertiesReader("pathTemplateRequests") + propertiesReader("pathPutRequest"));
        String responsePut = putProductRequest(product).
                assertThat().
                statusCode(SUCCESS_CODE)
                .extract().response().path(dataMessage);
        assertThat(responsePut, equalTo(JSOnFileGetMessage(propertiesReader("pathTemplateResponses") + propertiesReader("pathPutResponse"))));
    }

    @Test
    void deleteProduct() {
        Product product = readJSONFile(propertiesReader("pathTemplateRequests") + propertiesReader("pathDeleteRequest"));
        String responseDelete = deleteProductRequest(product).
                assertThat().
                statusCode(SUCCESS_CODE)
                .extract().response().path(dataMessage);
        assertThat(responseDelete, equalTo(JSOnFileGetMessage(propertiesReader("pathTemplateResponses") + propertiesReader("pathDeleteResponse"))));
    }

}
