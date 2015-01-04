package pl.codeleak.demo.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import pl.codeleak.demo.Application;
import pl.codeleak.support.Page;
import pl.codeleak.support.PageAssertion;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class SaveCustomerTest {

    private RestTemplate restTemplate = new TestRestTemplate("demo", "123");

@Test
public void savesCustomer() {
    // act
    URI uri = restTemplate.postForLocation("http://localhost:9000/customer",
            new Customer("John", "Doe"));
    // assert
    ResponseEntity<Customer> responseEntity =
            restTemplate.getForEntity(uri, Customer.class);

    Customer customer = responseEntity.getBody();

    assertThat(customer.getFirstname())
            .isEqualTo("John");
    assertThat(customer.getLastname())
            .isEqualTo("Doe");
}
}