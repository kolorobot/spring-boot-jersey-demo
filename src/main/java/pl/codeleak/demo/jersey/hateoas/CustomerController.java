package pl.codeleak.demo.jersey.hateoas;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.*;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Component;
import pl.codeleak.demo.core.Customer;
import pl.codeleak.demo.core.CustomerRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Profile("hateoas")
@Component
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@ExposesResourceFor(Customer.class)
public class CustomerController {

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private EntityLinks entityLinks;

    @GET
    public Response findAllResources() {
        Iterable<Customer> customers = customerRepository.findAll();

        CustomerResourceAssembler assembler = new CustomerResourceAssembler();
        List<CustomerResource> resources = assembler.toResources(customers);

        // wrap to add link
        Resources<CustomerResource> wrapped = new Resources<>(resources);
        wrapped.add(
                JaxRsLinkBuilder
                        .linkTo(CustomerController.class)
                        .withSelfRel()
        );

        return Response.ok(wrapped).build();
    }

    @GET
    @Path("{id}")
    public Response findOne(@PathParam("id") Long id) {
        Customer customer = customerRepository.findOne(id);

        Resource<Customer> resource = new Resource<>(customer);

        Link selfRel = entityLinks.linkToSingleResource(
                Customer.class, customer.getId()
        ).withSelfRel();

        resource.add(selfRel);

        return Response.ok(resource).build();
    }

    //
    // Other
    //

    //    @GET
    public Response findAllEntities() {
        Resources<Customer> resources = new Resources<>(
                customerRepository.findAll(),
                JaxRsLinkBuilder
                        .linkTo(CustomerController.class)
                        .withSelfRel()
        );

        return Response.ok(resources).build();
    }

    //    @GET
    public Response findAllPaged(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size,
            @QueryParam("sort") @DefaultValue("lastname") List<String> sort,
            @QueryParam("direction") @DefaultValue("asc") String direction) {

        Page<Customer> customers = customerRepository.findAll(
                new PageRequest(
                        page,
                        size,
                        Sort.Direction.fromString(direction),
                        sort.toArray(new String[0])
                )
        );

        PagedResources<Customer> resources = new PagedResources<>(
                customers.getContent(),
                new PagedResources.PageMetadata(
                        customers.getSize(),
                        customers.getNumber(),
                        customers.getTotalElements(),
                        customers.getTotalPages()));
        resources.add(JaxRsLinkBuilder.linkTo(CustomerController.class).withSelfRel());

        return Response.ok(resources).build();
    }
}
