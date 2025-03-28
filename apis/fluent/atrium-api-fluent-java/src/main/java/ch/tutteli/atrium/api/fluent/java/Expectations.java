package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.Expect;
import ch.tutteli.atrium.logic.creating.RootExpectBuilder;

public class Expectations {

    public static <SubjectT extends Comparable<SubjectT>> ComparableExpect<SubjectT> expect(SubjectT subjectT) {
        return new ComparableExpect<>(createExpect(subjectT));
    }

    private static <SubjectT> Expect<SubjectT> createExpect(SubjectT subject) {
        return RootExpectBuilder.Companion.forSubject(subject)
            .withVerb("I expected subject")
            .withoutOptions()
            .build();
    }
}
