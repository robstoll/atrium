package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.IAssertion

internal class AssertionPlantCheckImmediately<out T : Any> constructor(
    commonFields: IAssertionPlantWithCommonFields.CommonFields<T>) : AssertionPlantCheckLazily<T>(commonFields) {

    override fun addAssertion(assertion: IAssertion): IAssertionPlant<T> {
        super.addAssertion(assertion)
        checkAssertions()
        return this
    }
}
