package pl.codeleak.demo.jersey.hateoas;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Profile("hateoas")
@Component
@ApplicationPath("/")
// @EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableEntityLinks
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(CustomerController.class);
    }
}

