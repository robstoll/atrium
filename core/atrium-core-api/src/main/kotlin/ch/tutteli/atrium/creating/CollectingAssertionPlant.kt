package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Represents an [AssertionPlant] which is intended to serve as receiver object for lambdas which create
 * [Assertion]s, in which this assertion plant collects the so created assertions.
 *
 * In contrast to [ReportingAssertionPlant], this plant does not offer error reporting capabilities and in contrast to
 * [CheckingAssertionPlant] it does not offer checking capabilities either.
 * It merely offers a method to [getAssertions] (the collected ones).
 *
 * @param T The type of the [subject] of this [AssertionPlant].
 */
interface CollectingAssertionPlant<out T : Any> : AssertionPlant<T> {

    override fun addAssertionsCreatedBy(assertionCreator: AssertionPlant<T>.() -> Unit): CollectingAssertionPlant<T>

    /**
     * The subject for which this plant will create [Assertion]s or it throws a [PlantHasNoSubjectException] if absent.
     * @throws PlantHasNoSubjectException in case there was not a [subject] defined for this plant.
     */
    override val subject: T

    /**
     * Returns the [Assertion]s which have been [added][addAssertion] to this plant.
     *
     * @return The [Assertion]s which have been [added][addAssertion] to this plant.
     */
    fun getAssertions(): List<Assertion>
}
