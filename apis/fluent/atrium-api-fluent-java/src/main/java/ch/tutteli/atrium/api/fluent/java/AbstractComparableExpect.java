package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.AssertionContainer;
import ch.tutteli.atrium.creating.Expect;
import ch.tutteli.atrium.logic.ComparableKt;
import ch.tutteli.atrium.logic.LogicKt;

public abstract class AbstractComparableExpect<SubjectT extends Comparable<SubjectT>, SelfT extends AbstractComparableExpect<SubjectT, SelfT>>
    extends AbstractAnyExpect<SubjectT, SelfT> {

    public AbstractComparableExpect(Expect<SubjectT> expect) {
        super(expect);
    }

    public SelfT toBeLessThan(SubjectT expected) {
        AssertionContainer<SubjectT> container = LogicKt.get_logic(getCoreExpect());
        return switchCoreExpect(container.append(ComparableKt.isLessThan(container, expected)));
    }

}
