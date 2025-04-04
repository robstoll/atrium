package ch.tutteli.atrium.api.fluent.java;

import ch.tutteli.atrium.creating.AssertionContainer;
import ch.tutteli.atrium.creating.Expect;
import ch.tutteli.atrium.logic.AnyKt;
import ch.tutteli.atrium.logic.FeatureKt;
import ch.tutteli.atrium.logic.LogicKt;
import ch.tutteli.atrium.reporting.translating.Untranslatable;
import kotlin.jvm.functions.Function1;

import java.util.function.Function;

public abstract class AbstractAnyExpect<SubjectT, SelfT extends AbstractAnyExpect<SubjectT, SelfT>>
        extends AbstractExpect<SubjectT, SelfT> {

    public AbstractAnyExpect(Expect<SubjectT> expect) {
        super(expect);
    }

    public SelfT toEqual(SubjectT expected) {
        AssertionContainer<SubjectT> container = LogicKt.get_logic(getCoreExpect());
        return switchCoreExpect(container.append(AnyKt.toBe(container, expected)));
    }

    public <NewSubjectT> AnyExpect<NewSubjectT> feature(String description, Function<SubjectT, NewSubjectT> featureExtractor) {
        AssertionContainer<SubjectT> container = LogicKt.get_logic(getCoreExpect());
        var newCoreExpect = FeatureKt.<SubjectT, NewSubjectT>manualFeature(container, new Untranslatable(description), toKotlinFun(featureExtractor)).transform();
        return ExpectFactories.expectAny(newCoreExpect);
    }

    public <NewSubjectT, NewExpectT extends AbstractExpect<NewSubjectT, NewExpectT>> SelfT feature(
            String description,
            Function<SubjectT, NewSubjectT> featureExtractor,
            ExpectFactory<NewSubjectT, NewExpectT> factory,
            ExpectationCreator<NewSubjectT, NewExpectT> creator
    ) {
        AssertionContainer<SubjectT> container = LogicKt.get_logic(getCoreExpect());
        return switchCoreExpect(FeatureKt.<SubjectT, NewSubjectT>manualFeature(container, new Untranslatable(description), toKotlinFun(featureExtractor)).collectAndAppend(creator.toKotlin(factory)));
    }

    private static <T, R> Function1<T, R> toKotlinFun(Function<T, R> f) {
        return f::apply;
    }

    public <NewExpectT extends AbstractExpect<SubjectT, NewExpectT>> NewExpectT asInstanceOf(ExpectFactory<SubjectT, NewExpectT> factory) {
        return factory.create(getCoreExpect());
    }


    public SelfT toEqualNullIfNullGivenElse(ExpectationCreator<SubjectT, SelfT> assertionCreatorOrNull) {
        AssertionContainer<SubjectT> container = LogicKt.get_logic(getCoreExpect());
        return switchCoreExpect(container.append(AnyKt.toBeNullIfNullGivenElse(container, assertionCreatorOrNull.toKotlin(this::createSelf))));
    }
}

