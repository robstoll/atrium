package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.Expect;

public interface ExpectFactory<SubjectT, ExpectT extends AbstractExpect<SubjectT, ExpectT>> {
    ExpectT create(Expect<SubjectT> coreExpect);
}
