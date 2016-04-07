package pl.codeleak.demo.jersey.support;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.util.Assert;

public abstract class JaxRsResourceAssemblerSupport<T, D extends ResourceSupport>
        extends ResourceAssemblerSupport<T, D> {

    private final Class<?> controllerClass;

    public JaxRsResourceAssemblerSupport(
            Class<?> controllerClass, Class<D> resourceType) {

        super(controllerClass, resourceType);
        this.controllerClass = controllerClass;
    }

    @Override
    protected D createResourceWithId(Object id, T entity, Object... parameters) {
        Assert.notNull(entity);
        Assert.notNull(id);

        D instance = instantiateResource(entity);

        instance.add(
                JaxRsLinkBuilder.linkTo(controllerClass, parameters)
                        .slash(id)
                        .withSelfRel());
        return instance;
    }
}
