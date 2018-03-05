package ch.tutteli.atrium.creating.any.typetransformation.creators

import ch.tutteli.atrium.CoreFactory
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.Untranslatable

class TypeTransformationAssertionCreator<S : Any, T : Any> : AnyTypeTransformation.Creator<S, T> {

    override fun create(
        parameterObject: AnyTypeTransformation.ParameterObject<S, T>,
        canBeTransformed: (S) -> Boolean,
        transform: (S) -> T,
        failureHandler: AnyTypeTransformation.FailureHandler<S, T>
    ) {
        val (description, representation, subjectPlant, assertionCreator) = parameterObject
        val subject = subjectPlant.subject
        val assertionVerb =
            Untranslatable("Should not be shown to the user; if you see this, please fill in a bug report at $BUG_REPORT_URL")
        if (subject != null && canBeTransformed(subject)) {
            val assertionChecker = CoreFactory.newDelegatingAssertionChecker(subjectPlant)
            val plant = CoreFactory.newReportingPlant(assertionVerb, transform(subject), assertionChecker)
            plant.addAssertion(AssertionBuilder.descriptive.create(description, representation, true))
            plant.addAssertionsCreatedBy(assertionCreator)
        } else {
            failureHandler.createAndAddAssertionToPlant(parameterObject)
        }
    }
}
