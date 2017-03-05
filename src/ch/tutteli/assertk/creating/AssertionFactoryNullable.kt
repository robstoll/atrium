package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.OneMessageAssertion
import ch.tutteli.assertk.checking.IAssertionChecker
import ch.tutteli.assertk.reporting.RawString

internal class AssertionFactoryNullable<out T : Any?> constructor(
    override val assertionVerb: String,
    override val subject: T,
    override val assertionChecker: IAssertionChecker) : IAssertionFactoryNullable<T> {

    override fun isNull() {
        if (subject != null) {
            assertionChecker.fail(assertionVerb, subject, OneMessageAssertion("to be", RawString.NULL, false))
        }
    }
}
