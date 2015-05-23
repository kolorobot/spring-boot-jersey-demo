package pl.codeleak.demo.web.httpbuilder

import groovyx.net.http.RESTClient
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import pl.codeleak.support.ApplicationTest

@RunWith(SpringJUnit4ClassRunner.class)
@ApplicationTest
class FindAllCustomersGroovyTest {

    RESTClient client = new RESTClient("http://localhost:9000")

    @Before
    def void before() {
        client.auth.basic("demo", "123")
    }


    @Test
    def void findsAll() {
        def response = client.get(path: "/customer")

        def json = response.responseData

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

}
