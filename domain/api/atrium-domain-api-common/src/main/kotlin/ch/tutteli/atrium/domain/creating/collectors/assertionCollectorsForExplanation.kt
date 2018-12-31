package ch.tutteli.atrium.domain.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService

/**
 * The access point to an implementation of [NonThrowingAssertionCollectorForExplanation].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val nonThrowingAssertionCollectorForExplanation: NonThrowingAssertionCollectorForExplanation by lazy {
    loadSingleService(NonThrowingAssertionCollectorForExplanation::class)
}

/**
 * Represents an assertion collector meant for explanation which does *not* throw in case not a single [Assertion]
 * was collected.
 */
interface NonThrowingAssertionCollectorForExplanation : AssertionCollectorForExplanation


/**
 * The access point to an implementation of [ThrowingAssertionCollectorForExplanation].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val throwingAssertionCollectorForExplanation: ThrowingAssertionCollectorForExplanation by lazy {
    loadSingleService(ThrowingAssertionCollectorForExplanation::class)
}

/**
 * Represents an assertion collector meant for explanation which throws in case not a single [Assertion] was collected.
 */
interface ThrowingAssertionCollectorForExplanation : AssertionCollectorForExplanation

