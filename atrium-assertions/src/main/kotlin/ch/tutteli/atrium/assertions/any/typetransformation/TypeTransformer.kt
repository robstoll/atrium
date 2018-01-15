package ch.tutteli.atrium.assertions.any.typetransformation

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.BasicDescriptiveAssertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

class TypeTransformer<T : Any, TSub : Any>(private val failureHandler: AnyTypeTransformation.TypeTransformationFailureHandler<T, TSub>) {

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
            val assertionChecker = AtriumFactory.newDelegatingAssertionChecker(subjectPlant)
            val plant = AtriumFactory.newReportingPlant(assertionVerb, transform(subject), assertionChecker)
            plant.addAssertion(BasicDescriptiveAssertion(description, representation, true))
            plant.addAssertionsCreatedBy(assertionCreator)
        } else {
            failureHandler.createAndAddAssertionToPlant(warningTransformationFailed, subjectPlant, BasicDescriptiveAssertion(description, representation, false), assertionCreator)
        }
    }
}
