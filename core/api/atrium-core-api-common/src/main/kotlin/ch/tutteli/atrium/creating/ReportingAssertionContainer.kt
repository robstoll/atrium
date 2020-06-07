//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a container for [Assertion]s and offers the possibility to [addAssertion]s which are reported
 * in case they do not hold.
 *
 * @param T The type of the [subject] of this [Assert].
 */
@Deprecated("Use RootExpect instead; will be removed with 1.0.0", ReplaceWith("RootExpect"))
interface ReportingAssertionContainer<T> : RootExpect<T> {

    /**
     * The [AssertionChecker] which shall be used for checking the assertions.
     */
    val assertionChecker: AssertionChecker

    /**
     * Represents a decorator for [AssertionChecker] which passes a specified assertion verb, representation
     * and assertions to [AssertionChecker.check] for the given [maybeSubject].
     *
     * @param T The type of the [maybeSubject].
     */
    interface AssertionCheckerDecorator<T> {

        val assertionVerb: Translatable

        val representation: Any?

        /**
         * Either [Some] wrapping the subject of the current assertion or
         * [None] in case a previous subject change was not successful.
         */
        val maybeSubject: Option<T>

        /**
         * The [AssertionChecker] which shall be used for checking the assertions.
         */
        val assertionChecker: AssertionChecker

        /**
         * Uses [assertionChecker] to check the given [assertions] (see [AssertionChecker.check]).
         *
         * @param assertions The assertions which shall be checked.
         *
         * @throws AssertionError Might throw an [AssertionError] if any of the [assertions] does not hold.
         */
        fun check(assertions: List<Assertion>)


        companion object {

            /**
             * Creates an [AssertionCheckerDecorator].
             *
             * @param assertionVerb The assertion verb which will be used inter alia in reporting.
             * @param maybeSubject Either [Some] wrapping the subject of the assertion or
             *   [None] in case a previous subject change could not be performed. The assertion container will
             *   store (check/report) [Assertion]s for the subject of the assertion.
             * @param representation The representation which will be used to represent the subject in reporting.
             * @param assertionChecker The checker which will be used to check [Assertion]s.
             */
            fun <T> create(
                assertionVerb: Translatable,
                maybeSubject: Option<T>,
                representation: Any?,
                assertionChecker: AssertionChecker
            ): AssertionCheckerDecorator<T> = EagerCommonFields(
                assertionVerb, maybeSubject, representation, assertionChecker
            )
        }
    }

    private class EagerCommonFields<T>(
        override val assertionVerb: Translatable,
        override val maybeSubject: Option<T>,
        override val representation: Any?,
        override val assertionChecker: AssertionChecker
    ) : AssertionCheckerDecorator<T> {

        override fun check(assertions: List<Assertion>) {
            assertionChecker.check(assertionVerb, representation, assertions)
        }
    }
}
