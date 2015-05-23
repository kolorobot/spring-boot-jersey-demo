package pl.codeleak.demo.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import pl.codeleak.demo.core.Health;
import pl.codeleak.support.ApplicationTest;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ApplicationTest
public class HealthControllerTest {

    private RestTemplate restTemplate = new TestRestTemplate("demo", "123");

    @Test
    public void jerseyHealth() {
        ResponseEntity<Health> entity =
                restTemplate.getForEntity("http://localhost:9000/health", Health.class);

        assertThat(entity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(entity.getBody().getStatus()).isEqualTo("Jersey: Up and Running!");
    }

    @Test
    public void springHealth() {
        ResponseEntity<Health> entity =
                restTemplate.getForEntity("http://localhost:9000/s/spring-health", Health.class);

        assertThat(entity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(entity.getBody().getStatus()).isEqualTo("Spring MVC: Up and Running!");
    }
}