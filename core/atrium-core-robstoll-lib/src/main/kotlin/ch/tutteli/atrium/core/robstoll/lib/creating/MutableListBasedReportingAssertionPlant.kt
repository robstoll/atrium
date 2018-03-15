package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.creating.BaseReportingAssertionPlant

abstract class MutableListBasedReportingAssertionPlant<out T : Any?, out A : BaseAssertionPlant<T, A>>(
    override val commonFields: AssertionPlantWithCommonFields.CommonFields<T>
) : MutableListBasedAssertionPlant<T, A>(),
    BaseReportingAssertionPlant<T, A> {

    final override fun addAssertion(assertion: Assertion): A {
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
