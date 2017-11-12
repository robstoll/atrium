package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions._toThrow
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable

/**
 * Makes the assertion that the thrown [Throwable] is of type [TExpected].
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the assertion
 * (a [Throwable] was thrown and is of type [TExpected]) holds or not.
 * If you want to define subsequent assertions on the down-casted [Throwable], then use the overload which expects a
 * lambda (where you can define subsequent assertions).
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline fun <reified TExpected : Throwable> ThrowableThrownBuilder.toThrow() {
    toThrow<TExpected> {}
}

/**
 * Makes the assertion that the thrown [Throwable] is of type [TExpected] and it [createAssertions]
 * which are checked additionally as well.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 * assertion (a [Throwable] was thrown) holds or not.
 * Define subsequent assertions via the [createAssertions] lambda.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline fun <reified TExpected : Throwable> ThrowableThrownBuilder.toThrow(noinline createAssertions: IAssertionPlant<TExpected>.() -> Unit) {
    _toThrow(this, createAssertions)
}

/**
 * Creates an [IAssertionPlantNullable] for the [message][Throwable.message] of the plant's
 * [subject][IAssertionPlant.subject] (which is an [Throwable]) and makes the assertion that message [isNotNull]
 * and uses [createAssertions] which might create further [IAssertion]s which are lazily evaluated at the end.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 * assertion (message][Throwable.message] is not null) holds or not.
 * Define subsequent assertions via the [createAssertions] lambda.
 *
 * @throws AssertionError Might throw an [AssertionError] in case [message][Throwable.message] is `null`
 *         or if an additionally created [IAssertion]s (by calling [createAssertions]) does not hold.
 */
fun <T : Throwable> IAssertionPlant<T>.message(createAssertions: IAssertionPlant<String>.() -> Unit) {
    property(subject::message).isNotNull(createAssertions)
}

