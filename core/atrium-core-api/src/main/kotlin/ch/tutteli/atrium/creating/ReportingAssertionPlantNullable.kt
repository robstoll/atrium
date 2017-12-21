package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

interface ReportingAssertionPlantNullable<out T>
    : AssertionPlantNullable<T>, BaseReportingAssertionPlant<T, AssertionPlantNullable<T>> {

    /**
     * The subject for which this plant will create, check and report [Assertion]s.
     */
    override val subject get() = commonFields.subject
}
