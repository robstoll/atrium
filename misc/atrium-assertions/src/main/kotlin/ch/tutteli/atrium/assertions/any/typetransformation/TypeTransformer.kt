@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.any.typetransformation

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Deprecated, use AnyTypeTransformerAssertions.transformType; will be removed with 1.0.0
 */
@Deprecated("Use AssertImpl.any.typeTransformation.transform; will be removed with 1.0.0")
class TypeTransformer<T : Any, TSub : Any>(private val failureHandler: AnyTypeTransformation.TypeTransformationFailureHandler<T, TSub>) {

    @Deprecated("Use AssertImpl.any.typeTransformation.transform; will be removed with 1.0.0",
        ReplaceWith(
            "AssertImpl.any.typeTransformation.transform(AnyTypeTransformation.ParameterObject(description, representation, subjectPlant, assertionCreator, warningTransformationFailed), canBeTransformed, transform, AssertImpl.any.typeTransformation.failureHandlers.chooseAFailureHandler)",
            "ch.tutteli.atrium.creating.AssertImpl",
            "ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation"
        )
    )
    fun transform(
        description: Translatable,
        representation: Any,
        subjectPlant: BaseAssertionPlant<T?, *>,
        assertionCreator: AssertionPlant<TSub>.() -> Unit,
        warningTransformationFailed: Translatable,
        canBeTransformed: (T) -> Boolean,
        transform: (T) -> TSub
    ) {
        //TODO not subject less
        val subject = subjectPlant.subject
        if (subject != null && canBeTransformed(subject)) {
            val assertionVerb = Untranslatable(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG)
            val assertionChecker = coreFactory.newDelegatingAssertionChecker(subjectPlant)
            val plant = coreFactory.newReportingPlant(assertionVerb, { transform(subject) }, assertionChecker)
            plant.addAssertion(AssertImpl.builder.descriptive
                .holding
                .withDescriptionAndRepresentation(description, representation)
                .build())
            plant.addAssertionsCreatedBy(assertionCreator)
        } else {
            val failingAssertion = AssertImpl.builder.descriptive
                .failing
                .withDescriptionAndRepresentation(description, representation)
                .build()
            failureHandler.createAndAddAssertionToPlant(
                warningTransformationFailed, subjectPlant, failingAssertion, assertionCreator
            )
        }
    }
}
