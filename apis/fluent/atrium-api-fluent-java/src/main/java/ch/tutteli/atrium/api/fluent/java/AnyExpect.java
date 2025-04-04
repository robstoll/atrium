package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.Expect;

public class AnyExpect<SubjectT>
        extends AbstractAnyExpect<SubjectT, AnyExpect<SubjectT>> {

    public AnyExpect(Expect<SubjectT> expect) {
        super(expect);
    }

    @Override
    public AnyExpect<SubjectT> createSelf(Expect<SubjectT> expect) {
        return new AnyExpect<>(expect);
    }

    static <SubjectT> AnyExpect<SubjectT> expect(Expect<SubjectT> coreExpect) {
        return new AnyExpect<>(coreExpect);
    }
}
