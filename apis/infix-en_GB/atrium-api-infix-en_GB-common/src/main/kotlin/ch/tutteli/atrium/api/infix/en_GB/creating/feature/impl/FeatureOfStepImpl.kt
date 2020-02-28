package ch.tutteli.atrium.api.infix.en_GB.creating.feature.impl

import ch.tutteli.atrium.api.infix.en_GB.creating.feature.FeatureOfStep
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import kotlin.reflect.KCallable

internal class FeatureOfStepImpl<T, C : KCallable<R>, R>(
    override val expect: Expect<T>,
    override val callable: C
) : FeatureOfStep<T, C, R> {

    override infix fun assertIt(assertionCreator: Expect<R>.() -> Unit): Expect<T> =
        expect.addAssertion(ExpectImpl.feature.(expect, index).collect(assertionCreator))
}
