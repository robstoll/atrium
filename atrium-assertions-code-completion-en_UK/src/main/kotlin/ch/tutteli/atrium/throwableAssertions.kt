package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable

/**
 * Creates an [IAssertionPlantNullable] for the [message][Throwable.message] of the plant's
 * [subject][IAssertionPlant.subject] (which is an [Throwable]) and makes the assertion that message [isNotNull].
 *
 * @return An [IAssertionPlant] which immediately evaluates [IAssertion]s (see [IAtriumFactory.newCheckImmediately]).
 * @throws AssertionError Might throw an [AssertionError] in case [message][Throwable.message] is `null`.
 */
val <T : Throwable> IAssertionPlant<T>.message: IAssertionPlant<String> get() = property(subject::message).isNotNull()

/**
 * Creates an [IAssertionPlantNullable] for the [message][Throwable.message] of the plant's
 * [subject][IAssertionPlant.subject] (which is an [Throwable]) and makes the assertion that message [isNotNull]
 * and uses [createAssertions] which might create further [IAssertion]s which are lazily evaluated at the end.
 *
 * @return An [IAssertionPlant] which lazily evaluates [IAssertion]s (see [IAtriumFactory.newCheckLazily]).
 * @throws AssertionError Might throw an [AssertionError] in case [message][Throwable.message] is `null`
 *         or if an additionally created [IAssertion]s (by calling [createAssertions]) does not hold.
 */
fun <T : Throwable> IAssertionPlant<T>.message(createAssertions: IAssertionPlant<String>.() -> Unit): IAssertionPlant<String>
    = property(subject::message).isNotNull(createAssertions)
