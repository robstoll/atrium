package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAtriumFactory
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Creates an [isNotNull] assertion for the [Throwable.message] of a [Throwable] and
 * allows to define further assertions for the message, which are then immediately evaluated (see [IAtriumFactory.newCheckImmediately]).
 */
val <T : Throwable> IAssertionPlant<T>.message: IAssertionPlant<String> get() = its(subject::message).isNotNull()

/**
 * Creates an [isNotNull] assertion for the [Throwable.message] of a [Throwable] and
 * allows to define further assertions for the message, which are then lazily evaluated (see [IAtriumFactory.newCheckLazily]).
 */
fun <T : Throwable> IAssertionPlant<T>.message(createAssertions: IAssertionPlant<String>.() -> Unit): IAssertionPlant<String>
    = its(subject::message).isNotNull(createAssertions)
