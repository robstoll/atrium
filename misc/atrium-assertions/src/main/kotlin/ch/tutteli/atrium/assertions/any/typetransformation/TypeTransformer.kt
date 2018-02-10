package ch.tutteli.atrium.assertions.any.typetransformation

import ch.tutteli.atrium.CoreFactory
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Deprecated, use AnyTypeTransformerAssertions.transformType, will be removed with 1.0.0
 */
@Deprecated("use AssertImpl.any.typeTransformation.transform, will be removed with 1.0.0")
class TypeTransformer<T : Any, TSub : Any>(private val failureHandler: AnyTypeTransformation.TypeTransformationFailureHandler<T, TSub>) {

    @Deprecated("use AssertImpl.any.typeTransformation.transform, will be removed with 1.0.0",
        ReplaceWith(
            "AssertImpl.any.typeTransformation.transform(AnyTypeTransformation.ParameterObject(description, representation, subjectPlant, assertionCreator, warningTransformationFailed), canBeTransformed, transform, AssertImpl.any.typeTransformation.failureHandlers.chooseAFailureHandler)",
            "ch.tutteli.atrium.creating.AssertImpl",
            "ch.tutteli.atrium.creating.any.typetransformation.AnyTypeTransformation"
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
        val assertionVerb = Untranslatable("Should not be shown to the user; if you see this, please fill in a bug report at " + BUG_REPORT_URL)
        if (subject != null && canBeTransformed(subject)) {
            val assertionChecker = CoreFactory.newDelegatingAssertionChecker(subjectPlant)
            val plant = CoreFactory.newReportingPlant(assertionVerb, transform(subject), assertionChecker)
            plant.addAssertion(AssertionBuilder.descriptive.create(description, representation, true))
            plant.addAssertionsCreatedBy(assertionCreator)
        } else {
            failureHandler.createAndAddAssertionToPlant(warningTransformationFailed, subjectPlant,
                AssertionBuilder.descriptive.create(description, representation, false), assertionCreator)
        }
    }
}
