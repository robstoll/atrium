package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.Expect;

public class ComparableExpect<SubjectT extends Comparable<SubjectT>>
    extends AbstractComparableExpect<SubjectT, ComparableExpect<SubjectT>> {

    public ComparableExpect(Expect<SubjectT> expect) {
        super(expect);
    }

    @Override
    public ComparableExpect<SubjectT> createSelf(Expect<SubjectT> coreExpect) {
        return new ComparableExpect<>(coreExpect);
    }
}
