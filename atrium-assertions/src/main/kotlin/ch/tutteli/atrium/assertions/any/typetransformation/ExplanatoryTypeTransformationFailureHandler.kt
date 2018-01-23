package ch.tutteli.atrium.assertions.any.typetransformation

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionBuilder
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroup
import ch.tutteli.atrium.creating.AssertionCollector
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents an [AnyTypeTransformation.TypeTransformationFailureHandler] which wraps subsequent assertions into an
 * [ExplanatoryAssertionGroup].
 *
 * @param T The type of the [AssertionPlant.subject].
 * @param T The type to which the [AssertionPlant.subject] should have been down-casted.
 */
class ExplanatoryTypeTransformationFailureHandler<T : Any, out TSub : T> : AnyTypeTransformation.TypeTransformationFailureHandler<T, TSub> {
    /**
     * Wraps the assertions which might be created by [assertionCreator] into an [ExplanatoryAssertionGroup] and adds it
     * to the given [subjectPlant].
     *
     * @param warningTransformationFailed The type to which the [subjectPlant]'s [subject][AssertionPlant.subject] should have been
     *   down-casted.
     * @param subjectPlant The plant to which additional assertions would have been added.
     * @param assertionCreator The lambda which could have created subsequent assertions for the down-casted
     *   [AssertionPlant.subject].
     *
     * @throws AssertionError Might throw an [AssertionError] depending on the [subjectPlant].
     */
    override fun createAndAddAssertionToPlant(
        warningTransformationFailed: Translatable,
        subjectPlant: BaseAssertionPlant<T?, *>,
        failingAssertion: Assertion,
        assertionCreator: AssertionPlant<TSub>.() -> Unit
    ) {
        val explanatoryAssertions = collectAssertions(warningTransformationFailed, assertionCreator)
        subjectPlant.addAssertion(
            AssertionBuilder.invisible.create(listOf(
            failingAssertion,
            AssertionBuilder.explanatory.withDefault.create(explanatoryAssertions)
        )))
    }

    private fun collectAssertions(warningDownCastFailed: Translatable, assertionCreator: AssertionPlant<TSub>.() -> Unit)
        = AssertionCollector
        .doNotThrowIfNoAssertionIsCollected
        .collectAssertionsForExplanation(warningDownCastFailed,
            assertionCreator,
            null)
}
