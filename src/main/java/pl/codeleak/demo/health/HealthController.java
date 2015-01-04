package pl.codeleak.demo.health;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@RestController // Spring MVC
@Path("/health")
public class HealthController {

    @GET
    @Produces({"application/json"})
    public Health jersey() {
        return new Health("Jersey: Up and Running!");
    }

    @RequestMapping(value = "/spring-health", produces = "application/json") // Spring MVC
    public Health springMvc() {
        return new Health("Spring MVC: Up and Running!");
    }
}
