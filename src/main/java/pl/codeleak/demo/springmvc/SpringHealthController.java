package pl.codeleak.demo.springmvc;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.codeleak.demo.core.Health;

@Profile("web")
@RestController
public class SpringHealthController {

    @RequestMapping(value = "/spring-health", produces = "application/json")
    public Health springMvc() {
        return new Health("Spring MVC: Up and Running!");
    }
}
