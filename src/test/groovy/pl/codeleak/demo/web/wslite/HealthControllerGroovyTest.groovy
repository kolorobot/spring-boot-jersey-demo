package pl.codeleak.demo.web.wslite

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import pl.codeleak.support.ApplicationTest
import wslite.http.auth.HTTPBasicAuthorization
import wslite.rest.RESTClient

@RunWith(SpringJUnit4ClassRunner.class)
@ApplicationTest
class HealthControllerGroovyTest {


    def client = new RESTClient("http://localhost:9000")

    @Before
    def void before() {
        client.authorization = new HTTPBasicAuthorization("demo", "123")
    }

    @Test
    def void jerseyHealth() {

        def response = client.get(path: "/health", accept: "application/json")

        assert 200 == response.statusCode
        assert "OK" == response.statusMessage
        assert "Jersey: Up and Running!" == response.json.status
    }

    @Test
    def void springHealth() {

        def response = client.get(path: "/s/spring-health", accept: "application/json")
        assert 200 == response.statusCode
        assert "OK" == response.statusMessage
        assert "Spring MVC: Up and Running!" == response.json.status
    }

}
