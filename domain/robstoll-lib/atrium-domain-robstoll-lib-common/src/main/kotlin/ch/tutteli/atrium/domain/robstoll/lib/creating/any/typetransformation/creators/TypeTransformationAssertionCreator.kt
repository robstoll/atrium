@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators

import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation

@Deprecated("Use _changeSubject instead; will be removed with 1.0.0")
class TypeTransformationAssertionCreator<S : Any, T : Any> : AnyTypeTransformation.Creator<S, T> {

    @Suppress("DEPRECATION")
    override fun create(
        parameterObject: AnyTypeTransformation.ParameterObject<S, T>,
        canBeTransformed: (S) -> Boolean,
        transform: (S) -> T,
        failureHandler: AnyTypeTransformation.FailureHandler<S, T>
    ) {
        val (description, representation, subjectPlant, assertionCreator) = parameterObject
        subjectPlant.maybeSubject
            .filter { it != null && canBeTransformed(it) }
            .fold({
                failureHandler.createAndAddAssertionToPlant(parameterObject)
            }) {
                @Suppress("DEPRECATION") val plant = AssertImpl.changeSubject(subjectPlant) { transform(it as S) }
                plant.addAssertion(
                    AssertImpl.builder.descriptive
                        .holding
                        .withDescriptionAndRepresentation(description, representation)
                        .build()
                )
                plant.addAssertionsCreatedBy(assertionCreator)
            }
    }
}
