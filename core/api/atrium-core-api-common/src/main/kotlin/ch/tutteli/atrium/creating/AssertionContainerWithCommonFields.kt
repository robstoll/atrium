package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.AssertionContainerWithCommonFields.CommonFields
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * An assertion container which has [CommonFields].
 *
 * @param T The type of the [CommonFields.subjectProvider].
 */
interface AssertionContainerWithCommonFields<T>{
    /**
     * [CommonFields] of this assertion container.
     */
    val commonFields: CommonFields<T>

    /**
     * Common fields of an assertion container.
     *
     * @param T The type of the [subjectProvider].
     *
     * @property assertionVerb The assertion verb which will be used inter alia in reporting.
     * @property subjectProvider Provides the [Expect.subject] for which this assertion container will
     *   store (check/report) [Assertion]s.
     * @property representationProvider Provides the representation of the [Expect.subject] which will be used to
     *   represent the subject in reporting.
     * @property assertionChecker The checker which will be used to check [Assertion]s.
     * @property nullRepresentation The representation used in reporting in case [representationProvider]
     *   cannot provide a representation, provides `null` respectively.
     *
     * @constructor
     * @param assertionVerb The assertion verb which will be used inter alia in reporting.
     * @param subjectProvider Provides the [Expect.subject] for which this assertion container will
     *   store (check/report) [Assertion]s.
     * @param representationProvider Provides the representation of the [Expect.subject] which will be used to
     *   represent the subject in reporting.
     * @param assertionChecker The checker which will be used to check [Assertion]s.
     * @param nullRepresentation The representation used in reporting in case [representationProvider]
     *   cannot provide a representation, provides `null` respectively.
     */
    class CommonFields<out T>(
        val assertionVerb: Translatable,
        val subjectProvider: () -> T,
        private val representationProvider: () -> Any?,
        private val assertionChecker: AssertionChecker,
        private val nullRepresentation: Any
    ) {
        private val representation: () -> Any by lazy {
            { representationProvider() ?: nullRepresentation }
        }

        /**
         * Uses [assertionChecker] to check the given [assertions] (see [AssertionChecker.check]).
         *
         * @param assertions The assertions which shall be checked.
         *
         * @throws AssertionError Might throw an [AssertionError] if any of the [assertions] does not hold.
         */
        fun check(assertions: List<Assertion>) {
            assertionChecker.check(assertionVerb, representation, assertions)
        }
    }
}
