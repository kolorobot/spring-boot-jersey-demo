package pl.codeleak.support;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class PageAssertion extends AbstractAssert<PageAssertion, Page> {
    protected PageAssertion(Page actual) {
        super(actual, PageAssertion.class);
    }

    public static PageAssertion assertThat(Page actual) {
        return new PageAssertion(actual);
    }


    public PageAssertion hasTotalElements(int value) {
        Assertions.assertThat(actual.getTotalElements()).isEqualTo(value);
        return this;
    }

    public PageAssertion hasTotalPages(int value) {
        Assertions.assertThat(actual.getTotalPages()).isEqualTo(value);
        return this;
    }

    public PageAssertion hasPageSize(int value) {
        Assertions.assertThat(actual.getSize()).isEqualTo(value);
        return this;
    }

    public PageAssertion hasPageNumber(int value) {
        Assertions.assertThat(actual.getNumber()).isEqualTo(value);
        return this;
    }

    public PageAssertion hasContentSize(int value) {
        Assertions.assertThat(actual.getContent()).hasSize(value);
        return this;
    }
}
