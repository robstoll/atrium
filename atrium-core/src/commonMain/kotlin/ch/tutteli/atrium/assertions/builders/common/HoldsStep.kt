//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions.builders.common

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.assertions.RepresentationOnlyAssertion
import ch.tutteli.atrium.creating.Expect

/**
 * Step which allows to specify [RepresentationOnlyAssertion.holds].
 */
//TODO 1.3.0 deprecate and probably all other symbols in this package
interface HoldsStep<R> {

    /**
     * Defines a constant failing assertion.
     */
    val failing: R

    /**
     * Defines a constant holding assertion.
     */
    val holding: R

    /**
     * Uses the given [test] as [Proof.holds].
     */
    fun withTest(test: () -> Boolean): R

    /**
     * Uses the given [test] as [Proof.holds] based on the subject of the given [expect].
     *
     * @return `true` in case [ProofContainer.maybeSubject] is None or the result of [test] passing the subject.
     */
    //TODO 1.3.0 if we introduce Record or something else as replacement for Assertion then not but if we keep Assertion
    // then move to logic and expect ProofContainer
    fun <T> withTest(
        expect: Expect<T>,
        test: (T) -> Boolean
    ): R
}
