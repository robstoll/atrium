package ch.tutteli.atrium.assertions.builders.common

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.RepresentationOnlyAssertion
import ch.tutteli.atrium.creating.Expect

/**
 * Step which allows to specify [RepresentationOnlyAssertion.holds].
 */
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
     * Uses the given [test] as [Assertion.holds].
     */
    fun withTest(test: () -> Boolean): R

    /**
     * Uses the given [test] as [Assertion.holds] based on the subject provided by [subjectProvider].
     *
     * Notice, this function might change its signature with 1.0.0 to something like
     * ```
     * fun <T> withTest(expect: Expect, test: (T) -> Boolean): DescriptionOption<FinalStep>
     * ```
     *
     * @return `true` in case [SubjectProvider.maybeSubject] is None or the result of [test] passing the subject.
     */
    //TODO if we introduce Record or something else as replacement for Assertion then not but if we keep Assertion
    // then move to logic and expect ProofContainer with 0.18.0
    fun <T> withTest(
        expect: Expect<T>,
        test: (T) -> Boolean
    ): R
}
