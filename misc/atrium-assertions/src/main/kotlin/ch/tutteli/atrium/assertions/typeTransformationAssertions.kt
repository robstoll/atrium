package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.domain.builders.creating.AssertImpl
import ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers.failureHandlerFactory
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

@Deprecated("Use AssertImpl.any.typeTransformation.isNotNull, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.any.typeTransformation.isNotNull(plant, T::class, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
inline fun <reified T : Any> _isNotNull(
    plant: AssertionPlantNullable<T?>,
    noinline assertionCreator: AssertionPlant<T>.() -> Unit
) {
    AssertImpl.any.typeTransformation.isNotNull(plant, T::class, assertionCreator)
}

@Deprecated("Use AssertImpl.any.typeTransformation.isA, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.any.typeTransformation.isA(plant, TSub::class, assertionCreator)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
inline fun <reified TSub : Any> _isA(
    plant: AssertionPlant<Any>,
    noinline assertionCreator: AssertionPlant<TSub>.() -> Unit
) {
    AssertImpl.any.typeTransformation.isA(plant, TSub::class, assertionCreator)
}

@Deprecated("Use AssertImpl.any.typeTransformation.downCast, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.any.typeTransformation.downCast(description, subType, subjectPlant, assertionCreator, AssertImpl.any.typeTransformation.failureHandlers.newExplanatory())",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, TSub : T> _downCast(
    description: Translatable,
    subType: KClass<TSub>,
    subjectPlant: BaseAssertionPlant<T?, *>,
    assertionCreator: AssertionPlant<TSub>.() -> Unit
) {
    AssertImpl.any.typeTransformation.downCast(description, subType, subjectPlant, assertionCreator, failureHandlerFactory.newExplanatory())
}

@Deprecated("Use AssertImpl.any.typeTransformation.transform, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.any.typeTransformation.transform(AnyTypeTransformation.ParameterObject(description, representation, subjectPlant, assertionCreator, warningTransformationFailed), canBeTransformed, transform, AssertImpl.any.typeTransformation.failureHandlers.newExplanatory())",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Any, TSub : Any> _typeTransformation(
    description: Translatable,
    representation: Any,
    subjectPlant: BaseAssertionPlant<T?, *>,
    assertionCreator: AssertionPlant<TSub>.() -> Unit,
    warningTransformationFailed: Translatable,
    canBeTransformed: (T) -> Boolean,
    transform: (T) -> TSub
) {
    val parameterObject = AnyTypeTransformation.ParameterObject(
        description, representation, subjectPlant, assertionCreator, warningTransformationFailed
    )
    AssertImpl.any.typeTransformation.transform(parameterObject, canBeTransformed, transform, AssertImpl.any.typeTransformation.failureHandlers.newExplanatory())
}
