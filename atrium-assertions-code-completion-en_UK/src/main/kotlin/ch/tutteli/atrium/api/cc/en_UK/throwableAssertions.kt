package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.IAtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions._toThrow
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.IThrowableFluent

/**
 * Makes the assertion that [IThrowableFluent.commonFields]'
 * [subject][IAssertionPlantWithCommonFields.CommonFields.subject] is of the expected type [TExpected] and
 * reports an error if subject is `null` or another type than the expected one.
 *
 * @return The newly created plant to support a fluent API.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalStateException In case reporting a failure does not throw itself.
 */
inline fun <reified TExpected : Throwable> IThrowableFluent.toThrow(): IAssertionPlant<TExpected>
    = _toThrow(this)

/**
 * Makes the assertion that [IThrowableFluent.commonFields]'
 * [subject][IAssertionPlantWithCommonFields.CommonFields.subject] is of the expected type [TExpected] and
 * reports an error if subject is `null` or another type than the expected one -- furthermore it [createAssertions]
 * which are checked additionally as well.
 *
 * @return The newly created plant to support a fluent API.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalStateException In case reporting a failure does not throw itself.
 */
inline fun <reified TExpected : Throwable> IThrowableFluent.toThrow(noinline createAssertions: IAssertionPlant<TExpected>.() -> Unit): IAssertionPlant<TExpected>
    = _toThrow(this, createAssertions)

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
