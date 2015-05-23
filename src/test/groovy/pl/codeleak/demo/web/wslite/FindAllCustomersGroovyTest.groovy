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
class FindAllCustomersGroovyTest {

    def client = new RESTClient("http://localhost:9000")

    @Before
    def void before() {
        client.authorization = new HTTPBasicAuthorization("demo", "123")
    }

    @Test
    def void findsAll() {
        def response = client.get(path: "/customer", accept: "application/json")
        println response.contentAsString
        assert200OK(response)

        def json = response.json

        assert json.number == 0
        assert json.totalPages == 1
        assert json.totalElements == 3
        assert json.content.size() == 3
        assert json.content.firstname.any() {
            firstname ->
                firstname.equals("Boyd")
                firstname.equals("Carter")
                firstname.equals("Dave")
        }
    }


    def assert200OK(response) {
        assert 200 == response.statusCode
        assert "OK" == response.statusMessage
    }
}
