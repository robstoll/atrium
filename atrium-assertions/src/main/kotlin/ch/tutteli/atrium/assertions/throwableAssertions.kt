package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionThrowableAssertion.IS_A
import ch.tutteli.atrium.assertions.DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED
import ch.tutteli.atrium.assertions.any.narrow.failurehandler.ExplanatoryDownCastFailureHandler
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.assertions.throwable.thrown.creators.ThrowableThrownAssertionCreator
import ch.tutteli.atrium.assertions.throwable.thrown.providers.TranslatableAsAbsentThrowableMessageProvider
import ch.tutteli.atrium.creating.IAssertionPlant

inline fun <reified TExpected : Throwable> _toThrow(throwableThrownBuilder: ThrowableThrownBuilder, noinline createAssertions: IAssertionPlant<TExpected>.() -> Unit) {
    val provider = TranslatableAsAbsentThrowableMessageProvider(NO_EXCEPTION_OCCURRED)
    ThrowableThrownAssertionCreator<TExpected>(provider, ExplanatoryDownCastFailureHandler())
        .createAndAddAssertionToPlant(throwableThrownBuilder, IS_A, TExpected::class, createAssertions)
}
