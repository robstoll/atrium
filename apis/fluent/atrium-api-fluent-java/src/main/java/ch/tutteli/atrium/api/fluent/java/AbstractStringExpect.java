package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.Expect;

public abstract class AbstractStringExpect<SelfT extends AbstractStringExpect<SelfT>>
    extends AbstractCharSequenceExpect<String, SelfT> {
    public AbstractStringExpect(Expect<String> expect) {
        super(expect);
    }

}
