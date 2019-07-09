package ch.tutteli.atrium.domain.builders.migration

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE

/**
 * Turns this [Expect] into an [Assert] so that you can use functions which have not yet been migrated to [Expect].
 *
 * Will be removed with 1.0.0
 */
@Suppress("DEPRECATION")
fun <T : Any> Expect<T>.asAssert(): Assert<T> =
    coreFactory.newReportingPlant(
        SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE,
        { this.maybeSubject.getOrElse { throw PlantHasNoSubjectException() } },
        coreFactory.newDelegatingAssertionChecker(this)
    )

/**
 * Turns this [Assert] into an [Expect] so that you can new functionality which is not available on [Assert].
 *
 * Try to switch entirely to [Expect] as [Assert] along with this function will be removed with 1.0.0
 */
fun <T : Any> Assert<T>.asExpect(): Expect<T> =
    coreFactory.newReportingAssertionContainer(
        SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG_TRANSLATABLE,
        this.maybeSubject,
        coreFactory.newDelegatingAssertionChecker(this)
    )
