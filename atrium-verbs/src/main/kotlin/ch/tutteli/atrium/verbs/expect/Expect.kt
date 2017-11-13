package ch.tutteli.atrium.verbs.expect

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.verbs.AssertionVerb.EXPECT
import ch.tutteli.atrium.verbs.AssertionVerb.EXPECT_THROWN
import ch.tutteli.atrium.verbs.AtriumReporterSupplier

/**
 * Creates an [IAssertionPlant] for [subject] which immediately evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newReportingPlant
 */
fun <T : Any> expect(subject: T)
    = AtriumFactory.newReportingPlant(EXPECT, subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [IAssertionPlant] for [subject] which lazily evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newReportingPlantCheckLazilyAtTheEnd
 */
fun <T : Any> expect(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newReportingPlantCheckLazilyAtTheEnd(EXPECT, subject, AtriumReporterSupplier.REPORTER, createAssertions)

/**
 * Creates an [IAssertionPlantNullable] for [subject].
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newReportingPlantNullable
 */
fun <T : Any?> expect(subject: T)
    = AtriumFactory.newReportingPlantNullable(EXPECT, subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [ThrowableThrownBuilder] for the given function [act].
 *
 * @return The newly created [ThrowableThrownBuilder].
 */
fun expect(act: () -> Unit)
    = ThrowableThrownBuilder(EXPECT_THROWN, act, AtriumReporterSupplier.REPORTER)
