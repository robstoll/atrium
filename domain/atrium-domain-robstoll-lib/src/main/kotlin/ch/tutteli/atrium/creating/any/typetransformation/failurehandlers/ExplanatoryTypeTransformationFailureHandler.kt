package ch.tutteli.atrium.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.creating.AssertionCollector
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.creating.any.typetransformation.AnyTypeTransformation.ParameterObject
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents an [AnyTypeTransformation.FailureHandler] which wraps subsequent assertions into an
 * [AssertionGroup] with an [ExplanatoryAssertionGroupType].
 *
 * @param S The type of the [AssertionPlant.subject].
 * @param T The target type to which the [AssertionPlant.subject] should have been down-casted.
 */
class ExplanatoryTypeTransformationFailureHandler<in S : Any, out T : Any> :
    AnyTypeTransformation.FailureHandler<S, T> {

    /**
     * Creates a failing assertion and wraps the assertion which might be created by [ParameterObject.assertionCreator]
     * into an [AssertionGroup] with an [ExplanatoryAssertionGroupType] and adds it to the given
     * [ParameterObject.subjectPlant].
     *
     * @param parameterObject The [ParameterObject] containing inter alia [ParameterObject.assertionCreator], the
     *   lambda which could have created subsequent assertions for the transformed [AssertionPlant.subject].
     *
     * @throws AssertionError Might throw an [AssertionError] depending on the [ParameterObject.subjectPlant].
     */
    override fun createAndAddAssertionToPlant(parameterObject: ParameterObject<S, T>) {
        val explanatoryAssertions = collectAssertions(parameterObject.warningTransformationFailed, parameterObject.assertionCreator)
        val assertion = AssertionBuilder.invisibleGroup.create(listOf(
            AssertionBuilder.descriptive.create(parameterObject.description, parameterObject.representation, false),
            AssertionBuilder.explanatoryGroup.withDefault.create(explanatoryAssertions)
        ))
        parameterObject.subjectPlant.addAssertion(assertion)
    }

    private fun collectAssertions(
        warningDownCastFailed: Translatable,
        assertionCreator: AssertionPlant<T>.() -> Unit
    ) = AssertionCollector
        .doNotThrowIfNoAssertionIsCollected
        .collectAssertionsForExplanation(warningDownCastFailed, assertionCreator, null)
}
