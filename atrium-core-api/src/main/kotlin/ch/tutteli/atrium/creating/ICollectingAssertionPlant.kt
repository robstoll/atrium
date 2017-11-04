package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion

/**
 * Represents an [IAssertionPlant] which is intended to serve as receiver object for lambdas which create
 * [IAssertion]s, in which this assertion plant collects the so created assertions.
 *
 * In contrast to [IReportingAssertionPlant], this plant does not offer error reporting capabilities and in contrast to
 * [ICheckingAssertionPlant] it does not offer checking capabilities either.
 * It merely offers a method to [getAssertions].
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 */
interface ICollectingAssertionPlant<out T : Any> : IAssertionPlant<T> {
    /**
     * The subject for which this plant will create [IAssertion]s or it throws a [PlantHasNoSubjectException] if absent.
     * @throws PlantHasNoSubjectException in case there was not a [subject] defined for this plant.
     */
    override val subject: T

    /**
     * Returns the [IAssertion]s which have been [added][addAssertion] to this plant.
     *
     * @return The [IAssertion]s which have been [added][addAssertion] to this plant.
     */
    fun getAssertions(): List<IAssertion>

}
