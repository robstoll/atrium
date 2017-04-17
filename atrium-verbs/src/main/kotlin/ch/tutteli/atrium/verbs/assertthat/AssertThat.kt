package ch.tutteli.atrium.verbs.assertthat

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.ThrowableFluent
import ch.tutteli.atrium.verbs.AtriumReporterSupplier

/**
 * Creates an [IAssertionPlant] for [subject] which immediately evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newCheckImmediately
 */
fun <T : Any> assertThat(subject: T)
    = AtriumFactory.newCheckImmediately("assert that", subject, AtriumReporterSupplier.REPORTER)


/**
 * Creates an [IAssertionPlantNullable] for [subject].
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newNullable
 */
fun <T : Any?> assertThat(subject: T)
    = AtriumFactory.newNullable("assert that", subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [IAssertionPlant] for [subject] which lazily evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newCheckLazilyAtTheEnd
 */
inline fun <T : Any> assertThat(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newCheckLazilyAtTheEnd("assert that", subject, AtriumReporterSupplier.REPORTER, createAssertions)

/**
 * Creates an [ThrowableFluent] for the given function [act].
 *
 * @return The newly created [ThrowableFluent].
 */
fun assertThat(act: () -> Unit)
    = AtriumFactory.newThrowableFluent("assert that the thrown exception", act, AtriumReporterSupplier.REPORTER)
