package pl.codeleak.demo.web.httpbuilder;

import org.junit.runner.RunWith;

@RunWith(org.junit.runners.Suite.class)
@org.junit.runners.Suite.SuiteClasses(value = {
        SaveCustomerGroovyTest.class,
        FindAllCustomersGroovyTest.class,
        HealthControllerGroovyTest.class})
public class GroovyWithHttpBuilderSuite {

}
