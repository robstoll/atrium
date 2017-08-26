package ch.tutteli.atrium.verbs.assert

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.IAtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IThrowableFluent
import ch.tutteli.atrium.newCheckLazilyAtTheEnd
import ch.tutteli.atrium.verbs.AssertionVerb.ASSERT
import ch.tutteli.atrium.verbs.AssertionVerb.ASSERT_THROWN
import ch.tutteli.atrium.verbs.AtriumReporterSupplier

/**
 * Creates an [IAssertionPlant] for [subject] which immediately evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newCheckImmediately
 */
fun <T : Any> assert(subject: T)
    = AtriumFactory.newCheckImmediately(ASSERT, subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [IAssertionPlantNullable] for [subject].
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newNullable
 */
fun <T : Any?> assert(subject: T)
    = AtriumFactory.newNullable(ASSERT, subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [IAssertionPlant] for [subject] which lazily evaluates [IAssertion]s.
 *
 * @return The newly created plant.
 *
 * @see IAtriumFactory.newCheckLazilyAtTheEnd
 */
inline fun <T : Any> assert(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newCheckLazilyAtTheEnd(ASSERT, subject, AtriumReporterSupplier.REPORTER, createAssertions)

/**
 * Creates an [IThrowableFluent] for the given function [act].
 *
 * @return The newly created [IThrowableFluent].
 */
fun assert(act: () -> Unit)
    = AtriumFactory.newThrowableFluent(ASSERT_THROWN, act, AtriumReporterSupplier.REPORTER)


