package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a container for [Assertion]s and offers the possibility to [addAssertion]s which are reported
 * in case they do not hold.
 *
 * @param T The type of the [subject] of this [Assert].
 */
interface ReportingAssertionContainer<T> : Expect<T> {

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
             * Creates an [AssertionCheckerDecorator] -- use [createLazy] in case subject creation or representation
             * are costly.
             *
             * @param assertionVerb The assertion verb which will be used inter alia in reporting.
             * @param maybeSubject Either [Some] wrapping the subject of the assertion or
             *   [None] in case a previous subject change could not be performed. The assertion container will
             *   store (check/report) [Assertion]s for the subject of the assertion.
             * @param representation The representation which will be used to represent the subject in reporting.
             * @param assertionChecker The checker which will be used to check [Assertion]s.
             * @param nullRepresentation The representation used in reporting in case [representation] is `null`.
             */
            fun <T> create(
                assertionVerb: Translatable,
                maybeSubject: Option<T>,
                representation: Any?,
                assertionChecker: AssertionChecker,
                nullRepresentation: Any
            ): AssertionCheckerDecorator<T> = EagerCommonFields(
                assertionVerb, maybeSubject, representation, assertionChecker, nullRepresentation
            )

            /**
             * Creates an [AssertionCheckerDecorator] which lazily evaluates the
             * given [maybeSubjectProvider] and [representationProvider].
             *
             * @param assertionVerb The assertion verb which will be used inter alia in reporting.
             * @param maybeSubjectProvider Providing either a [Some] wrapping the subject of the assertion or
             *   [None] in case a previous subject change could not be performed. The assertion container will
             *   store (check/report) [Assertion]s for the subject of the assertion.
             * @param representationProvider Provides the representation which will be used to
             *   represent the subject in reporting.
             * @param assertionChecker The checker which will be used to check [Assertion]s.
             * @param nullRepresentation The representation used in reporting in case [representationProvider]
             *   cannot provide a representation, provides `null` respectively.
             */
            fun <T> createLazy(
                assertionVerb: Translatable,
                maybeSubjectProvider: () -> Option<T>,
                representationProvider: () -> Any?,
                assertionChecker: AssertionChecker,
                nullRepresentation: Any
            ): AssertionCheckerDecorator<T> = LazyCommonFields(
                assertionVerb, maybeSubjectProvider, representationProvider, assertionChecker, nullRepresentation
            )
        }
    }

    private class EagerCommonFields<T>(
        private val assertionVerb: Translatable,
        override val maybeSubject: Option<T>,
        private val representation: Any?,
        override val assertionChecker: AssertionChecker,
        private val nullRepresentation: Any
    ) : AssertionCheckerDecorator<T> {

        override fun check(assertions: List<Assertion>) {
            assertionChecker.check(assertionVerb, representation ?: nullRepresentation, assertions)
        }
    }

    private class LazyCommonFields<T>(
        private val assertionVerb: Translatable,
        maybeSubjectProvider: () -> Option<T>,
        representationProvider: () -> Any?,
        override val assertionChecker: AssertionChecker,
        private val nullRepresentation: Any
    ) : AssertionCheckerDecorator<T> {

        override val maybeSubject: Option<T> by lazy(maybeSubjectProvider)
        private val representation = LazyRepresentation { representationProvider() ?: nullRepresentation }

        override fun check(assertions: List<Assertion>) {
            assertionChecker.check(assertionVerb, representation, assertions)
        }
    }
}
