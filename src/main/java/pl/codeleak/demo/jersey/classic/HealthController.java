package pl.codeleak.demo.jersey.classic;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.codeleak.demo.core.Health;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Profile("web")
@Component
@Path("/health")
public class HealthController {

    @GET
    @Produces({"application/json"})
    public Health jersey() {
        return new Health("Jersey: Up and Running!");
    }
}
