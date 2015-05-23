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
class SaveCustomerGroovyTest {

    private RESTClient client

    @Before
    def void before() {
        client = new RESTClient("http://localhost:9000")
        client.authorization = new HTTPBasicAuthorization("demo", "123")
    }

    @Test
    public void savesCustomer() {
        def response = client.post(path: '/customer') {
            type "application/json"
            charset "UTF-8"
            json firstname: "John", lastname: "Doe", emailAddress: [value: "john@dummy.com"]
        }

        assert 201 == response.statusCode
        assert "Created" == response.statusMessage

        // location header contains the complete URL. We need to remove the base from it
        response = client.get(path: response.headers.location - client.url)

        def json = response.json
        assert json.id == 4
        assert json.firstname == 'John'
        assert json.lastname == 'Doe'
        assert json.emailAddress.value == "john@dummy.com"

    }

}
