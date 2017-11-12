package ch.tutteli.atrium.verbs.assertthat

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.verbs.AssertionVerb.ASSERT_THAT
import ch.tutteli.atrium.verbs.AssertionVerb.ASSERT_THAT_THROWN
import ch.tutteli.atrium.verbs.AtriumReporterSupplier

/**
 * Creates an [IAssertionPlant] for [subject] which immediately evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newReportingPlantCheckImmediately
 */
fun <T : Any> assertThat(subject: T)
    = AtriumFactory.newReportingPlantCheckImmediately(ASSERT_THAT, subject, AtriumReporterSupplier.REPORTER)


/**
 * Creates an [IAssertionPlantNullable] for [subject].
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newReportingPlantNullable
 */
fun <T : Any?> assertThat(subject: T)
    = AtriumFactory.newReportingPlantNullable(ASSERT_THAT, subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [IAssertionPlant] for [subject] which lazily evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newReportingPlantCheckLazilyAtTheEnd
 */
fun <T : Any> assertThat(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newReportingPlantCheckLazilyAtTheEnd(ASSERT_THAT, subject, AtriumReporterSupplier.REPORTER, createAssertions)

/**
 * Creates an [ThrowableThrownBuilder] for the given function [act].
 *
 * @return The newly created [ThrowableThrownBuilder].
 */
fun assertThat(act: () -> Unit)
    = ThrowableThrownBuilder(ASSERT_THAT_THROWN, act, AtriumReporterSupplier.REPORTER)
