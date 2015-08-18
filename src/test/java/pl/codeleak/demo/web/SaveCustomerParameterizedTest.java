package pl.codeleak.demo.web;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.web.client.RestTemplate;
import pl.codeleak.demo.core.Customer;
import pl.codeleak.support.ApplicationTest;

import java.net.URI;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
@ApplicationTest
public class SaveCustomerParameterizedTest {

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    private RestTemplate restTemplate = new TestRestTemplate("demo", "123");

    @Test
    @Parameters
    public void savesCustomer(String first, String last) {
        // act
        URI uri = restTemplate.postForLocation("http://localhost:9000/customer",
            new Customer(first, last));
        // assert
        ResponseEntity<Customer> responseEntity =
            restTemplate.getForEntity(uri, Customer.class);

        Customer customer = responseEntity.getBody();

        assertThat(customer.getFirstname())
            .isEqualTo(first);
        assertThat(customer.getLastname())
            .isEqualTo(last);
    }

    public Object[] parametersForSavesCustomer() {
        return $(
            $("John", "Doe"),
            $("John", "Smith"),
            $("Deborah", "Johnson"),
            $("Jan", "Kowalski")
        );
    }
}