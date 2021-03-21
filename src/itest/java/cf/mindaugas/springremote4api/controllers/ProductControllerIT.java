package cf.mindaugas.springremote4api.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rt;

    @Test
    public void getAllProducts_whenInvoked_returnsAllProductsSuccessfully(){
        // given
        String url = "/api/v1/products";

        // when
        ResponseEntity<String> response = rt.getForEntity(url, String.class);

        // then
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK)); // ... assertions on response status
        assertThat(response.getHeaders().getContentType(), equalTo(MediaType.APPLICATION_JSON)); // ... on response type
        String expectedData = "[" +
                "{\"id\":1,\"title\":\"Snowboard\",\"count\":100,\"price\":9.99,\"rating\":3.75}," +
                "{\"id\":2,\"title\":\"Kittens\",\"count\":999,\"price\":19.99,\"rating\":4.85}," +
                "{\"id\":3,\"title\":\"Small dogs\",\"count\":999,\"price\":19.99,\"rating\":4.85}," +
                "{\"id\":4,\"title\":\"Tesla P100\",\"count\":999,\"price\":19.99,\"rating\":4.85}" +
                "]";
        assertThat(response.getBody(), equalTo(expectedData)); // ... on response body value
    }

    @Test
    public void createProduct_whenSubmittedWithValidProduct_productSavedSuccessfully(){
        // given
        String url = "/api/v1/products";

        HttpHeaders headers = new HttpHeaders(){{
            set("Content-Type", MediaType.APPLICATION_JSON.toString());
        }};

        String product = "{\"title\":\"Pobieda2\",\"count\":666,\"price\":666.99,\"rating\":3.75}";

        // when
        HttpEntity<String> requestEntity = new HttpEntity(product, headers);
        ResponseEntity<String> response = rt.postForEntity(url, requestEntity, String.class);

        // then
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED)); // ... assertions on response status
        assertThat(response.getHeaders().getContentType(), equalTo(null)); // ... on response type
        // ... body is empty

        ResponseEntity<String> productRetrievedById = rt.getForEntity(url + "/5", String.class);
        String expectedResult = "{\"id\":5,\"title\":\"Pobieda2\",\"count\":666,\"price\":666.99,\"rating\":3.75}";
        assertThat(productRetrievedById.getBody(), equalTo(expectedResult));
    }
}
