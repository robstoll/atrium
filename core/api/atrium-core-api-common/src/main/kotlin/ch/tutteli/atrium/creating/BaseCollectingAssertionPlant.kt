package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Represents a [BaseAssertionPlant] which is intended to serve as receiver object for lambdas which create
 * [Assertion]s, in which this assertion plant collects the so created assertions.
 *
 * In contrast to [BaseReportingAssertionPlant], this plant does not offer reporting capabilities.
 * It merely offers a method to [getAssertions] (the collected ones).
 *
 * @param T The type of the [subject] of this plant.
 * @param A A subtype of [BaseAssertionPlant] -- has to correspond to `A` in [C]
 * @param C A subtype of [BaseCollectingAssertionPlant] which is used in the fluent style API and as self type.
 */
interface BaseCollectingAssertionPlant<
    out T,
    out A : BaseAssertionPlant<T, A>,
    out C : BaseCollectingAssertionPlant<T, A, C>
    > : BaseAssertionPlant<T, A> {

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
