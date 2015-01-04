package pl.codeleak.demo.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Component
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {

    @Inject
    private CustomerRepository customerRepository;

    @Context
    private UriInfo uriInfo;

    @GET
    public Page<Customer> findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size,
            @QueryParam("sort") @DefaultValue("lastname") List<String> sort,
            @QueryParam("direction") @DefaultValue("asc") String direction) {

        return customerRepository.findAll(
                new PageRequest(
                        page,
                        size,
                        Sort.Direction.fromString(direction),
                        sort.toArray(new String[0])
                )
        );
    }

    @GET
    @Path("{id}")
    public Customer findOne(@PathParam("id") Long id) {
        return customerRepository.findOne(id);
    }

    @POST
    public Response save(Customer customer) {

        customer = customerRepository.save(customer);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .resolveTemplate("id", customer.getId())
                .build();

        return Response.created(location).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        customerRepository.delete(id);
        return Response.accepted().build();
    }
}
