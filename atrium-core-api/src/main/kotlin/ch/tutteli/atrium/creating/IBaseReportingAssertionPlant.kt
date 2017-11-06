package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.reporting.IReporter

interface IBaseReportingAssertionPlant<out T : Any?, out A : IBaseAssertionPlant<T, A>>
    : IBaseAssertionPlant<T, A>, IAssertionPlantWithCommonFields<T> {
    /**
     * The subject for which this plant will create, check and report [IAssertion]s.
     */
    override val subject get() = commonFields.subject

    /**
     * Checks the so far [added][addAssertion] [IAssertion]s and reports if one of them fails.
     *
     * Calling this method more than once should not re-report previously failing assertions.
     * This method will typically use an [IAssertionChecker] for checking and an [IReporter] for error reporting.
     *
     * @return This plant to support a fluent API.
     *
     * @throws AssertionError Reporting a failing assertion might cause that an [AssertionError] is thrown.
     *
     * @see IAssertionChecker
     * @see IReporter
     */
    fun checkAssertions(): A
}
