package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * An assertion plant which has [CommonFields].
 *
 * @param T The type of [CommonFields.subject] of this [IAssertionPlant].
 */
interface IAssertionPlantWithCommonFields<out T> {
    /**
     * [CommonFields] of this plant.
     */
    val commonFields: CommonFields<T>

    /**
     * Common fields of an assertion plant.
     *
     * @param T The type of the [subject] of this [IAssertionPlant].
     *
     * @property assertionVerb The assertion verb which will be used inter alia in error reporting.
     * @property subject The subject for which this plant will create/check [IAssertion]s.
     * @property assertionChecker The checker which will be used to check [IAssertion]s.
     *
     * @constructor
     * @param assertionVerb The assertion verb which will be used inter alia in error reporting.
     * @param subject The subject for which this plant will create/check [IAssertion]s.
     * @param assertionChecker The checker which will be used to check [IAssertion]s.
     *
     */
    data class CommonFields<out T>(val assertionVerb: ITranslatable, val subject: T, val assertionChecker: IAssertionChecker) {

        /**
         * Uses [assertionChecker] to check the given [assertions] (see [IAssertionChecker.check]).
         *
         * @param assertions The assertions which shall be checked.
         *
         * @throws AssertionError Might throw an [AssertionError] if any of the [assertions] does not hold.
         */
        fun check(assertions: List<IAssertion>)
            = assertionChecker.check(assertionVerb, subject ?: RawString.NULL, assertions)

        /**
         * Uses [assertionChecker] to report a failing [assertion] (see [IAssertionChecker.fail]).
         *
         * In case [subject] is null, then [RawString.NULL] will be used as representation.
         *
         * @param assertion The failing assertion.
         *
         * @throws AssertionError Typically throws an [AssertionError] or another [Exception].
         */
        fun fail(assertion: IAssertion)
            = assertionChecker.fail(assertionVerb, subject ?: RawString.NULL, assertion)
    }
}
