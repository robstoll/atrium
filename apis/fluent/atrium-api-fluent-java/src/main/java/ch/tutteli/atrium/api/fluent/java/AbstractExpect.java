package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.Expect;

public abstract class AbstractExpect<SubjectT, SelfT extends AbstractExpect<SubjectT, SelfT>> {

    private Expect<SubjectT> coreExpect;

    protected Expect<SubjectT> getCoreExpect(){
        return coreExpect;
    }

    public AbstractExpect(Expect<SubjectT> expect) {
        this.coreExpect = expect;
    }

    public abstract  SelfT createSelf(Expect<SubjectT> coreExpect);

    @SuppressWarnings("unchecked")
    final protected SelfT self() {
        return (SelfT) this;
    }

    final protected SelfT switchCoreExpect(Expect<SubjectT> newExpect) {
        coreExpect = newExpect;
        return self();
    }
}
