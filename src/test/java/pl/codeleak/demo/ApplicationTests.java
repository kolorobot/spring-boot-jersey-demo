package pl.codeleak.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.codeleak.demo.core.Customer;
import pl.codeleak.demo.core.CustomerRepository;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests {

    @Inject
    private CustomerRepository customerRepository;

    @Test
    public void contextLoads() {
        assertThat(customerRepository.count()).isEqualTo(3);
        assertThat(customerRepository.findOne(1L)).isEqualToComparingOnlyGivenFields(
                new Customer("Dave", "Matthews"), "firstname", "lastname"
        );
        assertThat(customerRepository.findOne(2L)).isEqualToComparingOnlyGivenFields(
                new Customer("Carter", "Beauford"), "firstname", "lastname"
        );
        assertThat(customerRepository.findOne(3L)).isEqualToComparingOnlyGivenFields(
                new Customer("Boyd", "Tinsley"), "firstname", "lastname"
        );


    }
}
