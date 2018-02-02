package ch.tutteli.atrium.creating.any.typetransformation

import ch.tutteli.atrium.CoreFactory
import ch.tutteli.atrium.assertions.AssertionBuilder
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant

import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

//TODO remove once it is in atrium-domain-robstoll as well
private const val BUG_REPORT_URL = "https://github.com/robstoll/atrium/issues/new"

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
