package ch.tutteli.atrium.verbs.expect

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IThrowableFluent
import ch.tutteli.atrium.newReportingPlantCheckLazilyAtTheEnd
import ch.tutteli.atrium.verbs.AtriumReporterSupplier
import ch.tutteli.atrium.verbs.AssertionVerb.*

/**
 * Creates an [IAssertionPlant] for [subject] which immediately evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newReportingPlantCheckImmediately
 */
fun <T : Any> expect(subject: T)
    = AtriumFactory.newReportingPlantCheckImmediately(EXPECT, subject, AtriumReporterSupplier.REPORTER)

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
 * Creates an [IAssertionPlant] for [subject] which lazily evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newReportingPlantCheckLazilyAtTheEnd
 */
inline fun <T : Any> expect(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newReportingPlantCheckLazilyAtTheEnd(EXPECT, subject, AtriumReporterSupplier.REPORTER, createAssertions)

/**
 * Creates an [IThrowableFluent] for the given function [act].
 *
 * @return The newly created [IThrowableFluent].
 */
fun expect(act: () -> Unit)
    = AtriumFactory.newThrowableFluent(EXPECT_THROWN, act, AtriumReporterSupplier.REPORTER)
