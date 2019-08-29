@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.MaybeSubject
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation.ParameterObject
import ch.tutteli.atrium.reporting.translating.Translatable

@Deprecated("will be removed with 1.0.0")
abstract class ExplanatoryFailureHandlerBase<in S : Any, out T : Any> : AnyTypeTransformation.FailureHandler<S, T> {

    override fun createAndAddAssertionToPlant(parameterObject: ParameterObject<S, T>) {
        val explanatoryAssertions = collectAssertions(
            parameterObject.warningTransformationFailed, parameterObject.assertionCreator
        )
        val assertion = AssertImpl.builder.invisibleGroup
            .withAssertions(
                createFailingAssertion(parameterObject.description, parameterObject.representation),
                AssertImpl.builder.explanatoryGroup
                    .withDefaultType
                    .withAssertions(explanatoryAssertions)
                    .build()
            )
            .build()
        parameterObject.subjectPlant.addAssertion(assertion)
    }

    private fun collectAssertions(
        warningTransformationFailed: Translatable,
        assertionCreator: AssertionPlant<T>.() -> Unit
    ) = AssertImpl.collector
        .forExplanation
        .doNotThrowIfNoAssertionIsCollected
        .collect(warningTransformationFailed, MaybeSubject.Absent, assertionCreator)
}
