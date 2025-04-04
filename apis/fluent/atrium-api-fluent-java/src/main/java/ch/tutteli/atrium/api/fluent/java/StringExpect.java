package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.Expect;

public class StringExpect extends AbstractStringExpect<StringExpect> {

    public StringExpect(Expect<String> expect) {
        super(expect);
    }

    @Override
    public StringExpect createSelf(Expect<String> coreExpect) {
        return new StringExpect(coreExpect);
    }
}
