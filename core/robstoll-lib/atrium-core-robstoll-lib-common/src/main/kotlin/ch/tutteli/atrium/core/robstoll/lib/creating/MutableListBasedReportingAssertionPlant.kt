@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)

package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.creating.BaseReportingAssertionPlant

@Deprecated("Switch from Assert to Expect, will be removed with 1.0.0")
abstract class MutableListBasedReportingAssertionPlant<out T : Any?, out A : BaseAssertionPlant<T, A>>(
    override val commonFields: AssertionPlantWithCommonFields.CommonFields<T>
) : MutableListBasedAssertionPlant<T, A>(commonFields.subjectProvider),
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
