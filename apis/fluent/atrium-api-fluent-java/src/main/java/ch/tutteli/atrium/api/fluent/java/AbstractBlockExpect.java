package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.AssertionContainer;
import ch.tutteli.atrium.creating.Expect;
import ch.tutteli.atrium.logic.Fun0Kt;
import ch.tutteli.atrium.logic.LogicKt;
import ch.tutteli.atrium.logic.UtilsKt;
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder;
import kotlin.Unit;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;

public abstract class AbstractBlockExpect<SubjectT extends Block, SelfT extends AbstractBlockExpect<SubjectT, SelfT>> extends AbstractAnyExpect<SubjectT, SelfT> {

    public AbstractBlockExpect(Expect<SubjectT> expect) {
        super(expect);
    }

    public <T extends Throwable> AnyExpect<T> toThrow(Class<T> expectedType) {
        return ExpectFactories.expectAny(toThrowInternal(expectedType).transform());
    }

    public <T extends Throwable> AnyExpect<T> toThrow(Class<T> expectedType, ExpectationCreator<T, AnyExpect<T>> expectationCreator) {
        return ExpectFactories.expectAny(toThrowInternal(expectedType).transformAndAppend(expectationCreator.toKotlin(ExpectFactories::expectAny)));
    }

    private <T extends Throwable> SubjectChangerBuilder.ExecutionStep<?, T> toThrowInternal(Class<T> expectedType) {
        AssertionContainer<Function0<Unit>> retypedContainer = getFunction0Container();
        return Fun0Kt.<T>toThrow(retypedContainer, JvmClassMappingKt.getKotlinClass(expectedType));
    }

    public <T extends Throwable> AnyExpect<T> notToThrow(Class<T> c) {
        return null;
    }

    private AssertionContainer<Function0<Unit>> getFunction0Container() {
        AssertionContainer<SubjectT> container = LogicKt.get_logic(getCoreExpect());
        Expect<Function0<Unit>> retypedCoreExpect = UtilsKt.getChangeSubject(container).unreported(subject -> () -> {
            subject.action();
            return Unit.INSTANCE;
        });
        return LogicKt.get_logic(retypedCoreExpect);
    }
}
