@file:JvmName("DeprecatedThrowableThrownAssertions")
package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.throwable.thrown.creators.ThrowableThrownAssertions
import kotlin.reflect.KClass

@Deprecated("use ThrowableThrownAssertions.toThrow instead, will be removed with 1.0.0", ReplaceWith("ThrowableThrownAssertions.toThrow(throwableThrownBuilder, expectedType, assertionCreator)"))
fun <TExpected : Throwable> _toThrow(throwableThrownBuilder: ThrowableThrownBuilder, expectedType: KClass<TExpected>, assertionCreator: AssertionPlant<TExpected>.() -> Unit) {
    ThrowableThrownAssertions.toThrow(throwableThrownBuilder, expectedType, assertionCreator)
}
