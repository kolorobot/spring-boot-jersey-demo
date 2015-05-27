package pl.codeleak.demo.web.httpbuilder

import groovyx.net.http.RESTClient
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import pl.codeleak.support.ApplicationTest

@RunWith(SpringJUnit4ClassRunner.class)
@ApplicationTest
class SaveCustomerGroovyTest {

    private RESTClient client

    @Before
    def void before() {
        client = new RESTClient("http://localhost:9000")
        client.auth.basic("demo", "123")
    }

    @Test
    public void savesCustomer() {
        def response = client.post(
                path: '/customer',
                requestContentType: "application/json",
                body: [
                        firstname: "John",
                        lastname: "Doe",
                        emailAddress:
                                [value: "john@dummy.com"]
                ]
        )

        assert 201 == response.status
        assert response.headers.location
    }

}
