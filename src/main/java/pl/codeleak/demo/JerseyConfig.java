package pl.codeleak.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import pl.codeleak.demo.customer.CustomerController;
import pl.codeleak.demo.health.HealthController;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(HealthController.class);
        register(CustomerController.class);
    }
}

