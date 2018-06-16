package ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators

import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation

class TypeTransformationAssertionCreator<S : Any, T : Any> : AnyTypeTransformation.Creator<S, T> {

    override fun create(
        parameterObject: AnyTypeTransformation.ParameterObject<S, T>,
        canBeTransformed: (S) -> Boolean,
        transform: (S) -> T,
        failureHandler: AnyTypeTransformation.FailureHandler<S, T>
    ) {
        val (description, representation, subjectPlant, assertionCreator) = parameterObject
        val subject = subjectPlant.subject
        if (subject != null && canBeTransformed(subject)) {
            val plant = AssertImpl.changeSubject(subjectPlant, { transform(subject) })
            plant.addAssertion(AssertImpl.builder.descriptive
                .holding
                .withDescriptionAndRepresentation(description, representation)
                .build())
            plant.addAssertionsCreatedBy(assertionCreator)
        } else {
            failureHandler.createAndAddAssertionToPlant(parameterObject)
        }
    }
}
