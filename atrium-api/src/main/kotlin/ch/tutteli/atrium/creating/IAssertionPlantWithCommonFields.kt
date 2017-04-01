package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields
import ch.tutteli.atrium.reporting.RawString

/**
 * An [IAssertionPlant] which has [CommonFields]; provides the property [subject] for ease of use.
 */
interface IAssertionPlantWithCommonFields<out T> {
    val commonFields: CommonFields<T>
    val subject get() = commonFields.subject

    data class CommonFields<out T>(val assertionVerb: String, val subject: T, val assertionChecker: IAssertionChecker) {

        fun check(assertions: List<IAssertion>)
            = assertionChecker.check(assertionVerb, subject ?: RawString.NULL, assertions)

        fun fail(assertion: IAssertion)
            = assertionChecker.fail(assertionVerb, subject ?: RawString.NULL, assertion)
    }
}
