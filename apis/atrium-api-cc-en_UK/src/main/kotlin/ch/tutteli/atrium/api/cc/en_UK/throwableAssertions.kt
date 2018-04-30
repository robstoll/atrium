package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown

/**
 * Makes the assertion that the thrown [Throwable] is of type [TExpected].
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the assertion
 *   (a [Throwable] was thrown and is of type [TExpected]) holds or not.
 * If you want to define subsequent assertions on the down-casted [Throwable], then use the overload which expects a
 * lambda (where you can define subsequent assertions).
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.toThrow()"))
inline fun <reified TExpected : Throwable> ThrowableThrown.Builder.toThrow() {
    toThrow<TExpected> {}
}

/**
 * Makes the assertion that the thrown [Throwable] is of type [TExpected] and it [assertionCreator]
 * which are checked additionally as well.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion (a [Throwable] was thrown) holds or not.
 * Define subsequent assertions via the [assertionCreator] lambda.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.toThrow(assertionCreator)"))
inline fun <reified TExpected : Throwable> ThrowableThrown.Builder.toThrow(noinline assertionCreator: Assert<TExpected>.() -> Unit) {
    AssertImpl.throwable.thrown.toBe(this, TExpected::class, assertionCreator)
}

/**
 * Creates an [AssertionPlantNullable] for the [message][Throwable.message] of the plant's
 * [subject][AssertionPlant.subject] (which is a [Throwable]) and makes the assertion that message [isNotNull]
 * and uses [assertionCreator] which might create further [Assertion]s which are lazily evaluated at the end.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion [message][Throwable.message] is not null) holds or not.
 * Define subsequent assertions via the [assertionCreator] lambda.
 *
 * @throws AssertionError Might throw an [AssertionError] in case [message][Throwable.message] is `null`
 *   or if an additionally created [Assertion]s (by calling [assertionCreator]) does not hold.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.message(assertionCreator)"))
fun <T : Throwable> Assert<T>.message(assertionCreator: Assert<String>.() -> Unit) {
    property(subject::message).isNotNull(assertionCreator)
}
