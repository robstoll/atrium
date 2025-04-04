package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.Expect;


public interface ExpectFactories {

    static <SubjectT> AnyExpect<SubjectT> expectAny(Expect<SubjectT> coreExpect) {
        return new AnyExpect<>(coreExpect);
    }

    static StringExpect expectString(Expect<String> coreExpect) {
        return new StringExpect(coreExpect);
    }

    static <SubjectT extends CharSequence> CharSequenceExpect<SubjectT> expectCharSequence(Expect<SubjectT> coreExpect) {
        return new CharSequenceExpect<>(coreExpect);
    }

    static <SubjectT extends Comparable<SubjectT>> ComparableExpect<SubjectT> expectComparable(Expect<SubjectT> coreExpect) {
        return new ComparableExpect<>(coreExpect);
    }

    static <SubjectT extends Block> BlockExpect<SubjectT> expectBlock(Expect<SubjectT> coreExpect) {
        return new BlockExpect<>(coreExpect);
    }
}
