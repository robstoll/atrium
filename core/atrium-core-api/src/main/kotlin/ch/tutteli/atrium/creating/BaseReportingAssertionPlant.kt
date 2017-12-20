package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion

interface BaseReportingAssertionPlant<out T : Any?, out A : BaseAssertionPlant<T, A>>
    : BaseAssertionPlant<T, A>, AssertionPlantWithCommonFields<T> {
    /**
     * The subject for which this plant will create, check and report [IAssertion]s.
     */
    override val subject get() = commonFields.subject
}
