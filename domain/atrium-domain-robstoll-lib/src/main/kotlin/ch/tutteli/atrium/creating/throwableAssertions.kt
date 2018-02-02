package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.DescriptionThrowableAssertion.IS_A
import ch.tutteli.atrium.assertions.DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED
import ch.tutteli.atrium.creating.any.typetransformation.ExplanatoryTypeTransformationFailureHandler
import ch.tutteli.atrium.creating.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.throwable.thrown.creators.ThrowableThrownAssertionCreator
import ch.tutteli.atrium.creating.throwable.thrown.providers.TranslatableAsAbsentThrowableMessageProvider
import kotlin.reflect.KClass

fun <TExpected : Throwable> _toThrow(throwableThrownBuilder: ThrowableThrownBuilder, expectedType: KClass<TExpected>, assertionCreator: AssertionPlant<TExpected>.() -> Unit) {
    val provider = TranslatableAsAbsentThrowableMessageProvider(NO_EXCEPTION_OCCURRED)
    ThrowableThrownAssertionCreator<TExpected>(provider, ExplanatoryTypeTransformationFailureHandler())
        .executeActAndCreateAssertion(throwableThrownBuilder, IS_A, expectedType, assertionCreator)
}
