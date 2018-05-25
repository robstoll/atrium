package ch.tutteli.atrium.assertions.any.typetransformation

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Deprecated, use AnyTypeTransformerAssertions.transformType, will be removed with 1.0.0
 */
@Deprecated("Use AssertImpl.any.typeTransformation.transform, will be removed with 1.0.0")
class TypeTransformer<T : Any, TSub : Any>(private val failureHandler: AnyTypeTransformation.TypeTransformationFailureHandler<T, TSub>) {

    @Deprecated("Use AssertImpl.any.typeTransformation.transform, will be removed with 1.0.0",
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
        val subject = subjectPlant.subject
        val assertionVerb = Untranslatable("Should not be shown to the user; if you see this, please fill in a bug report at $BUG_REPORT_URL")
        if (subject != null && canBeTransformed(subject)) {
            val assertionChecker = coreFactory.newDelegatingAssertionChecker(subjectPlant)
            val plant = coreFactory.newReportingPlant(assertionVerb, transform(subject), assertionChecker)
            plant.addAssertion(AssertImpl.builder.descriptive.createHoldingAssertion(description, representation))
            plant.addAssertionsCreatedBy(assertionCreator)
        } else {
            failureHandler.createAndAddAssertionToPlant(warningTransformationFailed, subjectPlant,
                AssertImpl.builder.descriptive.createFailingAssertion(description, representation), assertionCreator)
        }
    }
}
