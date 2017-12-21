package ch.tutteli.atrium.assertions.any.narrow.failurehandler

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptionNarrowingAssertion
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroup
import ch.tutteli.atrium.assertions.any.narrow.AnyNarrow
import ch.tutteli.atrium.creating.AssertionCollector
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import kotlin.reflect.KClass

/**
 * Represents an [AnyNarrow.DownCastFailureHandler] which wraps subsequent assertions into an
 * [ExplanatoryAssertionGroup].
 *
 * @param T The type of the [AssertionPlant.subject].
 * @param T The type to which the [AssertionPlant.subject] should have been down-casted.
 */
class ExplanatoryDownCastFailureHandler<T : Any, TSub : T> : AnyNarrow.DownCastFailureHandler<T, TSub> {
    /**
     * Wraps the assertions which might be created by [assertionCreator] into an [ExplanatoryAssertionGroup] and adds it
     * to the given [subjectPlant].
     *
     * @param subType The type to which the [subjectPlant]'s [subject][AssertionPlant.subject] should have been
     *        down-casted.
     * @param subjectPlant The plant to which additional assertions would have been added.
     * @param assertionCreator The lambda which could have created subsequent assertions for the down-casted
     *        [AssertionPlant.subject].
     *
     * @throws AssertionError Might throw an [AssertionError] depending on the [subjectPlant].
     */
    override fun createAndAddAssertionToPlant(
        subType: KClass<TSub>,
        subjectPlant: BaseAssertionPlant<T?, *>,
        failingAssertion: Assertion,
        assertionCreator: AssertionPlant<TSub>.() -> Unit
    ) {
        val explanatoryAssertions = collectAssertions(subType, assertionCreator)
        subjectPlant.addAssertion(AssertionGroup.Builder.invisible.create(listOf(
            failingAssertion,
            AssertionGroup.Builder.explanatory.withDefault.create(explanatoryAssertions)
        )))
    }

    private fun collectAssertions(subType: KClass<TSub>, assertionCreator: AssertionPlant<TSub>.() -> Unit)
        = AssertionCollector
        .doNotThrowIfNoAssertionIsCollected
        .collectAssertionsForExplanation(
            "subject is not available because it could not be down-casted to ${subType.qualifiedName}",
            TranslatableWithArgs(DescriptionNarrowingAssertion.WARNING_DOWN_CAST_FAILED, subType.qualifiedName!!),
            assertionCreator,
            null)
}
