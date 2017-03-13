package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.ExceptionThrownAssertion
import ch.tutteli.assertk.checking.IAssertionChecker

class ThrowableFluent(val assertionVerb: String, val throwable: Throwable?, val assertionChecker: IAssertionChecker) {

    inline fun <reified TExpected : Throwable> toThrow(): IAssertionFactory<TExpected>
        = AssertionFactory.downCast(assertionVerb, throwable, NO_EXCEPTION_OCCURRED, ExceptionThrownAssertion(throwable, TExpected::class.java), assertionChecker)

    inline fun <reified TExpected : Throwable> toThrow(crossinline createAsserts: IAssertionFactory<TExpected>.() -> Unit)
        = AssertionFactory.downCast(assertionVerb, throwable, NO_EXCEPTION_OCCURRED, ExceptionThrownAssertion(throwable, TExpected::class.java), assertionChecker, createAsserts)

    companion object {
        val NO_EXCEPTION_OCCURRED = "no exception occurred"

        fun create(assertionVerb: String, act: () -> Unit, assertionChecker: IAssertionChecker): ThrowableFluent {
            var throwable: Throwable? = null
            try {
                act()
            } catch(t: Throwable) {
                throwable = t
            }
            return ThrowableFluent(assertionVerb, throwable, assertionChecker)
        }
    }
}
