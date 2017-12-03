package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion

abstract class BaseReportingAssertionPlant<out T : Any?, out A : IBaseAssertionPlant<T, A>>(
    override val commonFields: IAssertionPlantWithCommonFields.CommonFields<T>
) : BaseAssertionPlant<T, A>(), IBaseReportingAssertionPlant<T, A> {

    override final fun addAssertion(assertion: IAssertion): A {
        super.addAssertion(assertion)
        checkAssertions()
        return self
    }

    private fun checkAssertions(): A {
        try {
            commonFields.check(getAssertions())
        } finally {
            clearAssertions()
        }
        return self
    }
}
