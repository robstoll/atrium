package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.DescriptionThrowableAssertion.IS_A
import ch.tutteli.atrium.assertions.DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED
import ch.tutteli.atrium.assertions.any.typetransformation.ExplanatoryTypeTransformationFailureHandler
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.assertions.throwable.thrown.creators.ThrowableThrownAssertionCreator
import ch.tutteli.atrium.assertions.throwable.thrown.providers.TranslatableAsAbsentThrowableMessageProvider

inline fun <reified TExpected : Throwable> _toThrow(throwableThrownBuilder: ThrowableThrownBuilder, noinline assertionCreator: AssertionPlant<TExpected>.() -> Unit) {
    val provider = TranslatableAsAbsentThrowableMessageProvider(NO_EXCEPTION_OCCURRED)
    ThrowableThrownAssertionCreator<TExpected>(provider, ExplanatoryTypeTransformationFailureHandler())
        .executeActAndCreateAssertion(throwableThrownBuilder, IS_A, TExpected::class, assertionCreator)
}
