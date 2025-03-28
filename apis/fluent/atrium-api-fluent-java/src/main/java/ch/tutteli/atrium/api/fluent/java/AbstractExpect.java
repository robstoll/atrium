package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.Expect;

public abstract class AbstractExpect<SubjectT, SelfT extends AbstractExpect<SubjectT, SelfT>> {
    protected Expect<SubjectT> expect;

    public AbstractExpect(Expect<SubjectT> expect) {
        this.expect = expect;
    }

    protected SelfT switchExpect(Expect<SubjectT> newExpect) {
        expect = newExpect;
        @SuppressWarnings("unchecked") SelfT self = (SelfT) this;
        return self;
    }
}
