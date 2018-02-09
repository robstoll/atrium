package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.creating.any.typetransformation.creators.AnyTypeTransformationAssertions
import ch.tutteli.atrium.creating.any.typetransformation.failurehandlers.AnyTypeTransformationFailureHandlers
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

@Deprecated(
    "use AnyTypeTransformationAssertions.isNotNull instead, will be removed with 1.0.0",
    ReplaceWith(
        "AnyTypeTransformationAssertions.isNotNull(plant, T::class, assertionCreator)",
        "ch.tutteli.atrium.creating.any.typetransformation.creators.AnyTypeTransformationAssertions"
    )
)
inline fun <reified T : Any> _isNotNull(
    plant: AssertionPlantNullable<T?>,
    noinline assertionCreator: AssertionPlant<T>.() -> Unit
) {
    AnyTypeTransformationAssertions.isNotNull(plant, T::class, assertionCreator)
}

@Deprecated(
    "use AnyTypeTransformationAssertions.isA instead, will be removed with 1.0.0",
    ReplaceWith(
        "AnyTypeTransformationAssertions.isA(plant, TSub::class, assertionCreator)",
        "ch.tutteli.atrium.creating.any.typetransformation.creators.AnyTypeTransformationAssertions"
    )
)
inline fun <reified TSub : Any> _isA(
    plant: AssertionPlant<Any>,
    noinline assertionCreator: AssertionPlant<TSub>.() -> Unit
) {
    AnyTypeTransformationAssertions.isA(plant, TSub::class, assertionCreator)
}

@Deprecated(
    "use AnyTypeTransformationAssertions.downCast instead, will be removed with 1.0.0",
    ReplaceWith(
        "AnyTypeTransformationAssertions.downCast(description, subType, subjectPlant, assertionCreator)",
        "ch.tutteli.atrium.creating.any.typetransformation.creators.AnyTypeTransformationAssertions"
    )
)
fun <T : Any, TSub : T> _downCast(
    description: Translatable,
    subType: KClass<TSub>,
    subjectPlant: BaseAssertionPlant<T?, *>,
    assertionCreator: AssertionPlant<TSub>.() -> Unit
) {
    AnyTypeTransformationAssertions.downCast(description, subType, subjectPlant, assertionCreator, AnyTypeTransformationFailureHandlers.newExplanatory())
}

@Deprecated(
    "use AnyTypeTransformationAssertions.typeTransformation instead, will be removed with 1.0.0",
    ReplaceWith(
        "AnyTypeTransformationAssertions.typeTransformation(AnyTypeTransformation.ParameterObject(description, representation, subjectPlant, assertionCreator, warningTransformationFailed), canBeTransformed, transform)",
        "ch.tutteli.atrium.creating.any.typetransformation.creators.AnyTypeTransformationAssertions"
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
    AnyTypeTransformationAssertions.typeTransformation(parameterObject, canBeTransformed, transform, AnyTypeTransformationFailureHandlers.newExplanatory())
}
