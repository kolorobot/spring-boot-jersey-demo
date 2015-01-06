package pl.codeleak.demo.web;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.codeleak.demo.web.CustomerController;
import pl.codeleak.demo.web.HealthController;

import javax.ws.rs.ApplicationPath;

@Profile("web")
@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(HealthController.class);
        register(CustomerController.class);
    }
}

