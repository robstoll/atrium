package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * An assertion plant which has [CommonFields].
 *
 * @param T The type of [CommonFields.subject] of this [AssertionPlant].
 */
interface AssertionPlantWithCommonFields<out T> {

    /**
     * [CommonFields] of this plant.
     */
    val commonFields: CommonFields<T>

    /**
     * Common fields of an assertion plant.
     *
     * @param T The type of the [subject] of this [AssertionPlant].
     *
     * @property assertionVerb The assertion verb which will be used inter alia in error reporting.
     * @property subject The subject for which this plant will create/check [Assertion]s.
     * @property assertionChecker The checker which will be used to check [Assertion]s.
     * @property nullRepresentation The representation used in reporting in case [subject] is `null`.
     *
     * @constructor
     * @param assertionVerb The assertion verb which will be used inter alia in error reporting.
     * @param subjectProvider Provides the [subject] for which this plant will create/check [Assertion]s.
     * @param assertionChecker The checker which will be used to check [Assertion]s.
     * @param nullRepresentation The representation used in reporting in case [subject] is `null`.
     *
     */
    class CommonFields<out T>(
        val assertionVerb: Translatable,
        private val subjectProvider: () -> T,
        val assertionChecker: AssertionChecker,
        private val nullRepresentation: Any
    ) {
        val subject: T by lazy { subjectProvider() }

        @Deprecated(
            "Use the overload with a lazy subject instead. This constructor will be removed with 1.0.0",
            ReplaceWith("this.CommonFields(assertionVerb, { subject }, assertionChecker, nullRepresentation)")
        )
        constructor(
            assertionVerb: Translatable,
            subject: T,
            assertionChecker: AssertionChecker,
            nullRepresentation: Any
        ) : this(assertionVerb, { subject }, assertionChecker, nullRepresentation)

        /**
         * Uses [assertionChecker] to check the given [assertions] (see [AssertionChecker.check]).
         *
         * @param assertions The assertions which shall be checked.
         *
         * @throws AssertionError Might throw an [AssertionError] if any of the [assertions] does not hold.
         */
        fun check(assertions: List<Assertion>) {
            val nonNullSubjectProvider = { subjectProvider() ?: nullRepresentation }
            assertionChecker.check(assertionVerb, nonNullSubjectProvider, assertions)
        }
    }
}
