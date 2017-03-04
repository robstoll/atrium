package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.ExceptionThrownAssertion
import ch.tutteli.assertk.checking.IAssertionChecker

class ThrowableFluent(val assertionVerb: String, val throwable: Throwable?, val assertionChecker: IAssertionChecker) {

    inline fun <reified TExpected : Throwable> toThrow(): IAssertionFactory<TExpected> {
        val assertion = ExceptionThrownAssertion(throwable, TExpected::class.java)
        if (assertion.holds()) {
            //needs to hold in order that cast can be performed
            val factory = AssertionFactory.newCheckImmediately(assertionVerb, throwable as TExpected, assertionChecker)
            factory.addAssertion(assertion)
            return factory
        }
        assertionChecker.fail(assertionVerb, TExpected::class.java, assertion)
        throw IllegalStateException("calling ${IAssertionChecker::class.java.simpleName}#${IAssertionChecker::fail.name} should throw an exception")
    }

    inline fun <reified TExpected : Throwable> toThrow(createAsserts: IAssertionFactory<TExpected>.() -> Unit) {
        val assertion = ExceptionThrownAssertion(throwable, TExpected::class.java)
        if (assertion.holds()) {
            //needs to hold in order that cast can be performed
            val factory = AssertionFactory.new(assertionVerb, throwable as TExpected, assertionChecker)
            factory.addAssertion(assertion)
            factory.createAsserts()
            factory.checkAssertions()
        } else {
            assertionChecker.fail(assertionVerb, TExpected::class.java, assertion)
        }
    }

    companion object {
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
