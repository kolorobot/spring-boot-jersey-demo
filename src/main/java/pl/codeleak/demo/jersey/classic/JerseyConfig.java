package pl.codeleak.demo.jersey.classic;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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

