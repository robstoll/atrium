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
        //TODO #89 Base TypeTransformationAssertionCreator on SubjectChanger (or do not use it at all)
        val (description, representation, subjectPlant, assertionCreator) = parameterObject
        if (subjectPlant.maybeSubject.isDefined()) {
            @Suppress("DEPRECATION") val subject = subjectPlant.subject
            if (subject != null && canBeTransformed(subject)) {
                @Suppress("DEPRECATION") val plant = AssertImpl.changeSubject(subjectPlant) { transform(subject) }
                plant.addAssertion(AssertImpl.builder.descriptive
                    .holding
                    .withDescriptionAndRepresentation(description, representation)
                    .build())
                plant.addAssertionsCreatedBy(assertionCreator)
            } else {
                failureHandler.createAndAddAssertionToPlant(parameterObject)
            }
        } else {
            failureHandler.createAndAddAssertionToPlant(parameterObject)
        }
    }
}
