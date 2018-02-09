package ch.tutteli.atrium.creating.throwable.thrown.creators

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.any.typetransformation.failurehandlers.ExplanatoryTypeTransformationFailureHandler
import ch.tutteli.atrium.creating.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.throwable.thrown.providers.TranslatableAsAbsentThrowableMessageProvider
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion.IS_A
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED
import kotlin.reflect.KClass

fun <TExpected : Throwable> _toBe(throwableThrownBuilder: ThrowableThrownBuilder, expectedType: KClass<TExpected>, assertionCreator: AssertionPlant<TExpected>.() -> Unit) {
    val provider = TranslatableAsAbsentThrowableMessageProvider(NO_EXCEPTION_OCCURRED)
    ThrowableThrownAssertionCreator<TExpected>(provider, ExplanatoryTypeTransformationFailureHandler())
        .executeActAndCreateAssertion(throwableThrownBuilder, IS_A, expectedType, assertionCreator)
}
