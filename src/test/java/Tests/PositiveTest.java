package Tests;
import models.Product;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static config.Path.*;
import static controller.ProductController.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PositiveTest {


    @Test
    public void getProduct() throws IOException, ParseException {
     Product response = given().
            queryParam("id", 2).
        when().
            get(endpoint(get)).
        then().
            assertThat().
                statusCode(SUCCESS_CODE)
               .extract().response().as(Product.class);

    assertThat(response , equalTo(readJSONFile(pathResponse(pathRequest(pathGetResponse))))); }


        @Test
        public void createProduct() throws IOException, ParseException {
        String body = JSOnFileToString(pathRequest(pathPost));
        String responsePost = given().
                body(body).
                when().
                post(endpoint(create)).
                then().
                assertThat().
                    statusCode(SUCCESS_CODE)
                    .extract().response().path(dataMessage);
        assertThat(responsePost, equalTo(JSOnFileGetMessage(pathResponse(pathPostResponse))));

        }

    @Test
    public void updateProduct() throws IOException, ParseException {
        String body = JSOnFileToString(pathRequest(pathPut));
        String  responsePut = given().
                body(body).
                when().
                put(endpoint(update)).
                then().
                assertThat().
                statusCode(SUCCESS_CODE)
                .extract().response().path(dataMessage);
            assertThat(responsePut, equalTo(JSOnFileGetMessage(pathResponse(pathPutResponse))));
    }

    @Test
    public void deleteProduct() throws IOException, ParseException {
        String body = JSOnFileToString(pathRequest(pathDelete));
        String responseDelete = given().
                body(body).
                when().
                delete(endpoint(delete)).
                then().
                assertThat().
                    statusCode(SUCCESS_CODE)
                    .extract().response().path(dataMessage);
            assertThat(responseDelete, equalTo(JSOnFileGetMessage(pathResponse(pathDeleteResponse))));
    }

}
