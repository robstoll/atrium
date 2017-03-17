package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.IAssertion

internal class AssertionFactoryCheckImmediately<out T : Any> constructor(
    commonFields: IAssertionFactoryBase.CommonFields<T>) : AssertionFactoryCheckLazily<T>(commonFields) {

    override fun addAssertion(assertion: IAssertion): IAssertionFactory<T> {
        super.addAssertion(assertion)
        checkAssertions()
        return this
    }
}
