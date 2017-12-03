package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion

interface IBaseReportingAssertionPlant<out T : Any?, out A : IBaseAssertionPlant<T, A>>
    : IBaseAssertionPlant<T, A>, IAssertionPlantWithCommonFields<T> {
    /**
     * The subject for which this plant will create, check and report [IAssertion]s.
     */
    override val subject get() = commonFields.subject
}
