package pl.codeleak.demo.hateoas;

import pl.codeleak.demo.core.Customer;
import pl.codeleak.demo.support.JaxRsResourceAssemblerSupport;

public class CustomerResourceAssembler
        extends JaxRsResourceAssemblerSupport<Customer, CustomerResource> {

    public CustomerResourceAssembler() {
        super(CustomerController.class, CustomerResource.class);
    }

    @Override
    public CustomerResource toResource(Customer entity) {
        CustomerResource resource = createResourceWithId(
                entity.getId(),
                entity
        );
        resource.setFullName(
                String.join(", ", entity.getLastname(), entity.getFirstname())
        );
        resource.setEmail(entity.getEmailAddress().getValue());
        return resource;
    }
}
