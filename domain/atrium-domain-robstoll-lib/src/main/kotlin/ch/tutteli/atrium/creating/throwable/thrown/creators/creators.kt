@file:Suppress("NOTHING_TO_INLINE")

package ch.tutteli.atrium.creating.throwable.thrown.creators

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.throwable.thrown.providers.TranslatableAsAbsentThrowableMessageProvider
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion.IS_A
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED
import kotlin.reflect.KClass

inline fun <TExpected : Throwable> _toBe(throwableThrownBuilder: ThrowableThrownBuilder, expectedType: KClass<TExpected>, noinline assertionCreator: AssertionPlant<TExpected>.() -> Unit) {
    val provider = TranslatableAsAbsentThrowableMessageProvider(NO_EXCEPTION_OCCURRED)
    ThrowableThrownAssertionCreator<TExpected>(provider)
        .executeActAndCreateAssertion(throwableThrownBuilder, IS_A, expectedType, assertionCreator)
}
