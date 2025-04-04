package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.Expect;

public class CharSequenceExpect<SubjectT extends CharSequence>
    extends AbstractCharSequenceExpect<SubjectT, CharSequenceExpect<SubjectT>> {

    public CharSequenceExpect(Expect<SubjectT> expect) {
        super(expect);
    }

    @Override
    public CharSequenceExpect<SubjectT> createSelf(Expect<SubjectT> coreExpect) {
        return new CharSequenceExpect<>(coreExpect);
    }
}
