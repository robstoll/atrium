package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion

interface IReportingAssertionPlantNullable<out T> : IAssertionPlantNullable<T>, IAssertionPlantWithCommonFields<T> {
    /**
     * The subject for which this plant will create, check and report [IAssertion]s.
     */
    override val subject get() = commonFields.subject
}
