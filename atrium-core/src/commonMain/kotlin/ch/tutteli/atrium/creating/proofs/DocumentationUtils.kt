package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.reporting.reportables.InlineElement

/**
 * Collection of proof functions and builders which help in documenting expectations.
 */
interface DocumentationUtils {

    fun <T> because(
        container: ProofContainer<T>,
        reason: InlineElement,
        expectationCreator: Expect<T>.() -> Unit
    ): Proof
}
