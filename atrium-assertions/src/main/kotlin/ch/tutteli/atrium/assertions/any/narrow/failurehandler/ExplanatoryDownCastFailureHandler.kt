package ch.tutteli.atrium.assertions.any.narrow.failurehandler

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.any.narrow.AnyNarrow
import ch.tutteli.atrium.creating.AssertionCollector
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IBaseAssertionPlant
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import kotlin.reflect.KClass

/**
 * Represents an [AnyNarrow.DownCastFailureHandler] which wraps subsequent assertions into an
 * [ExplanatoryAssertionGroup].
 *
 * @param T The type of the [IAssertionPlant.subject].
 * @param T The type to which the [IAssertionPlant.subject] should have been down-casted.
 */
class ExplanatoryDownCastFailureHandler<T : Any, TSub : T> : AnyNarrow.DownCastFailureHandler<T, TSub> {
    /**
     * Wraps the assertions which might be created by [assertionCreator] into an [ExplanatoryAssertionGroup] and adds it
     * to the given [subjectPlant].
     *
     * @param subType The type to which the [subjectPlant]'s [subject][IAssertionPlant.subject] should have been
     *        down-casted.
     * @param subjectPlant The plant to which additional assertions would have been added.
     * @param assertionCreator The lambda which could have created subsequent assertions for the down-casted
     *        [IAssertionPlant.subject].
     *
     * @throws AssertionError Might throw an [AssertionError] depending on the [subjectPlant].
     */
    override fun createAndAddAssertionToPlant(
        subType: KClass<TSub>,
        subjectPlant: IBaseAssertionPlant<T?, *>,
        failingAssertion: IAssertion,
        assertionCreator: IAssertionPlant<TSub>.() -> Unit
    ) {
        val explanatoryAssertions = collectAssertions(subType, assertionCreator)
        subjectPlant.addAssertion(InvisibleAssertionGroup(listOf(
            failingAssertion,
            ExplanatoryAssertionGroup(ExplanatoryAssertionGroupType, explanatoryAssertions)
        )))
    }

    private fun collectAssertions(subType: KClass<TSub>, assertionCreator: IAssertionPlant<TSub>.() -> Unit)
        = AssertionCollector
        .doNotThrowIfNoAssertionIsCollected
        .collectAssertionsForExplanation(
            "subject is not available because it could not be down-casted to ${subType.qualifiedName}",
            TranslatableWithArgs(DescriptionNarrowingAssertion.WARNING_DOWN_CAST_FAILED, subType.qualifiedName!!),
            assertionCreator,
            null)
}
