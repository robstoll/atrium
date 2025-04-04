package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.Expect;
import kotlin.Unit;

import java.util.function.Consumer;

public interface ExpectationCreator<SubjectT, SelfT extends AbstractExpect<SubjectT, SelfT>> extends Consumer<SelfT> {
    default kotlin.jvm.functions.Function1<Expect<SubjectT>, kotlin.Unit> toKotlin(ExpectFactory<SubjectT, SelfT> factory) {
        return (expect) -> {
            accept(factory.create(expect));
            return Unit.INSTANCE;
        };
    }
}
