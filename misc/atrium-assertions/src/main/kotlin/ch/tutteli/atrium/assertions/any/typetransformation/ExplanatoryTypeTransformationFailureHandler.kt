@file:Suppress("DEPRECATION" /* TODO remove with 0.10.0 */)

package ch.tutteli.atrium.assertions.any.typetransformation

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.creating.MaybeSubject
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents an [AnyTypeTransformation.TypeTransformationFailureHandler] which wraps subsequent assertions into an
 * [AssertionGroup] with an [ExplanatoryAssertionGroupType].
 *
 * @param T The type of the [AssertionPlant.subject][SubjectProvider.subject].
 * @param T The type to which the [AssertionPlant.subject][SubjectProvider.subject] should have been down-casted.
 */
@Deprecated("Use AssertImpl.any.typeTransformation.failureHandlers.newExplanatory; will be removed with 1.0.0")
class ExplanatoryTypeTransformationFailureHandler<T : Any, out TSub : T> :
    AnyTypeTransformation.TypeTransformationFailureHandler<T, TSub> {
    /**
     * Wraps the assertions which might be created by [assertionCreator] into an [AssertionGroup]
     * with an [ExplanatoryAssertionGroupType] and adds it to the given [subjectPlant].
     *
     * @param warningTransformationFailed The type to which the [subjectPlant]'s [subject][SubjectProvider.subject] should have been
     *   down-casted.
     * @param subjectPlant The plant to which additional assertions would have been added.
     * @param assertionCreator The lambda which could have created subsequent assertions for the down-casted
     *   [AssertionPlant.subject][SubjectProvider.subject].
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
        val explanatoryGroup = AssertImpl.builder.explanatoryGroup
            .withDefaultType
            .withAssertions(explanatoryAssertions)
            .build()
        subjectPlant.addAssertion(
            AssertImpl.builder.invisibleGroup
                .withAssertions(
                    failingAssertion,
                    explanatoryGroup
                )
                .build()
        )
    }

    private fun collectAssertions(
        warningDownCastFailed: Translatable,
        assertionCreator: AssertionPlant<TSub>.() -> Unit
    ) = AssertImpl.collector
        .forExplanation
        .doNotThrowIfNoAssertionIsCollected
        .collect(warningDownCastFailed, MaybeSubject.Absent, assertionCreator)
}
