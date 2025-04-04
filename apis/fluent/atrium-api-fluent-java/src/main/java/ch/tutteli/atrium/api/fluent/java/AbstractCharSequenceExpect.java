package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.AssertionContainer;
import ch.tutteli.atrium.creating.Expect;
import ch.tutteli.atrium.logic.CharSequenceKt;
import ch.tutteli.atrium.logic.ComparableKt;
import ch.tutteli.atrium.logic.LogicKt;

public abstract class AbstractCharSequenceExpect<SubjectT extends CharSequence, SelfT extends AbstractCharSequenceExpect<SubjectT, SelfT>>
    extends AbstractAnyExpect<SubjectT, SelfT> {
    public AbstractCharSequenceExpect(Expect<SubjectT> expect) {
        super(expect);
    }

    public SelfT toStartWith(CharSequence expectedStart) {
        AssertionContainer<SubjectT> container = LogicKt.get_logic(getCoreExpect());
        return switchCoreExpect(container.append(CharSequenceKt.startsWith(container, expectedStart)));
    }

}
