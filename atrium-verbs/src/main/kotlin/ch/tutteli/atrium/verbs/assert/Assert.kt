package ch.tutteli.atrium.verbs.assert

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
fun <T : Any> assert(subject: T)
    = AtriumFactory.newCheckImmediately("assert", subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [IAssertionPlantNullable] for [subject].
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newNullable
 */
fun <T : Any?> assert(subject: T)
    = AtriumFactory.newNullable("assert", subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [IAssertionPlant] for [subject] which lazily evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newCheckLazilyAtTheEnd
 */
inline fun <T : Any> assert(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newCheckLazilyAtTheEnd("assert", subject, AtriumReporterSupplier.REPORTER, createAssertions)

/**
 * Creates an [ThrowableFluent] for the given function [act].
 *
 * @return The newly created [ThrowableFluent].
 */
fun assert(act: () -> Unit)
    = AtriumFactory.newThrowableFluent("assert the thrown exception", act, AtriumReporterSupplier.REPORTER)
