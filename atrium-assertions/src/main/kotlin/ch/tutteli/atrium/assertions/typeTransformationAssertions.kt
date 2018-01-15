package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionTypeTransformationAssertion.IS_A
import ch.tutteli.atrium.assertions.any.typetransformation.DownCaster
import ch.tutteli.atrium.assertions.any.typetransformation.ExplanatoryTypeTransformationFailureHandler
import ch.tutteli.atrium.assertions.any.typetransformation.TypeTransformer
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

inline fun <reified T : Any> _isNotNull(plant: AssertionPlantNullable<T?>, noinline assertionCreator: AssertionPlant<T>.() -> Unit) {
    _downCast(IS_A, T::class, plant, assertionCreator)
}

inline fun <reified TSub : Any> _isA(plant: AssertionPlant<Any>, noinline assertionCreator: AssertionPlant<TSub>.() -> Unit) {
    _downCast(IS_A, TSub::class, plant, assertionCreator)
}

fun <T : Any, TSub : T> _downCast(
    description: Translatable,
    subType: KClass<TSub>,
    subjectPlant: BaseAssertionPlant<T?, *>,
    assertionCreator: AssertionPlant<TSub>.() -> Unit
) {
    DownCaster<T, TSub>(ExplanatoryTypeTransformationFailureHandler())
        .downCast(description, subType, subjectPlant, assertionCreator)
}

fun <T : Any, TSub : Any> _typeTransformation(
    description: Translatable,
    representation: Any,
    subjectPlant: BaseAssertionPlant<T?, *>,
    assertionCreator: AssertionPlant<TSub>.() -> Unit,
    warningTransformationFailed: Translatable,
    canBeTransformed: (T) -> Boolean,
    transform: (T) -> TSub
) {
    TypeTransformer<T, TSub>(ExplanatoryTypeTransformationFailureHandler()).transform(
        description, representation, subjectPlant, assertionCreator,
        warningTransformationFailed, canBeTransformed, transform
    )
}
