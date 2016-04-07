package pl.codeleak.demo.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import pl.codeleak.demo.core.Customer;
import pl.codeleak.demo.core.CustomerRepository;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Profile("web")
@RestController
@RequestMapping("customer")
public class SpringCustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @RequestMapping("{id}")
    public Customer findOne(@PathVariable Long id) {
        return customerRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> save(Customer customer) {

        customer = customerRepository.save(customer);

        UriComponents uriComponents = ServletUriComponentsBuilder
            .fromCurrentRequest().path("{id}")
            .buildAndExpand(customer.getId());

        return ResponseEntity
            .created(uriComponents.toUri())
            .body(customer);
    }

    @DELETE
    @Path("{id}")
    public ResponseEntity<Void> delete(@PathParam("id") Long id) {
        customerRepository.delete(id);
        return ResponseEntity.accepted().build();
    }

}
