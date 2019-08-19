package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.AssertionContainerWithCommonFields.CommonFields
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * An assertion container which has [CommonFields].
 *
 * @param T The type of the [CommonFields.maybeSubject].
 */
interface AssertionContainerWithCommonFields<T> {
    /**
     * [CommonFields] of this assertion container.
     */
    val commonFields: CommonFields<T>

    /**
     * Common fields of an assertion container.
     *
     * @param T The type of the [maybeSubject].
     *
     * @property assertionVerb The assertion verb which will be used inter alia in reporting.
     * @property maybeSubject Either [Some] wrapping the subject of the assertion or
     *   [None] in case a previous subject change could not be performed. The assertion container will
     *   store (check/report) [Assertion]s for the subject of the assertion.
     * @property representation Provides the representation which will be used to
     *   represent the subject in reporting.
     * @property assertionChecker The checker which will be used to check [Assertion]s.
     * @property nullRepresentation The representation used in reporting in case [representation]
     *   cannot provide a representation, provides `null` respectively.
     *
     * @constructor
     * @param assertionVerb The assertion verb which will be used inter alia in reporting.
     * @param maybeSubject Either [Some] wrapping the subject of the assertion or
     *   [None] in case a previous subject change could not be performed. The assertion container will
     *   store (check/report) [Assertion]s for the subject of the assertion.
     * @param representation Provides the representation which will be used to
     *   represent the subject in reporting.
     * @param assertionChecker The checker which will be used to check [Assertion]s.
     * @param nullRepresentation The representation used in reporting in case [representation]
     *   cannot provide a representation, provides `null` respectively.
     */
    class CommonFields<out T>(
        val assertionVerb: Translatable,
        val maybeSubject: Option<T>,
        private val representation: Any?,
        val assertionChecker: AssertionChecker,
        private val nullRepresentation: Any
    ) {

        /**
         * Uses [assertionChecker] to check the given [assertions] (see [AssertionChecker.check]).
         *
         * @param assertions The assertions which shall be checked.
         *
         * @throws AssertionError Might throw an [AssertionError] if any of the [assertions] does not hold.
         */
        fun check(assertions: List<Assertion>) {
            assertionChecker.check(assertionVerb, { representation ?: nullRepresentation }, assertions)
        }
    }
}
