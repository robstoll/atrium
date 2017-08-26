package ch.tutteli.atrium.verbs.assertthat

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IThrowableFluent
import ch.tutteli.atrium.newCheckLazilyAtTheEnd
import ch.tutteli.atrium.verbs.AssertionVerb.ASSERT_THAT
import ch.tutteli.atrium.verbs.AssertionVerb.ASSERT_THAT_THROWN
import ch.tutteli.atrium.verbs.AtriumReporterSupplier

/**
 * Creates an [IAssertionPlant] for [subject] which immediately evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newCheckImmediately
 */
fun <T : Any> assertThat(subject: T)
    = AtriumFactory.newCheckImmediately(ASSERT_THAT, subject, AtriumReporterSupplier.REPORTER)


/**
 * Creates an [IAssertionPlantNullable] for [subject].
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newNullable
 */
fun <T : Any?> assertThat(subject: T)
    = AtriumFactory.newNullable(ASSERT_THAT, subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [IAssertionPlant] for [subject] which lazily evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newCheckLazilyAtTheEnd
 */
inline fun <T : Any> assertThat(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newCheckLazilyAtTheEnd(ASSERT_THAT, subject, AtriumReporterSupplier.REPORTER, createAssertions)

/**
 * Creates an [IThrowableFluent] for the given function [act].
 *
 * @return The newly created [IThrowableFluent].
 */
fun assertThat(act: () -> Unit)
    = AtriumFactory.newThrowableFluent(ASSERT_THAT_THROWN, act, AtriumReporterSupplier.REPORTER)
