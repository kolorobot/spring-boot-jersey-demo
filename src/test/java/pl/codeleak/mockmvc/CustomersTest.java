package pl.codeleak.mockmvc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.WebApplicationContext;
import pl.codeleak.demo.Application;

import java.io.IOException;
import java.io.InputStreamReader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("web")
public class CustomersTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Test
    public void getsCustomer() throws Exception {

        String json = "{\"emailAddress\":{\"value\":\"dave@dmband.com\"}, \"firstname\":\"Dave\", \"id\":1, \"lastname\":\"Matthews\"}";

        mockMvc.perform(get("/customer/1"))
               .andDo(print())
               .andExpect(status().is2xxSuccessful())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(content().json(json));
    }

    @Test
    public void returnsAllPages() throws Exception {
        String json = readJSONFromFile("all-customers.json");
        mockMvc.perform(get("/customer"))
               .andDo(print())
               .andExpect(status().is2xxSuccessful())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(content().json(json));
    }

    @Test
    @Ignore // failing because of strict validation
    public void returnsAllPagesStrict() throws Exception {
        String json = readJSONFromFile("all-customers.json");
        mockMvc.perform(get("/customer"))
               .andDo(print())
               .andExpect(status().is2xxSuccessful())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(content().json(json, true));
    }

    private String readJSONFromFile(String filename) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(filename);
        if (!classPathResource.exists()) {
            Assert.fail("No resource found " + filename);
        }
        return FileCopyUtils.copyToString(new InputStreamReader(classPathResource.getInputStream()));
    }
}