package pl.codeleak.demo.web.httpbuilder

import groovyx.net.http.RESTClient
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import pl.codeleak.support.ApplicationTest

@RunWith(SpringJUnit4ClassRunner.class)
@ApplicationTest
class HealthControllerGroovyTest {


    def client = new RESTClient("http://localhost:9000")

    @Before
    def void before() {
        client.auth.basic("demo", "123")
    }

    @Test
    def void jerseyHealth() {

        def response = client.get(path: "/health")

        assert 200 == response.statusLine.statusCode
        assert "OK" == response.statusLine.reasonPhrase
        assert "Jersey: Up and Running!" == response.responseData.status
    }

    @Test
    def void springHealth() {

        def response = client.get(path: "/s/spring-health")
        assert 200 == response.statusLine.statusCode
        assert "OK" == response.statusLine.reasonPhrase
        assert "Spring MVC: Up and Running!" == response.responseData.status
    }

}
