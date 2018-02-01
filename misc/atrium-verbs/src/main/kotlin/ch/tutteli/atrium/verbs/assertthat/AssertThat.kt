package ch.tutteli.atrium.verbs.assertthat

import ch.tutteli.atrium.CoreFactory
import ch.tutteli.atrium.ICoreFactory
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.verbs.AssertionVerb.ASSERT_THAT
import ch.tutteli.atrium.verbs.AssertionVerb.ASSERT_THAT_THROWN
import ch.tutteli.atrium.verbs.AtriumReporterSupplier

/**
 * Creates an [AssertionPlant] for the given [subject].
 *
 * @return The newly created plant.
 *
 * @see CoreFactory.newReportingPlant
 */
fun <T : Any> assertThat(subject: T)
    = CoreFactory.newReportingPlant(ASSERT_THAT, subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [AssertionPlant] for the given [subject] and [AssertionPlant.addAssertionsCreatedBy] the
 * given [assertionCreator] lambda where the created [Assertion]s are added as a group and usually (depending on
 * the configured [Reporter]) reported as a whole.
 *
 * @return The newly created plant.
 *
 * @see ICoreFactory.newReportingPlantAndAddAssertionsCreatedBy
 */
fun <T : Any> assertThat(subject: T, assertionCreator: Assert<T>.() -> Unit)
    = CoreFactory.newReportingPlantAndAddAssertionsCreatedBy(ASSERT_THAT, subject, AtriumReporterSupplier.REPORTER, assertionCreator)

/**
 * Creates an [AssertionPlantNullable] for the given [subject] which might be `null`.
 *
 * @return The newly created plant.
 *
 * @see CoreFactory.newReportingPlantNullable
 */
fun <T : Any?> assertThat(subject: T)
    = CoreFactory.newReportingPlantNullable(ASSERT_THAT, subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates a [ThrowableThrownBuilder] for the given function [act] which is expected to throw a [Throwable].
 *
 * @return The newly created [ThrowableThrownBuilder].
 */
fun assertThat(act: () -> Unit)
    = ThrowableThrownBuilder(
    ASSERT_THAT_THROWN,
    act,
    AtriumReporterSupplier.REPORTER
)
