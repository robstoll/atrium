package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.Expect;
import ch.tutteli.atrium.logic.creating.RootExpectBuilder;


public interface Expects {

    static <SubjectT> AbstractAnyExpect<SubjectT, ?> expect(SubjectT subject) {
        return ExpectFactories.expectAny(createExpect(subject));
    }

    static AbstractStringExpect<?> expect(String subject) {
        return ExpectFactories.expectString(createExpect(subject));
    }

    static <SubjectT extends CharSequence> AbstractCharSequenceExpect<SubjectT, ?> expect(SubjectT subject) {
        return ExpectFactories.expectCharSequence(createExpect(subject));
    }

    static <SubjectT extends Comparable<SubjectT>> AbstractComparableExpect<SubjectT, ?> expect(SubjectT subject) {
        return ExpectFactories.expectComparable(createExpect(subject));
    }

    static <SubjectT extends Block> AbstractBlockExpect<SubjectT, ?> expect(SubjectT subject) {
        return ExpectFactories.expectBlock(createExpect(subject));
    }

    static <SubjectT> Expect<SubjectT> createExpect(SubjectT subject) {
        // Try to load an ExpectBuilder via ServiceLoader or the like so that one can customise
        return RootExpectBuilder.Companion.forSubject(subject)
            .withVerb("I expected subject")
            .withoutOptions()
            .build();
    }
}
