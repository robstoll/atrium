package ch.tutteli.atrium.verbs.expect

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.ThrowableFluent
import ch.tutteli.atrium.newCheckLazilyAtTheEnd
import ch.tutteli.atrium.verbs.AtriumReporterSupplier
import ch.tutteli.atrium.verbs.AssertionVerb.*

/**
 * Creates an [IAssertionPlant] for [subject] which immediately evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newCheckImmediately
 */
fun <T : Any> expect(subject: T)
    = AtriumFactory.newCheckImmediately(EXPECT, subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [IAssertionPlantNullable] for [subject].
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newNullable
 */
fun <T : Any?> expect(subject: T)
    = AtriumFactory.newNullable(EXPECT, subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [IAssertionPlant] for [subject] which lazily evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newCheckLazilyAtTheEnd
 */
inline fun <T : Any> expect(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newCheckLazilyAtTheEnd(EXPECT, subject, AtriumReporterSupplier.REPORTER, createAssertions)

/**
 * Creates an [ThrowableFluent] for the given function [act].
 *
 * @return The newly created [ThrowableFluent].
 */
fun expect(act: () -> Unit)
    = AtriumFactory.newThrowableFluent(EXPECT_THROWN, act, AtriumReporterSupplier.REPORTER)
