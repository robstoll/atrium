package ch.tutteli.assertk

class ThrowableFluent<out T : Throwable?>(val assertionVerb: String, val throwable: T) {

    inline fun <reified TExpected : Throwable> toThrow(): IAssertionFactory<TExpected> {
        val assertion = ExceptionThrownAssertion(throwable, TExpected::class.java)
        if (assertion.holds()) {
            //needs to hold in order that cast can be performed
            return AssertionFactory.newCheckImmediately(assertionVerb, throwable as TExpected)
        }
        AssertionFactory.fail(assertionVerb, TExpected::class.java, assertion.logMessages())
    }

    inline fun <reified TExpected : Throwable> toThrow(createAsserts: IAssertionFactory<TExpected>.() -> Unit) {
        val assertion = ExceptionThrownAssertion(throwable, TExpected::class.java)
        if (assertion.holds()) {
            //needs to hold in order that cast can be performed
            val factory = AssertionFactory.new(assertionVerb, throwable as TExpected)
            factory.addAssertion(assertion)
            factory.createAsserts()
            factory.checkAssertions()
        } else {
            AssertionFactory.fail(assertionVerb, TExpected::class.java, assertion.logMessages())
        }
    }

    companion object {
        fun create(assertionVerb: String, act: () -> Unit) : ThrowableFluent<Throwable?>{
            var throwable: Throwable? = null
            try {
                act()
            } catch(t: Throwable) {
                throwable = t
            }
            return ThrowableFluent(assertionVerb, throwable)
        }
    }
}
